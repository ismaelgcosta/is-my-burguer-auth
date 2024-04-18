package br.com.ismyburguer.auth.core.web.api.request;

import br.com.ismyburguer.core.validation.Validation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmarCadastroRequest implements Validation {

    @NotBlank(message = "Informe o CPF")
    @CPF
    private String cpf;

    @NotBlank(message = "Informe o password")
    private String password;

    @NotBlank(message = "Informe o código de verificação")
    private String code;
}
