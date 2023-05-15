import React, { useEffect, useState } from "react";
import { Armario } from "../../components/armario/armario.component";
import '../profile/profile.css'
import {useUsuarioApi} from '../../../hooks/usuario/usuario.hook'

export function ProfileScreen() {
  const [perfil,setPerfil] = useState([]);
  const [carregando,setCarregando] = useState(true)
  const useApi = useUsuarioApi();

  useEffect(() => {
    async function getPerfil() {
      const {data} = await useApi.buscarPerfil(0);
      setPerfil(data)
      setCarregando(false)
    }
    getPerfil();
  }, [useApi]);
  return (
    <>
    {carregando? "" :
    <div>
       <div className="container">
      <div className="product-armario--header">
        <div className="profile--header">
        <div >
          <img className="profile--image" src={perfil.fotoPerfil} alt=""/>
        </div>
        <div >
  <h2 className="margin--zero">{perfil.nome}</h2>
          <div style={{display:"flex"}}><p className="margin--zero">{perfil.qtdItensVendidos} itens vendidos</p>
          <p className="margin--zero">{perfil.qtdItensAVenda} itens à venda</p>
          </div>
        </div>
        </div>
      </div>
  {perfil.armarios?.length > 0 ? <> {perfil.armarios?.map(a => <Armario key={a.idArmario} profile armario={a}/>)} </> : <> <p>Você não possui armários cadastrados ainda</p> </>}
    </div>
    </div>}
    </>
  );
}
