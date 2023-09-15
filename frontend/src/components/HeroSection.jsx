import { useEffect, useState } from "react";
import styles from "./HeroSection.module.css";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

const allImages = [
  { url: "banner-2.png" },
  { url: "banner-1.webp" },
  { url: "banner-3.webp" },
  { url: "banner-4.webp" },
];

function HeroSection() {
  const [translate, setTranslate] = useState(0);

  useEffect(() => {
    const intervalId = setInterval(() => {
      if (translate == allImages.length - 1) {
        setTranslate(0);
      } else {
        setTranslate((count) => count + 1);
      }
    }, 3000);

    return () => {
      clearInterval(intervalId);
    };
  }, [translate]);

  return (
    <>
      <header className={styles.header}>
        {allImages.map((image, index) => (
          <ImgSlider
            key={index}
            translate={translate}
            index={index}
            image={image}
          />
        ))}
      </header>
      <aside className={styles.dotContainer}>
        {allImages.map((_, i) => {
          const isActive = i - translate === 0;
          const circleClassName = isActive
            ? styles.circleActive
            : styles.circle;

          return <div key={i} className={circleClassName}></div>;
        })}
      </aside>
    </>
  );
}

export default HeroSection;

ImgSlider.propTypes = {
  translate: PropTypes.number,
  index: PropTypes.number,
  image: PropTypes.object,
};

function ImgSlider({ translate, index, image }) {
  return (
    <Link to={index === 2 ? "/mensFashion" : "/womensFashion"}>
      <div
        className={styles.imgContainer}
        style={{
          transform: `translateX(${(index - translate) * 100}%)`,
          width: "100%",
          cursor: "pointer",
        }}
      >
        <img src={image.url} />
      </div>
    </Link>
  );
}
