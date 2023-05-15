import React, { useState } from 'react';
import {Input} from '../../components/index'
import {useHistory } from 'react-router-dom';
import { useGlobalUser, useGlobalAddress } from '../../../context';
import {GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL} from '../../../hooks/auth/use-auth.hook';
import GoogleImg from '../../../assets/icons/google.svg';
import { useAuth, useAddressApi } from '../../../hooks';
import { Logo } from '../../components/logo/logo.component';
import "../login/login.css";

export function LoginScreen() {
    const authApi = useAuth();
    const addressApi = useAddressApi();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [_, setUser] = useGlobalUser('');
    const [address, setAddress] = useGlobalAddress("");
    const [erro,setErro] = useState("");
    const history = useHistory();

    function getGeolocation(address){
        var localizacao = {
          latitude: address.coords.latitude,
          longitude: address.coords.longitude,
        };
        localStorage.setItem("address", JSON.stringify({ ...localizacao }));
        setAddress({ ...localizacao});
    }

    function errorGeolocation (err){
        var localizacao = {
          latitude: -30.027806,
          longitude: -51.226987,
        };
        localStorage.setItem("address", JSON.stringify({ ...localizacao }));
        setAddress({ ...localizacao });
    }

    var options = {
      enableHighAccuracy: true,
      timeout: 5000,
      maximumAge: 0,
    };

    async function handleSubmit(event) {
        event.preventDefault()
        if(!email || !password) {
            setErro("Email ou senha não podem ser vazios")
        }else {
        try {
            const response = await authApi.login(email, password);
            const { token } = response.data;
            setUser(token);
            localStorage.setItem('user', token)
            const isAddress = await addressApi.isAddress(email);
            if( !isAddress.data ){
                navigator.geolocation.getCurrentPosition(
                  getGeolocation,
                  errorGeolocation,
                  options
                );
            }
            history.push("/")
        } catch (ex) {
            const retornoErro = ex.response.data.message;
            setErro(retornoErro)
     }
    }
    }
    function handleRegisterClick() {
        history.push('/cadastrar')
    }
    return (
        <div className="register--box">
            <div className="register--image">
            <img src="https://cdn.discordapp.com/attachments/783014961785733146/783374631070597180/4519420.png" alt="foto"/>
            </div>
        <div className="login--card">
            <Logo/>
                <form onSubmit={handleSubmit} className="login--box">
                    <Input
                        name="txtUsuario"
                        handleChange={setEmail}
                        value={email}
                        id="txtUsuario"
                        type="email"
                        classInput="login--input"
                        placeholder="E-mail"
                    />
                    <Input
                        name="txtSenha"
                        handleChange={setPassword}
                        value={password}
                        id="txtSenha"
                        type="password"
                        classInput="login--input"
                        placeholder="Senha"
                    />
                    <button className="commom-primary--button" >Entrar</button>
                       <div style={{color:"red"}}>{erro}</div>
                </form>
                <div className="social--login">
                    <div>- OU -</div>
                    <a className="facebook--button"  href={FACEBOOK_AUTH_URL}>Fazer login com o Facebook </a>
                    <a href={GOOGLE_AUTH_URL} className="google--button" ><img src={GoogleImg} alt="" className="google--logo"/> Fazer login com o Google </a>
                    <button onClick={handleRegisterClick} className="register--button">Criar nova conta</button>
                </div>
        </div>
        </div>
    );
}
