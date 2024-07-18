package br.com.ismyburguer.cliente.gateway.out;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import jakarta.validation.Valid;

import java.util.UUID;

public interface SolicitacaoExclusaoRepository {

    UUID salvarSolicitacao(@Valid SolicitacaoExclusao solicitacaoExclusao);




}
