package br.com.ismyburguer.auth.core.web.api;

import br.com.ismyburguer.core.auth.adapter.interfaces.in.UserSignInUseCase;
import br.com.ismyburguer.core.auth.entity.Login;
import br.com.ismyburguer.core.auth.entity.UserToken;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTokenAPITest {

    @Mock
    private UserSignInUseCase userSignInUseCase;

    @InjectMocks
    private UserTokenAPI userTokenAPI;

    private Login loginRequest;
    private UserToken userToken;

    @BeforeEach
    public void setUp() {
        loginRequest = new Login("username", "password");
        userToken = EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(UserToken.class);
    }

    @Test
    public void testarGerarTokenComSucesso() {
        // Arrange
        when(userSignInUseCase.login(loginRequest.getUsername(), loginRequest.getPassword()))
                .thenReturn(userToken);

        // Act
        UserToken result = userTokenAPI.login(loginRequest);

        // Assert
        assertNotNull(result);
        assertEquals(userToken.getIdToken(), result.getIdToken());
        assertEquals(userToken.getRefreshToken(), result.getRefreshToken());
        assertEquals(userToken.getAccessToken(), result.getAccessToken());
        verify(userSignInUseCase, times(1)).login(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
