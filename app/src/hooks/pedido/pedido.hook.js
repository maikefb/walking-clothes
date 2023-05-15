import { useAuthenticated } from '../authentication/use-authentication.hook'

function useApi(axiosInstance) {

    async function buscarPedidos() {
        return axiosInstance.get(`/pedidos`)
    }
    async function realizarPedido(ids, idVendedor) {
        return axiosInstance.post(`/realizar-pedido`,{
            ids,
            idVendedor
        })
    }
    async function cancelarPedido(id) {
        return axiosInstance.delete(`/cancelar-pedido/${id}`)
    }
    async function finalizarPedido(id) {
        return axiosInstance.put(`/finalizar-pedido/${id}`)
    }
    async function removerItemPedido(idItem,idPedido) {
        return axiosInstance.patch(`/remover-item/${idItem}/${idPedido}`)
    }
    async function buscarChatPedido(id) {
        return axiosInstance.get(`/chat/${id}`)

    }
    async function enviarMensagemChat(id , mensagem) {
        return axiosInstance.post(`/chat/${id}` , {mensagem})
    }
    async function pedidosVendas() {
        return axiosInstance.get(`/vendas`)

    }
    async function pedidoPorId(id) {
        return axiosInstance.get(`pedido/buscar/${id}`)

    }
    async function pedidosInativos() {
        return axiosInstance.post(`/pedidos-inativos`)

    }

    return {
        buscarPedidos,
        realizarPedido,
        cancelarPedido,
        finalizarPedido,
        removerItemPedido,
        buscarChatPedido,
        enviarMensagemChat,
        pedidosVendas,
        pedidosInativos,
        pedidoPorId
    }
}

export const usePedidoApi = () => useAuthenticated('', useApi)
