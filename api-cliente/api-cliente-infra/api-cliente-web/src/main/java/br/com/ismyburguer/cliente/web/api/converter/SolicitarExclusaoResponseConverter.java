package br.com.ismyburguer.cliente.web.api.converter;

import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.web.api.response.SolicitacaoExclusaoResponse;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.in.WebConverter;

@WebConverter
public class SolicitarExclusaoResponseConverter implements Converter<SolicitacaoExclusao, SolicitacaoExclusaoResponse> {

    @Override
    public SolicitacaoExclusaoResponse convert(SolicitacaoExclusao source) {
        return null;
    }

}
