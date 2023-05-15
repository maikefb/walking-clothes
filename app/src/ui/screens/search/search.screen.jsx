
import React, { useEffect, useState } from "react";
import ComboBox from "../../components/busca-dropbox/combobox.component";
import { Input } from "../../components/index";
import '../search/search.css'
import { ItemCard } from "../../components/item-card/item-card.component";
import { useGlobalAddress } from "../../../context";
import { useParams } from "react-router-dom";
import { useItemApi } from "../../../hooks/item/item.hook";
import Typography from '@material-ui/core/Typography';
import Slider from '@material-ui/core/Slider';
import Pagination from "@material-ui/lab/Pagination";
import {estadoUso,cor,tamanhoItem,tipo} from '../cadastro-item/enums'
export function SearchScreen() {

const {busca} = useParams();
const api = useItemApi();
const [itens,setItens] = useState([]);
const [address, setAddress] = useGlobalAddress("");
const [estadoPesquisa,setEstado] = useState(null);
const [corPesquisa,setCor] = useState(null);
const [tipoPesquisa,setTipo] = useState(null);
const [tamanho,setTamanho] = useState(null);
const [precoInicial, setPrecoInicial] = useState("0");
const [preco, setPreco] = useState(1000);
const [page, setpage] = useState(0);
const[raio,setRaio] = useState(30);

  function getGeolocation(address) {
    var localizacao = {
      latitude: address.coords.latitude,
      longitude: address.coords.longitude,
    };
    localStorage.setItem("address", JSON.stringify({ ...localizacao }));
    localStorage.setItem("radius", raio);
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


  function teste(_, value) {
    setRaio(value);
  }

  useEffect(() => {
    async function getProduct() {
     const {data}  = await api.buscarItem(
        busca,
        -30.025134,
        -51.20282,
        page,
        raio
      )
     setItens(data)
    }

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
    getProduct();
  }, [api, busca]);

  useEffect(()=>{
    async function getItens() {
        handleOnClick()
    }
    getItens()
  },[page])


    async function handleOnClick() {
      const { data } = await api.buscarItem(
        busca,
        address.latitude,
        address.longitude,
        page,
        raio,
        tipoPesquisa?.value,
        corPesquisa?.value,
        estadoPesquisa?.value,
        tamanho?.value,
        precoInicial?.value,
        preco?.value
      );
      setItens(data);
      }
      
      function handleOnChangePage(event,value){
      setpage(value -1);
      }
  return (
    <>
      <div className="busca-itens--container">
        <div className="busca--container">
          <p>Resultado da busca</p>

          <h1 className="busca--titulo">{busca}</h1>
          <div className="combo-box--div">
            <div className="item--line">
              <span className="name--input">Estado:</span>
              <ComboBox label="Estado" array={estadoUso} func={setEstado} />
            </div>
            <div className="item--line">
              <span className="name--input">Cor:</span>
              <ComboBox label="Cor" array={cor} func={setCor} />
            </div>
            <div className="item--line">
              <span className="name--input">Tipo:</span>
              <ComboBox label="Tipo" array={tipo} func={setTipo} />
            </div>
            <div className="item--line">
              <span className="name--input">Tamanho:</span>
              <ComboBox label="Tamanho" array={tamanhoItem} func={setTamanho} />
            </div>
            <div className="item--line">
              <span className="name--input">Preço Inicial:</span>
              <Input
                value={precoInicial}
                type="number"
                handleChange={setPrecoInicial}
                classInput="busca--input"
              />
            </div>
            <div className="item--line">
              <span className="name--input">Preço Final:</span>
              <Input
                value={preco}
                type="number"
                handleChange={setPreco}
                classInput="busca--input"
              />
            </div>
            <div
              className="item--line item--slider"
              style={{ width: "90%" }}
            >
              <Typography
                id="discrete-slider"
                gutterBottom
                
              >
                Distância:

              </Typography>
              <Slider
                className="input--slider"
                defaultValue={30}
                onChangeCommitted={teste}
                aria-labelledby="discrete-slider"
                valueLabelDisplay="auto"
                step={10}
                marks
                min={10}
                max={110}
              />
            </div>
            <button className="buscar--button" onClick={handleOnClick}>Aplicar</button>
          </div>
        </div>

        <div className="itens--container-right">
          {itens.itens?.map((item) => (
            <ItemCard key={item.idItem} item={item} />
          ))}
        </div>
        <div className="page--item">
          {itens.itens?.length > 0 ?
          <Pagination
            size="small"
            page={itens.currentPage + 1}
            count={itens.totalPages}
            variant="outlined"
            color="secondary"
            onChange={handleOnChangePage}
          
          /> : "Não foi encontrado nenhum resultado"}
        </div>
      </div>
    </>
  );

}