import createGlobalState from 'react-create-global-state';


const [useGlobalCarrinho, CarrinhoProvider] = createGlobalState();

export { useGlobalCarrinho, CarrinhoProvider }
