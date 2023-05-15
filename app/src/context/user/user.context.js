import createGlobalState from 'react-create-global-state';

const user = localStorage.getItem('user');

const [useGlobalUser, UserProvider] = createGlobalState(user);

export { useGlobalUser, UserProvider }
