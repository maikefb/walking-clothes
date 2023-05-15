import { useAuthenticated } from '../authentication/use-authentication.hook'

function useApi(axiosInstance) {

    async function buscarArmario(id) {
        return axiosInstance.get(`public/armario/${id}`)
    }
    async function buscarArmarios(raio, latitude, longitude, pagePost) {
      return axiosInstance.post(`public/armario`, {
        raio,
        latitude,
        longitude,
        pagePost
      });
    }
    async function buscarArmariosParaCadastrarItem(){
        return axiosInstance.get(`buscar/armarios`)
    }
    async function cadastrarItem(aceitaTroca,cor,descricao,estadoUso,idArmario,imgUrl,marca,preco,tags,tamanho,titulo,tipo){
        return axiosInstance.post(`/cadastrar/item`, { aceitaTroca, cor, descricao, estadoUso, idArmario, "imgUrl" :[imgUrl], marca, preco, "tags":[tags], tamanho, titulo, tipo})
    }
    async function buscarItemPorId(id) {
        return axiosInstance.get(`/public/item/${id}`)
    }
    async function buscarItemPorNome(nomeOuDescricao) {
        return axiosInstance.get(`public/buscar/item/${nomeOuDescricao}`)
    }
    async function buscarComentariosDoItem(id) {
        return axiosInstance.get(`public/comentarios/${id}`)
    }
    async function comentarItem(id, mensagem) {
        return axiosInstance.post(`item/comentario/${id}`, {
            mensagem
        })
    }
    async function responderComentario(id, mensagem) {
        return axiosInstance.post(`responder/comentario/${id}`, {
            mensagem
        })
    }
    async function curtirComentario(id) {
        return axiosInstance.post(`curtir/comentario/${id}`)
    }

    
    async function buscarItem(nome,latitude, longitude, pagePost, raio, tipo, cor, estadoUso, tamanho,precoini, preco, aceitaTroca) {
        return axiosInstance.post(`/item/public/buscar`, {
          nome,
          raio,
          tamanho,
          tipo,
          cor,
          estadoUso,
          precoini,
          preco,
          aceitaTroca,
          latitude,
          longitude,
          pagePost
        });
    }
    async function adicionarAosFavoritos(id) {
        return axiosInstance.post(`favoritos/${id}`)
    }
    return {
        buscarArmario,
        cadastrarItem,
        buscarItem,
        buscarItemPorNome,
        buscarComentariosDoItem,
        comentarItem,
        buscarItemPorId,
        adicionarAosFavoritos,
        buscarArmarios,
        responderComentario,
        curtirComentario,
        buscarArmariosParaCadastrarItem
    }
}

export const useItemApi = () => useAuthenticated('', useApi)
