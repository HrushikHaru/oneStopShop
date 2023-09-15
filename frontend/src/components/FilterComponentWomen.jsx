import { useNavigate } from "react-router-dom";
import styles from "./FilterComponentMen.module.css";
import { useEffect, useState } from "react";
import FilterAltIcon from "@mui/icons-material/FilterAlt";

function FilterComponentWomen() {
  const navigate = useNavigate();

  const [selectedSubCategories, setSelectedSubCategories] = useState([]);
  const [selectedColors, setSelectedColors] = useState([]);
  const [selectedPriceRanges, setSelectedPriceRanges] = useState([]);

  function handleSubCategoryChange(subCategory) {
    toggleSelection(
      subCategory,
      selectedSubCategories,
      setSelectedSubCategories
    );
  }

  function handleColorChange(color) {
    toggleSelection(color, selectedColors, setSelectedColors);
  }

  function handlePriceChange(priceRange) {
    toggleSelection(priceRange, selectedPriceRanges, setSelectedPriceRanges);
  }

  function toggleSelection(item, selectedItems, setSelectedItems) {
    if (selectedItems.includes(item)) {
      setSelectedItems(
        selectedItems.filter((selectedItem) => selectedItem !== item)
      );
    } else {
      setSelectedItems([...selectedItems, item]);
    }
  }

  const queryParams = [];

  if (selectedSubCategories.length > 0) {
    queryParams.push(`subCategory=${selectedSubCategories.join(",")}`);
  }

  if (selectedColors.length > 0) {
    queryParams.push(`color=${selectedColors.join(",")}`);
  }

  if (selectedPriceRanges.length > 0) {
    queryParams.push(`price=${selectedPriceRanges.join(",")}`);
  }

  // Construct the URL with the selected criteria
  const url = `${queryParams.length > 0 ? `?${queryParams.join("&")}` : ""}`;

  useEffect(
    function () {
      navigate(url);
    },
    [url, navigate]
  );
  return (
    <aside className={styles.filterCategories}>
      <div className={styles.filter}>
        <FilterAltIcon />
        <p style={{ fontWeight: 800 }}>Filters</p>
      </div>
      <div>
        <p>Sub-Categories</p>
        <form className={styles.subCategories}>
          <label>
            <input
              type="checkbox"
              name="clothing"
              value="Kurta"
              onClick={(e) => handleSubCategoryChange(e.target.value)}
            />
            Kurta & kurti
          </label>
          <label>
            <input
              type="checkbox"
              name="clothing"
              value="Dresses"
              onClick={(e) => handleSubCategoryChange(e.target.value)}
            />
            Dresses
          </label>
        </form>
      </div>

      <div>
        <p>Price</p>
        <form className={styles.subCategories}>
          <label>
            <input
              type="checkbox"
              name="clothing"
              value="189 to 499"
              onClick={(e) => handlePriceChange(e.target.value)}
            />
            &#8377;189 to &#8377;499
          </label>
          <label>
            <input
              type="checkbox"
              name="clothing"
              value="500 to 699"
              onClick={(e) => handlePriceChange(e.target.value)}
            />
            &#8377;500 to &#8377;699
          </label>
          <label>
            <input
              type="checkbox"
              name="clothing"
              value="700 to 999"
              onClick={(e) => handlePriceChange(e.target.value)}
            />
            &#8377;700 to &#8377;999
          </label>
        </form>
      </div>
      <div>
        <p>Color</p>
        <form className={styles.subCategories}>
          <label>
            <input
              type="checkbox"
              name="clothing"
              value="Black"
              onClick={(e) => handleColorChange(e.target.value)}
            />
            Black
          </label>
          <label>
            <input
              type="checkbox"
              name="clothing"
              value="White"
              onClick={(e) => handleColorChange(e.target.value)}
            />
            White
          </label>
          <label>
            <input
              type="checkbox"
              name="clothing"
              value="Pink"
              onClick={(e) => handleColorChange(e.target.value)}
            />
            Pink
          </label>
        </form>
      </div>
    </aside>
  );
}

export default FilterComponentWomen;
