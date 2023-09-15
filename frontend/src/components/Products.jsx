import { useEffect, useState } from "react";
import styles from "./Products.module.css";
import ProductsCarousel from "./ProductsCarousel";
import PropTypes from "prop-types";

Products.propTypes = {
  products: PropTypes.array,
  loggedIn: PropTypes.bool,
  wishListProd: PropTypes.array,
  setTriggerChange: PropTypes.func,
};

function Products({ products, loggedIn, wishListProd, setTriggerChange }) {
  const [mensProduct, setMensProduct] = useState([]);
  const [womensProduct, setWomensProduct] = useState([]);

  useEffect(
    function () {
      const mensProducts = [];

      products.forEach((product) => {
        if (product.categoryName === `Men's Fashion`)
          mensProducts.push(product);
      });

      setMensProduct(mensProducts);

      const womensProducts = products.filter(
        (product) => product.categoryName === `Women's Fashion`
      );

      setWomensProduct(womensProducts);
    },
    [products]
  );

  return (
    <section className={styles.product}>
      <h2>Products</h2>
      <h3>Men&rsquo;s Fashion</h3>
      <ProductsCarousel
        products={mensProduct}
        loggedIn={loggedIn}
        wishListProd={wishListProd}
        setTriggerChange={setTriggerChange}
      />
      <h3>Women&rsquo;s Fashion</h3>
      <ProductsCarousel
        products={womensProduct}
        loggedIn={loggedIn}
        wishListProd={wishListProd}
        setTriggerChange={setTriggerChange}
      />
    </section>
  );
}

export default Products;
