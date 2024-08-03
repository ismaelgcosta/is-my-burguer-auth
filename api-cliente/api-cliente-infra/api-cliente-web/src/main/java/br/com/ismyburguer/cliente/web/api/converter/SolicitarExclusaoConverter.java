package br.com.ismyburguer.cliente.web.api.converter;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.entity.Endereco;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.web.api.request.SolicitacaoExclusaoRequest;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.in.WebConverter;
import jakarta.validation.ConstraintViolationException;

@WebConverter
public class SolicitarExclusaoConverter implements Converter<SolicitacaoExclusaoRequest, SolicitacaoExclusao> {

    @Override
    public SolicitacaoExclusao convert(SolicitacaoExclusaoRequest source) {
        if(source == null) {
            throw new ConstraintViolationException("Não foi informado o corpo da requisição", null);
        }

        if(source != null) {
            source.validate();
        }

        return new SolicitacaoExclusao(
                new SolicitacaoExclusao.Nome(source.getNome()),
                new SolicitacaoExclusao.Telefone(source.getTelefone()),
                new Endereco(
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
