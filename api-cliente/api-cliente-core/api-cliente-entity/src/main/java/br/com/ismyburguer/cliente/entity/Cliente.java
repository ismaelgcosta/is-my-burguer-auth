package br.com.ismyburguer.cliente.entity;

import br.com.ismyburguer.core.validation.Validation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Cliente implements Validation {

    @Valid
    @Setter
    private ClienteId clienteId;

    @Valid
    @Setter
    private Nome nome;

    @Valid
    @Setter
    private Email email;

    @Valid
    @Setter
    private CPF cpf;

    @Valid
    @Setter
    private Username username;

    public Cliente(Nome nome) {
        this.nome = nome;
    }

    public Cliente(Nome nome, Email email, CPF cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Cliente(ClienteId clienteId, Nome nome, Email email, CPF cpf) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Cliente(Nome nome, Email email, CPF cpf, Username username) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.username = username;
    }

    public Optional<CPF> getCpf() {
        return Optional.ofNullable(cpf);
    }

    public Optional<Username> getUsername() {
        return Optional.ofNullable(username);
    }

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class ClienteId {

        @NotNull(message = "Informe o código do Cliente")
        private UUID clienteId;

    }

    @Getter
    @AllArgsConstructor
    public static class Email {

        @jakarta.validation.constraints.Email(message = "Informe um e-mail válido")
        String endereco;
    }

    @Getter
    @AllArgsConstructor
    public static class Nome {

        @NotBlank(message = "Informe o campo nome")
        @Size(min = 3, message = "O nome deve conter pelo menos 3 letras")
        String nome;

        @Setter
        String sobrenome;

        public Nome(String nome) {
            this.nome = nome;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class CPF {

        @org.hibernate.validator.constraints.br.CPF(message = "Informe um CPF válido")
        private String numero;

    }

    @Getter
    @AllArgsConstructor
    public static class Username {

        @NotBlank(message = "Informe o campo username")
        @Size(min = 3, message = "O username deve conter pelo menos 3 letras")
        private String username;

    }

}
