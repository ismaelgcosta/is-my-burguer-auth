package br.com.ismyburguer.cliente.adapter.interfaces.in;

import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;

import java.util.List;

public interface ConsultarSolicitacaoRemocaoDadosClienteUseCase {
    List<SolicitacaoExclusao> consultar(String cpf);
}
