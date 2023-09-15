import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import styles from "./Checkout.module.css";
import { Link } from "react-router-dom";
import { useEffect } from "react";
import PropTypes from "prop-types";

Success.propTypes = {
  product: PropTypes.arrayOf(Object),
  setQtyChange: PropTypes.func,
};

function Success({ product, setQtyChange }) {
  async function getCartProducts(products) {
    try {
      const response = await fetch("http://localhost:8080/getProductsToOrder", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("userToken")}`,
        },
        body: JSON.stringify(products),
      });

      if (response.status === 201) {
        console.log("Done");
        setQtyChange((change) => !change);
      }
      console.log(products);
    } catch (err) {
      console.log(err);
    }
  }

  useEffect(
    function () {
      if (product.length === 0) return;

      const products = [];

      const currentDatetime = new Date().toISOString();

      for (let i = 0; i < product.length; i++) {
        console.log(product[i]);
        products.push({
          userId: Number(localStorage.getItem("userId")),
          productId: product[i].productDetails.productId,
          numItems: product[i].numItems,
          sizeSelected: product[i].sizeSelected,
          cartId: product[i].cartId,
          dateTime: currentDatetime,
        });
      }
      getCartProducts(products);
    },
    [product]
  );
  return (
    <>
      <div className={styles.output}>
        <CheckCircleOutlineIcon />
        <p className={styles.message}>
          Payment was successfully made. You can continue browsing through the
          website and keep shopping
        </p>
        <Link to={"/"} className={styles.message}>
          Click here to continue shopping
        </Link>
      </div>
    </>
  );
}

export default Success;
