package br.com.ismyburguer.cliente.adapter.interfaces.in;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.adapter.ExistsByIdUseCase;
import br.com.ismyburguer.core.adapter.ExistsByUsernameUseCase;

import java.util.UUID;

public interface ConsultarClienteUseCase extends ExistsByIdUseCase<Cliente>, ExistsByUsernameUseCase<Cliente> {

    Cliente buscar(ConsultaCliente query);

    Cliente buscarPorCpf(ConsultaClientePorCpf query);

    Cliente buscarPorId(ConsultaClientePorId query);

    Cliente buscarPorUsername(ConsultaClientePorUsername query);

    boolean existsByCpf(String username);

    record ConsultaCliente(String email) {

    }
    record ConsultaClientePorCpf(String cpf) {

    }
    record ConsultaClientePorUsername(String username) {

    }
    record ConsultaClientePorId(String clienteId) {
        public ConsultaClientePorId(UUID uuid) {
            this(uuid.toString());
        }
    }
}
