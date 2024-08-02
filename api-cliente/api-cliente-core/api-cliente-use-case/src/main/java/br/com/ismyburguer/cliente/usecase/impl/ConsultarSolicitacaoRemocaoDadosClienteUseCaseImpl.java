package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarSolicitacaoRemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.gateway.out.ConsultarSolicitacaoExclusaoRepository;
import br.com.ismyburguer.core.usecase.UseCase;

import java.util.List;

@UseCase
public class ConsultarSolicitacaoRemocaoDadosClienteUseCaseImpl implements ConsultarSolicitacaoRemocaoDadosClienteUseCase {

    private final ConsultarSolicitacaoExclusaoRepository consultarSolicitacaoExclusaoRepository;

    public ConsultarSolicitacaoRemocaoDadosClienteUseCaseImpl(ConsultarSolicitacaoExclusaoRepository consultarSolicitacaoExclusaoRepository) {
        this.consultarSolicitacaoExclusaoRepository = consultarSolicitacaoExclusaoRepository;
    }

    @Override
    public List<SolicitacaoExclusao> consultar(String cpf) {
        return consultarSolicitacaoExclusaoRepository.listarSolicitacoes(cpf);
    }
}
