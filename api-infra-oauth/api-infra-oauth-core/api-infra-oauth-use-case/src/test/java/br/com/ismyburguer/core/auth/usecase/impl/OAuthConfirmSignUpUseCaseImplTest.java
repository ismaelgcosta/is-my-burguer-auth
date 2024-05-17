package br.com.ismyburguer.core.auth.usecase.impl;

import br.com.ismyburguer.core.auth.entity.ConfirmSignUp;
import br.com.ismyburguer.core.auth.gateway.out.LambdaOAuthConfirmSignUp;
import br.com.ismyburguer.core.auth.usecase.impl.client.ApiGatewayFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OAuthConfirmSignUpUseCaseImplTest {

    @InjectMocks
    private OAuthConfirmSignUpUseCaseImpl oAuthConfirmSignUpUseCase;

    @Mock
    private ApiGatewayFeignClient client;

    @BeforeEach
    public void setUp() {
        oAuthConfirmSignUpUseCase = new OAuthConfirmSignUpUseCaseImpl(client);
    }

    @Test
    public void testConfirmSignUp() {
        // Arrange
        ConfirmSignUp input = new ConfirmSignUp("username", "password", "code", "cpf");
        Map<String, Object> expectedResult = new HashMap<>();
        when(client.createClient(LambdaOAuthConfirmSignUp.class)).thenReturn(new LambdaOAuthConfirmSignUp() {
            @Override
            public Map<String, Object> confirmSignUp(ConfirmSignUp input) {
                return expectedResult;
            }
        });

        // Act
        Map<String, Object> result = oAuthConfirmSignUpUseCase.confirmSignUp(input);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResult, result);
        verify(client).createClient(LambdaOAuthConfirmSignUp.class);
    }
}
