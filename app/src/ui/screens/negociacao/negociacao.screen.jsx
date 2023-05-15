import React, { useEffect, useState } from "react";
import '../negociacao/negociacao.css'
import {usePedidoApi} from '../../../hooks/pedido/pedido.hook'
import PedidoCard from "../../components/pedido/pedido-card.component";
import Chat from "../../components/chat/chat.component";

export function NegociacaoScreen() {
const [pedidos,setPedidos] = useState([]);
const[idChat,setIdChat] = useState();
const [carregando,setCarregando] = useState(true);
const[aberto,setAberto] = useState(false);
const [idPedido,setIdPedido] = useState();
const [pedidoAtivo,setPedidoAtivo] = useState(true);
const [vendas,setVendas] = useState({});
const[compras,setCompras] = useState(true);

  const useApi = usePedidoApi();
  const [pedido,setPedido] = useState({});
  useEffect(() => {
    async function getProduct() {
      const {data} = await useApi.buscarPedidos();
      setPedidos(data);
      setCarregando(false)
    }
    getProduct();
  }, [aberto, useApi]);
  useEffect(() => {
    async function getProduct() {
      const {data} = await useApi.pedidosVendas();
      setVendas(data);
      setCarregando(false)
    }
    getProduct();
  }, [aberto,useApi]);

 async function handleClick(id,idPedido){
    const {data} = await useApi.pedidoPorId(idPedido)
    setPedido(data)
    setIdChat(id)
    setIdPedido(idPedido)
    setAberto(true);
  }
  async function handleCancelarPedido(){
    await useApi.cancelarPedido(idPedido)
    setAberto(false)
  }
  function handleClickOnCompras(){
     setCompras(true)
      setAberto(false)
      setPedidoAtivo(true)
  }
  function handleClickOnVendas(){
    setCompras(false)
      setAberto(false)
    setPedidoAtivo(false)
  }
  async function handleFinalizarPedido(){
    await useApi.finalizarPedido(idPedido)
    setAberto(false)
  }
  return (
    <>
    {carregando?  "" : pedidos.length > 0  || vendas.length > 0?
      <div className="chat--container">
        <div className="pedido-box">
          <div className="pedido--status">
            <button onClick={handleClickOnCompras} className={`botao--pedidos ${compras?"botao--selecionado" : ""}`}> Compras</button>
            <button onClick={handleClickOnVendas} className={`botao--pedidos ${!compras?"botao--selecionado" : ""}`} >Vendas</button>
          </div>
          {pedidoAtivo? pedidos.map((p,index) => <PedidoCard key={index} pedido={p}  handle={handleClick}/>) : vendas.map((p, index) => <PedidoCard key={index} pedido={p}  handle={handleClick}/>)}  
        </div>

        {aberto? <> <Chat key={idChat} id={idChat}/>

        <div className="itens--carrinho">
      {pedido.itens?.map( i =>
        <div key={i.idItem} className="item--carrinho">
          <h2>{i.titulo}</h2>
      <img className="item-carrinho--imagem"  src={i.imagens[0]} alt=""/>
   </div>) }
      
  <div className="pedido--info"> <p>Valor do pedido </p>

        <p className="produto--preco">R${pedido.itens[0].preco.toFixed(2)} </p>
  
  {pedidoAtivo? <div>
    <div className="button--container">
    <button className="button--pedido" onClick={handleCancelarPedido}>Quero desistir</button>
    </div>
   </div> : 
   <div>
     <div className="button--container">
<button className="button--cancelar" onClick={handleCancelarPedido}>Cancelar pedido</button>
<button className="button--pedido" onClick={handleFinalizarPedido}>Fechar desapego</button></div> </div> } </div> </div></> : ""}
    </div> : <div className="chat--container">Você ainda não fez nenhum pedido</div>}
    </>
  );
}
