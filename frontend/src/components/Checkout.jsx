import PropTypes from "prop-types";
import Navigation from "./Navigation";
import Footer from "./Footer";
import { useEffect, useState } from "react";
import DoneIcon from "@mui/icons-material/Done";
import styles from "./Checkout.module.css";
import { useLocation, useNavigate } from "react-router-dom";
import DeliveryAddress from "./DeliveryAddress";
import OrderSummary from "./OrderSummary";
import Success from "./Success";

Checkout.propTypes = {
  setLoggedIn: PropTypes.func,
  loggedIn: PropTypes.bool,
  modalWindow: PropTypes.bool,
  setModalWindow: PropTypes.func,
  cartProducts: PropTypes.number,
  handleLogOut: PropTypes.func,
  product: PropTypes.arrayOf(Object),
  itemsCost: PropTypes.number,
  discountedPrice: PropTypes.number,
  totalCost: PropTypes.number,
  setQtyChange: PropTypes.func,
};

function Checkout({
  setLoggedIn,
  loggedIn,
  modalWindow,
  setModalWindow,
  cartProducts,
  handleLogOut,
  product,
  itemsCost,
  discountedPrice,
  totalCost,
  setQtyChange,
}) {
  const navigate = useNavigate();

  const location = useLocation();
  const params = new URLSearchParams(location.search);

  let step = parseInt(params.get("steps"), 10) || 1;

  const [address, setAddress] = useState([]);
  const [selectedAddress, setSelectedAddress] = useState({
    addressId: 0,
    arrId: 0,
  });
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(
    function () {
      if (location.search.slice(-1) == 4) {
        navigate(`?steps=4`);
        return;
      }

      if (loggedIn) {
        navigate(`?steps=2`);
      } else {
        navigate(`?steps=${step}`);
      }
    },
    [loggedIn]
  );

  function handlePrevious() {
    if (step > 0) {
      step--;
      navigate(`?steps=${step}`);
    }
  }

  function handleNext() {
    if (step < 4) {
      if (step === 2 && selectedAddress.addressId <= 0) {
        setErrorMessage("Please select the address first to proceed further.");
        return;
      }
      step++;
      navigate(`?steps=${step}`);
    }
  }

  function openLogInWindow() {
    setModalWindow(true);
  }

  return (
    <>
      <Navigation
        setLoggedIn={setLoggedIn}
        loggedIn={loggedIn}
        modalWindow={modalWindow}
        setModalWindow={setModalWindow}
        cartProducts={cartProducts}
        handleLogOut={handleLogOut}
      />
      <section>
        <div className={`${styles[`steps`]}`}>
          <div className={styles.numbers}>
            <div className={step >= 1 ? styles.active : ""}>
              {step >= 1 ? <DoneIcon /> : "1"}
            </div>

            <div className={step >= 2 ? styles.active : ""}>
              {step >= 2 ? <DoneIcon /> : "2"}
            </div>

            <div className={step >= 3 ? styles.active : ""}>
              {step >= 3 ? <DoneIcon /> : "3"}
            </div>

            <div className={step >= 4 ? styles.active : ""}>
              {step >= 4 ? <DoneIcon /> : "4"}
            </div>
          </div>

          {step === 1 ? (
            loggedIn ? (
              <>
                <p className={styles.message}>You&rsquo;re Already Logged In</p>
                <p className={styles.message}>
                  Click next to proceed further to complete the form filling for
                  address details to be delivered at and the payment options.
                </p>
              </>
            ) : (
              <button className={styles.logInButton} onClick={openLogInWindow}>
                Click Here to Log-In
              </button>
            )
          ) : (
            ""
          )}
          {step === 2 ? (
            <DeliveryAddress
              loggedIn={loggedIn}
              selectedAddress={selectedAddress}
              setSelectedAddress={setSelectedAddress}
              errorMessage={errorMessage}
              setErrorMessage={setErrorMessage}
              address={address}
              setAddress={setAddress}
            />
          ) : (
            ""
          )}
          {step === 3 ? (
            <OrderSummary
              product={product}
              itemsCost={itemsCost}
              discountedPrice={discountedPrice}
              totalCost={totalCost}
              selectedAddress={selectedAddress}
              address={address}
            />
          ) : (
            ""
          )}
          {step === 4 ? (
            <Success product={product} setQtyChange={setQtyChange} />
          ) : (
            ""
          )}

          <div className={styles.buttons}>
            {step < 4 ? (
              <button
                style={{ backgroundColor: "#7950f2", color: "#fff" }}
                onClick={handlePrevious}
              >
                Previous
              </button>
            ) : (
              ""
            )}
            {step < 3 ? (
              <button
                style={{ backgroundColor: "#7950f2", color: "#fff" }}
                onClick={handleNext}
              >
                Next
              </button>
            ) : (
              ""
            )}
          </div>
        </div>
      </section>
      <Footer />
    </>
  );
}

export default Checkout;
