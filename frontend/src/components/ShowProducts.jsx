import { useState } from "react";
import styles from "./ShowProducts.module.css";
import PropTypes from "prop-types";

ShowProducts.propTypes = {
  nestedCategories: PropTypes.array,
  products: PropTypes.array,
};

export function ShowProducts({ nestedCategories, products }) {
  const [category, setCategory] = useState("");
  const [subCategory, setSubCategory] = useState("");
  const [filteredProduct, setFilteredProduct] = useState([]);

  function handleCategory(e) {
    setCategory(e.target.value);
  }

  function handleSubCategory(e) {
    setSubCategory(e.target.value);
  }

  function handleChangedCategory(e) {
    const filteredProducts = [];
    products.forEach((product) => {
      if (product.categoryName === e.target.value)
        filteredProducts.push(product);
    });

    setFilteredProduct(filteredProducts);
  }

  function handleChangedSubCategory(e) {
    const filteredProducts = [];
    products.forEach((product) => {
      if (product.subCategoryName === e.target.value)
        filteredProducts.push(product);
    });
    setFilteredProduct(filteredProducts);
  }

  return (
    <section className={styles.section}>
      <div className={styles.categories}>
        <div className={styles.fields}>
          <label>Category</label>
          <div onChange={handleCategory}>
            <select value={category} onChange={handleChangedCategory}>
              <option>Select a category</option>
              {nestedCategories.map((category, i) => (
                <option key={i}>{category.category}</option>
              ))}
            </select>
          </div>
        </div>
        <div className={styles.fields}>
          <label>Sub-Category</label>
          <div onChange={handleSubCategory}>
            <select value={subCategory} onChange={handleChangedSubCategory}>
              <option>Select a sub-category</option>
              {category === "Select a category"
                ? ""
                : nestedCategories
                    .filter((categor) => categor?.category === category)[0]
                    ?.subCategory.map((subCategory) => (
                      <option key={subCategory}>{subCategory}</option>
                    ))}
            </select>
          </div>
        </div>
      </div>
      <table className={styles.table}>
        <thead>
          <tr>
            <th>Sr. No.</th>
            <th>ProductImg</th>
            <th>ProductTitle</th>
            <th>ProductBrand</th>
            <th>ProductColor</th>
            <th>ProductAvailability</th>
          </tr>
        </thead>
        <tbody>
          {filteredProduct.map((product, i) => (
            <Product
              product={product}
              key={product.productId}
              productNumber={i + 1}
            />
          ))}
        </tbody>
      </table>
    </section>
  );
}

export default ShowProducts;

Product.propTypes = {
  product: PropTypes.object,
  productNumber: PropTypes.number,
};

function Product({ product, productNumber }) {
  const productImageInBytes = Uint8Array.from(
    atob(product.base64EncodedProductImg),
    (c) => c.charCodeAt(0)
  );

  console.log(product);

  return (
    <tr>
      <td>
        <b style={{ marginLeft: "3rem" }}>{productNumber}</b>
      </td>
      <td className={styles.productImg}>
        <img
          src={URL.createObjectURL(new Blob([productImageInBytes]))}
          alt={product.title}
        />
      </td>
      <td>{product.productTitle}</td>
      <td>{product.brand}</td>
      <td>{product.color}</td>
      <td>
        <b>{product.sizeL + product.sizeM + product.sizeS}</b>
      </td>
    </tr>
  );
}
