import styles from "./Footer.module.css";
import FacebookIcon from "@mui/icons-material/Facebook";
import TwitterIcon from "@mui/icons-material/Twitter";
import InstagramIcon from "@mui/icons-material/Instagram";

function Footer() {
  return (
    <footer className={styles.footer}>
      <h2>One Stop Shop</h2>
      <div>
        <div className={styles.links}>
          <FacebookIcon />
          <TwitterIcon />
          <InstagramIcon />
        </div>
      </div>
      <div>
        &copy;<span id="year">2023</span>
        <span> One Stop Shop. All rights reserved.</span>
      </div>
    </footer>
  );
}

export default Footer;
