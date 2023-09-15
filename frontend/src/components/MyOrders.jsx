import Footer from "./Footer";
import Navigation from "./Navigation";
import PropTypes from "prop-types";
import styles from "./MyOrders.module.css";
import { useEffect, useState } from "react";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";

MyOrders.propTypes = {
  setLoggedIn: PropTypes.func,
  loggedIn: PropTypes.bool,
  modalWindow: PropTypes.bool,
  setModalWindow: PropTypes.func,
  cartProducts: PropTypes.number,
  handleLogOut: PropTypes.func,
};

function MyOrders({
  setLoggedIn,
  loggedIn,
  modalWindow,
  setModalWindow,
  cartProducts,
  handleLogOut,
}) {
  const [orderDetails, setOrderDetails] = useState([]);

  useEffect(
    function () {
      if (!loggedIn) return;

      async function getOrderDetails() {
        try {
          const response = await fetch(
            `http://localhost:8080/getOrdersPlaced/${localStorage.getItem(
              "userId"
            )}`,
            {
              method: "GET",
              headers: {
                "Content-type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("userToken")}`,
              },
            }
          );

          const data = await response.json();

          console.log(data);
          setOrderDetails(data);
        } catch (err) {
          console.log(err);
        }
      }

      getOrderDetails();
    },
    [loggedIn]
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
      <section className={styles.section}>
        <h1>My Orders</h1>
        {orderDetails.length === 0 ? (
          <p className={styles.message}>No Orders Have been placed</p>
        ) : (
          <div className={styles.orders}>
            {orderDetails.map((order, index) => (
              <ProductDetails key={index} order={order} />
            ))}
          </div>
        )}
      </section>
      <Footer />
    </>
  );
}

export default MyOrders;

ProductDetails.propTypes = {
  order: PropTypes.object,
};

function ProductDetails({ order }) {
  const dateString = order.dateTime;
  const date = new Date(dateString);

  // Define months as an array for formatting
  const months = [
    "JAN",
    "FEB",
    "MAR",
    "APR",
    "MAY",
    "JUN",
    "JUL",
    "AUG",
    "SEP",
    "OCT",
    "NOV",
    "DEC",
  ];

  // Format the date components
  const day = date.getDate();
  const month = months[date.getMonth()];
  const year = date.getFullYear();
  const hours = date.getHours().toString().padStart(2, 0);
  const minutes = date.getMinutes().toString().padStart(2, 0);

  // Create the formatted date string
  const formattedDate = `${day} ${month} ${year} ${hours}:${minutes}`;
  return (
    <>
      <div className={styles.productDetails}>
        <h2>Product Details</h2>
        {order.productDetails.map((product, index) => (
          <Product key={index} product={product} />
        ))}
      </div>
      <div>
        <p>Your order was placed at {formattedDate}</p>
        <div className={styles.confirmed}>
          <p>Order Confirmed</p>
          <CheckCircleIcon />
        </div>
      </div>
    </>
  );
}

Product.propTypes = {
  product: PropTypes.object,
};

function Product({ product }) {
  return (
    <div>
      <p>
        {product.numItems} X {product.title}
      </p>
      <p>{product.color}</p>
      <p>{product.brand}</p>
      <p>{product.sizeSelected}</p>
    </div>
  );
}
