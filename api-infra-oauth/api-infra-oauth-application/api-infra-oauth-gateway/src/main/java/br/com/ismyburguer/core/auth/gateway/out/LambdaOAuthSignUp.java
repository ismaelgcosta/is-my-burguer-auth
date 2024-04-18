package br.com.ismyburguer.core.auth.gateway.out;


import br.com.ismyburguer.core.auth.gateway.out.request.SignUpRequest;
import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface LambdaOAuthSignUp {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /user/sign-up")
    Map<String, Object> signUp(SignUpRequest input);
}
