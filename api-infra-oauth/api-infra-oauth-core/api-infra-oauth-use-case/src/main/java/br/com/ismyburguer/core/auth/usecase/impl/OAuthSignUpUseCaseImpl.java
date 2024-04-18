package br.com.ismyburguer.core.auth.usecase.impl;

import br.com.ismyburguer.core.auth.adapter.interfaces.in.OAuthSignUpUseCase;
import br.com.ismyburguer.core.auth.entity.SignUp;
import br.com.ismyburguer.core.auth.gateway.out.LambdaOAuthSignUp;
import br.com.ismyburguer.core.auth.gateway.out.request.SignUpRequest;
import br.com.ismyburguer.core.auth.usecase.impl.client.ApiGatewayFeignClient;
import br.com.ismyburguer.core.usecase.UseCase;

import java.util.Map;

@UseCase
public class OAuthSignUpUseCaseImpl implements OAuthSignUpUseCase {
    private final ApiGatewayFeignClient client;

    public OAuthSignUpUseCaseImpl(ApiGatewayFeignClient client) {
        this.client = client;
    }

    @Override
    public Map<String, Object> signUp(SignUp input) {
        return client.createClient(LambdaOAuthSignUp.class).signUp(new SignUpRequest(
                input.getUsername(),
                input.getPassword(),
                input.getEmail(),
                input.getCpf(),
                input.getName()
        ));
    }
}
