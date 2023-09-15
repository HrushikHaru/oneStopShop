import CategoryIcon from "@mui/icons-material/Category";
import styles from "./AddCategories.module.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import PropTypes from "prop-types";

AddCategories.propTypes = {
  setCategoryData: PropTypes.func,
  categoryData: PropTypes.arrayOf(Object),
};

function AddCategories({ setCategoryData, categoryData }) {
  const [category, setCategory] = useState("");
  const [subCategory, setSubCategory] = useState("");
  const [selectValue, setSelectValue] = useState("");

  const navigate = useNavigate();

  useEffect(
    function () {
      async function getCategories() {
        try {
          const response = await fetch(
            "http://localhost:8080/admin/getCategory",
            {
              method: "GET",
              headers: {
                "Content-type": "application/json",
              },
            }
          );

          const data = await response.json();
          setCategoryData(data);
        } catch (error) {
          console.log(error);
        }
      }

      getCategories();
    },
    [setCategoryData]
  );

  function addCategory(e) {
    e.preventDefault();

    async function addCategoryInDB() {
      try {
        const response = await fetch(
          "http://localhost:8080/admin/addCategory",
          {
            method: "POST",
            headers: {
              "Content-type": "application/json",
            },
            body: JSON.stringify({
              categoryName: category,
            }),
          }
        );

        const data = await response.json();
        console.log(data);

        if (response.status === 201) {
          navigate("/show-categories");
        }
      } catch (error) {
        console.log(error);
      }
    }

    addCategoryInDB();
  }

  function addSubCategory(e) {
    e.preventDefault();

    async function addSubCategoryInDB() {
      const response = await fetch(
        "http://localhost:8080/admin/addSubCategory",
        {
          method: "POST",
          headers: {
            "Content-type": "application/json",
          },
          body: JSON.stringify({
            subCategoryName: subCategory,
            categoryId: Number(selectValue),
          }),
        }
      );

      const data = await response.json();
      console.log(data);

      if (response.status === 201) {
        navigate("/show-categories");
      }
    }

    addSubCategoryInDB();
  }

  return (
    <section className="globalMarginForAdmin">
      <div className={styles.categories}>
        <div>
          <div className={styles.category}>
            <CategoryIcon />
            <p>Add Category</p>
          </div>
          <form className={styles.product} onSubmit={addCategory}>
            <label>Add a New Category</label>
            <input
              value={category}
              onChange={(e) => setCategory(e.target.value)}
              required
            />
            <button type="submit">Submit</button>
          </form>
        </div>
        <hr />
        <div className={styles.subCategory}>
          <div>
            <div className={styles.category}>
              <CategoryIcon />
              <p>Add Sub-Category</p>
            </div>
          </div>
          <div className={styles.product}>
            <label>Select a Category</label>
            <div>
              <select
                value={selectValue}
                onChange={(e) => setSelectValue(e.target.value)}
              >
                <option>Select a Category</option>
                {categoryData.map((e) => (
                  <Option key={e.categoryId} element={e} />
                ))}
              </select>
            </div>
          </div>
          <form className={styles.product} onSubmit={addSubCategory}>
            <label>Add a New Sub-Category</label>
            <input
              value={subCategory}
              onChange={(e) => setSubCategory(e.target.value)}
            />
            <button type="submit">Submit</button>
          </form>
        </div>
      </div>
    </section>
  );
}

export default AddCategories;

Option.propTypes = {
  element: PropTypes.shape({
    categoryId: PropTypes.number,
    categoryName: PropTypes.string,
    // Add other required shape properties here
  }).isRequired,
  id: PropTypes.number,
  categoryName: PropTypes.string,
};

function Option({ element }) {
  return <option value={element.categoryId}>{element.categoryName}</option>;
}
