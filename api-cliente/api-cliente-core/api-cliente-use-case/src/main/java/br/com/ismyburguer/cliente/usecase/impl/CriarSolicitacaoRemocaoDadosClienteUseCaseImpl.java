package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.CriarSolicitacaoRemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.gateway.out.CriarSolicitacaoExclusaoRepository;
import br.com.ismyburguer.core.usecase.UseCase;
import org.springframework.transaction.annotation.Transactional;

@UseCase
public class CriarSolicitacaoRemocaoDadosClienteUseCaseImpl implements CriarSolicitacaoRemocaoDadosClienteUseCase {
    private final CriarSolicitacaoExclusaoRepository criarSolicitacaoExclusaoRepository;

    public CriarSolicitacaoRemocaoDadosClienteUseCaseImpl(CriarSolicitacaoExclusaoRepository criarSolicitacaoExclusaoRepository) {
        this.criarSolicitacaoExclusaoRepository = criarSolicitacaoExclusaoRepository;
    }

    @Override
    @Transactional
    public void solicitar(SolicitacaoExclusao solicitacao) {
        criarSolicitacaoExclusaoRepository.salvarSolicitacao(solicitacao);
    }

}
