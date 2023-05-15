import React from 'react'
import '../pedido/pedido.css'

export default function PedidoCard({pedido, handle}) {
  function handleClick(){
      handle(pedido?.idChat,pedido?.idPedido)
  }
  return (
      <>
      <div className="pedido--card" onClick={handleClick}>
        <h2>Pedido {pedido?.idPedido}</h2>
        </div>
    </>
  );
}