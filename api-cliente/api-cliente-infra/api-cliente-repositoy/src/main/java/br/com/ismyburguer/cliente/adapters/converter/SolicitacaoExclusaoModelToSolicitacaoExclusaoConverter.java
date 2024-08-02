package br.com.ismyburguer.cliente.adapters.converter;

import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.adapters.model.SolicitacaoExclusaoModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.out.PersistenceConverter;

import java.util.Optional;

@PersistenceConverter
public class SolicitacaoExclusaoModelToSolicitacaoExclusaoConverter implements Converter<SolicitacaoExclusaoModel, SolicitacaoExclusao> {
    @Override
    public SolicitacaoExclusao convert(SolicitacaoExclusaoModel source) {
        return new SolicitacaoExclusao(
                new SolicitacaoExclusao.Nome(source.getNome()),
                new SolicitacaoExclusao.Telefone(source.getTelefone()),
                new SolicitacaoExclusao.Endereco(
                        source.getRua(),
                        source.getNumero(),
                        source.getComplemento(),
                        source.getBairro(),
                        source.getCidade(),
                        source.getEstado(),
                        source.getCep()
                ),
                new Cliente.CPF(source.getCpf())
        );
    }
}
