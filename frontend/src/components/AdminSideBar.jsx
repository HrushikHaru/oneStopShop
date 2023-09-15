import { NavLink } from "react-router-dom";
import styles from "./AdminSideBar.module.css";

function AdminSideBar() {
  return (
    <nav className={styles.nav}>
      <ul className={styles.lists}>
        <NavLink to="show-products">
          <li>Show Products</li>
        </NavLink>

        <NavLink to="add-products">
          <li>Add Products</li>
        </NavLink>

        <NavLink to="show-categories">
          <li>Show Categories</li>
        </NavLink>
        <NavLink to="add-categories">
          <li>Add Categories</li>
        </NavLink>
      </ul>
    </nav>
  );
}

export default AdminSideBar;
