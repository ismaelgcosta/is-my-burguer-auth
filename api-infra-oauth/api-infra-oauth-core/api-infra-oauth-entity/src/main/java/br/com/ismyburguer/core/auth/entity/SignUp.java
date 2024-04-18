package br.com.ismyburguer.core.auth.entity;

import br.com.ismyburguer.core.validation.Validation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUp implements Validation {
    private String username;
    private String password;
    private String email;
    private String cpf;
    private String name;
}
