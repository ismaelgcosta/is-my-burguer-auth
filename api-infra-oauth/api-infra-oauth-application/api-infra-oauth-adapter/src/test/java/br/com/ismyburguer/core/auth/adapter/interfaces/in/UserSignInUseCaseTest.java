package br.com.ismyburguer.core.auth.adapter.interfaces.in;
import br.com.ismyburguer.cliente.adapter.interfaces.in.AlterarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.CadastrarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.auth.entity.Login;
import br.com.ismyburguer.core.auth.entity.UserInfo;
import br.com.ismyburguer.core.auth.entity.UserToken;
import br.com.ismyburguer.core.usecase.UseCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserSignInUseCaseTest {

    @Mock
    private CadastrarClienteUseCase cadastrarClienteUseCase;

    @Mock
    private AlterarClienteUseCase alterarClienteUseCase;

    @Mock
    private ConsultarClienteUseCase consultarClienteUseCase;

    @Mock
    private OAuthSignInUseCase oAuthSignInUseCase;

    @Mock
    private UserInfoUseCase userInfoUseCase;

    @InjectMocks
    private UserSignInUseCase userSignInUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeLoginComClienteNovo() {
        // Arrange
        String username = "newuser";
        String password = "newpassword";
        String accessToken = "newAccessToken";
        String cpf = "10987654321";
        UserToken token = new UserToken(accessToken, null, null, 3600, "jwt");
        UserInfo userInfo = new UserInfo(username, cpf, "New User", "name", "newuser@test.com");

        when(oAuthSignInUseCase.signin(any(Login.class))).thenReturn(token);
        when(userInfoUseCase.userInfo(any())).thenReturn(userInfo);
        when(consultarClienteUseCase.existsByCpf(cpf)).thenReturn(false);

        // Act
        UserToken resultado = userSignInUseCase.login(username, password);

        // Assert
        verify(cadastrarClienteUseCase).cadastrar(any(Cliente.class));
        assertThat(resultado).isEqualTo(token);
    }
}
