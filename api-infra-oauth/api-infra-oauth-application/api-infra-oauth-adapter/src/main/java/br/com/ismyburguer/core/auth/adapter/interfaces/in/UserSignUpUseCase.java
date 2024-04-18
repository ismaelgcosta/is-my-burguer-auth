package br.com.ismyburguer.core.auth.adapter.interfaces.in;

import br.com.ismyburguer.cliente.entity.Cliente;

public interface UserSignUpUseCase {
    Cliente cadastrarNovoUsuario(Cliente cliente, String password);
}
