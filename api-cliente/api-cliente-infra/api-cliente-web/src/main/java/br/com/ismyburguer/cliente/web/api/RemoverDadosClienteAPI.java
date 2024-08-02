package br.com.ismyburguer.cliente.web.api;

import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarSolicitacaoRemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.RemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.CriarSolicitacaoRemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.web.api.converter.SolicitarExclusaoConverter;
import br.com.ismyburguer.cliente.web.api.converter.SolicitarExclusaoResponseConverter;
import br.com.ismyburguer.cliente.web.api.request.SolicitacaoExclusaoRequest;
import br.com.ismyburguer.cliente.web.api.response.SolicitacaoExclusaoResponse;
import br.com.ismyburguer.core.adapter.in.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cliente", description = "Gerenciamento de Clientes")
@WebAdapter
@RequestMapping("/clientes")
@AllArgsConstructor
public class RemoverDadosClienteAPI {

    private final CriarSolicitacaoRemocaoDadosClienteUseCase criarSolicitacaoRemocaoDadosClienteUseCase;
    private final ConsultarSolicitacaoRemocaoDadosClienteUseCase consultarSolicitacaoRemocaoDadosClienteUseCase;
    private final RemocaoDadosClienteUseCase remocaoDadosClienteUseCase;
    private final SolicitarExclusaoResponseConverter solicitarExclusaoResponseConverter;
    private final SolicitarExclusaoConverter solicitarExclusaoConverter;

    @Operation(security = @SecurityRequirement(name = "Bearer-Authentication"), method = "Solicitar exclusão dos Dados segundo LGPD", description = "Solicitar exclusão dos Dados segundo LGPD")
    @PostMapping("/lgpd/solicitacao")
    @ResponseStatus(HttpStatus.CREATED)
    public void solicitarExclusaoDeCadastro(@RequestBody SolicitacaoExclusaoRequest solicitacao) {
        criarSolicitacaoRemocaoDadosClienteUseCase.solicitar(solicitarExclusaoConverter.convert(solicitacao));
    }

    @Operation(security = @SecurityRequirement(name = "Bearer-Authentication"), method = "Listar Solicitações de exclusão dos Dados segundo LGPD", description = "Listar Solicitações de exclusão dos Dados segundo LGPD")
    @GetMapping("/lgpd/solicitacao/{cpf}")
    public List<SolicitacaoExclusaoResponse> consultarSolicitarExclusaoDeCadastro(@PathVariable(name = "cpf") @CPF(message = "Informe um CPF válido no formato 999.999.999-99") String cpf) {
        return consultarSolicitacaoRemocaoDadosClienteUseCase.consultar(cpf).stream().map(solicitarExclusaoResponseConverter::convert).toList();
    }

    @Operation(security = @SecurityRequirement(name = "Bearer-Authentication"), method = "Solicitar exclusão dos Dados segundo LGPD", description = "Solicitar exclusão dos Dados segundo LGPD")
    @DeleteMapping("/lgpd/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclusaoDeCadastro(@PathVariable(name = "cpf") @CPF(message = "Informe um CPF válido no formato 999.999.999-99") String cpf) {
        remocaoDadosClienteUseCase.remover(new ConsultarClienteUseCase.ConsultaClientePorCpf(cpf));
    }

}
