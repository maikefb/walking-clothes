import React, { useEffect, useState } from "react";
import userSvg from '../../../assets/icons/user-pink.svg'
import map from '../../../assets/icons/map.svg'
import troca from '../../../assets/icons/troca.svg'
import naoTroca from '../../../assets/icons/nao-troca.svg'
import "./product.css";
import {Input} from '../../components/input/input.component'
import ArrowForwardIcon from '@material-ui/icons/ArrowForward';
import StarBorderIcon from '@material-ui/icons/StarBorder';
import StarIcon from '@material-ui/icons/Star';
import { useHistory, useParams } from "react-router-dom";
import {useItemApi, useUsuarioApi} from '../../../hooks/index'
import {usePedidoApi} from '../../../hooks/pedido/pedido.hook'
import { Comentario } from "../../components/comentario/comentario.component";
import { useGlobalUser } from "../../../context/user/user.context";

export function ProductScreen() {
  const [item,setItem] = useState({});
  const [user] = useGlobalUser();
  const [comentarios,setComentarios] = useState([]);
  const [perfil,setPerfil] = useState({});
  const [idPerfil,setIdPerfil] = useState();
  const [enviouComentario,setEnviouComentario] = useState(false);
  const [carregando,setCarregando] = useState(true);
  const [favorito,setFavorito] = useState(false)
  const [comentario,setComentario] = useState('');
  const history = useHistory();
  const {id} = useParams();
  const apiUser = useUsuarioApi();
  const api = useItemApi();
  const pedidoApi = usePedidoApi();

  useEffect(() => {
    async function getProduct() { 
      const {data} = await api.buscarItemPorId(id)
      setItem(data)
      setFavorito(data.favorito)
      setCarregando(false)
    }
    getProduct();
  }, [id,favorito,api]);

useEffect(() => {
    async function getComentarios() { 
      const {data} = await api.buscarComentariosDoItem(id)
      setComentarios(data)

      setEnviouComentario(false)
    }
    getComentarios();
  }, [enviouComentario,api,id]);
  
useEffect(() => {
    async function getPerfil() { 
      if(user){
      const {data} = await apiUser.buscarInfoPerfil();
      setIdPerfil(data.idUsuario);
      setPerfil(data)
      }else {
        setPerfil({fotoPerfil:"https://media.discordapp.net/attachments/438500690491342861/787154328322310224/imagem_logo.png"})
      }
    }
    getPerfil();
  }, [apiUser, user]);

  async function handleFavoritar(){
    try{
        await api.adicionarAosFavoritos(id)
        setFavorito(!favorito)
    }catch (erro){
      history.push('/login')
    }
      
  }
  async function handleQueroPraMim(){
      await pedidoApi.realizarPedido([id], item.idVendedor)
      history.push("/pedidos")
  }
  async function handleEnviarComentario(){
    await api.comentarItem(id,comentario);
    setComentario('')
    setEnviouComentario(true)
  }
  function handleEditarItem(){
  }
  return (
    <>
    {carregando? "" : 
      <div className="container">
        <div className="product-armario--header">
        <div className="product-armario--info">
  <h2>{item.armario}</h2>  
        </div>
      <div className="product-armario--dono">
  <p> <img src={userSvg} alt=""/>{item.vendedor}</p>
            <p><img src={map} alt=""/>{item.cidade}</p>
          </div> 
      </div>
    <div className="product--container">
      <div>
        <div className="product--image"><img src={item.imagens[0]} alt=""/> </div>
          <div className="item--images">
          </div>
      </div>
      <div className="product--about">
        <div className = "product-about-header">
          <div className="product--name">
             <p>{item?.tagsEstilos.map(estilo => estilo +" /")} {item.cor}</p>
  <h3>{item?.titulo}</h3>
  <p>{item.marca}</p>
          </div>
          {!favorito? <StarBorderIcon onClick={handleFavoritar} fontSize="large"/> : <StarIcon onClick={handleFavoritar} style={{ color:"#FF304F" }}  fontSize="large"/>}  
         </div>
        <div>
      <div className="product--price">
  <div className="produto--troca">{item.aceitaTroca? <><img src={troca} alt=""/> <p>Aceita Troca</p></> : <><img src={naoTroca} alt=""/> <p> Não Aceita Troca</p></> }</div>
  <div> <h2>R$ {item?.preco.toFixed(2)}</h2></div>
      </div>
  <p className="product--disponibility"> {item.status === "Disponível"?
  <span className="dot green--dot"></span> : item.status === "Negociação"? 
  <span className="dot orange--dot"></span> : 
  <span className="dot red--dot"></span> } {item.status}</p>
        </div>
        {idPerfil ===  item.idVendedor ? <button className="product-main--button" onClick={handleEditarItem}>EDITAR ITEM</button> : item.status === "Disponível"? <button className="product-main--button" onClick={handleQueroPraMim}>QUERO PARA MIM</button>: <button className="product-main--button" disabled >INDISPONÍVEL</button>}

        <div style={{display:"flex",justifyContent:"center"}}>
        <div className="tabela">
        <div className="tabela-cima">
            <div className="texto">
                <p>Tamanho</p>
    <p>{item?.tamanho}</p>
            </div>
            <span className="table--line"></span>
            <div className="texto">
                <p>Condição</p>
              <p>{item.estadoUso}</p>
            </div>
        </div>
    </div>
</div>
<div className="produto--descricao">
  <p>Descrição</p>
  <p>{item.descricao}</p>

</div>
      </div>
      </div>
      <div className="product--bottom">
        <div className="product-profile--box">
        </div>
        <div className="comment--box">
          <div className="comment-input--box">
            <img className="comment-profile--icon" src={perfil.fotoPerfil} alt=""/>
            <Input placeholder="Escreva um comentário..." classInput="comment--input" value={comentario} handleChange={setComentario}/>
            <button className="comment--button" onClick={handleEnviarComentario}><ArrowForwardIcon/></button>
            </div>
            <div className="comentarios--section">
              {comentarios?.map(c =>  <Comentario key={c.id} perfil={perfil} idPerfil={idPerfil} comentario={c}  idVendedor={item.idVendedor}/>  )}
              </div>  
        </div>
      </div>
      </div>
}
    </>
  );
}
