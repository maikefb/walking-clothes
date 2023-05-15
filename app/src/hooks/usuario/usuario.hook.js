import { useAuthenticated } from '../authentication/use-authentication.hook'

function useApi(axiosInstance) {

    async function buscarPerfil(pagina) {
        return axiosInstance.get(`/usuario/meu-perfil/${pagina}`)
    }
    async function buscarPerfilPorId(id,pagina) {
        return axiosInstance.get(`/public/usuario/${id}/${pagina}`)
    }
    async function buscarNotificacoes() {
        return axiosInstance.get(`/notificacoes`)
    }
    async function editarPerfil(nome,email,fotoPerfil,descricao){
        return axiosInstance.put(`/editar-perfil`,{
            nome,
            email,
            fotoPerfil,
            descricao
        })
    }
    async function buscarInfoPerfil(){
        return axiosInstance.get("/usuario/info")
    }

    async function cadastrarEndereco(cep,numero,complemento) {
        return axiosInstance.post(`/cadastrar/endereco`,{
            cep ,
            numero,
            complemento
        })
    }
    async function atualizarEndereco(cep, numero, complemento) {
        return axiosInstance.put(`/editar/endereco`, {
            cep,
            numero,
            complemento
        })
    } async function verificarEndereco() {
        return axiosInstance.get(`/usuario/verificar-possui-endereco`)
    }

    return {
        buscarPerfil,
        buscarPerfilPorId,
        buscarNotificacoes,
        cadastrarEndereco,
        atualizarEndereco,
        editarPerfil,
        verificarEndereco,
        buscarInfoPerfil
    }
}

export const useUsuarioApi = () => useAuthenticated('', useApi)
