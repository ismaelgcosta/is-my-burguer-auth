package br.com.ismyburguer.core.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String username;
    private String cpf;
    private String preferredUsername;
    private String name;
    private String email;
}
