import React, { useState } from 'react'
import '../pedido/pedido.css'
import {Input} from '../../components/input/input.component'
import ArrowForwardIcon from '@material-ui/icons/ArrowForward';
import { useEffect } from 'react'
import {usePedidoApi} from '../../../hooks/pedido/pedido.hook'
import { useUsuarioApi } from '../../../hooks';
import '../chat/chat.css'
let client = null
export default function Chat({id}) {
    const [chat,setChat] = useState({});
    const[mensagem,setMensagem] = useState('');
    const [carregando,setCarregando] = useState(true)
    const useApi = usePedidoApi();
    const useUserApi = useUsuarioApi();
    const [perfil,setPerfil] = useState({})

  useEffect(() => {
        async function getChat(){
           const {data} =  await useApi.buscarChatPedido(id)
           setChat(data)
           setCarregando(false)
        }
       getChat();
    }, [useApi,id])

     useEffect(() => {
        async function getPerfil(){
           const {data} =  await useUserApi.buscarInfoPerfil()
           setPerfil(data)
        }
       getPerfil();
    }, [])

    useEffect(() => {

    const Stomp = require('stompjs')
    var SockJS = require('sockjs-client')
    SockJS = new SockJS('https://walking-clothes.cwi.com.br/api/websocket-chat/')
    client = Stomp.over(SockJS)
		client.connect({}, onConnected, onError)
    }, [])

	function onConnected(){
    client.subscribe(`/topic/user`, onMessageReceived)
  }

  function sendMessage() {
		client.send('/app/user-all',{},JSON.stringify({
            message: mensagem,
      idChat: id,
    idUsuario:perfil.idUsuario}
			))
			setMensagem('')
}
    function onError() {
        console.log('Erro ao conectar no ws')
    }
    function onMessageReceived(message) {
     const mensagensRecebidas = JSON.parse(message.body)
     setChat(mensagensRecebidas)
}
  return (
      <>
  <div className="conversas--div">
  <div className="conversa--pedido-div"> PEDIDO {chat.idPedido}</div>
        <div className="mensagens--div">
        <div>
        {carregando? "" : chat.mensagens?.map((mensagem,index) => <div key={index} className={`${mensagem?.remetente.idUsuario === perfil.idUsuario ? "mensagem--box--sender" : "mensagem--box"}`} >
          <img src={mensagem?.remetente?.fotoPerfil} className="messagem-foto" alt=""/>
        <p className="mensagem--card">{mensagem.mensagem} </p>
        </div>)}

        </div>
      </div>
      <div className="comment-input--box mensagem-texto-box">

            <img className="comment-profile--icon" src={perfil.fotoPerfil} alt=""/>
            <Input value={mensagem} handleChange={setMensagem} placeholder="Digite uma mensagem" classInput="mensagem-input-box"/>
            <button className="send--button" onClick={sendMessage}><ArrowForwardIcon/></button>   
          </div>
      </div>
    </>
  );
}