package br.com.ismyburguer.core.auth.usecase.impl;

import br.com.ismyburguer.core.auth.entity.Login;
import br.com.ismyburguer.core.auth.entity.UserToken;
import br.com.ismyburguer.core.auth.gateway.out.LambdaUserOAuthSignIn;
import br.com.ismyburguer.core.auth.usecase.impl.client.ApiGatewayFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OAuthSignInUseCaseImplTest {

    @InjectMocks
    private OAuthSignInUseCaseImpl oAuthSignInUseCase;

    @Mock
    private ApiGatewayFeignClient client;

    @BeforeEach
    public void setUp() {
        oAuthSignInUseCase = new OAuthSignInUseCaseImpl(client);
    }

    @Test
    public void testSignin() {
        // Arrange
        Login input = new Login("username", "password");
        UserToken expectedResult = new UserToken("idToken", "accessToken", "refreshToken", 3600, "jwt");
        when(client.createClient(LambdaUserOAuthSignIn.class)).thenReturn(new LambdaUserOAuthSignIn() {
            @Override
            public UserToken signin(Login input) {
                return expectedResult;
            }
        });

        // Act
        UserToken result = oAuthSignInUseCase.signin(input);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResult, result);
        verify(client).createClient(LambdaUserOAuthSignIn.class);
    }
}
