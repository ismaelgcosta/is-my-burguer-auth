package br.com.ismyburguer.core.auth.usecase.impl;

import br.com.ismyburguer.core.auth.adapter.interfaces.in.OAuthConfirmSignUpUseCase;
import br.com.ismyburguer.core.auth.entity.ConfirmSignUp;
import br.com.ismyburguer.core.auth.gateway.out.LambdaOAuthConfirmSignUp;
import br.com.ismyburguer.core.auth.usecase.impl.client.ApiGatewayFeignClient;
import br.com.ismyburguer.core.usecase.UseCase;

import java.util.Map;

@UseCase
public class OAuthConfirmSignUpUseCaseImpl implements OAuthConfirmSignUpUseCase {
    private final ApiGatewayFeignClient client;

    public OAuthConfirmSignUpUseCaseImpl(ApiGatewayFeignClient client) {
        this.client = client;
    }

    @Override
    public Map<String, Object> confirmSignUp(ConfirmSignUp input) {
        return client.createClient(LambdaOAuthConfirmSignUp.class).confirmSignUp(input);
    }
}
