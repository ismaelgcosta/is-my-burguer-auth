package br.com.ismyburguer.core.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private String clienteId;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String username;

}
