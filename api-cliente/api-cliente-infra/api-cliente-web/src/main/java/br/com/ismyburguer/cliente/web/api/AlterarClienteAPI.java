package br.com.ismyburguer.cliente.web.api;


import br.com.ismyburguer.cliente.adapter.interfaces.in.AlterarClienteUseCase;
import br.com.ismyburguer.cliente.web.api.converter.AlterarClienteRequestConverter;
import br.com.ismyburguer.cliente.web.api.request.AlterarClienteRequest;
import br.com.ismyburguer.core.adapter.in.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cliente", description = "Gerenciamento de Clientes")
@WebAdapter
@RequestMapping("/clientes")
public class AlterarClienteAPI {
    private final AlterarClienteUseCase useCase;
    private final AlterarClienteRequestConverter converter;

    public AlterarClienteAPI(AlterarClienteUseCase useCase,
                             AlterarClienteRequestConverter converter) {
        this.useCase = useCase;
        this.converter = converter;
    }

    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"), method = "Alterar Cliente", description = "Alterar Cliente")
    @PutMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarCliente(
            @PathVariable @Valid @UUID(message = "O código do cliente informado está num formato inválido") String clienteId,
            @RequestBody AlterarClienteRequest request
    ) {
        useCase.alterar(clienteId, converter.convert(request));
    }

}
