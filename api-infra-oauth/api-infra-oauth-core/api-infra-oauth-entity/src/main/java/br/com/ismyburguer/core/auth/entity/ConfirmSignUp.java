package br.com.ismyburguer.core.auth.entity;

import br.com.ismyburguer.core.validation.Validation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmSignUp implements Validation {

    @NotBlank(message = "Informe o username")
    private String username;

    @NotBlank(message = "Informe o password")
    private String password;

    @NotBlank(message = "Informe o código de verificação")
    private String code;

    @NotBlank(message = "Informe o cpf")
    private String cpf;
}
