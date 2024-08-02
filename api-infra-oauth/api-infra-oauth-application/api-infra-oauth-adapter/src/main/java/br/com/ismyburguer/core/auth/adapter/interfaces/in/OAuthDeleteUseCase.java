package br.com.ismyburguer.core.auth.adapter.interfaces.in;

import br.com.ismyburguer.core.auth.entity.Delete;
import br.com.ismyburguer.core.auth.entity.Login;
import br.com.ismyburguer.core.auth.entity.UserToken;

public interface OAuthDeleteUseCase {

    void delete(Delete delete);
}
