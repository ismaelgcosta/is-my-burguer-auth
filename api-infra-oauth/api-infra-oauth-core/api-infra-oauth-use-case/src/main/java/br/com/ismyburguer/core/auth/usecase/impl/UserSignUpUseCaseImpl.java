package br.com.ismyburguer.core.auth.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.CadastrarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.OAuthSignUpUseCase;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.UserSignUpUseCase;
import br.com.ismyburguer.core.auth.entity.SignUp;
import br.com.ismyburguer.core.usecase.UseCase;
import com.github.javafaker.Faker;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
public class UserSignUpUseCaseImpl implements UserSignUpUseCase {
    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final ConsultarClienteUseCase consultarClienteUseCase;
    private final OAuthSignUpUseCase oAuthSignUpUseCase;

    public UserSignUpUseCaseImpl(CadastrarClienteUseCase cadastrarClienteUseCase, ConsultarClienteUseCase consultarClienteUseCase, OAuthSignUpUseCase oAuthSignUpUseCase) {
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.consultarClienteUseCase = consultarClienteUseCase;
        this.oAuthSignUpUseCase = oAuthSignUpUseCase;
    }

    @Transactional
    public Cliente cadastrarNovoUsuario(Cliente cliente, @NotBlank(message = "Informe o password") String password) {

        Cliente.Nome nome = cliente.getNome();
        String username = generateUsername(nome);

        while (consultarClienteUseCase.existsByUsername(username)) {
            username = generateUsername(nome);
        }

        cliente.setUsername(new Cliente.Username(username));

        UUID uuid = cadastrarClienteUseCase.cadastrar(cliente);
        Cliente novoCliente = consultarClienteUseCase.buscarPorId(new ConsultarClienteUseCase.ConsultaClientePorId(uuid));

        String cpf = cliente.getCpf().map(Cliente.CPF::getNumero).orElse(null);
        oAuthSignUpUseCase.signUp(new SignUp(
                username,
                password,
                cliente.getEmail().getEndereco(),
                StringUtils.getDigits(cpf),
                cliente.getNome().getNome()
        ));

        return novoCliente;
    }

    private static String generateUsername(Cliente.Nome nome) {
        return StringUtils.lowerCase(new Faker().superhero().prefix() + nome.getNome() + new Faker().idNumber().valid().split("-")[2]);
    }
}
