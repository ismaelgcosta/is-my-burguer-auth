package br.com.ismyburguer.core.auth.gateway.out;

import feign.Feign;
import feign.RequestTemplate;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OAuth2ClientCredentialsFeignInterceptorTest {

    @Mock
    private RequestTemplate template;

    @Mock
    private Jwt jwt;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private LambdaClientOAuthSignIn lambdaClientOAuthSignIn;

    @InjectMocks
    private OAuth2ClientCredentialsFeignInterceptor interceptor;

    @BeforeEach
    void setUp() {
        mockStatic(SecurityContextHolder.class);
    }

    @BeforeAll
    public static void beforeAll() {
        mockStatic(Feign.class);
    }

    @Test
    @DisplayName("Deve adicionar token de autorização ao RequestTemplate")
    public void apply_DeveAdicionarTokenDeAutorizacaoAoRequestTemplate() {
        // Mocking dependencies
        Feign.Builder builderMock = mock(Feign.Builder.class);

        lenient().when(Feign.builder()).thenReturn(builderMock);

        // Configurando comportamento do Feign.Builder
        lenient().when(builderMock.encoder(any())).thenReturn(builderMock);
        lenient().when(builderMock.decoder(any())).thenReturn(builderMock);
        lenient().when(builderMock.requestInterceptor(interceptor)).thenReturn(builderMock);
        lenient().when(builderMock.target(eq(LambdaClientOAuthSignIn.class), any())).thenReturn(lambdaClientOAuthSignIn);

        // Arrange
        lenient().when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        lenient().when(authentication.getCredentials()).thenReturn(jwt);
        lenient().when(lambdaClientOAuthSignIn.token(any())).thenReturn(Map.of("access_token", "mockToken"));

        // Act
        interceptor.apply(template);

        // Assert
        verify(template).header("Authorization", "Bearer mockToken");
    }
}
