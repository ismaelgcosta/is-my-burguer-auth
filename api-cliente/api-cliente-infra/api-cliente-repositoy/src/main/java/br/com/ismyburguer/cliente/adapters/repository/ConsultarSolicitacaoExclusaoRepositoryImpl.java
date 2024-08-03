package br.com.ismyburguer.cliente.adapters.repository;

import br.com.ismyburguer.cliente.adapters.converter.SolicitacaoExclusaoModelToSolicitacaoExclusaoConverter;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.gateway.out.ConsultarSolicitacaoExclusaoRepository;
import br.com.ismyburguer.core.adapter.out.PersistenceAdapter;
import lombok.AllArgsConstructor;

import java.util.List;

@PersistenceAdapter
@AllArgsConstructor
public class ConsultarSolicitacaoExclusaoRepositoryImpl implements ConsultarSolicitacaoExclusaoRepository {

    private final SolicitacaoExclusaoRepository repository;
    private final SolicitacaoExclusaoModelToSolicitacaoExclusaoConverter converter;

    @Override
    public List<SolicitacaoExclusao> listarSolicitacoes(String cpf) {
        return repository.findAllByCpf(cpf).stream().map(converter::convert).toList();
    }
}
