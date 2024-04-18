package br.com.ismyburguer.cliente.adapter.interfaces.in;

import br.com.ismyburguer.cliente.entity.Cliente;

import java.util.UUID;

public interface CadastrarClienteUseCase {
    UUID cadastrar(Cliente cliente);
}
