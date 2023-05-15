import React, { useEffect, useState } from "react";
import '../dashboard/dashboard.css'
import { Armario } from "../../components/armario/armario.component";
import { useItemApi } from '../../../hooks';
import { useGlobalAddress } from "../../../context";
import Typography from '@material-ui/core/Typography';
import Slider from '@material-ui/core/Slider';
import Pagination from "@material-ui/lab/Pagination";

export function DashBoard() {
    const api = useItemApi();

  const [address, setAddress] = useGlobalAddress("");
  const [armarios,setArmarios] = useState({})
  const [carregando,setCarregando] = useState(true)
  const [raio,setRaio] = useState(30);
  const [page, setpage] = useState(0);

  function getGeolocation(address) {
    var localizacao = {
      latitude: address.coords.latitude,
      longitude: address.coords.longitude,
    };
    localStorage.setItem("address", JSON.stringify({ ...localizacao }));
    setAddress({ ...localizacao });
  }

  function errorGeolocation(err) {
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

  useEffect(() => {
    window.scrollTo(0, 0)
    async function getArmarios() {
      const { data } = await api.buscarArmarios(
        raio,
        address.latitude,
        address.longitude,
        page
      );
      setArmarios(data);
      setCarregando(false);
    }
    getArmarios();
  }, [raio, api, page, address.latitude, address.longitude]);

  useEffect(() => {
     async function verificaEndereco() {
      if (localStorage.getItem("address") == null) {
        navigator.geolocation.getCurrentPosition(
          getGeolocation,
          errorGeolocation,
          options
        );
      }
    }
    verificaEndereco();
  }, [])

 function handleOnChangePage(event, value) {
   setpage(value - 1);
 }

function handleOnChangeRadius(_, value) {
  setRaio(value);
}

  return (
    <>
      {carregando ? (
        ""
      ) : (
        <div className="container">
          <div className="dashboard--container">
            <div className="titulo--dashboard">
              <h1>Confira os armários mais próximos de você</h1>
            </div>
            <div style={{ width: "300px", color: "white" }}>
              <Typography style={{color:"black"}} id="discrete-slider" gutterBottom>
                Produtos próximos no raio de {raio} km
              </Typography>
              <Slider
                defaultValue={30}
                onChangeCommitted={handleOnChangeRadius}
                aria-labelledby="discrete-slider"
                valueLabelDisplay="auto"
                step={10}
                marks
                min={10}
                max={110}
              />
            </div>
            {armarios.itens?.map((a) => (
              <Armario key={a.idArmario} armario={a} />
            ))}

            <div className="page--dashboard">
              <Pagination
                size="small"
                page={page + 1}
                count={armarios.totalPages}
                variant="outlined"
                color="secondary"
                onChange={handleOnChangePage}
              />
            </div>
          </div>
        </div>
      )}
    </>
  );
}
