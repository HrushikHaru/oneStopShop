import Navigation from "./Navigation";
import PropTypes from "prop-types";
import styles from "./Cart.module.css";
import Footer from "./Footer";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import AddShoppingCartIcon from "@mui/icons-material/AddShoppingCart";

Cart.propTypes = {
  setLoggedIn: PropTypes.func,
  loggedIn: PropTypes.bool,
  modalWindow: PropTypes.bool,
  setModalWindow: PropTypes.func,
  product: PropTypes.arrayOf(Object),
  cartProducts: PropTypes.number,
  handleLogOut: PropTypes.func,
  setQtyChange: PropTypes.func,
  itemsCost: PropTypes.number,
  discountedPrice: PropTypes.number,
  totalCost: PropTypes.number,
};

function Cart({
  setLoggedIn,
  loggedIn,
  product,
  cartProducts,
  modalWindow,
  setModalWindow,
  handleLogOut,
  setQtyChange,
  itemsCost,
  discountedPrice,
  totalCost,
}) {
  if (!loggedIn) {
    return (
      <>
        <Navigation
          setLoggedIn={setLoggedIn}
          loggedIn={loggedIn}
          cartProducts={cartProducts}
          modalWindow={modalWindow}
          setModalWindow={setModalWindow}
        />
        <div className={styles.logInMessage}>
          <p>Please Log-In to add Products in the Cart.</p>
          <span>
            Click On the log-In button and log-In/Sign-Up as a new User.
          </span>
        </div>
        <Footer />
      </>
    );
  }

  if (product.length === 0) {
    return (
      <>
        <Navigation
          setLoggedIn={setLoggedIn}
          loggedIn={loggedIn}
          cartProducts={cartProducts}
          modalWindow={modalWindow}
          setModalWindow={setModalWindow}
        />
        <div className={styles.logInMessage}>
          <div className={styles.cart}>
            <AddShoppingCartIcon />
            <p>Add Products of Your choice.</p>
          </div>
          <Link to={"/"}>
            <span>Click Here to add Products.</span>
          </Link>
        </div>
        <Footer />
      </>
    );
  }

  return (
    <>
      <Navigation
        setLoggedIn={setLoggedIn}
        loggedIn={loggedIn}
        cartProducts={cartProducts}
        modalWindow={modalWindow}
        setModalWindow={setModalWindow}
        handleLogOut={handleLogOut}
      />
      <section className={styles.grid}>
        <div className={styles.cartProducts}>
          {product.map((prod, i) => (
            <CartProducts key={i} prod={prod} setQtyChange={setQtyChange} />
          ))}
        </div>
        <div className={styles.amount}>
          <h2>Price Details</h2>
          <div className={styles.checkOutDetails}>
            <label>Price ({product.length} items)</label>
            <p>&#8377;{itemsCost}</p>
          </div>
          <div className={styles.checkOutDetails}>
            <label>Discout</label>
            <p>-&#8377;{discountedPrice}</p>
          </div>
          <div className={styles.checkOutDetails}>
            <label>Delivery Charges</label>
            <p>Free</p>
          </div>
          <div className={styles.checkOutDetails}>
            <label>Packaging Fee</label>
            <p>&#8377;0</p>
          </div>
          <div className={`${styles[`checkOutDetails`]} ${styles[`total`]}`}>
            <label>Total</label>
            <p>&#8377;{totalCost}</p>
          </div>
          <Link to={"/checkout?steps=1"}>
            <button>CheckOut</button>
          </Link>
        </div>
      </section>
      <Footer />
    </>
  );
}

export default Cart;

CartProducts.propTypes = {
  prod: PropTypes.object,
  setQtyChange: PropTypes.func,
};

function CartProducts({ prod, setQtyChange }) {
  const [increment, setIncrement] = useState(prod.numItems);

  const imgInBytes = Uint8Array.from(
    atob(prod.productDetails.base64EncodedProductImg),
    (c) => c.charCodeAt(0)
  );

  function handleIncrement() {
    setIncrement((increment) => {
      if (increment < 10) {
        return increment + 1;
      } else {
        return increment;
      }
    });
  }

  function handleDecrement() {
    setIncrement((decrement) => {
      if (decrement == 1) {
        return decrement;
      } else {
        return decrement - 1;
      }
    });
  }

  useEffect(
    function () {
      async function changeQty() {
        const response = await fetch(
          `http://localhost:8080/qtyChange/${prod.cartId}/${increment}`,
          {
            method: "PUT",
            headers: {
              Authorization: `Bearer ${localStorage.getItem("userToken")}`,
            },
          }
        );

        if (response.status === 204) {
          setQtyChange((change) => !change);
        }
      }

      changeQty();
    },
    [increment]
  );

  async function removeFromCart() {
    try {
      const response = await fetch(
        `http://localhost:8080/removeCartItem/${prod.cartId}`,
        {
          method: "DELETE",
          headers: {
            "Content-type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("userToken")}`,
          },
        }
      );

      if (response.status === 204) {
        setQtyChange((change) => !change);
      }
    } catch (err) {
      console.log(err);
    }
  }

  return (
    <div className={styles.product}>
      <div className={styles.imgContainer}>
        <img src={URL.createObjectURL(new Blob([imgInBytes]))} />
      </div>
      <div className={styles.productDetails}>
        <p className={styles.title}>
          Title - {prod.productDetails.productTitle}
        </p>
        <p>Size - {prod.sizeSelected}</p>
        <p>Color - {prod.productDetails.color}</p>
        <p className={styles.price}>
          <span>
            <strike>&#8377;{prod.productDetails.originalPrice}</strike>
          </span>
          <span>&#8377;{prod.productDetails.discountedPrice}</span>
        </p>
        <div className={styles.change}>
          <button
            className={`${styles[`variation`]} ${
              increment === 1 ? `${styles[`active`]}` : ""
            }`}
          >
            <p onClick={handleDecrement}>-</p>
          </button>
          <p className={styles.value}>{increment}</p>
          <button
            className={`${styles[`variation`]} ${
              increment === 10 ? `${styles[`active`]}` : ""
            }`}
          >
            <p onClick={handleIncrement}>+</p>
          </button>
          <button className={styles.remove} onClick={removeFromCart}>
            REMOVE
          </button>
        </div>
      </div>
    </div>
  );
}
