package br.com.ismyburguer.cliente.adapters.converter;

import br.com.ismyburguer.cliente.adapters.model.SolicitacaoExclusaoModel;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.out.PersistenceConverter;

@PersistenceConverter
public class SolicitacaoExclusaoToSolicitacaoExclusaoModelConverter implements Converter<SolicitacaoExclusao, SolicitacaoExclusaoModel> {
    @Override
    public SolicitacaoExclusaoModel convert(SolicitacaoExclusao source) {
        return new SolicitacaoExclusaoModel(
                source.getTelefone().getTelefone(),
                source.getNome().getNome(),
                source.getCpf().getNumero(),
                source.getEndereco().getRua(),
                source.getEndereco().getNumero(),
                source.getEndereco().getComplemento(),
                source.getEndereco().getBairro(),
                source.getEndereco().getCidade(),
                source.getEndereco().getEstado(),
                source.getEndereco().getCep()
        );
    }
}
