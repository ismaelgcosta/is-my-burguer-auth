package br.com.ismyburguer.core.auth.usecase.impl.client;

import br.com.ismyburguer.core.adapter.out.FeignClientAPI;
import br.com.ismyburguer.core.adapter.out.OAuth2ClientCredentialsFeignInterceptorAPI;
import br.com.ismyburguer.core.auth.gateway.out.LambdaClientOAuthSignIn;
import br.com.ismyburguer.core.auth.gateway.out.LambdaOAuthSignUp;
import br.com.ismyburguer.core.auth.gateway.out.OAuth2ClientCredentialsFeignInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class ApiGatewayFeignClientTest {

    @InjectMocks
    private ApiGatewayFeignClient apiGatewayFeignClient;

    @Mock
    private OAuth2ClientCredentialsFeignInterceptor interceptor;

    @BeforeEach
    public void setUp() {
        apiGatewayFeignClient = new ApiGatewayFeignClient(interceptor);
        ReflectionTestUtils.setField(apiGatewayFeignClient, "apiGateway", "http://example.com");
    }

    @Test
    public void testCreateClient() {
        // Arrange
        Class<LambdaOAuthSignUp> apiType = LambdaOAuthSignUp.class;

        // Act
        LambdaOAuthSignUp client = apiGatewayFeignClient.createClient(apiType);

        // Assert
        assertNotNull(client);
    }

    @Test
    public void testCreateClientWithInterceptor() {
        // Arrange
        Class<LambdaClientOAuthSignIn> apiType = LambdaClientOAuthSignIn.class;

        // Act
        LambdaClientOAuthSignIn client = apiGatewayFeignClient.createClient(apiType);

        // Assert
        assertNotNull(client);
        verifyNoInteractions(interceptor);
    }
}
