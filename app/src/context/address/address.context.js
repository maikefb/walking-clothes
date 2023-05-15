import createGlobalState from "react-create-global-state";

const stringifyAddress = localStorage.getItem("address");

const address = JSON.parse(stringifyAddress) || {}

const [useGlobalAddress, AddressProvider] = createGlobalState(address);

export { useGlobalAddress, AddressProvider };
