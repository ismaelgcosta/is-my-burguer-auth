package br.com.ismyburguer.core.auth.gateway.out.request;

import br.com.ismyburguer.core.validation.Validation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest implements Validation {

    private String username;
    private String password;
    private String email;
    private String cpf;
    private String name;
}
