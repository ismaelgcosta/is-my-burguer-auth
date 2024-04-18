package br.com.ismyburguer.core.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoEntity {
    private String username;
    private String cpf;
    private String name;
    private String email;

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
