import styles from "./WishList.module.css";
import PropTypes from "prop-types";
import Footer from "./Footer";
import Navigation from "./Navigation";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AddIcon from "@mui/icons-material/Add";

WishList.propTypes = {
  setLoggedIn: PropTypes.func,
  loggedIn: PropTypes.bool,
  modalWindow: PropTypes.bool,
  setModalWindow: PropTypes.func,
  cartProducts: PropTypes.number,
  handleLogOut: PropTypes.func,
  cartOverallProducts: PropTypes.array,
  setTriggerChange: PropTypes.func,
  wishListProducts: PropTypes.array,
  setWishListProducts: PropTypes.func,
};

function WishList({
  setLoggedIn,
  loggedIn,
  modalWindow,
  setModalWindow,
  cartProducts,
  handleLogOut,
  cartOverallProducts,
  setTriggerChange,
  wishListProducts,
  setWishListProducts,
}) {
  useEffect(
    function () {
      if (!loggedIn) setWishListProducts([]);
    },
    [wishListProducts, loggedIn]
  );

  //To Construct an array that contains all the cart product ID
  const cartProductsId = cartOverallProducts.map(
    (product) => product.productDetails.productId
  );

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
      {loggedIn || (
        <div className={styles.logInMessage}>
          <p>Please Log-In to get the Products from your wish-list.</p>
          <span>
            Click On the log-In button and log-In/Sign-Up as a new User.
          </span>
        </div>
      )}
      {wishListProducts.length === 0 ? (
        <div className={styles.logInMessage}>
          <div className={styles.cart}>
            <AddIcon />
            <p>Add Products of Your choice.</p>
          </div>
          <Link to={"/"}>
            <span>Click Here to add Products.</span>
          </Link>
        </div>
      ) : (
        ""
      )}
      {wishListProducts.length && (
        <section className={styles.productsGrid}>
          {wishListProducts?.map((product) => (
            <WishListComp
              key={product.productId}
              product={product}
              cartOverallProducts={cartOverallProducts}
              cartProductsId={cartProductsId}
              setTriggerChange={setTriggerChange}
            />
          ))}
        </section>
      )}
      <Footer />
    </>
  );
}

export default WishList;

WishListComp.propTypes = {
  product: PropTypes.object,
  cartProductsId: PropTypes.array,
  setTriggerChange: PropTypes.func,
};

function WishListComp({ product, cartProductsId, setTriggerChange }) {
  const [addedToCart, setAddedToCart] = useState(false);

  useEffect(
    function () {
      if (cartProductsId?.includes(product.productId)) {
        setAddedToCart(true);
      } else {
        setAddedToCart(false);
      }
    },
    [addedToCart, cartProductsId]
  );

  let pathName;

  if (product.categoryName === "Men's Fashion") {
    pathName = "mensFashion";
  } else {
    pathName = "womensFashion";
  }

  const imgInBytes = Uint8Array.from(
    atob(product.base64EncodedProductImg),
    (c) => c.charCodeAt(0)
  );

  function addToCart() {
    async function addToCart() {
      try {
        const response = await fetch(`http://localhost:8080/addToUserCart`, {
          method: "POST",
          headers: {
            "Content-type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("userToken")}`,
          },
          body: JSON.stringify({
            productId: Number(product.productId),
            sizeSelected: "S",
            numItems: 1,
            userId: localStorage.getItem("userId"),
          }),
        });

        if (response.status === 201) {
          setAddedToCart(true);
          setTriggerChange((trigger) => !trigger);
        }
      } catch (err) {
        console.log(err);
      }
    }

    addToCart();
  }

  function removeFromWishList() {
    async function removeProductFromWishList() {
      try {
        const response = await fetch(
          `http://localhost:8080/removeWishListProduct/${localStorage.getItem(
            "userId"
          )}/${product.productId}`,
          {
            method: "DELETE",
            headers: {
              "Content-type": "application/json",
              Authorization: `Bearer ${localStorage.getItem("userToken")}`,
            },
          }
        );

        if (response.status === 204) {
          setTriggerChange((trigger) => !trigger);
        }
      } catch (err) {
        console.log(err);
      }
    }

    removeProductFromWishList();
  }

  return (
    <>
      <div>
        <Link to={`/${pathName}/${product.productId}`}>
          <div className={styles.product}>
            <div className={styles.imgContainer}>
              <img src={URL.createObjectURL(new Blob([imgInBytes]))} />
            </div>
            <div className={styles.contents}>
              <p className={styles.brand}>{product.brand}</p>
              <p className={styles.title}>{product.title}</p>
              <p className={styles.cost}>
                <span>
                  <strike>
                    &#8377;
                    {product.originalPrice}
                  </strike>
                </span>
                <span className={styles.discount}>
                  &#8377;
                  {product.discountedPrice} Only
                </span>
              </p>
            </div>
          </div>
        </Link>
        <div className={styles.btns}>
          {addedToCart ? (
            <Link to={"/cart"}>
              <button>Go To Cart</button>
            </Link>
          ) : (
            <button onClick={addToCart}>Add to Cart</button>
          )}
          <button onClick={removeFromWishList}>Remove from wish-list</button>
        </div>
      </div>
    </>
  );
}
