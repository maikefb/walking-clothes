import React, { useState } from "react";
import { Input } from "../../components/index";
import "./register.css";
import {useAuth} from '../../../hooks/auth/use-auth.hook'
import { useHistory } from "react-router-dom";
import { useGlobalUser } from '../../../context/user/user.context';

export function RegisterScreen() {
  const [name, setName] = useState("");
  const [aniversario, setAniversario] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
   const [user, setUser] = useGlobalUser('');
  const useApi = useAuth()
  const history = useHistory();

  async function handleSubmit(event) {
  event.preventDefault();
    try {
      await useApi.register(email, password, name, aniversario)
      const response = await useApi.login(email, password);
      const { token } = response.data;
      setUser(token);
      localStorage.setItem('user', token)
      history.push("/")
    } catch (ex) {}
  }
  return (
    <div className="register--box">
      <div className="register--image">
        <img
          src="https://cdn.discordapp.com/attachments/783014961785733146/783374631070597180/4519420.png"
          alt=""
        />
      </div>
      <div className="register--card">
        <h1>Crie uma nova conta</h1>
        <form onSubmit={handleSubmit} className="login--box">
          <Input
            label="Nome: "
            name="NomeCompleto"
            handleChange={setName}
            value={name}
            id="NomeCompleto"
            type="text"
            classInput="login--input"
            placeholder="Nome Completo"
          />
          <Input
            label="E-Mail: "
            name="Email"
            handleChange={setEmail}
            value={email}
            id="Email"
            type="email"
            classInput="login--input"
            placeholder="E-mail"
          />
          <Input
            name="Aniversario"
            label="Data de nacimento: "
            handleChange={setAniversario}
            value={aniversario}
            id="Aniversario"
            type="date"
            classInput="login--input"
          />
          <Input
            label="Senha: "
            name="txtSenha"
            handleChange={setPassword}
            value={password}
            id="txtSenha"
            type="password"
            classInput="login--input"
            placeholder="Senha"/>
          <button className="commom-primary--button" >Cadastrar</button>
        </form>
      </div>
    </div>
  );
}
