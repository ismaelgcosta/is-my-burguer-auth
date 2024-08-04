package br.com.ismyburguer.cliente.entity;

import br.com.caelum.stella.type.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Endereco {

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
