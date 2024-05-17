package br.com.ismyburguer;

import br.com.ismyburguer.core.auth.gateway.out.OAuth2ClientCredentialsFeignInterceptor;
import feign.RequestTemplate;

public class TestOAuth2ClientCredentialsFeignInterceptor extends OAuth2ClientCredentialsFeignInterceptor {
    @Override
    public void apply(RequestTemplate template) {
    }
}
