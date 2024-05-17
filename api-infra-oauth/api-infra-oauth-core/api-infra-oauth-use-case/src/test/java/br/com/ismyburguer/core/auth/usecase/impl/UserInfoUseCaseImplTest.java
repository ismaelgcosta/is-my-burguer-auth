package br.com.ismyburguer.core.auth.usecase.impl;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.UserInfoUseCase;
import br.com.ismyburguer.core.auth.entity.UserInfo;
import br.com.ismyburguer.core.auth.gateway.out.LambdaOAuthUserInfo;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserInfoUseCaseImplTest {

    @InjectMocks
    private UserInfoUseCaseImpl userInfoUseCase;

    @Mock
    private LambdaOAuthUserInfo lambdaOAuthUserInfo;

    private String apiGateway = "https://localhost";

    @BeforeEach
    public void setUp() {
        userInfoUseCase.setApiGateway(apiGateway);
    }

    @BeforeAll
    public static void beforeAll() {
        mockStatic(Feign.class);
        mockStatic(SSLContextBuilder.class);
    }

    @Test
    public void testUserInfo() {
        // Mocking dependencies
        Feign.Builder builderMock = mock(Feign.Builder.class);
        when(Feign.builder()).thenReturn(builderMock);

        // Configurando comportamento do Feign.Builder
        when(builderMock.encoder(any())).thenReturn(builderMock);
        when(builderMock.decoder(any())).thenReturn(builderMock);

        // Arrange
        String accessToken = "fakeAccessToken";
        UserInfo expectedResult = new UserInfo("username", "cpf","preferred", "name","email");
        when(builderMock.encoder(any())).thenReturn(builderMock);
        when(builderMock.decoder(any())).thenReturn(builderMock);
        when(builderMock.requestInterceptor(any())).thenReturn(builderMock);
        when(builderMock.target(LambdaOAuthUserInfo.class, apiGateway)).thenReturn(lambdaOAuthUserInfo);
        when(lambdaOAuthUserInfo.userInfo()).thenReturn(expectedResult);

        // Act
        UserInfo result = userInfoUseCase.userInfo(accessToken);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResult, result);
        verify(builderMock).encoder(any());
        verify(builderMock).decoder(any());
        verify(builderMock).requestInterceptor(any());
        verify(builderMock).target(LambdaOAuthUserInfo.class, apiGateway);
        verify(lambdaOAuthUserInfo).userInfo();
    }
}
