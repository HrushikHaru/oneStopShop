import PropTypes from "prop-types";
import styles from "./MyCard.module.css";
import { Link } from "react-router-dom";
import FavoriteIcon from "@mui/icons-material/Favorite";
import { useEffect, useState } from "react";
import Modal from "./Modal";
import Overlay from "./Overlay";
import LoginIcon from "@mui/icons-material/Login";

MyCard.propTypes = {
  product: PropTypes.object,
  loggedIn: PropTypes.bool,
  wishListProd: PropTypes.array,
  setTriggerChange: PropTypes.func,
};

function MyCard({ product, loggedIn, wishListProd, setTriggerChange }) {
  const productImgInBytes = Uint8Array.from(
    atob(product.base64EncodedProductImg),
    (c) => c.charCodeAt(0)
  );

  let queryParam = "";
  if (product.categoryName === "Men's Fashion") {
    queryParam = "/mensFashion/";
  } else {
    queryParam = "/womensFashion/";
  }

  const [modalError, setModalError] = useState(false);
  const [addedToWishList, setAddedToWishList] = useState(false);

  async function addToWishList() {
    try {
      const response = await fetch(`http://localhost:8080/addWishListProduct`, {
        method: "POST",
        headers: {
          "Content-type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("userToken")}`,
        },
        body: JSON.stringify({
          productId: product.productId,
          userId: localStorage.getItem("userId"),
        }),
      });

      if (response.status === 201) {
        setTriggerChange((trigger) => !trigger);
      }
    } catch (err) {
      console.log(err);
    }
  }

  async function removeFromWishList() {
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

  function handleWishList() {
    if (!loggedIn) {
      setModalError(true);

      setTimeout(() => {
        setModalError(false);
      }, 2000);
      return;
    }

    if (addedToWishList) {
      removeFromWishList();
    } else {
      addToWishList();
    }
  }

  useEffect(
    function () {
      if (!loggedIn) return;
      if (wishListProd.includes(product.productId)) {
        setAddedToWishList(true);
      } else {
        setAddedToWishList(false);
      }
    },
    [wishListProd, product.productId, loggedIn, addedToWishList]
  );

  return (
    <>
      <div className={styles.carouselComp}>
        <Link to={`${queryParam}${product.productId}`}>
          <div className={styles.myCard}>
            <div className={styles.imgContainer}>
              <img src={URL.createObjectURL(new Blob([productImgInBytes]))} />
            </div>
            <div className={styles.contents}>
              <p className={styles.brand}>{product.brand}</p>
              <p className={styles.title}>{product.productTitle}</p>
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
        <div
          className={`${
            addedToWishList && loggedIn
              ? styles[`wishListActive`]
              : styles[`wishList`]
          }`}
          onClick={() => handleWishList()}
        >
          <FavoriteIcon />
        </div>
      </div>
      {modalError && (
        <>
          <Overlay />
          <Modal className={styles.modal}>
            <p>
              <span>
                <LoginIcon />
              </span>
              Please log-In to add to your wishList
            </p>
          </Modal>
        </>
      )}
    </>
  );
}

export default MyCard;
