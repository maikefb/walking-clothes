import { useAuthenticated } from '../authentication/use-authentication.hook'

function useApi(axiosInstance) {

    async function cadastrarArmario(nome, tagEstilo) {
        return axiosInstance.post(`cadastrar/armario`, {
            nome,
            tagEstilo
        })
    }
    async function buscarArmarioPorId(id) {
        return axiosInstance.get(`/public/armario/${id}`)
    }
    async function buscarArmarioPorNome(nome,pagina) {
        return axiosInstance.get(`/public/armario/${nome}/${pagina}`)
    }
    async function editarArmario(id,nome,tagDeEstilo) {
        return axiosInstance.put(`editar/armario/${id}`, {
            nome,
            tagDeEstilo,
        })
    }
    async function buscarArmarioPorIdPaginado(idUsuario, pagina) {
        return axiosInstance.get(`/public/armario/${idUsuario}/${pagina}`)
    }
    async function buscarFavoritos() {
        return axiosInstance.get(`favoritos/`)
    }

    return {
        cadastrarArmario,
        buscarArmarioPorId,
        buscarArmarioPorNome,
        editarArmario,
        buscarFavoritos,
        buscarArmarioPorIdPaginado

    }
}

export const useArmarioApi = () => useAuthenticated('', useApi)
