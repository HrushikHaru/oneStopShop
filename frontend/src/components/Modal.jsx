import styles from "./Modal.module.css";
import PropTypes from "prop-types";

Modal.propTypes = {
  children: PropTypes.node,
};

function Modal({ children }) {
  return <aside className={styles.modal}>{children}</aside>;
}

export default Modal;
