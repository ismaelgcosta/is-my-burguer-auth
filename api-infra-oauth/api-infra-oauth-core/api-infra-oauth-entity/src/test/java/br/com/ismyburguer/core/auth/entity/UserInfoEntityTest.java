package br.com.ismyburguer.core.auth.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserInfoEntityTest {

    @Test
    void getCpf_ShouldReturnMaskedCpf() {
        // Arrange
        String cpf = "123.456.789-00";
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setCpf(cpf);

        // Act
        String maskedCpf = userInfoEntity.getCpf();

        // Assert
        assertEquals("123.***.**9-00", maskedCpf);
    }

    @Test
    void getEmail_ShouldReturnMaskedEmail() {
        // Arrange
        String email = "example@example.com";
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setEmail(email);

        // Act
        String maskedEmail = userInfoEntity.getEmail();

        // Assert
        assertEquals("ex*****@example.com", maskedEmail);
    }

    @Test
    void setUsername_ShouldReturnUsername() {
        // Arrange
        String username = "user123";
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUsername(username);

        // Act
        String result = userInfoEntity.getUsername();

        // Assert
        assertEquals(username, result);
    }

    @Test
    void setName_ShouldReturnName() {
        // Arrange
        String name = "John Doe";
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName(name);

        // Act
        String result = userInfoEntity.getName();

        // Assert
        assertEquals(name, result);
    }
}
