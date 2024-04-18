package br.com.ismyburguer.cliente.adapters.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "clientes")
public class ClienteModel {

    @Id
    private UUID clienteId = UUID.randomUUID();

    @NotBlank(message = "Informe o campo nome")
    @Size(min = 3, message = "O nome deve conter pelo menos 3 letras")
    private String nome;

    private String sobrenome;

    @Indexed(unique = true)
    @Email(message = "Informe um e-mail válido")
    private String email;

    @Indexed(unique = true)
    @CPF(message = "Informe um CPF válido")
    private String cpf;

    private boolean ativo = true;

    @Indexed(unique = true)
    @NotBlank(message = "Informe o campo username")
    @Size(min = 3, message = "O username deve conter pelo menos 3 letras")
    private String username;

    public ClienteModel() {
    }

    public ClienteModel(String nome, String sobrenome, String email, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.cpf = cpf;
    }

    public ClienteModel(String nome, String sobrenome, String email, String cpf, String username) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.cpf = cpf;
        this.username = username;
    }

    public Optional<String> getCpf() {
        return Optional.ofNullable(cpf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ClienteModel that)) return false;

        return new EqualsBuilder().append(getClienteId(), that.getClienteId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getClienteId()).toHashCode();
    }
}
