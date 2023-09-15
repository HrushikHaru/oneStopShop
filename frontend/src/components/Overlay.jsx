import PropTypes from "prop-types";

Overlay.propTypes = {
  closeModal: PropTypes.func,
};

function Overlay({ closeModal }) {
  return (
    <div
      style={{
        position: "fixed",
        top: "0",
        left: "0",
        width: "100%",
        height: "100%",
        backgroundColor: "rgba(0, 0, 0, 0.2)",
        zIndex: "50",
        backdropFilter: "blur(4px)",

        transition: "all 1s",
      }}
      onClick={closeModal}
    ></div>
  );
}

export default Overlay;
