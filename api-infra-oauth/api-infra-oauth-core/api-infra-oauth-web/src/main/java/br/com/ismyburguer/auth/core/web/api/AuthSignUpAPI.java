package br.com.ismyburguer.auth.core.web.api;

import br.com.ismyburguer.auth.core.web.api.converter.BuscarClienteAuthConverter;
import br.com.ismyburguer.auth.core.web.api.converter.CadastrarUsuarioRequestConverter;
import br.com.ismyburguer.auth.core.web.api.request.UserSignUpRequest;
import br.com.ismyburguer.core.adapter.in.WebAdapter;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.UserSignUpUseCase;
import br.com.ismyburguer.core.auth.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Autenticação", description = "Autenticação do Usuário")
@WebAdapter
@RequestMapping("/auth")
public class AuthSignUpAPI {
    private final CadastrarUsuarioRequestConverter converter;
    private final BuscarClienteAuthConverter buscarClienteAuthConverter;
    private final UserSignUpUseCase useCase;

    public AuthSignUpAPI(CadastrarUsuarioRequestConverter converter, BuscarClienteAuthConverter buscarClienteAuthConverter, UserSignUpUseCase useCase) {
        this.converter = converter;
        this.buscarClienteAuthConverter = buscarClienteAuthConverter;
        this.useCase = useCase;
    }

    @Operation(security = @SecurityRequirement(name = "Bearer-Authentication"), method = "Cadastrar Usuário", description = "Cadastrar Usuário")
    @PostMapping("/sign-up")
    public User login(@Valid @RequestBody UserSignUpRequest request) {
        return buscarClienteAuthConverter.convert(useCase.cadastrarNovoUsuario(converter.convert(request), request.getPassword()));
    }

}