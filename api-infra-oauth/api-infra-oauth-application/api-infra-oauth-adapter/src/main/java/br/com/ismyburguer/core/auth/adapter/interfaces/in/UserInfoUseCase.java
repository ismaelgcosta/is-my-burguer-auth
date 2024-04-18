package br.com.ismyburguer.core.auth.adapter.interfaces.in;

import br.com.ismyburguer.core.auth.entity.UserInfo;

public interface UserInfoUseCase {

    UserInfo userInfo(String accessToken);
}
