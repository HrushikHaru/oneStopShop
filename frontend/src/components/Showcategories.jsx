import styles from "./ShowCategories.module.css";
import EastIcon from "@mui/icons-material/East";
import PropTypes from "prop-types";
import { useEffect } from "react";

ShowCategories.propTypes = {
  nestedCategories: PropTypes.array,
  setNestedCategories: PropTypes.func,
};

function ShowCategories({ nestedCategories, setNestedCategories }) {
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
    <section className="globalMarginForAdmin">
      {nestedCategories.map((e) => (
        <NestedCategory key={e.category} element={e} />
      ))}
    </section>
  );
}

export default ShowCategories;

NestedCategory.propTypes = {
  element: PropTypes.object,
};

function NestedCategory({ element }) {
  return (
    <div className={styles.category}>
      <p>{element.category}</p>
      <EastIcon />
      <div>
        {element.subCategory
          ? element.subCategory.map((e) => <p key={e}>{e}</p>)
          : ""}
      </div>
    </div>
  );
}
