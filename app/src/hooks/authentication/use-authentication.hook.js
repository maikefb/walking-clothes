import { useCallback } from 'react'
import { useAxios } from '../axios/use-axios.hook'
import { useGlobalUser } from '../../context/user/user.context'

export const useAuthenticated = (baseUrl, apiFunctions) => {
    const [user] = useGlobalUser()
    const axiosInstance = useAxios(baseUrl, { authorization: 'Bearer ' + user })

    const functions = apiFunctions(axiosInstance)

    return useCallback({ ...functions }, [])
}
