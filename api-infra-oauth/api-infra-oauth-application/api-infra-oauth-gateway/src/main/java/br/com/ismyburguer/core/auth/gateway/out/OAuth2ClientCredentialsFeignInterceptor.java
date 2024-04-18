package br.com.ismyburguer.core.auth.gateway.out;

import br.com.ismyburguer.core.adapter.out.OAuth2ClientCredentialsFeignInterceptorAPI;
import br.com.ismyburguer.core.auth.entity.ClientCredentialsLogin;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;

@Component
public class OAuth2ClientCredentialsFeignInterceptor implements OAuth2ClientCredentialsFeignInterceptorAPI {

    @Value("${aws.api-gateway}")
    private String apiGateway;

    @Value("${aws.cognito.client-id}")
    private String clientId;

    @Value("${aws.cognito.client-secret}")
    private String clientSecret;

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER_TOKEN = "Bearer {0}";

    public void apply(RequestTemplate template) {
        Map<String, Object> token = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(LambdaClientOAuthSignIn.class, apiGateway)
                .token(new ClientCredentialsLogin(clientId, clientSecret));

        template.header(AUTHORIZATION, MessageFormat.format(BEARER_TOKEN, token.get("access_token")));
    }
}