import { useState } from "react";
import styles from "./Registration.module.css";
import PropTypes from "prop-types";

Registration.propTypes = {
  setLoginWindow: PropTypes.func,
  setRegisterWindow: PropTypes.func,
};

function Registration({ setLoginWindow, setRegisterWindow }) {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  function handleRegistrationSubmit(e) {
    e.preventDefault();

    async function createNewUser() {
      try {
        const response = await fetch("http://localhost:8080/signUp", {
          method: "POST",
          headers: {
            "Content-type": "application/json",
          },
          body: JSON.stringify({
            firstName: firstName,
            lastName: lastName,
            email: userName,
            password: password,
          }),
        });

        const data = await response.json();

        console.log(data);
        if (response.status === 201) {
          setLoginWindow(true);
          setRegisterWindow(false);
        }
      } catch (error) {
        console.log(error);
      }
    }

    createNewUser();
  }

  function handleFirstNameChange(e) {
    setFirstName(e.target.value);
  }

  function handleLastNameChange(e) {
    setLastName(e.target.value);
  }

  function handleEmailChange(e) {
    setUserName(e.target.value);
  }

  function handlePasswordChange(e) {
    setPassword(e.target.value);
  }

  function handleChange() {
    setLoginWindow(true);
    setRegisterWindow(false);
  }

  return (
    <div className={styles.registration}>
      <h2>Register as a new user</h2>
      <form onSubmit={(e) => handleRegistrationSubmit(e)}>
        <div>
          <label>First-Name:</label>
          <input
            type="text"
            name="first-name"
            value={firstName}
            onChange={(e) => handleFirstNameChange(e)}
            required
          />
        </div>
        <div>
          <label>Last-Name:</label>
          <input
            type="text"
            name="last-name"
            value={lastName}
            onChange={(e) => handleLastNameChange(e)}
            required
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="text"
            name="email"
            value={userName}
            onChange={(e) => handleEmailChange(e)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={password}
            onChange={(e) => handlePasswordChange(e)}
            required
          />
        </div>
        <div className={styles.btns}>
          <button type="submit">Register</button>
          <a className={styles.change} onClick={handleChange}>
            Already have an account?
          </a>
        </div>
      </form>
    </div>
  );
}

export default Registration;
