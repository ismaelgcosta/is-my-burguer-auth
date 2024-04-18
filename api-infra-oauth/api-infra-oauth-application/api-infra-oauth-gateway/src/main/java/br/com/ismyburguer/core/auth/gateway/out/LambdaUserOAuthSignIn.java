package br.com.ismyburguer.core.auth.gateway.out;


import br.com.ismyburguer.core.auth.entity.Login;
import br.com.ismyburguer.core.auth.entity.UserToken;
import feign.Headers;
import feign.RequestLine;

public interface LambdaUserOAuthSignIn {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /user/token")
    UserToken signin(Login input);
}
