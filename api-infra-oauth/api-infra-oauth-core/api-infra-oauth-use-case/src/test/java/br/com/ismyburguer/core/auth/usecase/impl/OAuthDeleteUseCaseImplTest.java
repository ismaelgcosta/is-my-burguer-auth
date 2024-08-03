package br.com.ismyburguer.core.auth.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.OAuthDeleteUseCase;
import br.com.ismyburguer.core.auth.entity.Delete;
import br.com.ismyburguer.core.auth.entity.Login;
import br.com.ismyburguer.core.auth.entity.UserToken;
import br.com.ismyburguer.core.auth.gateway.out.LambdaUserOAuthDelete;
import br.com.ismyburguer.core.auth.gateway.out.LambdaUserOAuthSignIn;
import br.com.ismyburguer.core.auth.usecase.impl.client.ApiGatewayFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OAuthDeleteUseCaseImplTest {

    @Mock
    private ApiGatewayFeignClient client;

    @Mock
    private LambdaUserOAuthSignIn lambdaUserOAuthSignIn;

    @Mock
    private LambdaUserOAuthDelete lambdaUserOAuthDelete;

    @InjectMocks
    private OAuthDeleteUseCaseImpl oAuthDeleteUseCase;

    @Test
    void signin_DeveRetornarUserToken() {
        // Arrange
        Login login = new Login("username", "password");
        UserToken expectedToken = new UserToken(
                "idToken",
                "refreshToken",
                "accessToken",
                360,
                "tokenType"
        );

        when(client.createClient(LambdaUserOAuthSignIn.class)).thenReturn(lambdaUserOAuthSignIn);
        when(lambdaUserOAuthSignIn.signin(login)).thenReturn(expectedToken);

        // Act
        UserToken actualToken = oAuthDeleteUseCase.signin(login);

        // Assert
        assertEquals(expectedToken, actualToken);
        verify(client, times(1)).createClient(LambdaUserOAuthSignIn.class);
        verify(lambdaUserOAuthSignIn, times(1)).signin(login);
    }

    @Test
    void delete_DeveChamarDeleteDoClient() {
        // Arrange
        Delete delete = new Delete("username");

        when(client.createClient(LambdaUserOAuthDelete.class)).thenReturn(lambdaUserOAuthDelete);

        // Act
        oAuthDeleteUseCase.delete(delete);

        // Assert
        verify(client, times(1)).createClient(LambdaUserOAuthDelete.class);
        verify(lambdaUserOAuthDelete, times(1)).delete(delete);
    }
}
