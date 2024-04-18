package br.com.ismyburguer.core.auth.usecase.impl;

import br.com.ismyburguer.core.auth.adapter.interfaces.in.OAuthSignInUseCase;
import br.com.ismyburguer.core.auth.entity.Login;
import br.com.ismyburguer.core.auth.entity.UserToken;
import br.com.ismyburguer.core.auth.gateway.out.LambdaUserOAuthSignIn;
import br.com.ismyburguer.core.auth.usecase.impl.client.ApiGatewayFeignClient;
import br.com.ismyburguer.core.usecase.UseCase;

@UseCase
public class OAuthSignInUseCaseImpl implements OAuthSignInUseCase {
    private final ApiGatewayFeignClient client;

    public OAuthSignInUseCaseImpl(ApiGatewayFeignClient client) {
        this.client = client;
    }

    public UserToken signin(Login input) {
        return client.createClient(LambdaUserOAuthSignIn.class).signin(input);
    }
}
