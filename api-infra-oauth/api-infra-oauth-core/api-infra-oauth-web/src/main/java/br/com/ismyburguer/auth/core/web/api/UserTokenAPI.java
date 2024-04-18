package br.com.ismyburguer.auth.core.web.api;

import br.com.ismyburguer.core.adapter.in.WebAdapter;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.UserSignInUseCase;
import br.com.ismyburguer.core.auth.entity.Login;
import br.com.ismyburguer.core.auth.entity.UserToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Autenticação", description = "Autenticação do Usuário")
@WebAdapter
@RequestMapping("/user")
public class UserTokenAPI {
    private final UserSignInUseCase useCase;

    public UserTokenAPI(UserSignInUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"), method = "Gerar Token", description = "Gerar Token")
    @PostMapping("/token")
    public UserToken login(@Valid @RequestBody Login request) {
        return useCase.login(request.getUsername(), request.getPassword());
    }

}