import React, { useState,useEffect} from "react";
import "../header/header.css";
import Logon from '../../../assets/icons/Logon.svg'
import Carrinho from '../../../assets/icons/carrinho.svg'
import User from '../../../assets/icons/user.svg'
import { IconButton, Input, InputAdornment} from "@material-ui/core";
import { Search } from "@material-ui/icons";
import { Link, useHistory } from "react-router-dom";
export function Header({cadastrar}) {
  const [busca,setBusca] = useState("");
  const [logado,setLogado] = useState(false);
  const history = useHistory();

useEffect(() => {
  const test = localStorage.getItem('user')
  function logar(){
    if(test){
      setLogado(true)
    }
  }
logar()
}, [])

  async function handleLogOut() {
    localStorage.removeItem("user");
    history.push('/login')
  }
  function handleBuscar(){
    history.push(`/busca=${busca}`)
    setBusca('')
  }
  function handleChange(e){
      setBusca(e.target.value)
  }
  function handleClickEntrar(){
    history.push('/login')
  }
  return (
    <div className="header">
      <div className="logo--header">
            <Link to="/"><img src={Logon} alt="" className="header-logo--image"/>   </Link>
            </div>
            <div className="header--icons">
          <div className="search--header">
  <Input onChange={handleChange} value={busca} placeholder="Buscar" endAdornment={
  <InputAdornment position="end">
  <IconButton aria-label="" onClick={handleBuscar}>
  <Search/>
</IconButton>
  </InputAdornment>
} />
        </div>
        {!logado?<> <button className="header--button" onClick={handleClickEntrar}>Entrar</button></> :<>
          <div className="header--util" >
           <nav className="nav--perfil">
         <label htmlFor="btn2" className="button"> <img src={User} alt=""className="header--icon"/> <span className="fas fa-caret-down"></span></label>
         <input type="checkbox" id="btn2" className="input--click"/>
          <ul className="menu">
          <li><Link to="/perfil"> Perfil</Link> </li>
            <li> <Link to="/favoritos"> Favoritos</Link></li>
            <li><button className="sair--header"  onClick={handleLogOut}> Sair</button></li>
          </ul>
          </nav>
        <div><Link to="/pedidos"><img src={Carrinho} alt="" className="header--icon"/></Link></div>
          <button className="header--button" onClick={()=> history.push('/cadastrar-item')}>Desapegar</button>
        <div>
    </div>
    </div>
        </> }</div>
    </div>
  );
}
