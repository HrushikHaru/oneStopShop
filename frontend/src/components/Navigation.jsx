import { Link, NavLink } from "react-router-dom";
import styles from "./Navigation.module.css";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { useEffect, useRef, useState } from "react";
import Overlay from "./Overlay";
import Modal from "./Modal";
import Login from "./Login";
import Registration from "./Registration";
import PropTypes from "prop-types";

Navigation.propTypes = {
  setLoggedIn: PropTypes.func,
  loggedIn: PropTypes.bool,
  modalWindow: PropTypes.bool,
  setModalWindow: PropTypes.func,
  cartProducts: PropTypes.number,
  handleLogOut: PropTypes.func,
};

function Navigation({
  setLoggedIn,
  loggedIn,
  modalWindow,
  setModalWindow,
  cartProducts,
  handleLogOut,
}) {
  const [loginWindow, setLoginWindow] = useState(false);
  const [registerWindow, setRegisterWindow] = useState(false);
  const [userNav, setUserNav] = useState(false);

  //To Log-In
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  useEffect(() => {
    if (loginWindow) {
      setModalWindow(true);
      setRegisterWindow(false);
    } else if (registerWindow) {
      setModalWindow(true);
      setLoginWindow(false);
    } else {
      setModalWindow(false);
    }
  }, [loginWindow, registerWindow]);

  useEffect(
    function () {
      const userToken = localStorage.getItem("userToken");

      if (userToken) {
        setLoggedIn(true);
      } else {
        setLoggedIn(false);
      }
    },
    [setLoggedIn]
  );

  function handleLoginSubmit(e) {
    e.preventDefault();
    async function checkUser() {
      try {
        const response = await fetch("http://localhost:8080/signIn", {
          method: "GET",
          headers: {
            "Content-type": "application/json",
            Authorization: "Basic " + btoa(`${userName}:${password}`),
          },
        });

        if (response.status === 200) {
          const data = await response.json();

          localStorage.setItem("userToken", data.token);
          localStorage.setItem("userId", data.userId);
          localStorage.setItem("userName", data.firstName);
          localStorage.setItem("userRole", data.role);

          setLoggedIn(true);
          setModalWindow(false);
          setUserName("");
          setPassword("");
        } else {
          console.log("Hii");
          setErrorMsg(
            "The provided credentials are wrong. Please login with the right credentials."
          );
        }
      } catch (err) {
        console.log(err);
      }
    }

    console.log(errorMsg);

    checkUser();
  }

  function handleLogin() {
    setModalWindow(true);
    setLoginWindow(true);
  }

  function closeModal() {
    setModalWindow(false);
  }

  function handleUserNav() {
    setUserNav(true);
  }

  const navRef = useRef();

  useEffect(() => {
    function handleClickOutside(event) {
      const userNavElement = navRef.current;

      if (userNavElement && !userNavElement.contains(event.target)) {
        setUserNav(false);
      }
    }

    if (userNav) {
      // Attach the event listener when userNav is open
      document.addEventListener("click", handleClickOutside);
    }

    // Clean up the event listener when userNav closes
    return () => {
      document.removeEventListener("click", handleClickOutside);
    };
  }, [userNav]);

  return (
    <>
      <nav className={styles.navigation}>
        <div className={styles.container}>
          <div className={styles.navigate}>
            <NavLink to="/">
              <div className={styles.logoContainer}>
                <img src="/public/onestopshop-logo.webp" alt="logo-picture" />
              </div>
            </NavLink>
            <NavLink to="/mensFashion">
              <p>Men&rsquo;s Fashion</p>
            </NavLink>
            <NavLink to="/womensFashion">
              <p>Women&rsquo;s Fashion</p>
            </NavLink>
          </div>

          <div className={styles.sections}>
            {loggedIn ? (
              <>
                <div
                  ref={navRef}
                  className={styles.avatar}
                  onClick={handleUserNav}
                >
                  <p>{localStorage.getItem("userName")?.slice(0, 1)}</p>
                  {userNav ? (
                    <div className={styles.userNav}>
                      {localStorage.getItem("userRole") === "User" ? (
                        ""
                      ) : (
                        <Link to={"/admin/show-products"}>
                          <p>Admin Acc</p>
                        </Link>
                      )}
                      {localStorage.getItem("userRole") === "User" ? (
                        <Link to={"/wishList"}>
                          <p>My WishList</p>
                        </Link>
                      ) : (
                        ""
                      )}
                      {localStorage.getItem("userRole") === "User" ? (
                        <Link to={"/myorders"}>
                          <p>My Orders</p>
                        </Link>
                      ) : (
                        ""
                      )}
                      <p onClick={() => handleLogOut()}>Log Out</p>
                    </div>
                  ) : (
                    ""
                  )}
                </div>
              </>
            ) : (
              <button className={styles.loginBtn} onClick={handleLogin}>
                Log-In
              </button>
            )}

            <div className={styles.shoppingCart}>
              <Link to={"/cart"}>
                <ShoppingCartIcon />
              </Link>
              <p>{cartProducts && loggedIn ? cartProducts : ""}</p>
            </div>
          </div>
        </div>
      </nav>
      {modalWindow && (
        <aside>
          <Overlay closeModal={closeModal} />
          <Modal>
            {loginWindow && (
              <Login
                setLoginWindow={setLoginWindow}
                setRegisterWindow={setRegisterWindow}
                setUserName={setUserName}
                setPassword={setPassword}
                userName={userName}
                password={password}
                handleLoginSubmit={handleLoginSubmit}
                errorMsg={errorMsg}
                setErrorMsg={setErrorMsg}
              />
            )}
            {registerWindow && (
              <Registration
                setLoginWindow={setLoginWindow}
                setRegisterWindow={setRegisterWindow}
              />
            )}
          </Modal>
        </aside>
      )}
    </>
  );
}

export default Navigation;
