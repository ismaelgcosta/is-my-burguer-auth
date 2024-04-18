package br.com.ismyburguer.cliente.web.api;


import br.com.ismyburguer.cliente.adapter.interfaces.in.CadastrarClienteUseCase;
import br.com.ismyburguer.cliente.web.api.converter.CadastrarClienteRequestConverter;
import br.com.ismyburguer.cliente.web.api.request.CriarClienteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Cliente", description = "Gerenciamento de Clientes")
@RestController
@RequestMapping("/clientes")
public class CadastrarClienteAPI {
    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final CadastrarClienteRequestConverter cadastrarClienteRequestConverter;

    public CadastrarClienteAPI(CadastrarClienteUseCase cadastrarClienteUseCase,
                               CadastrarClienteRequestConverter cadastrarClienteRequestConverter) {
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.cadastrarClienteRequestConverter = cadastrarClienteRequestConverter;
    }

    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"), method = "Cadastrar Cliente", description = "Cadastrar Cliente")
    @PostMapping
    public UUID cadastrarCliente(@RequestBody CriarClienteRequest criarClienteRequest) {
        return cadastrarClienteUseCase.cadastrar(cadastrarClienteRequestConverter.convert(criarClienteRequest));
    }

}
