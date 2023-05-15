package br.com.cwi.crescer.api.security.oauth2.user;

import br.com.cwi.crescer.api.domain.AuthProvider;
import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else {
            throw new ValidacaoNegocioException("Não foi possível efetuar o registro");
        }
    }
}
