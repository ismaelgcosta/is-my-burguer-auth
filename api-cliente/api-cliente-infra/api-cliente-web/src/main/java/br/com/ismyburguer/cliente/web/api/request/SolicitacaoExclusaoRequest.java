package br.com.ismyburguer.cliente.web.api.request;

import br.com.caelum.stella.type.Estado;
import br.com.ismyburguer.core.validation.Validation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@Getter
@Setter
public class SolicitacaoExclusaoRequest implements Validation {

    private String telefone;

    private String nome;

    @CPF(message = "Informe um CPF válido")
    private String cpf;

    @NotBlank
    private String rua;

    @NotNull
    private String numero;

    @Length(max = 255)
    private String complemento;

    @NotBlank
    @Length(max = 255)
    private String bairro;

    @NotBlank
    @Length(max = 255)
    private String cidade;

    @NotNull
    private Estado estado;

    @NotBlank
    private String cep;
}
