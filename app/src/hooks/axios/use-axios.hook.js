import axios from 'axios'

export function useAxios(contextUrl, headers) {
    const baseUrl = contextUrl ? `/${contextUrl}` : ''

    return axios.create({
        baseURL: `https://walking-clothes.cwi.com.br/api${baseUrl}`,
        headers
    })
}
