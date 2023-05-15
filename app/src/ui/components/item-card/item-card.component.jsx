import React from "react";
import { useHistory } from "react-router-dom";
import map from "../../../assets/icons/map.svg";
import '../item-card/item.css'
export function ItemCard({ item, wardrobe, pedido , cidade, distancia }) {
  const history = useHistory();

  function handleOnClick() {
    history.push(`/p/${item.idItem}`);
  }
  async function handleExcluirItem(){
  }
  return (
    <>
      <div className="item--card">
        <div className="item--nome">
          <h3>{item.titulo}</h3>
        </div>
        <div>
          <img
            className="item--image"
            src={wardrobe? item?.imagens[0] : item?.imagens[0].urlImagem}
            alt=""
          />
        </div>
        <div className="item--images">
        </div>
        <div className="item--about">
          <div className="about-item--border">
            <h4>Tamanho</h4> <p>{item.tamanho}</p>
          </div>
          <div>
            <h4>Valor</h4> <p>R$ {item?.preco?.toFixed(2)}</p>
          </div>
        </div>
        <div className="item--localization">
          {wardrobe ? (
            ""
          ) : (
            <>
              <img src={map} alt="" />
              <p>{item.cidadeVendedor} - {item.distancia?.toFixed(1)} km de distância</p>
            </>
          )}
        </div>
        {!pedido? <button className="item--button" onClick={handleOnClick}>
          Ver item
        </button> : <button className="item--button" onClick={handleExcluirItem}>
          Excluir item
        </button> }
      </div>
    </>
  );
}
