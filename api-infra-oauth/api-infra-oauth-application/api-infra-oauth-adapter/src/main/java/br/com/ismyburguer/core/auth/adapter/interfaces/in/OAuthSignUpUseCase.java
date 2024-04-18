package br.com.ismyburguer.core.auth.adapter.interfaces.in;

import br.com.ismyburguer.core.auth.entity.SignUp;

import java.util.Map;

public interface OAuthSignUpUseCase {

    Map<String, Object> signUp(SignUp input);
}
