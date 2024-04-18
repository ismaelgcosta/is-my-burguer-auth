package br.com.ismyburguer.core.auth.gateway.out;


import br.com.ismyburguer.core.auth.entity.UserInfo;
import feign.Headers;
import feign.RequestLine;

public interface LambdaOAuthUserInfo {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /user/info")
    UserInfo userInfo();
}
