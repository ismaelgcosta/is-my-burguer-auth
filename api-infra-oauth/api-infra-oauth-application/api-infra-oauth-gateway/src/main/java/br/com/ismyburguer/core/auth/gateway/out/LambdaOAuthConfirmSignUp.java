package br.com.ismyburguer.core.auth.gateway.out;


import br.com.ismyburguer.core.auth.entity.ConfirmSignUp;
import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface LambdaOAuthConfirmSignUp {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /user/sign-up/confirm")
    Map<String, Object> confirmSignUp(ConfirmSignUp input);
}
