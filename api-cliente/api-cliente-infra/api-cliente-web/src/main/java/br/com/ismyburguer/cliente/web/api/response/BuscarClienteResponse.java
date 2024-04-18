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

}
