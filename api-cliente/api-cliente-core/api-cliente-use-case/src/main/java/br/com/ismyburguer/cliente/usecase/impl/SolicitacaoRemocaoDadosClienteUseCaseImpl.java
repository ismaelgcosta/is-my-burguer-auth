package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.SolicitacaoRemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.gateway.out.ConsultarClienteRepository;
import br.com.ismyburguer.cliente.gateway.out.SolicitacaoExclusaoRepository;
import br.com.ismyburguer.core.usecase.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
public class SolicitacaoRemocaoDadosClienteUseCaseImpl implements SolicitacaoRemocaoDadosClienteUseCase {

    private final SolicitacaoExclusaoRepository solicitacaoExclusaoRepository;

    public SolicitacaoRemocaoDadosClienteUseCaseImpl(SolicitacaoExclusaoRepository solicitacaoExclusaoRepository) {
        this.solicitacaoExclusaoRepository = solicitacaoExclusaoRepository;
    }

    @Override
    @Transactional
    public SolicitacaoExclusao solicitar(SolicitacaoExclusao solicitacao) {
        UUID uuid = solicitacaoExclusaoRepository.salvarSolicitacao(solicitacao);
        return solicitacao;
    }

}
