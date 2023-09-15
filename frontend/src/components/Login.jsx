import styles from "./Login.module.css";
import PropTypes from "prop-types";

Login.propTypes = {
  setLoginWindow: PropTypes.func,
  setRegisterWindow: PropTypes.func,
  setUserName: PropTypes.func,
  setPassword: PropTypes.func,
  userName: PropTypes.string,
  password: PropTypes.string,
  handleLoginSubmit: PropTypes.func,
  errorMsg: PropTypes.string,
  setErrorMsg: PropTypes.func,
};

function Login({
  setLoginWindow,
  setRegisterWindow,
  setUserName,
  setPassword,
  userName,
  password,
  handleLoginSubmit,
  errorMsg,
  setErrorMsg,
}) {
  function handleUserName(e) {
    setErrorMsg("");
    setUserName(e.target.value);
  }

  function handlePassword(e) {
    setErrorMsg("");
    setPassword(e.target.value);
  }

  function handleChange() {
    setLoginWindow(false);
    setRegisterWindow(true);
  }

  return (
    <div className={styles.login}>
      <h2>Log in to keep shopping!</h2>
      <p className={styles.errorMsg}>{errorMsg && errorMsg}</p>
      <form onSubmit={(e) => handleLoginSubmit(e)}>
        <div>
          <label>Email:</label>
          <input
            type="text"
            name="email"
            value={userName}
            onChange={(e) => handleUserName(e)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={password}
            onChange={(e) => handlePassword(e)}
            required
          />
        </div>
        <div className={styles.btns}>
          <button type="submit">Login</button>
          <a className={styles.change} onClick={handleChange}>
            Don&rsquo;t have an account?
          </a>
        </div>
      </form>
    </div>
  );
}

export default Login;
