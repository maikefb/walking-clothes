import React from "react";
import user from '../../../assets/icons/user-pink.svg'
import map from '../../../assets/icons/map.svg'
import { useHistory,Link } from "react-router-dom";
export function Armario({armario, profile}) {
  const history = useHistory();


  function handleOnClick(){
    history.push(`/armario/${armario.idArmario}`)
  }
  return (
    <> 
      <div className="armario--div">
      <div className="armario--header">
        <div className="armario--info">
          <div className="product-armario--info">
      <h2>{armario?.nome}</h2>  
        </div>
        </div>
      <div className="product-armario--dono">
       <p> <img src={user} alt=""/> {armario?.nomeVendedor}</p>
        <p> {profile ? "" : <><img src={map} alt=""/>{armario?.cidadeVendedor} | {armario?.distanciaVendedorUsuario?.toFixed(1)} km de distância </>}</p>
      </div> 
      </div>
      <div className="armario--button">
          <h4>{armario?.qtdPecasDisponiveis} itens</h4>
          <button className="armario-button" onClick={handleOnClick}>Ver armário</button>
        </div> 
      <div className="armario--roupas">
        {armario?.itens?.map((i,index) =>  <Link key={index} to={`p/${i.idItem}`}><img  key={index} className="armario--roupa" src={profile? i.imagens[0] : i.imagens[0].urlImagem} alt="roupa"/></Link>)
        }
      </div>
      </div>
    </>
  );
}
