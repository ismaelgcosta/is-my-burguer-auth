package br.com.ismyburguer.cliente.gateway.out;

import br.com.ismyburguer.cliente.entity.Cliente;
import jakarta.validation.Valid;

import java.util.UUID;

public interface CadastrarClienteRepository {
    UUID salvarCliente(@Valid Cliente cliente);
}
