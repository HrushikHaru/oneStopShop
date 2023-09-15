import { Outlet } from "react-router-dom";
// import AddProducts from "./AddProducts";
import AdminSideBar from "./AdminSideBar";
import styles from "./Admin.module.css";
import AdminHeader from "./AdminHeader";
// import AddCategories from "./AddCategories";
// import ShowCategories from "./Showcategories";
import { useEffect } from "react";
// import ShowProducts from "./ShowProducts";
import PropTypes from "prop-types";

Admin.propTypes = {
  // products: PropTypes.array,
  setNestedCategories: PropTypes.func,
};

function Admin({ setNestedCategories }) {
  // const [nestedCategories, setNestedCategories] = useState([]);
  // const [categoryData, setCategoryData] = useState([]);

  useEffect(
    function () {
      async function getNestedCategories() {
        try {
          const response = await fetch(
            "http://localhost:8080/admin/getNestedCategories",
            {
              method: "GET",
              headers: {
                "Content-type": "application/json",
              },
            }
          );

          const data = await response.json();
          setNestedCategories(data);
        } catch (error) {
          console.log(error);
        }
      }

      getNestedCategories();
    },
    [setNestedCategories]
  );

  return (
    <div className={styles.admin}>
      <AdminHeader />
      <main className={styles.grid}>
        <AdminSideBar />
        <Outlet />
        {/*<Routes>
           <Route
            path="/"
            element={<Navigate replace to="/show-products" />}
          ></Route> 
          <Route
            path="/show-products"
            element={
              <ShowProducts
                nestedCategories={nestedCategories}
                products={products}
              />
            }
          ></Route>
          <Route path="/add-products" element={<AddProducts />}></Route>
          <Route
            path="/show-categories"
            element={
              <ShowCategories
                nestedCategories={nestedCategories}
                setNestedCategories={setNestedCategories}
              />
            }
          ></Route>
          <Route
            path="/add-categories"
            element={
              <AddCategories
                setCategoryData={setCategoryData}
                categoryData={categoryData}
              />
            }
          ></Route>
        </Routes>
        */}
      </main>
    </div>
  );
}

export default Admin;
