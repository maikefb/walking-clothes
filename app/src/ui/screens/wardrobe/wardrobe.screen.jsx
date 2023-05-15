import React, { useEffect, useState } from "react";
import { ItemCard } from "../../components/item-card/item-card.component";
import user from '../../../assets/icons/user-pink.svg'
import {useItemApi} from '../../../hooks/index'
import '../wardrobe/armario.css'
import { useParams } from "react-router-dom";
export function WardrobeScreen() {
  const {id} = useParams();
  const [armario,setArmario] = useState({})
  const api = useItemApi();
  useEffect(() => {
    async function getItens() {
      const {data} = await api.buscarArmario(id);
      setArmario(data)
    }
    getItens();
  }, [api, id]);
  return (
    <>
    <div className="container">
      <div className="armario--container">
      <div className="product-armario--header">
        <div className="product-armario--info">
  <h2>{armario.nome}</h2>  
  <p className="wadrobe--tag">{armario.tagDeEstilo}</p>
        </div>
      <div className="product-armario--dono">
  <p> <img src={user} alt=""/> {armario.nomeVendedor}</p>
          </div> 
      </div>
      <div className="itens--container">
        {armario.itens?.map( i => <ItemCard key={i.idItem}  item={i} wardrobe/>)}
      </div>
      </div>
    </div>
    </>
  );
}
