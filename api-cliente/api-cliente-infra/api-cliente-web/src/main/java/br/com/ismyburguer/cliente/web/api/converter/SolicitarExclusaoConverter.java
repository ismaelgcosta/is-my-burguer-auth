package br.com.ismyburguer.cliente.web.api.converter;

import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.web.api.request.SolicitacaoExclusaoRequest;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.in.WebConverter;

@WebConverter
public class SolicitarExclusaoConverter implements Converter<SolicitacaoExclusaoRequest, SolicitacaoExclusao> {

    @Override
    public SolicitacaoExclusao convert(SolicitacaoExclusaoRequest source) {
        return null;
    }
}
