package br.com.ismyburguer.core.auth.adapter.interfaces.in;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.auth.entity.ConfirmSignUp;
import br.com.ismyburguer.core.exception.BusinessException;
import br.com.ismyburguer.core.usecase.UseCase;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;

@UseCase
public class ConfirmSignUpUseCase {
    private final ConsultarClienteUseCase consultarClienteUseCase;
    private final OAuthConfirmSignUpUseCase oAuthConfirmSignUpUseCase;

    public ConfirmSignUpUseCase(ConsultarClienteUseCase consultarClienteUseCase, OAuthConfirmSignUpUseCase oAuthConfirmSignUpUseCase) {
        this.consultarClienteUseCase = consultarClienteUseCase;
        this.oAuthConfirmSignUpUseCase = oAuthConfirmSignUpUseCase;
    }

    public void confirmarNovoUsuario(@NotBlank(message = "Informe o cpf") String cpf,
                                     @NotBlank(message = "Informe o password") String password,
                                     @NotBlank(message = "Informe o código de verificação") String code) {
        String cpfFormatado = new CPFFormatter().isFormatted(cpf) ? cpf : new CPFFormatter().format(cpf);
        Cliente cliente = consultarClienteUseCase.buscarPorCpf(new ConsultarClienteUseCase.ConsultaClientePorCpf(cpfFormatado));
        String username = cliente.getUsername().map(Cliente.Username::getUsername)
                .orElseThrow(() -> new BusinessException("Não foi encontrado um Username associado ao Cliente informado"));
        oAuthConfirmSignUpUseCase.confirmSignUp(new ConfirmSignUp(
                username,
                password,
                code,
                StringUtils.getDigits(cpf)
        ));
    }
}
