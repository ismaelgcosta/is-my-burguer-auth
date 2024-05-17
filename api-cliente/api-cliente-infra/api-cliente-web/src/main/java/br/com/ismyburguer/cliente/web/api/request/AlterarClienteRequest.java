package br.com.ismyburguer.cliente.web.api.request;

import br.com.ismyburguer.core.validation.Validation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class AlterarClienteRequest implements Validation {

    @NotBlank(message = "É obrigatório informar o nome")
    private String nome;

    private String sobrenome;

}
