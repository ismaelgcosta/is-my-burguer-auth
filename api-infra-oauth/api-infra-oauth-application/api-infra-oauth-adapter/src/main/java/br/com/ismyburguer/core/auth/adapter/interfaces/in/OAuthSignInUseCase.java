package br.com.ismyburguer.core.auth.adapter.interfaces.in;

import br.com.ismyburguer.core.auth.entity.Login;
import br.com.ismyburguer.core.auth.entity.UserToken;

public interface OAuthSignInUseCase {

    UserToken signin(Login input);
}
