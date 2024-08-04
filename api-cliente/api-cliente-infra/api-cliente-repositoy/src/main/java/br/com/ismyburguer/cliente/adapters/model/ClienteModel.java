package br.com.ismyburguer.cliente.adapters.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "clientes")
@NoArgsConstructor
@EqualsAndHashCode(of = "clienteId")
public class ClienteModel {

    @Id
    private UUID clienteId = UUID.randomUUID();

    @NotBlank(message = "Informe o campo nome")
    @Size(min = 3, message = "O nome deve conter pelo menos 3 letras")
    private String nome;

    private String sobrenome;

    @Email(message = "Informe um e-mail válido")
    private String email;

    private String cpf;

    private boolean ativo = true;

    @Indexed(unique = true)
    @NotBlank(message = "Informe o campo username")
    @Size(min = 3, message = "O username deve conter pelo menos 3 letras")
    private String username;

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

}
