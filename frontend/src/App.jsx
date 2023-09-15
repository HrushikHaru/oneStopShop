import { useEffect, useState } from "react";
import { Route, Routes, useLocation } from "react-router-dom";
import LandingPage from "./components/LandingPage";
import MensFashion from "./components/MensFashion";
import WomensFashion from "./components/WomensFashion";
import ProductShowCase from "./components/ProductShowCase";
import Cart from "./components/Cart";
import Admin from "./components/Admin";
import ShowProducts from "./components/ShowProducts";
import AddProducts from "./components/AddProducts";
import ShowCategories from "./components/Showcategories";
import AddCategories from "./components/AddCategories";
import WishList from "./components/WishList";
import Checkout from "./components/Checkout";
import Failed from "./components/Failed";
import MyOrders from "./components/MyOrders";

function App() {
  const [triggerChange, setTriggerChange] = useState(false); //This is so that whenever you try to add/remove the wishlist, it triggers

  const [modalWindow, setModalWindow] = useState(false);
  const [products, setProducts] = useState([]);
  const [loggedIn, setLoggedIn] = useState(false);

  //To Showcase all products on landing page
  useEffect(function getAllProducts() {
    async function allProducts() {
      try {
        const response = await fetch(
          "http://localhost:8080/admin/getProductDetails",
          {
            method: "GET",
            headers: {
              "Content-type": "application/json",
            },
          }
        );

        const data = await response.json();

        setProducts(data);
      } catch (error) {
        console.log(error);
      }
    }

    allProducts();
  }, []);

  //For Cart
  const [product, setProduct] = useState([]);
  const [addedToCart, setAddedToCart] = useState(false);
  const [qtyChange, setQtyChange] = useState(false);
  const [itemsCost, setItemsCost] = useState(0);
  const [discountedPrice, setDiscountedPrice] = useState(0);
  const [totalCost, setTotalCost] = useState(0);

  useEffect(
    function () {
      if (!loggedIn) return;

      async function getCartProducts() {
        const response = await fetch(
          `http://localhost:8080/getUsersCart/${localStorage.getItem(
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

        setProduct(data);
      }

      getCartProducts();
    },
    [setProduct, loggedIn, addedToCart, triggerChange, qtyChange]
  );

  useEffect(
    function () {
      if (!loggedIn) return;

      async function getCartDetails() {
        try {
          const response = await fetch(
            `http://localhost:8080/cartDetails/${localStorage.getItem(
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

          setItemsCost(data.itemsCost);
          setDiscountedPrice(data.discountedPrice);
          setTotalCost(data.totalCost);
        } catch (error) {
          console.log(error);
        }
      }

      getCartDetails();
    },
    [qtyChange, loggedIn, addedToCart]
  );

  //To add new products to cart
  //This is to handle the error when size/quantity not selected to add it in the cart
  const [selectedSize, setSelectedSize] = useState(null);
  const [qtySelected, setQtySelected] = useState(null);
  const [modalDescription, setModalDescription] = useState({
    size: "",
    quantity: "",
    logFirst: "",
  });

  //To Get the product Id from the URL
  const location = useLocation();

  let currLocation = location.pathname;

  const productId = currLocation.substring(
    currLocation.lastIndexOf("/") + 1,
    currLocation.length
  );

  function handleAddToCart() {
    if (qtySelected === null || qtySelected === "Select") {
      setModalDescription({
        ...modalDescription,
        quantity: "Please select the quantity, before adding it to cart",
      });
      return;
    }

    if (selectedSize === null) {
      setModalDescription({
        ...modalDescription,
        size: "Please select the size, before adding it the cart",
      });
      return;
    }

    if (loggedIn === false) {
      setModalDescription({
        ...modalDescription,
        logFirst: "Please log In first to add the product in the cart",
      });

      setTimeout(() => {
        setModalDescription({
          ...modalDescription,
          logFirst: "",
        });
      }, 3000);
      return;
    }

    async function postProductInCart() {
      const response = await fetch("http://localhost:8080/addToUserCart", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("userToken")}`,
        },
        body: JSON.stringify({
          productId: Number(productId),
          sizeSelected: selectedSize,
          numItems: qtySelected,
          userId: localStorage.getItem("userId"),
        }),
      });

      if (response.status === 201) {
        setAddedToCart((addProd) => !addProd);
      }
    }

    postProductInCart();
  }

  function handleLogOut() {
    localStorage.removeItem("userToken");
    localStorage.removeItem("userId");
    localStorage.removeItem("userName");
    localStorage.removeItem("userRole");
    setLoggedIn(false);
  }

  //To get the wishListed Products
  const [wishListProd, setWishListProd] = useState([]);

  useEffect(
    function () {
      if (!loggedIn) return;

      async function getWishList() {
        try {
          const response = await fetch(
            `http://localhost:8080/getWishList/${localStorage.getItem(
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

          setWishListProd(data);
        } catch (err) {
          console.log(err);
        }
      }

      getWishList();
    },
    [loggedIn, triggerChange]
  );

  //To get the overall Details about wish list products
  const [wishListProducts, setWishListProducts] = useState([]);

  useEffect(
    function () {
      if (!loggedIn) return;

      async function getWishListDetails() {
        try {
          const response = await fetch(
            `http://localhost:8080/getDetailsAboutWishList/${localStorage.getItem(
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
          setWishListProducts(data);
        } catch (err) {
          console.log(err);
        }
      }

      getWishListDetails();
    },
    [loggedIn, triggerChange]
  );

  //Admin's State
  const [nestedCategories, setNestedCategories] = useState([]);
  const [categoryData, setCategoryData] = useState([]);

  return (
    <div
      style={{ minHeight: "100vh", display: "flex", flexDirection: "column" }}
    >
      <Routes>
        <Route
          path="/"
          element={
            <LandingPage
              products={products}
              setLoggedIn={setLoggedIn}
              loggedIn={loggedIn}
              modalWindow={modalWindow}
              setModalWindow={setModalWindow}
              cartProducts={product.length}
              handleLogOut={handleLogOut}
              wishListProd={wishListProd}
              setTriggerChange={setTriggerChange}
            />
          }
        ></Route>
        <Route
          path="/mensFashion"
          element={
            <MensFashion
              products={products}
              setLoggedIn={setLoggedIn}
              loggedIn={loggedIn}
              modalWindow={modalWindow}
              setModalWindow={setModalWindow}
              cartProducts={product.length}
              handleLogOut={handleLogOut}
              wishListProd={wishListProd}
              setTriggerChange={setTriggerChange}
            />
          }
        ></Route>
        <Route
          path="/mensFashion/:id"
          element={
            <ProductShowCase
              products={products}
              setLoggedIn={setLoggedIn}
              loggedIn={loggedIn}
              modalWindow={modalWindow}
              setModalWindow={setModalWindow}
              cartProducts={product}
              selectedSize={selectedSize}
              setSelectedSize={setSelectedSize}
              qtySelected={qtySelected}
              setQtySelected={setQtySelected}
              setModalDescription={setModalDescription}
              modalDescription={modalDescription}
              handleAddToCart={handleAddToCart}
              showCaseProduct={product}
              setShowCaseProduct={setProduct}
              handleLogOut={handleLogOut}
              wishListProd={wishListProd}
              setTriggerChange={setTriggerChange}
            />
          }
        />
        <Route
          path="/womensFashion"
          element={
            <WomensFashion
              products={products}
              setLoggedIn={setLoggedIn}
              loggedIn={loggedIn}
              modalWindow={modalWindow}
              setModalWindow={setModalWindow}
              cartProducts={product.length}
              handleLogOut={handleLogOut}
              wishListProd={wishListProd}
              setTriggerChange={setTriggerChange}
            />
          }
        ></Route>
        <Route
          path="/womensFashion/:id"
          element={
            <ProductShowCase
              products={products}
              setLoggedIn={setLoggedIn}
              loggedIn={loggedIn}
              modalWindow={modalWindow}
              setModalWindow={setModalWindow}
              cartProducts={product}
              selectedSize={selectedSize}
              setSelectedSize={setSelectedSize}
              qtySelected={qtySelected}
              setQtySelected={setQtySelected}
              setModalDescription={setModalDescription}
              modalDescription={modalDescription}
              handleAddToCart={handleAddToCart}
              showCaseProduct={product}
              setShowCaseProduct={setProduct}
              handleLogOut={handleLogOut}
              wishListProd={wishListProd}
              setTriggerChange={setTriggerChange}
            />
          }
        />
        <Route
          path="/cart"
          element={
            <Cart
              setLoggedIn={setLoggedIn}
              loggedIn={loggedIn}
              modalWindow={modalWindow}
              setModalWindow={setModalWindow}
              cartProducts={product.length}
              product={product}
              handleLogOut={handleLogOut}
              setQtyChange={setQtyChange}
              itemsCost={itemsCost}
              discountedPrice={discountedPrice}
              totalCost={totalCost}
            />
          }
        />
        <Route
          path="/wishList"
          element={
            <WishList
              setLoggedIn={setLoggedIn}
              loggedIn={loggedIn}
              modalWindow={modalWindow}
              setModalWindow={setModalWindow}
              cartProducts={product.length}
              handleLogOut={handleLogOut}
              cartOverallProducts={product}
              setTriggerChange={setTriggerChange}
              wishListProducts={wishListProducts}
              setWishListProducts={setWishListProducts}
            />
          }
        />
        <Route
          path="/admin"
          element={<Admin setNestedCategories={setNestedCategories} />}
        >
          <Route
            path="show-products"
            element={
              <ShowProducts
                nestedCategories={nestedCategories}
                products={products}
              />
            }
          ></Route>
          <Route path="add-products" element={<AddProducts />}></Route>
          <Route
            path="show-categories"
            element={
              <ShowCategories
                nestedCategories={nestedCategories}
                setNestedCategories={setNestedCategories}
              />
            }
          ></Route>
          <Route
            path="add-categories"
            element={
              <AddCategories
                setCategoryData={setCategoryData}
                categoryData={categoryData}
              />
            }
          ></Route>
        </Route>
        <Route
          path="/checkout"
          element={
            <Checkout
              setLoggedIn={setLoggedIn}
              loggedIn={loggedIn}
              modalWindow={modalWindow}
              setModalWindow={setModalWindow}
              cartProducts={product.length}
              handleLogOut={handleLogOut}
              product={product}
              itemsCost={itemsCost}
              discountedPrice={discountedPrice}
              totalCost={totalCost}
              setQtyChange={setQtyChange}
            />
          }
        ></Route>
        <Route
          path="/failed"
          element={
            <Failed
              setLoggedIn={setLoggedIn}
              loggedIn={loggedIn}
              modalWindow={modalWindow}
              setModalWindow={setModalWindow}
              cartProducts={product.length}
              handleLogOut={handleLogOut}
            />
          }
        />
        <Route
          path="/myorders"
          element={
            <MyOrders
              setLoggedIn={setLoggedIn}
              loggedIn={loggedIn}
              modalWindow={modalWindow}
              setModalWindow={setModalWindow}
              cartProducts={product.length}
              handleLogOut={handleLogOut}
            />
          }
        />
      </Routes>
    </div>
  );
}

export default App;
