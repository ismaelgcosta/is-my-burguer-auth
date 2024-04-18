package br.com.ismyburguer.auth.core.web.api;

import br.com.ismyburguer.auth.core.web.api.request.ConfirmarCadastroRequest;
import br.com.ismyburguer.core.adapter.in.WebAdapter;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.ConfirmSignUpUseCase;
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
public class ConfirmSignUpAPI {
    private final ConfirmSignUpUseCase confirmSignUpUseCase;

    public ConfirmSignUpAPI(ConfirmSignUpUseCase confirmSignUpUseCase) {
        this.confirmSignUpUseCase = confirmSignUpUseCase;
    }

    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"), method = "Confirmar Cadastro de Usuário", description = "Confirmar Cadastro de Usuário")
    @PostMapping("/sign-up/confirm")
    public void login(@Valid @RequestBody ConfirmarCadastroRequest request) {
        confirmSignUpUseCase.confirmarNovoUsuario(
                request.getCpf(),
                request.getPassword(),
                request.getCode()
        );
    }

}