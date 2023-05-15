import React, { useState } from 'react'
import '../pedido/pedido.css'
import {Input} from '../../components/input/input.component'
import { useUsuarioApi } from '../../../hooks/usuario/usuario.hook';


export  function EnderecoScreen({handle}) {
    const [cep, setCep] = useState();
    const [numero,setNumero] = useState();
    const [complemento,setComplemento] = useState();
    const useApi = useUsuarioApi();

 async function handleCadastrarEndereco(){
        await useApi.cadastrarEndereco(cep,numero,complemento);
        handle(true)
  }

  return (
      <>
   <div>
   <h3>Cadastre um endereço para continuar</h3>
    <Input name="Imagem Url" handleChange={setCep}value={cep}id="tag"type="text" classInput="login--input"placeholder="Cep"/>
    <Input name="Imagem Url" handleChange={setNumero}value={numero}id="medidas"type="text" classInput="login--input"placeholder="Numero"/>
   <Input name="Imagem Url" handleChange={setComplemento}value={complemento}id="imagem"type="text" classInput="login--input"placeholder="Complemento"/> </div>
    <button className="desapegar--button" onClick={handleCadastrarEndereco}>Cadastrar</button>
    </>
  );
}
