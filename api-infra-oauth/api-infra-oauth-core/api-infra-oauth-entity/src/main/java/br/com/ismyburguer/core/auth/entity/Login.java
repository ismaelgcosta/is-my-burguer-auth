package br.com.ismyburguer.core.auth.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login implements Serializable {

    @NotBlank(message = "Informe o username")
    private String username;

    @NotBlank(message = "Informe o password")
    private String password;

}
