package br.com.ismyburguer.core.auth.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCredentialsLogin implements Serializable {

    @SerializedName(value = "client_id")
    private String clientId;

    @SerializedName(value = "client_secret")
    private String clientSecret;

}