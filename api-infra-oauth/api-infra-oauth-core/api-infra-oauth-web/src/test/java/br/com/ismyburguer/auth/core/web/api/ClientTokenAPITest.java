package br.com.ismyburguer.auth.core.web.api;

import br.com.ismyburguer.core.adapter.out.ApiGatewayFeignClient;
import br.com.ismyburguer.core.auth.entity.ClientCredentialsLogin;
import br.com.ismyburguer.core.auth.gateway.out.LambdaClientOAuthSignIn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientTokenAPITest {

    @Mock
    private ApiGatewayFeignClient apiGatewayFeignClient;

    @Mock
    private LambdaClientOAuthSignIn lambdaClientOAuthSignIn;

    @InjectMocks
    private ClientTokenAPI clientTokenAPI;

    private ClientCredentialsLogin request;
    private Map<String, Object> tokenResponse;

    @BeforeEach
    public void setUp() {
        request = new ClientCredentialsLogin("clientId", "clientSecret");

        tokenResponse = new HashMap<>();
        tokenResponse.put("access_token", "mockAccessToken");
        tokenResponse.put("token_type", "Bearer");

        when(apiGatewayFeignClient.createClient(LambdaClientOAuthSignIn.class)).thenReturn(lambdaClientOAuthSignIn);
    }

    @Test
    public void testarGerarTokenComSucesso() {
        // Arrange
        when(lambdaClientOAuthSignIn.token(request)).thenReturn(tokenResponse);

        // Act
        Map<String, Object> response = clientTokenAPI.login(request);

        // Assert
        assertEquals(tokenResponse, response);
        verify(apiGatewayFeignClient, times(1)).createClient(LambdaClientOAuthSignIn.class);
        verify(lambdaClientOAuthSignIn, times(1)).token(request);
    }

    @Test
    public void testarGerarTokenComErro() {
        // Arrange
        when(lambdaClientOAuthSignIn.token(any(ClientCredentialsLogin.class))).thenThrow(new RuntimeException("Erro ao gerar token"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clientTokenAPI.login(request);
        });

        assertEquals("Erro ao gerar token", exception.getMessage());
        verify(apiGatewayFeignClient, times(1)).createClient(LambdaClientOAuthSignIn.class);
        verify(lambdaClientOAuthSignIn, times(1)).token(request);
    }
}
