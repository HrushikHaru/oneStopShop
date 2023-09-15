import ImageIcon from "@mui/icons-material/Image";
import styles from "./AddProducts.module.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function AddProducts() {
  const [imgAdded, setImgAdded] = useState("");
  const [imgAddedInInput, setImgAddedInInput] = useState(false);
  const [title, setTitle] = useState("");
  const [ogPrice, setOgPrice] = useState("");
  const [disPrice, setDisPrice] = useState("");
  const [company, setCompany] = useState("");
  const [description, setDescription] = useState("");
  const [material, setMaterial] = useState("");
  const [subCategory, setSubCategory] = useState("");
  const [getSubCategories, setSubCategories] = useState([]);
  const [productImg, setProductImg] = useState({});
  const [color, setColor] = useState("");
  const [sizeS, setSizeS] = useState("");
  const [sizeM, setSizeM] = useState("");
  const [sizeL, setSizeL] = useState("");

  const navigate = useNavigate();

  function addImg(e) {
    const file = e.target.files[0];

    if (file) {
      const reader = new FileReader();
      let result;
      reader.onload = function () {
        result = reader.result;
        setImgAdded(result);
      };

      setImgAddedInInput((set) => !set);
      reader.readAsDataURL(file);

      setProductImg(file);
    }
  }

  function titleAdd(e) {
    setTitle(e.target.value);
  }

  function ogPriceAdd(e) {
    setOgPrice(e.target.value);
  }

  function dPriceAdd(e) {
    setDisPrice(e.target.value);
  }

  function companyAdd(e) {
    setCompany(e.target.value);
  }

  function descriptionAdd(e) {
    setDescription(e.target.value);
  }

  function materialAdd(e) {
    setMaterial(e.target.value);
  }

  function subCategoryAdd(e) {
    setSubCategory(e.target.value);
  }

  function handleColor(e) {
    setColor(e.target.value);
  }

  function handleSizeS(e) {
    setSizeS(e.target.value);
  }

  function handleSizeM(e) {
    setSizeM(e.target.value);
  }

  function handleSizeL(e) {
    setSizeL(e.target.value);
  }

  useEffect(function () {
    async function getSubCategories() {
      const response = await fetch(
        "http://localhost:8080/admin/getSubCategory",
        {
          method: "GET",
          headers: {
            "Content-type": "application/json",
          },
        }
      );

      const data = await response.json();

      setSubCategories(data);
    }

    getSubCategories();
  }, []);

  function saveProduct(e) {
    e.preventDefault();

    console.log(productImg);

    async function getProductImg() {
      const formdata = new FormData();

      formdata.append("productImg", productImg);
      const response = await fetch(
        "http://localhost:8080/admin/getProductImage",
        {
          method: "POST",
          body: formdata,
        }
      );

      const data = await response.json();

      return data;
    }

    async function addProductDetails() {
      const data = await getProductImg();

      const response = await fetch(
        "http://localhost:8080/admin/postProductDetails",
        {
          method: "POST",
          headers: {
            "Content-type": "application/json",
          },
          body: JSON.stringify({
            title: title,
            originalPrice: ogPrice,
            discountedPrice: disPrice,
            color: color,
            brand: company,
            description: description,
            sizeS: Number(sizeS),
            sizeM: Number(sizeM),
            sizeL: Number(sizeL),
            productImgName: data.imgName,
            subCategoryId: Number(subCategory),
            material: material,
          }),
        }
      );

      const dataNew = await response.json();

      console.log(dataNew.message);

      if (response.status === 201) {
        navigate("/show-products");
      }
    }

    addProductDetails();
  }

  return (
    <section className="globalMarginForAdmin">
      <form className={styles.form}>
        <div className={styles[imgAddedInInput ? "disable" : "overallImg"]}>
          <div className={styles.imgAdd}>
            <ImageIcon />
            <p>Drag Images and add</p>
          </div>
          <div>
            <label htmlFor="file-upload" className={styles.fileUploadLabel}>
              Select from Your device
            </label>
            <input
              type="file"
              id="file-upload"
              accept="image/*"
              className={styles.disable}
              onChange={addImg}
            />
          </div>
        </div>
        <div className={styles[imgAddedInInput ? "imgShow" : "disable"]}>
          <img src={imgAdded} />
        </div>
        <div className={styles.product}>
          <div className={styles.fields}>
            <label>Product title</label>
            <input type="text" value={title} onChange={titleAdd} />
          </div>
          <div className={styles.fields}>
            <label>Original Price</label>
            <input type="number" value={ogPrice} onChange={ogPriceAdd} />
          </div>
          <div className={styles.fields}>
            <label>Discounted Price</label>
            <input type="number" value={disPrice} onChange={dPriceAdd} />
          </div>
          <div className={styles.fields}>
            <label>Color</label>
            <input type="text" value={color} onChange={handleColor} />
          </div>
          <div className={styles.fields}>
            <label>Size (S) Qty</label>
            <input type="number" value={sizeS} onChange={handleSizeS} />
          </div>
          <div className={styles.fields}>
            <label>Size (M) Qty</label>
            <input type="number" value={sizeM} onChange={handleSizeM} />
          </div>
          <div className={styles.fields}>
            <label>Size (L) Qty</label>
            <input type="number" value={sizeL} onChange={handleSizeL} />
          </div>
          <div className={styles.fields}>
            <label>Sub-Category</label>
            <div onChange={subCategoryAdd}>
              <select value={subCategory}>
                <option>Select a sub-category</option>
                {getSubCategories.map((e) => (
                  <option key={e.subCategoryId} value={e.subCategoryId}>
                    {e.subCategoryName}
                  </option>
                ))}
              </select>
            </div>
          </div>
          <div className={styles.fields}>
            <label>Brand</label>
            <input type="text" value={company} onChange={companyAdd} />
          </div>
          <div className={styles.fields}>
            <label>Description</label>
            <textarea
              type="number"
              value={description}
              onChange={descriptionAdd}
              cols={28}
              rows={10}
            />
          </div>
          <div className={styles.fields}>
            <label>Material</label>
            <input type="text" value={material} onChange={materialAdd} />
          </div>
          <button type="submit" onClick={saveProduct}>
            Save
          </button>
        </div>
      </form>
    </section>
  );
}

export default AddProducts;
