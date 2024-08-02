package br.com.ismyburguer.cliente.adapters.repository;

import br.com.ismyburguer.cliente.adapters.converter.SolicitacaoExclusaoToSolicitacaoExclusaoModelConverter;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.gateway.out.CriarSolicitacaoExclusaoRepository;
import br.com.ismyburguer.core.adapter.out.PersistenceAdapter;
import lombok.AllArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@AllArgsConstructor
public class CriarSolicitacaoExclusaoRepositoryImpl implements CriarSolicitacaoExclusaoRepository {

    private final SolicitacaoExclusaoRepository repository;
    private final SolicitacaoExclusaoToSolicitacaoExclusaoModelConverter converter;

    @Override
    public UUID salvarSolicitacao(SolicitacaoExclusao solicitacaoExclusao) {
        return repository.save(converter.convert(solicitacaoExclusao)).getSolicitacaoExclusaoId();
    }
}
