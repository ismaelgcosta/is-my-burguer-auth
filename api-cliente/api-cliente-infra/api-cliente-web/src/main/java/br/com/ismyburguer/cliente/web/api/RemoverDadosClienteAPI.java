package br.com.ismyburguer.cliente.web.api;


import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.RemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.SolicitacaoRemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.web.api.converter.SolicitarExclusaoConverter;
import br.com.ismyburguer.cliente.web.api.converter.SolicitarExclusaoResponseConverter;
import br.com.ismyburguer.cliente.web.api.request.SolicitacaoExclusaoRequest;
import br.com.ismyburguer.cliente.web.api.response.SolicitacaoExclusaoResponse;
import br.com.ismyburguer.core.adapter.in.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cliente", description = "Gerenciamento de Clientes")
@WebAdapter
@RequestMapping("/clientes")
public class RemoverDadosClienteAPI {
    private final SolicitacaoRemocaoDadosClienteUseCase solicitacaoRemocaoDadosClienteUseCase;
    private final RemocaoDadosClienteUseCase remocaoDadosClienteUseCase;
    private final SolicitarExclusaoResponseConverter solicitarExclusaoResponseConverter;
    private final SolicitarExclusaoConverter solicitarExclusaoConverter;

    public RemoverDadosClienteAPI(SolicitacaoRemocaoDadosClienteUseCase solicitacaoRemocaoDadosClienteUseCase,
                                  RemocaoDadosClienteUseCase remocaoDadosClienteUseCase,
                                  SolicitarExclusaoResponseConverter solicitarExclusaoResponseConverter,
                                  SolicitarExclusaoConverter solicitarExclusaoConverter) {
        this.solicitacaoRemocaoDadosClienteUseCase = solicitacaoRemocaoDadosClienteUseCase;
        this.remocaoDadosClienteUseCase = remocaoDadosClienteUseCase;
        this.solicitarExclusaoResponseConverter = solicitarExclusaoResponseConverter;
        this.solicitarExclusaoConverter = solicitarExclusaoConverter;
    }

    @Operation(security = @SecurityRequirement(name = "Bearer-Authentication"), method = "Solicitar exclusão dos Dados segundo LGPD", description = "Solicitar exclusão dos Dados segundo LGPD")
    @PostMapping("/lgpd/solicitacao")
    public SolicitacaoExclusaoResponse solicitarExclusaoDeCadastro(@RequestBody SolicitacaoExclusaoRequest solicitacao) {
        return solicitarExclusaoResponseConverter.convert(solicitacaoRemocaoDadosClienteUseCase.solicitar(solicitarExclusaoConverter.convert(solicitacao)));
    }

    @Operation(security = @SecurityRequirement(name = "Bearer-Authentication"), method = "Solicitar exclusão dos Dados segundo LGPD", description = "Solicitar exclusão dos Dados segundo LGPD")
    @DeleteMapping("/lgpd/{cpf}")
    public void exclusaoDeCadastro(@PathVariable(name = "cpf") @CPF(message = "Informe um CPF válido no formato 999.999.999-99") String cpf) {
        remocaoDadosClienteUseCase.remover(new ConsultarClienteUseCase.ConsultaClientePorCpf(cpf));
    }

}
