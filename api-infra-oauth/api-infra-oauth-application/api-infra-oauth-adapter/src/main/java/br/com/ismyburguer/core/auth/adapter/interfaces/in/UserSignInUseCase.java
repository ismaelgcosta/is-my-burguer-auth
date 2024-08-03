package br.com.ismyburguer.core.auth.adapter.interfaces.in;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.ismyburguer.cliente.adapter.interfaces.in.AlterarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.CadastrarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.auth.entity.Login;
import br.com.ismyburguer.core.auth.entity.UserInfo;
import br.com.ismyburguer.core.auth.entity.UserToken;
import br.com.ismyburguer.core.usecase.UseCase;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

@UseCase
public class UserSignInUseCase {
    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final AlterarClienteUseCase alterarClienteUseCase;
    private final ConsultarClienteUseCase consultarClienteUseCase;
    private final OAuthSignInUseCase oAuthSignInUseCase;
    private final UserInfoUseCase userInfoUseCase;

    public UserSignInUseCase(CadastrarClienteUseCase cadastrarClienteUseCase,
                             AlterarClienteUseCase alterarClienteUseCase,
                             ConsultarClienteUseCase consultarClienteUseCase,
                             OAuthSignInUseCase oAuthSignInUseCase,
                             UserInfoUseCase userInfoUseCase) {
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.alterarClienteUseCase = alterarClienteUseCase;
        this.consultarClienteUseCase = consultarClienteUseCase;
        this.oAuthSignInUseCase = oAuthSignInUseCase;
        this.userInfoUseCase = userInfoUseCase;
    }

    @Transactional
    public UserToken login(@NotBlank(message = "Informe o username") String username, @NotBlank(message = "Informe o password") String password) {

        UserToken signin = oAuthSignInUseCase.signin(new Login(StringUtils.getDigits(username), password));
        UserInfo user = userInfoUseCase.userInfo(signin.getAccessToken());

        Cliente.Nome nome = new Cliente.Nome(user.getName());
        Cliente novoCliente = new Cliente(
                nome,
                new Cliente.Email(user.getEmail()),
                new Cliente.CPF(user.getCpf()),
                new Cliente.Username(user.getUsername())
        );

        if(consultarClienteUseCase.existsByCpf(user.getCpf())) {
            Cliente cliente = consultarClienteUseCase.buscarPorCpf(new ConsultarClienteUseCase.ConsultaClientePorCpf(user.getCpf()));
            nome.setSobrenome(cliente.getNome().getSobrenome());
            alterarClienteUseCase.alterar(cliente.getClienteId().getClienteId().toString(), novoCliente);
        } else {
            cadastrarClienteUseCase.cadastrar(novoCliente);
        }

        return signin;
    }
}
