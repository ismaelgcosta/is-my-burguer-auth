package br.com.ismyburguer.cliente.gateway.out;

import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;

import java.util.List;

public interface ConsultarSolicitacaoExclusaoRepository {

    List<SolicitacaoExclusao> listarSolicitacoes(String cpf);




}
