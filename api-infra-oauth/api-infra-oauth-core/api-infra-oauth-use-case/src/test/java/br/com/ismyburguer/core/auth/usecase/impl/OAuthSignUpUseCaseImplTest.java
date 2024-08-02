package br.com.ismyburguer.core.auth.usecase.impl;

import br.com.ismyburguer.core.auth.entity.SignUp;
import br.com.ismyburguer.core.auth.gateway.out.LambdaOAuthSignUp;
import br.com.ismyburguer.core.auth.gateway.out.request.SignUpRequest;
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
public class OAuthSignUpUseCaseImplTest {

    @InjectMocks
    private OAuthSignUpUseCaseImpl oAuthSignUpUseCase;

    @Mock
    private ApiGatewayFeignClient client;

    @BeforeEach
    public void setUp() {
        oAuthSignUpUseCase = new OAuthSignUpUseCaseImpl(client);
    }

    @Test
    public void testSignUp() {
        // Arrange
        SignUp input = new SignUp("username", "password", "email", "cpf", "name");
        Map<String, Object> expectedResult = new HashMap<>();
        when(client.createClient(LambdaOAuthSignUp.class)).thenReturn(new LambdaOAuthSignUp() {
            @Override
            public Map<String, Object> signUp(SignUpRequest request) {
                return expectedResult;
            }
        });

        // Act
        Map<String, Object> result = oAuthSignUpUseCase.signUp(input);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResult, result);
        verify(client).createClient(LambdaOAuthSignUp.class);
    }
}
