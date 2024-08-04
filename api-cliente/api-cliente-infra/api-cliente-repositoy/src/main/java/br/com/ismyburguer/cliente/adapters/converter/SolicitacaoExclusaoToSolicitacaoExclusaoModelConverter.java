package br.com.ismyburguer.cliente.adapters.converter;

import br.com.ismyburguer.cliente.adapters.model.SolicitacaoExclusaoModel;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.out.PersistenceConverter;

import java.util.UUID;

@PersistenceConverter
public class SolicitacaoExclusaoToSolicitacaoExclusaoModelConverter implements Converter<SolicitacaoExclusao, SolicitacaoExclusaoModel> {
    @Override
    public SolicitacaoExclusaoModel convert(SolicitacaoExclusao source) {
        return SolicitacaoExclusaoModel.builder()
                .solicitacaoExclusaoId(UUID.randomUUID())
                .cpf(source.getCpf().getNumero())
                .telefone(source.getTelefone().getTelefone())
                .nome(source.getNome().getDescricao())
                .numero(source.getCpf().getNumero())
                .rua(source.getEndereco().getRua())
                .numero(source.getEndereco().getNumero())
                .complemento(source.getEndereco().getComplemento())
                .bairro(source.getEndereco().getBairro())
                .cidade(source.getEndereco().getCidade())
                .estado(source.getEndereco().getEstado())
                .cep(source.getEndereco().getCep())
                .build();
    }
}
