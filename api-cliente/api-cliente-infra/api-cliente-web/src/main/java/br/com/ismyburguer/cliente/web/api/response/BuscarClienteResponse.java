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
        int lastDigitsIndex = cpf.length() - 6;
        String regex = "(\\+)(\\d+)(\\d{4})";
        String repeatedAsterisks = "*".repeat(Math.max(0, lastDigitsIndex));
        return cpf.replaceAll("(\\d{3})(\\d{5})(\\d{3})", "$1*****$3");
    }

    public String getEmail() {
        int atIndex = email.indexOf('@');
        String regex = "(.{2})(.*)(@.*)";
        String repeatedAsterisks = "*".repeat(atIndex - 2);
        return email.replaceAll(regex, "$1" + repeatedAsterisks + "$3");
    }
}
