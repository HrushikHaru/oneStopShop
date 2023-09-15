import styles from "./OrderSummary.module.css";
import PropTypes from "prop-types";
import { loadStripe } from "@stripe/stripe-js";

OrderSummary.propTypes = {
  product: PropTypes.arrayOf(Object),
  itemsCost: PropTypes.number,
  discountedPrice: PropTypes.number,
  totalCost: PropTypes.number,
  selectedAddress: PropTypes.object,
  address: PropTypes.arrayOf(Object),
};

function OrderSummary({
  product,
  itemsCost,
  discountedPrice,
  totalCost,
  selectedAddress,
  address,
}) {
  async function checkout() {
    const stripe = await loadStripe(
      "pk_test_51NpsqKSDAiDRCBdrlkODmqJX7jcTqqiifP5A0zQXSbN6MONC9TFgl59IvIgBj98B4zcCthjgjACbYwKAN7k2d65T00glKsEpdG"
    );

    const response = await fetch(`http://localhost:8080/admin/checkout`, {
      method: "POST",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify(product),
    });

    const session = await response.json();

    const result = stripe.redirectToCheckout({
      sessionId: session.sessionId,
    });

    if (result.error) {
      console.log(result.error);
    }
  }

  if (address.length === 0) return;

  return (
    <>
      <section className={styles.summary}>
        <h2>Your Order Summary</h2>
        <div>
          <h3>Your Address</h3>
          <p>{address[selectedAddress.arrId]?.address}</p>
          <p>
            {address[selectedAddress.arrId]?.firstName}
            {address[selectedAddress.arrId]?.lastName}
          </p>
          <p>
            {address[selectedAddress.arrId]?.city}
            {address[selectedAddress.arrId]?.state}
            {address[selectedAddress.arrId]?.postalCode}
          </p>
          <p>{address[selectedAddress.arrId]?.phoneNumber}</p>
        </div>
        <div className={styles.grid}>
          <div className={styles.cartProducts}>
            {product.map((prod, i) => (
              <CartProducts key={i} prod={prod} />
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
            <button onClick={checkout}>CheckOut</button>
          </div>
        </div>
      </section>
    </>
  );
}

export default OrderSummary;

CartProducts.propTypes = {
  prod: PropTypes.object,
};

function CartProducts({ prod }) {
  const imgInBytes = Uint8Array.from(
    atob(prod.productDetails.base64EncodedProductImg),
    (c) => c.charCodeAt(0)
  );

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
          <p className={styles.value}>{prod.numItems}</p>
        </div>
      </div>
    </div>
  );
}
