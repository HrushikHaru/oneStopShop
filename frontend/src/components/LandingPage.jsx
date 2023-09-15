import Footer from "./Footer";
import HeroSection from "./HeroSection";
import Navigation from "./Navigation";
import Products from "./Products";
import styles from "./LandingPage.module.css";
import PropTypes from "prop-types";

LandingPage.propTypes = {
  products: PropTypes.array,
  setLoggedIn: PropTypes.func,
  loggedIn: PropTypes.bool,
  modalWindow: PropTypes.bool,
  setModalWindow: PropTypes.func,
  cartProducts: PropTypes.number,
  handleLogOut: PropTypes.func,
  wishListProd: PropTypes.array,
  setTriggerChange: PropTypes.func,
};

function LandingPage({
  products,
  setLoggedIn,
  loggedIn,
  modalWindow,
  setModalWindow,
  cartProducts,
  handleLogOut,
  wishListProd,
  setTriggerChange,
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
      <HeroSection />
      <div className={styles.container}>
        <Products
          products={products}
          loggedIn={loggedIn}
          wishListProd={wishListProd}
          setTriggerChange={setTriggerChange}
        />
      </div>
      <Footer />
    </>
  );
}

export default LandingPage;
