package br.com.ismyburguer.cliente.adapters.model;

import br.com.caelum.stella.type.Estado;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "solicitacoesExclusao")
@NoArgsConstructor
@EqualsAndHashCode(of = "solicitacaoExclusaoId")
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolicitacaoExclusaoModel {

    @Id
    private UUID solicitacaoExclusaoId = UUID.randomUUID();

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
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @NotBlank
    private String cep;

}
