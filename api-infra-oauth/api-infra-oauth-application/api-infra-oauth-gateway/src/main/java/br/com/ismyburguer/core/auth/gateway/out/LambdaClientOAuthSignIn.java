package br.com.ismyburguer.core.auth.gateway.out;


import br.com.ismyburguer.core.auth.entity.ClientCredentialsLogin;
import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface LambdaClientOAuthSignIn {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /auth/token")
    Map<String, Object> token(ClientCredentialsLogin input);
}
