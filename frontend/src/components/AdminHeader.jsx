import styles from "./AdminHeader.module.css";

function AdminHeader() {
  return (
    <header className={styles.header}>
      <div className={styles.logo}>
        <div className={styles.headerImg}>
          <img src="/onestopshop-logo.webp" alt="oneshopstop-logo" />
        </div>
        <p className={styles.text}>One-Stop-Shop</p>
      </div>
      <p className={styles.text}>Admin panel</p>
    </header>
  );
}

export default AdminHeader;
