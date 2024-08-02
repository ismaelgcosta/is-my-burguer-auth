package br.com.ismyburguer.core.auth.usecase.impl.client;

import br.com.ismyburguer.core.adapter.out.FeignClientAPI;
import br.com.ismyburguer.core.auth.gateway.out.LambdaClientOAuthSignIn;
import br.com.ismyburguer.core.auth.gateway.out.OAuth2ClientCredentialsFeignInterceptor;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApiGatewayFeignClient extends FeignClientAPI implements br.com.ismyburguer.core.adapter.out.ApiGatewayFeignClient {

    public ApiGatewayFeignClient(OAuth2ClientCredentialsFeignInterceptor interceptor) {
        super(interceptor);
    }

    @Override
    public <T> T createClient(Class<T> apiType) {
        Feign.Builder builder = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder());

        if(!apiType.isAssignableFrom(LambdaClientOAuthSignIn.class)) {
            builder.requestInterceptor(interceptor);
        }

        return builder
                .target(apiType, apiGateway);
    }
}
