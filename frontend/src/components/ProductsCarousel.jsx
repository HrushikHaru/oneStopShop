import MyCard from "./MyCard";
import styles from "./ProductsCarousel.module.css";
import PropTypes from "prop-types";
import { useRef } from "react";

ProductsCarousel.propTypes = {
  products: PropTypes.array,
  loggedIn: PropTypes.bool,
  wishListProd: PropTypes.array,
  setTriggerChange: PropTypes.func,
};

function ProductsCarousel({
  products,
  loggedIn,
  wishListProd,
  setTriggerChange,
}) {
  const productRef = useRef(null);

  function handleNextBtnPress() {
    let width = productRef?.current?.clientWidth;
    if (productRef.current) {
      productRef.current.scrollLeft += width;
    }
  }

  function handlePrevBtnPress() {
    let width = productRef?.current?.clientWidth;
    if (productRef.current) {
      productRef.current.scrollLeft -= width;
    }
  }

  return (
    <div className={styles.productContainer}>
      <button className={styles.preBtn} onClick={handlePrevBtnPress}>
        <p>&lt;</p>
      </button>
      <button className={styles.postBtn} onClick={handleNextBtnPress}>
        <p>&gt;</p>
      </button>

      <div className={styles.carouselContainer} ref={productRef}>
        {products.map((product, index) => (
          <MyCard
            key={index}
            product={product}
            loggedIn={loggedIn}
            wishListProd={wishListProd}
            setTriggerChange={setTriggerChange}
          />
        ))}
      </div>
    </div>
  );
}

export default ProductsCarousel;
