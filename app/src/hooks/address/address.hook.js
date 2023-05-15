import { useAuthenticated } from '../authentication/use-authentication.hook'

function useApi(axiosInstance) {

  async function isAddress(email) {
    return axiosInstance.get(`/endereco?email=${email}`);
  }
  return{
      isAddress
  }
}
export const useAddressApi = () => useAuthenticated("", useApi);
