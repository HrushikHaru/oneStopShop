import PropTypes from "prop-types";
import Navigation from "./Navigation";
import Footer from "./Footer";
import SmsFailedIcon from "@mui/icons-material/SmsFailed";
import styles from "./Checkout.module.css";
import { Link } from "react-router-dom";

Failed.propTypes = {
  setLoggedIn: PropTypes.func,
  loggedIn: PropTypes.bool,
  modalWindow: PropTypes.bool,
  setModalWindow: PropTypes.func,
  cartProducts: PropTypes.number,
  handleLogOut: PropTypes.func,
};

function Failed({
  setLoggedIn,
  loggedIn,
  modalWindow,
  setModalWindow,
  cartProducts,
  handleLogOut,
}) {
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
        <div className={styles.output}>
          <SmsFailedIcon />
          <p className={styles.message}>
            Payment was not processed successfully.
          </p>
          <Link to={"/cart"} className={styles.message}>
            Click here to try again
          </Link>
        </div>
      </section>
      <Footer />
    </>
  );
}

export default Failed;
