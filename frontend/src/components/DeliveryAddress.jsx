import { useEffect, useState } from "react";
import styles from "./DeliveryAddress.module.css";
import PropTypes from "prop-types";

DeliveryAddress.propTypes = {
  loggedIn: PropTypes.bool,
  selectedAddress: PropTypes.object,
  setSelectedAddress: PropTypes.func,
  errorMessage: PropTypes.string,
  setErrorMessage: PropTypes.func,
  address: PropTypes.arrayOf(Object),
  setAddress: PropTypes.func,
};

const initialState = {
  firstName: "",
  lastName: "",
  address: "",
  city: "",
  state: "",
  postalCode: "",
  phoneNumber: "",
};

function DeliveryAddress({
  loggedIn,
  selectedAddress,
  setSelectedAddress,
  errorMessage,
  setErrorMessage,
  address,
  setAddress,
}) {
  const [addressForm, setAddressForm] = useState(initialState);

  function handleAddressSelect(addressId, arrId) {
    console.log(addressId, arrId);
    setSelectedAddress((prev) => ({
      ...prev,
      addressId: addressId,
      arrId: arrId,
    }));
    setErrorMessage("");
  }

  function handleChange(e) {
    setAddressForm({
      ...addressForm,
      [e.target.name]: e.target.value,
    });
  }

  useEffect(
    function () {
      if (!loggedIn) return;

      async function getAddressDetails() {
        try {
          const response = await fetch(
            `http://localhost:8080/getAddress/${localStorage.getItem(
              "userId"
            )}`,
            {
              method: "GET",
              headers: {
                "Content-type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("userToken")}`,
              },
            }
          );

          const data = await response.json();
          setAddress(data);
        } catch (err) {
          console.log(err);
        }
      }

      getAddressDetails();
    },
    [loggedIn, selectedAddress]
  );

  async function handleAddressSubmit(e) {
    e.preventDefault();

    try {
      const response = await fetch(`http://localhost:8080/createNewAddress`, {
        method: "POST",
        headers: {
          "Content-type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("userToken")}`,
        },
        body: JSON.stringify({
          firstName: addressForm.firstName,
          lastName: addressForm.lastName,
          address: addressForm.address,
          city: addressForm.city,
          state: addressForm.state,
          postalCode: Number(addressForm.postalCode),
          phoneNumber: addressForm.phoneNumber,
          userId: localStorage.getItem("userId"),
        }),
      });

      if (response.status === 201) {
        console.log("Hii");
        setSelectedAddress((add) => add - 1);
        //setAddressForm(initialState);
      }
    } catch (err) {
      console.log(err);
    }
  }

  return (
    <>
      <section className={styles.addressGrid}>
        <div className={styles.previousAddr}>
          {errorMessage && <p className={styles.error}>{errorMessage}</p>}
          <h3>Choose from below</h3>
          <div className={styles.addressSelector}>
            {address.map((add, i) => (
              <div
                key={i}
                onClick={() => handleAddressSelect(add.addressId, i)}
                className={`${styles["address-option"]} ${
                  selectedAddress.addressId === add.addressId
                    ? styles["address-selected"]
                    : ""
                }`}
              >
                <p>{add.address}</p>
                <p>
                  {add.firstName} {add.lastName}
                </p>
                <p>
                  {add.city} - {add.state} - {add.postalCode}
                </p>
                <p>{add.phoneNumber}</p>
              </div>
            ))}
          </div>
        </div>
        <form className={styles.form}>
          <h3>Fill Your address details below</h3>
          <div>
            <label>First Name:</label>
            <input
              type="text"
              id="firstName"
              name="firstName"
              required
              onChange={handleChange}
            />
          </div>
          <div>
            <label>Last Name:</label>
            <input
              type="text"
              id="lastName"
              name="lastName"
              required
              onChange={handleChange}
            />
          </div>
          <div>
            <label>Address:</label>
            <textarea
              id="address"
              name="address"
              rows="4"
              cols="32"
              required
              onChange={handleChange}
            ></textarea>
          </div>
          <div>
            <label>City:</label>
            <input
              type="text"
              id="city"
              name="city"
              required
              onChange={handleChange}
            />
          </div>
          <div>
            <label>State/Region:</label>
            <input
              type="text"
              id="state"
              name="state"
              required
              onChange={handleChange}
            />
          </div>
          <div>
            <label>Postal Code:</label>
            <input
              type="number"
              id="postalCode"
              name="postalCode"
              required
              onChange={handleChange}
            />
          </div>
          <div>
            <label>Phone Number:</label>
            <input
              type="number"
              id="phoneNumber"
              name="phoneNumber"
              required
              onChange={handleChange}
            />
          </div>
          <div>
            <button type="submit" onClick={handleAddressSubmit}>
              Submit
            </button>
          </div>
        </form>
      </section>
    </>
  );
}

export default DeliveryAddress;
