import {Input} from '../input/input.component'
import React, { useState,useEffect } from 'react'
import '../comentario/comentario.css'
import { useItemApi } from '../../../hooks';
import { useGlobalUser } from '../../../context/user/user.context';
import { useHistory } from 'react-router-dom';
import like from '../../../assets/icons/like.png'
import liked from '../../../assets/icons/liked.png'

export  function Comentario({comentario, idVendedor, idPerfil}) {
const api = useItemApi()
    const [responder,setResponder] = useState(false);
    const [respostaMsg,setRespostaMsg] = useState('');
    const [curtiu,setCurtiu]= useState(false);
    const [user] = useGlobalUser();
    const history = useHistory();
    useEffect(() => {
    function getLike() {
      if(comentario.curtidas?.some(c => idPerfil === c.idUsuario)){
          setCurtiu(true)
      } 
    }
    getLike();
  }, [curtiu]);

    function abrirResposta(){
        setResponder(true)
    }
  async  function responderComentario(){
   verificaLogado()
      await api.responderComentario(comentario.id,respostaMsg);
      setResponder(false)
  }
  async function handleCurtirComentario(){
    verificaLogado()
    await api.curtirComentario(comentario.id)
    setCurtiu(!curtiu)
  }
  function verificaLogado(){
      if(!user){
        history.push('/login')
    }
  }
  return (
      <>
        <div className="comentario--box" >
            <div  className="comentario--box-header">
              <div className="perfil-comentario-info">
          <img src={comentario.fotoPerfil} className="comment-profile--icon" alt=""/>
          {comentario.idUsuario === idVendedor? <p className="vendedor--comentario">VENDEDOR</p>: ""}
          </div>
            <div className="mensagem-texto-div">
            <p className="comentario--p">{comentario.mensagem} </p>
            <div className="botoes--comentario">
            <button className="responder--comentario" onClick={handleCurtirComentario}>{!curtiu?<img src={like} alt=""/> : <img src={liked} alt=""/> } </button> <button className="responder--comentario" onClick={abrirResposta}>Responder</button>
            </div>
            <div>{responder? <> <Input handleChange={setRespostaMsg} value={respostaMsg} />  <button onClick={responderComentario}> Enviar</button></>: ""}</div>
            </div>
               </div>
        </div>
    </>
  );
}
