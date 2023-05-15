import React from "react";
import "./App.css";
import {LoginScreen,ProductScreen,DashBoard,ProfileScreen,RegisterScreen,
  SearchScreen,WardrobeScreen, CadastroItemScreen , NegociacaoScreen,FavoriteScreen} from "./ui/screens";
import { Switch, Route, Redirect } from "react-router-dom";
import { Header } from "./ui/components";
import OAuth2RedirectHandler from "../src/hooks/auth/OAuth2RedirectHandler";
import { useGlobalUser } from "./context/user/user.context";

function PrivateRoute({ path, children }) {
  const [user] = useGlobalUser();

  if (!user) {
    return <Redirect to="/login" />
  }
  return (
    <Route path={path} exact>
      { children}
    </Route>
  )
}

function App() {
  return (
    <div className="App">
      <Switch>
        <Route path="/login" exact>
          <LoginScreen/>
        </Route>
        <Route path="/cadastrar">
          <RegisterScreen/>
        </Route>
        <Route path="/" exact>
          <Header/>
          <DashBoard/>
        </Route>
        <PrivateRoute path="/perfil" exact>
          <Header/>
          <ProfileScreen/>
        </PrivateRoute>
        <Route path="/p/:id">
          <Header/>
          <ProductScreen/>
        </Route>
        <Route path="/busca=:busca">
          <Header/>
          <SearchScreen/>
        </Route>
        <Route path="/armario/:id">
          <Header/>
          <WardrobeScreen/>
        </Route>
        <Route path="/perfil/:nome">
          <Header/>
          <ProfileScreen/>
        </Route>
        <PrivateRoute path="/cadastrar-item" exact>
          <Header cadastrar/>
          <CadastroItemScreen/>
        </PrivateRoute>
        <PrivateRoute path="/pedidos" exact>
          <Header/>
          <NegociacaoScreen/>
        </PrivateRoute>
        <PrivateRoute path="/favoritos" exact>
          <Header/>
          <FavoriteScreen/>
        </PrivateRoute>
        <Route
          path="/oauth2/redirect"
          component={OAuth2RedirectHandler}
        ></Route>
      </Switch>
    </div>
  );
}

export default App;
