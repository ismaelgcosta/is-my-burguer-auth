package br.com.ismyburguer.core.auth.adapter.interfaces.in;

import br.com.ismyburguer.core.auth.entity.ConfirmSignUp;

import java.util.Map;

public interface OAuthConfirmSignUpUseCase {
    Map<String, Object> confirmSignUp(ConfirmSignUp input);
}
