import createGlobalState from "react-create-global-state";

const radius = localStorage.getItem("radius");

const [useGlobalRadius, RadiusProvider] = createGlobalState(radius);

export { useGlobalRadius, RadiusProvider };
