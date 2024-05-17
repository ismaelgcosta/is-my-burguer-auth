package br.com.ismyburguer.auth.core.web.api;

import br.com.ismyburguer.core.auth.adapter.interfaces.in.UserInfoUseCase;
import br.com.ismyburguer.core.auth.entity.UserInfo;
import br.com.ismyburguer.core.auth.entity.UserInfoEntity;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserInfoAPITest {

    @Mock
    private UserInfoUseCase userInfoUseCase;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private Jwt jwt;

    @InjectMocks
    private UserInfoAPI userInfoAPI;

    private UserInfo userInfo;

    @BeforeEach
    public void setUp() {
        userInfo = EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(UserInfo.class);
        userInfo.setEmail("email@email.com");
        userInfo.setCpf("11111111111");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getCredentials()).thenReturn(jwt);
        when(jwt.getTokenValue()).thenReturn("mock-token");

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testarUserInfoComSucesso() {
        // Arrange
        when(userInfoUseCase.userInfo(any())).thenReturn(userInfo);

        // Act
        UserInfoEntity result = userInfoAPI.userInfo();

        // Assert
        assertNotNull(result);
        assertEquals(userInfo.getUsername(), result.getUsername());
        assertEquals("111*****111", result.getCpf());
        assertEquals(userInfo.getName(), result.getName());
        assertEquals("em***@email.com", result.getEmail());

        verify(userInfoUseCase, times(1)).userInfo("mock-token");
    }

    @Test
    public void testarUserInfoComErro() {
        // Arrange
        when(userInfoUseCase.userInfo("mock-token")).thenThrow(new RuntimeException("Erro ao buscar informações do usuário"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userInfoAPI.userInfo();
        });

        assertEquals("Erro ao buscar informações do usuário", exception.getMessage());

        verify(userInfoUseCase, times(1)).userInfo("mock-token");
    }
}
