package br.com.ismyburguer.cliente.web.api.response;

import br.com.caelum.stella.type.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolicitacaoExclusaoResponse {

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

    public String getCpf() {
        return cpf.replaceAll("(.{3})(.*)(.{4})", "$1.***.**$3");
    }

    public String getTelefone() {
        int atIndex = telefone.indexOf('-');
        String regex = "(.{2})(.*)(-.*)";
        String repeatedAsterisks = "*".repeat(atIndex - 2);
        return telefone.replaceAll(regex, "$1" + repeatedAsterisks + "$3");
    }

    public String getCep() {
        int atIndex = cep.indexOf('-');
        String regex = "(.{2})(.*)(-.*)";
        String repeatedAsterisks = "*".repeat(atIndex - 2);
        return cep.replaceAll(regex, "$1" + repeatedAsterisks + "$3");
    }
}
