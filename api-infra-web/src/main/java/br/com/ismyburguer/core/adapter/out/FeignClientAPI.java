package br.com.ismyburguer.core.adapter.out;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeignClientAPI {

    @Setter
    @Value("${aws.api-gateway}")
    protected String apiGateway;

    protected final OAuth2ClientCredentialsFeignInterceptorAPI interceptor;

    public FeignClientAPI(OAuth2ClientCredentialsFeignInterceptorAPI interceptor) {
        this.interceptor = interceptor;
    }

    public <T> T createClient(Class<T> apiType) {
        Feign.Builder builder = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder());

        builder.requestInterceptor(interceptor);

        return builder
                .target(apiType, apiGateway);
    }
}
