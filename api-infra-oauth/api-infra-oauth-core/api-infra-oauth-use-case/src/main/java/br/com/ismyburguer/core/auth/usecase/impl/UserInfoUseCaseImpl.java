package br.com.ismyburguer.core.auth.usecase.impl;

import br.com.ismyburguer.core.auth.adapter.interfaces.in.UserInfoUseCase;
import br.com.ismyburguer.core.auth.entity.UserInfo;
import br.com.ismyburguer.core.auth.gateway.out.LambdaOAuthUserInfo;
import br.com.ismyburguer.core.usecase.UseCase;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;

import java.text.MessageFormat;

@UseCase
public class UserInfoUseCaseImpl implements UserInfoUseCase {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER_TOKEN = "Bearer {0}";

    @Value("${aws.api-gateway}")
    private String apiGateway;

    public UserInfo userInfo(String accessToken) {
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(template -> template.header(AUTHORIZATION, MessageFormat.format(BEARER_TOKEN, accessToken)))
                .target(LambdaOAuthUserInfo.class, apiGateway)
                .userInfo();
    }
}
