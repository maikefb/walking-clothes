import React, { useEffect, useState } from "react";
import { ItemCard } from "../../components/item-card/item-card.component";
import {useArmarioApi} from '../../../hooks/armario/armario.hook'
import '../wardrobe/armario.css'

export function FavoriteScreen() {
  const [armario,setArmario] = useState([])
  const [carregando,setCarregando] = useState(true)
  const api = useArmarioApi();

  useEffect(() => {
    async function getItens() {
      const {data} = await api.buscarFavoritos();
      setArmario(data)
      setCarregando(false)
    }
    getItens();
  }, [api]);
  return (
    <>
    <div className="main--container">
      <div className="armario--container">
      <div className="product-armario--header">
        <div className="product-armario--info">
  <h2>Favoritos</h2>  

        </div>
      <div className="product-armario--dono">
          </div> 
      </div>
      <div className="itens--container">  
        {carregando? "" : armario.length > 0 ? armario?.map( i => <ItemCard key={i.idItem} item={i} wardrobe/>) : "Você não possui favoritos"}
      </div>
      </div>
    </div>
    </>
  );
}
