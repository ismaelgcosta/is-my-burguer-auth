package br.com.ismyburguer.cliente.entity;

import br.com.caelum.stella.type.Estado;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class SolicitacaoExclusao {

    @Valid
    private Nome nome;

    @Valid
    private Telefone telefone;

    @Valid
    private Endereco endereco;

    @Valid
    @Setter
    private Cliente.CPF cpf;

    @Getter
    @AllArgsConstructor
    public static class CPF {

        @org.hibernate.validator.constraints.br.CPF(message = "Informe um CPF válido")
        private String numero;

    }

    @Getter
    @AllArgsConstructor
    public static class Nome {

        @NotBlank(message = "Informe o campo nome")
        @Size(min = 3, message = "O nome deve conter pelo menos 3 letras")
        String nome;
    }

    @Getter
    @AllArgsConstructor
    public static class Telefone {

        @NotBlank(message = "Informe o campo telefone")
        @Size(min = 3, message = "O nome deve conter pelo menos 3 letras")
        String telefone;

    }

    @Getter
    @AllArgsConstructor
    public static class Endereco {

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

}
