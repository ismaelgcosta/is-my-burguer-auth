package br.com.ismyburguer.core.auth.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {

    @SerializedName("id_token")
    private String idToken;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("expires_in")
    private Integer expiresIn;

    @SerializedName("token_type")
    private String tokenType;
}
