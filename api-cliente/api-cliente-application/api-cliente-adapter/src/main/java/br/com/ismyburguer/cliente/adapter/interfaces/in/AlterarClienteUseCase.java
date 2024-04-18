package br.com.ismyburguer.cliente.adapter.interfaces.in;

import br.com.ismyburguer.cliente.entity.Cliente;

public interface AlterarClienteUseCase {
    void alterar(String clienteId, Cliente cliente);
}
