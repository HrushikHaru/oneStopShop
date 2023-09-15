import { useEffect, useState } from "react";
import { Link, useLocation, useParams } from "react-router-dom";
import Navigation from "./Navigation";
// import Loader from "./Loader";
import styles from "./ProductShowCase.module.css";
import PropTypes from "prop-types";
import ProductsCarousel from "./ProductsCarousel";
import Footer from "./Footer";
//import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";

ProductShowCase.propTypes = {
  products: PropTypes.array,
  setLoggedIn: PropTypes.func,
  loggedIn: PropTypes.bool,
  modalWindow: PropTypes.bool,
  setModalWindow: PropTypes.func,
  cartProducts: PropTypes.arrayOf(Object),
  selectedSize: PropTypes.string,
  setSelectedSize: PropTypes.func,
  // QtySelected: PropTypes.string,
  setQtySelected: PropTypes.func,
  modalDescription: PropTypes.object,
  setModalDescription: PropTypes.func,
  handleAddToCart: PropTypes.func,
  handleLogOut: PropTypes.func,
  wishListProd: PropTypes.array,
  setTriggerChange: PropTypes.func,
};

function ProductShowCase({
  products,
  setLoggedIn,
  loggedIn,
  modalWindow,
  setModalWindow,
  cartProducts,
  selectedSize,
  setSelectedSize,
  // QtySelected,
  setQtySelected,
  modalDescription,
  setModalDescription,
  handleAddToCart,
  handleLogOut,
  wishListProd,
  setTriggerChange,
}) {
  const productId = useParams();
  const location = useLocation();
  //const [isLoading, setIsLoading] = useState(false);
  const [product, setProduct] = useState({});

  //This is to handle the Go to Cart or Add to poduct
  const [productAdded, setProductAdded] = useState(false);

  const handleSizeClick = (event) => {
    setSelectedSize(event);
    setModalDescription({ ...modalDescription, size: "" });
  };

  let pathName = location.pathname;

  if (pathName.slice(1).split("/")[0] === "womensFashion") {
    pathName = "Women's Fashion";
  } else {
    pathName = "Men's Fashion";
  }

  useEffect(
    function () {
      async function getProduct() {
        //setIsLoading(true);
        try {
          const response = await fetch(
            `http://localhost:8080/admin/getProductDetails/${Number(
              productId.id
            )}`,
            {
              method: "GET",
              headers: {
                "Content-type": "application/json",
              },
            }
          );

          const data = await response.json();
          setProduct(data);
          //setIsLoading(false);

          let addedToCart = false;

          cartProducts.forEach((product) => {
            if (product.productDetails.productId === data.productId) {
              addedToCart = true;
            }
          });

          setProductAdded(addedToCart);
        } catch (err) {
          console.log(err);
        }
      }
      getProduct();
    },
    [productId]
  );

  function handleQtyValue(e) {
    setQtySelected(e.target.value);
    setModalDescription({ ...modalDescription, quantity: "" });
  }

  const similarProducts = products.filter(
    (productSimile) =>
      productSimile.categoryName === product.categoryName &&
      productSimile.subCategoryName === product.subCategoryName &&
      productSimile.productId !== product.productId
  );

  if (Object.keys(product).length === 0) return;

  return (
    <>
      <Navigation
        setLoggedIn={setLoggedIn}
        loggedIn={loggedIn}
        modalWindow={modalWindow}
        setModalWindow={setModalWindow}
        cartProducts={cartProducts.length}
        handleLogOut={handleLogOut}
      />
      {/* {isLoading && <Loader />} */}
      <section className={styles.product}>
        <div className={styles.imgContainer}>
          {product && (
            <img
              src={URL?.createObjectURL(
                new Blob([
                  Uint8Array?.from(
                    atob(product?.base64EncodedProductImg),
                    (c) => c.charCodeAt(0)
                  ),
                ])
              )}
            />
          )}
        </div>
        <div className={styles.contents}>
          <p className={styles.navigation}>
            <Link to="/">
              <span>Home </span>
            </Link>
            /
            <Link to={`/${location.pathname.slice(1).split("/")[0]}`}>
              <span> {pathName} </span>
            </Link>
          </p>
          <p className={styles.title}>{product.productTitle}</p>
          <div>
            <p>
              <span className={styles.availiability}>Brand :</span>
              <span className={styles.brand}> {product.brand}</span>
            </p>
          </div>
          <p className={styles.price}>
            <span className={styles.original}>
              <strike>&#8377;{product.originalPrice}</strike>
            </span>
            <span className={styles.discount}>
              &#8377;{product.discountedPrice} Only.
            </span>
          </p>
          <p className={styles.availiability}>Currently available.</p>
          <p className={styles.availiability}>
            We don&rsquo;t know when or if this item will be back in stock.
          </p>
          <div>
            {modalDescription.size ? (
              <p className={styles.select}>{modalDescription.size}</p>
            ) : (
              ""
            )}
            <p className={styles.availiability}>Sizes availiable :-</p>
            <div className={styles["size-selector"]}>
              <div
                className={`${styles["size-option"]} ${
                  selectedSize === "S" ? styles.selected : ""
                }`}
                onClick={() => handleSizeClick("S")}
              >
                Small
              </div>
              <div
                className={`${styles["size-option"]} ${
                  selectedSize === "M" ? styles.selected : ""
                }`}
                onClick={() => handleSizeClick("M")}
              >
                Medium
              </div>
              <div
                className={`${styles["size-option"]} ${
                  selectedSize === "L" ? styles.selected : ""
                }`}
                onClick={() => handleSizeClick("L")}
              >
                Large
              </div>
            </div>
          </div>
          <div className={styles.qty}>
            {modalDescription.quantity ? (
              <label className={styles.select}>
                {modalDescription.quantity}
              </label>
            ) : (
              ""
            )}
            <p>Quantity</p>
            <select onChange={(e) => handleQtyValue(e)}>
              <option>Select</option>
              {Array.from({ length: 6 }, (_, i) => (
                <option key={i}>{i + 1}</option>
              ))}
            </select>
          </div>
          {modalDescription.logFirst ? (
            <p className={styles.select}>{modalDescription.logFirst}</p>
          ) : (
            ""
          )}
          <div className={styles.buttons}>
            {productAdded && loggedIn ? (
              <Link to={"/cart"}>
                <button>Go To Cart</button>
              </Link>
            ) : (
              <button onClick={handleAddToCart}>Add to Cart</button>
            )}
          </div>
          <div>
            <p className={styles.desc}>Description about the product</p>
            {product?.description.split(".").map((desc) => (
              <p className={styles.description} key={desc}>
                {desc}
              </p>
            ))}
          </div>
          <div>
            <p className={styles.desc}>Material</p>
            <p className={styles.availiability}>{product.material}</p>
          </div>
        </div>
      </section>
      <aside className={styles.similar}>
        <h2>Similar Products</h2>
        <ProductsCarousel
          products={similarProducts}
          wishListProd={wishListProd}
          loggedIn={loggedIn}
          setTriggerChange={setTriggerChange}
        />
      </aside>
      <Footer />
    </>
  );
}

export default ProductShowCase;
