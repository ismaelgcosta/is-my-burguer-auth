package br.com.ismyburguer.auth.core.web.api;

import br.com.ismyburguer.core.adapter.in.WebAdapter;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.UserInfoUseCase;
import br.com.ismyburguer.core.auth.entity.UserInfo;
import br.com.ismyburguer.core.auth.entity.UserInfoEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Autenticação", description = "Autenticação do Usuário")
@WebAdapter
@RequestMapping("/user")
public class UserInfoAPI {
    private final UserInfoUseCase userInfoUseCase;

    public UserInfoAPI(UserInfoUseCase userInfoUseCase) {
        this.userInfoUseCase = userInfoUseCase;
    }

    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"), method = "Informações do Usuário Autenticado", description = "Informações do Usuário Autenticado")
    @PostMapping("/info")
    public UserInfoEntity userInfo() {
        UserInfo userInfoResponse = userInfoUseCase.userInfo(((Jwt) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getTokenValue());
        return new UserInfoEntity(
                userInfoResponse.getUsername(),
                userInfoResponse.getCpf(),
                userInfoResponse.getName(),
                userInfoResponse.getEmail()
        );
    }

}