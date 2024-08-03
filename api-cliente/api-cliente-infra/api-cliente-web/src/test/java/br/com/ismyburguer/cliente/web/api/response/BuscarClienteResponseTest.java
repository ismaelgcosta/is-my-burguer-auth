package br.com.ismyburguer.cliente.web.api.response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuscarClienteResponseTest {

    @Test
    void testGetCpf() {
        // Arrange
        BuscarClienteResponse response = new BuscarClienteResponse();
        response.setCpf("123.456.789-09");

        // Act
        String result = response.getCpf();

        // Assert
        assertEquals("123.***.**9-09", result);
    }

    @Test
    void testGetEmail() {
        // Arrange
        BuscarClienteResponse response = new BuscarClienteResponse();
        response.setEmail("example@example.com");

        // Act
        String result = response.getEmail();

        // Assert
        assertEquals("ex*****@example.com", result);
    }
}
