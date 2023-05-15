import React, { useEffect ,useState} from "react";
import ComboBox from "../../components/busca-dropbox/combobox.component";
import { Button, Dialog, DialogActions, DialogContent, DialogContentText,TextField} from "@material-ui/core";
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormLabel from '@material-ui/core/FormLabel';
import {Input} from '../../components/index'
import '../cadastro-item/cadastro-item.css'
import AddIcon from '@material-ui/icons/Add';
import {useItemApi} from '../../../hooks/item/item.hook'
import { useArmarioApi } from "../../../hooks/armario/armario.hook";
import { EnderecoScreen } from "../../components/endereco/endereco.component";
import { useUsuarioApi } from "../../../hooks/usuario/usuario.hook";
import { useHistory } from "react-router-dom";
import Autocomplete from '@material-ui/lab/Autocomplete';
import {cor,tipo,tamanhoItem} from '../cadastro-item/enums'

export function CadastroItemScreen() {
  const history = useHistory();
  const usuarioApi = useUsuarioApi();
  const useApi = useItemApi();
  const armarioApi = useArmarioApi();
  const [open, setOpen] = useState(false);
  const [troca, setTroca] = useState("true");
  const [nomeArmario, setNomeArmario] = useState("");
  const [tagArmario, setTagArmario] = useState("");
  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [tag, setTag] = useState("");
  const [imagem, setImagem] = useState("");
  const [medidas, setMedidas] = useState("");
  const [marca, setMarca] = useState("");
  const [corEscolhida, setCorEscolhida] = useState("");
  const [condicao, setCondicao] = useState("N");
  const [idArmario, setIdArmario] = useState();
  const [preco, setPreco] = useState();
  const [endereco, setEndereco] = useState(false);
  const [carregando, setCarregando] = useState(true);
  const [armarios,setArmarios] = useState([])
  const [cadastrou,setCadastrou] = useState(false);
  const [tipoEscolhido,setTipoEscolhido]= useState();
  const [cadastrouEnd,setCadastrouEnd] = useState(false)

  useEffect(() => {
    async function getProduct() {
        const {data} = await usuarioApi.verificarEndereco();
        const response = await useApi.buscarArmariosParaCadastrarItem();
        setEndereco(data.possuiEnderecoCadastrado)
        setArmarios(response.data)
        setCarregando(false);
    }
    getProduct();

  }, [cadastrou, useApi, usuarioApi,cadastrouEnd]);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  async function handleCriarArmario(){
    await armarioApi.cadastrarArmario(nomeArmario,tagArmario)
    setCadastrou(true);
  }
  async function handleDesapegar(){
    await useApi.cadastrarItem(troca,corEscolhida?.value,descricao,condicao,idArmario,imagem,marca,preco,tag,medidas?.title,titulo,tipoEscolhido?.value)
    history.push('/perfil')
    
  }
  const handleChange = (event,func) => {
    func(event.target.value);
  };
  return (
    <> 
    {carregando? "" : !endereco?<div className="endereco--div"> <EnderecoScreen handle={setCadastrouEnd}/> </div>:<><div className="cadastrar-item--box">
      <h1>Desapegar</h1>
      <div className="selecionar--armario">
        <h4>Selecione o armário</h4>
      <div style={{display:"flex"}}>
          </div>
      </div>
    <div className="input--container">
      <div style={{display:"flex"}}>
 <Autocomplete
      id="armarios"
      size="small"
      options={armarios}
      onChange={(e,valor) => setIdArmario(valor?.idArmario)}
      getOptionLabel={(option) => option.nome}
      style={{ width: 200 }}
      renderInput={(params) => <TextField {...params} label="Armários" variant="outlined" />}
    />
      <button style={{marginLeft:"20px"}} onClick={handleClickOpen} className="comment--button"><AddIcon/></button>
      </div>
 
      <ComboBox id="tipo" label="Tipo" array={tipo} func={setTipoEscolhido} required/>
      <TextField
          id="titulo"
          label="Título"
          value={titulo}
          onChange={(event) => handleChange(event,setTitulo)}
          variant="outlined"
          required
      />
       <TextField
          id="marca"
          label="Marca"
          value={marca}
          onChange={(event) => handleChange(event,setMarca)}
          variant="outlined"
          required
      />
 <TextField
          id="tag"
          label="Tag Estilo"
          value={tag}
          onChange={(event) => handleChange(event,setTag)}
          variant="outlined"
          required
      />
      <ComboBox id="tamanho"  label="Tamanho" array={tamanhoItem} func={setMedidas} required/>
      <ComboBox id="cor" label="Cor" array={cor} func={setCorEscolhida} required/>
         <TextField
          id="preco"
          label="Preço"
          value={preco}
          onChange={(event) => handleChange(event,setPreco)}
          variant="outlined"
          type="number"
          required
      />
         <TextField
          id="url-image"
          label="Imagem URL"
          value={imagem}
          onChange={(event) => handleChange(event,setImagem)}
          variant="outlined"
          required
      />
      <TextField  required id="descricao" label="Descrição" multiline  value={descricao} onChange={(event) => handleChange(event,setDescricao)}/>
       
<RadioGroup row aria-label="troca" name="troca" value={troca} onChange={(event) => handleChange(event,setTroca)}>
    <FormControlLabel value="true" control={<Radio />} label="Aceita troca" />
    <FormControlLabel value="false" control={<Radio />} label="Não Aceita Troca" />

  </RadioGroup>
       <FormLabel component="legend">Estado de Uso </FormLabel>
  <RadioGroup row aria-label="estado" name="estado" value={condicao} onChange={(event) => handleChange(event,setCondicao)}>
    <FormControlLabel value="N" control={<Radio />} label="Novo" />
    <FormControlLabel value="S" control={<Radio />} label="Semi-novo" />
    <FormControlLabel value="U" control={<Radio />} label="Com marcas de uso" />
  </RadioGroup>
    <button onClick={handleDesapegar} className="desapegar--button">Desapegar</button>
    </div>
    </div>
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogContent>
          <DialogContentText>
            Preencha os campos para criar um armário
          </DialogContentText>
          <Input
            name="Imagem Url"
                        handleChange={setNomeArmario}
                        value={nomeArmario}
                        id="nomeArmario"
                        type="text"
                        classInput="login--input"
                        placeholder="Nome do armário"
          />
          <Input
                        name="tag"
                        handleChange={setTagArmario}
                        value={tagArmario}
                        id="tag"
                        type="text"
                        classInput="login--input"
                        placeholder="Tag de estilo"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} >
            Cancelar
          </Button>
          <Button onClick={()=> {handleCriarArmario(); handleClose()}} >
            Adicionar
          </Button>
        </DialogActions>
      </Dialog>
</>}
    </>
  );
}
