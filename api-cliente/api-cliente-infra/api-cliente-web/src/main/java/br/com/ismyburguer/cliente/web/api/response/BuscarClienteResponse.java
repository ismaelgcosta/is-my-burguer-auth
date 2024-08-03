package br.com.ismyburguer.cliente.web.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuscarClienteResponse {

    private String clienteId;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String username;

    public String getCpf() {
        return cpf.replaceAll("(.{3})(.*)(.{4})", "$1.***.**$3");
    }

    public String getEmail() {
        int atIndex = email.indexOf('@');
        String regex = "(.{2})(.*)(@.*)";
        String repeatedAsterisks = "*".repeat(atIndex - 2);
        return email.replaceAll(regex, "$1" + repeatedAsterisks + "$3");
    }
}
