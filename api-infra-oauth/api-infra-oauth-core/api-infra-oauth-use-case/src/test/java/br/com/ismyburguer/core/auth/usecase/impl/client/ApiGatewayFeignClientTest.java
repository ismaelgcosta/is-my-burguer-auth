package br.com.ismyburguer.core.auth.usecase.impl.client;

import br.com.ismyburguer.core.auth.gateway.out.LambdaClientOAuthSignIn;
import br.com.ismyburguer.core.auth.gateway.out.OAuth2ClientCredentialsFeignInterceptor;
import feign.Feign;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static org.mockito.Mockito.*;

@Isolated
public class ApiGatewayFeignClientTest {

    private static MockedStatic<Feign> feignMockedStatic;
    private static MockedStatic<SSLContextBuilder> sslContextBuilderMockedStatic;
    private ApiGatewayFeignClient apiGatewayFeignClient;

    @Mock
    private OAuth2ClientCredentialsFeignInterceptor interceptor;

    private static SSLContextBuilder contextBuilder;

    @BeforeAll
    public static void beforeAll() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        feignMockedStatic = mockStatic(Feign.class);

        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                .build();

        sslContextBuilderMockedStatic = mockStatic(SSLContextBuilder.class);
        contextBuilder = mock(SSLContextBuilder.class);
        when(SSLContextBuilder.create()).thenReturn(contextBuilder);
        when(contextBuilder.loadTrustMaterial(TrustAllStrategy.INSTANCE)).thenReturn(contextBuilder);
        when(contextBuilder.build()).thenReturn(sslContext);
    }

    @AfterAll
    static void tearDown() {
        feignMockedStatic.close();
        sslContextBuilderMockedStatic.close();
    }

    @BeforeEach
    void setUp() {
        interceptor = mock(OAuth2ClientCredentialsFeignInterceptor.class);
        apiGatewayFeignClient = new ApiGatewayFeignClient(interceptor);
        apiGatewayFeignClient.setApiGateway("gateway");
    }

    @Test
    void deveCriarClienteFeignCorretamente() {
        // Mocking dependencies
        Feign.Builder builderMock = mock(Feign.Builder.class);
        Feign feignMock = mock(Feign.class);

        when(Feign.builder()).thenReturn(builderMock);

        // Configurando comportamento do Feign.Builder
        when(builderMock.encoder(any())).thenReturn(builderMock);
        when(builderMock.decoder(any())).thenReturn(builderMock);
        when(builderMock.requestInterceptor(interceptor)).thenReturn(builderMock);

        // Configurando comportamento do Feign
        when(builderMock.client(any())).thenReturn(builderMock);

        // Configurando comportamento do Client.Default
        when(builderMock.target(any(), anyString())).thenReturn(feignMock);

        apiGatewayFeignClient.createClient(Feign.Builder.class);

        verify(builderMock).requestInterceptor(interceptor);
    }

    @Test
    void testCreateClientWithInterceptor() {
        // Mocking dependencies
        Feign.Builder builderMock = mock(Feign.Builder.class);
        Feign feignMock = mock(Feign.class);

        when(Feign.builder()).thenReturn(builderMock);

        // Configurando comportamento do Feign.Builder
        when(builderMock.encoder(any())).thenReturn(builderMock);
        when(builderMock.decoder(any())).thenReturn(builderMock);
        when(builderMock.requestInterceptor(interceptor)).thenReturn(builderMock);

        // Configurando comportamento do Feign
        when(builderMock.client(any())).thenReturn(builderMock);

        // Configurando comportamento do Client.Default
        when(builderMock.target(any(), anyString())).thenReturn(feignMock);

        apiGatewayFeignClient.createClient(LambdaClientOAuthSignIn.class);

        verify(builderMock, never()).requestInterceptor(interceptor);
    }
}
