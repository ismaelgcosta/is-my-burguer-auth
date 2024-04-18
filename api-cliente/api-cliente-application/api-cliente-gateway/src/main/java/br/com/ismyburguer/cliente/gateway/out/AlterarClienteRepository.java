package br.com.ismyburguer.cliente.gateway.out;

import br.com.ismyburguer.cliente.entity.Cliente;
import jakarta.validation.Valid;

public interface AlterarClienteRepository {
    void alterarCliente(String clienteId, @Valid Cliente cliente);
}
