import { useEffect, useState } from "react";
import Navigation from "./Navigation";
import PropTypes from "prop-types";
import styles from "./MensFashion.module.css";
import FilterComponentWomen from "./FilterComponentWomen";
import ShowProductDetails from "./ShowProductDetails";
import { useLocation } from "react-router-dom";

WomensFashion.propTypes = {
  products: PropTypes.array,
  setLoggedIn: PropTypes.func,
  loggedIn: PropTypes.bool,
  cartProducts: PropTypes.number,
  modalWindow: PropTypes.bool,
  setModalWindow: PropTypes.func,
  handleLogOut: PropTypes.func,
  wishListProd: PropTypes.array,
  setTriggerChange: PropTypes.func,
};

function WomensFashion({
  products,
  setLoggedIn,
  loggedIn,
  cartProducts,
  modalWindow,
  setModalWindow,
  handleLogOut,
  wishListProd,
  setTriggerChange,
}) {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const subCategory = queryParams.get("subCategory");
  const color = queryParams.get("color");
  const price = queryParams.get("price");

  const [productsList, setProductsList] = useState();
  const [womensProducts, setWomensProducts] = useState([]);

  useEffect(
    function () {
      const womensProduct = products?.filter(
        (product) => product.categoryName === "Women's Fashion"
      );

      setWomensProducts(womensProduct);
    },
    [products]
  );

  useEffect(
    function () {
      let filterThrough = [];

      const subParams = subCategory?.split(",");
      const colorParams = color?.split(",");
      const priceParams = price?.split(",");

      subParams?.forEach((param) => {
        if (param === "Kurta") param = "Kurta & kurti";

        const filter = womensProducts?.filter(
          (product) => product.subCategoryName === param
        );

        filterThrough = [...filterThrough, ...filter];
        setProductsList(filterThrough);
      });

      colorParams?.forEach((param) => {
        const filter = womensProducts?.filter(
          (product) => product.color === param
        );

        filterThrough = [...filterThrough, ...filter];
        setProductsList(filterThrough);
      });

      priceParams?.forEach((param) => {
        const splitPrice = param?.split(" ");
        const price1 = Number(splitPrice[0]);
        const price2 = Number(splitPrice[2]);

        const filter = womensProducts?.filter(
          (product) =>
            product.discountedPrice >= price1 &&
            product.discountedPrice <= price2
        );

        filterThrough = [...filterThrough, ...filter];
        setProductsList(filterThrough);
      });

      if (filterThrough.length === 0 && queryParams.size === 0) {
        setProductsList(womensProducts);
      }
    },
    [subCategory, color, price, womensProducts]
  );
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
        <FilterComponentWomen />
        <div className={styles.productsGrid}>
          {productsList?.map((product, index) => (
            <ShowProductDetails
              key={index}
              product={product}
              loggedIn={loggedIn}
              wishListProd={wishListProd}
              setTriggerChange={setTriggerChange}
            />
          ))}
        </div>
      </section>
    </>
  );
}

export default WomensFashion;
