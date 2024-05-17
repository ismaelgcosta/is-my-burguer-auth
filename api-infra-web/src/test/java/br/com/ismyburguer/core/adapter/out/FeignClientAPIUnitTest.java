package br.com.ismyburguer.core.adapter.out;

import feign.Feign;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FeignClientAPIUnitTest {
    private static SSLContextBuilder contextBuilder;
    private static MockedStatic<Feign> feignMockedStatic;
    private static MockedStatic<SSLContextBuilder> sslContextBuilderMockedStatic;
    private OAuth2ClientCredentialsFeignInterceptorAPI interceptor;
    private FeignClientAPI feignClientAPI;
    @BeforeEach
    void setUp() {
        interceptor = mock(OAuth2ClientCredentialsFeignInterceptorAPI.class);
        feignClientAPI = new FeignClientAPI(interceptor);
        feignClientAPI.setApiGateway("gateway");
    }

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
        feignMockedStatic.reset();
        sslContextBuilderMockedStatic.reset();
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

        feignClientAPI.createClient(Feign.Builder.class);
    }

}
