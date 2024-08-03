package br.com.ismyburguer.cliente.adapters.repository;

import br.com.ismyburguer.cliente.adapters.converter.ClienteModelToClienteConverter;
import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsultarClienteRepositoryImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteModelToClienteConverter converter;

    @InjectMocks
    private ConsultarClienteRepositoryImpl consultarClienteRepository;

    @Test
    public void testarObterPeloClienteId() {
        // Arrange
        UUID clienteId = UUID.randomUUID();
        ClienteModel clienteModel = new ClienteModel();
        Cliente cliente = new Cliente(new Cliente.Nome("nome"));

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteModel));
        when(converter.convert(clienteModel)).thenReturn(cliente);

        // Act
        Optional<Cliente> resultado = consultarClienteRepository.obterPeloClienteId(clienteId);

        // Assert
        assertEquals(cliente, resultado.get());
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(converter, times(1)).convert(clienteModel);
    }

    @Test
    public void testarObterPeloEmail() {
        // Arrange
        String email = "example@example.com";
        ClienteModel clienteModel = new ClienteModel();
        Cliente cliente = new Cliente(new Cliente.Nome("nome"));

        when(clienteRepository.findByEmail(email)).thenReturn(Optional.of(clienteModel));
        when(converter.convert(clienteModel)).thenReturn(cliente);

        // Act
        Optional<Cliente> resultado = consultarClienteRepository.obterPeloEmail(email);

        // Assert
        assertEquals(cliente, resultado.get());
        verify(clienteRepository, times(1)).findByEmail(email);
        verify(converter, times(1)).convert(clienteModel);
    }

    @Test
    public void testarObterPeloClienteUsername() {
        // Arrange
        String username = "example_username";
        ClienteModel clienteModel = new ClienteModel();
        Cliente cliente = new Cliente(new Cliente.Nome("nome"));

        when(clienteRepository.findByUsername(username)).thenReturn(Optional.of(clienteModel));
        when(converter.convert(clienteModel)).thenReturn(cliente);

        // Act
        Optional<Cliente> resultado = consultarClienteRepository.obterPeloClienteUsername(username);

        // Assert
        assertEquals(cliente, resultado.get());
        verify(clienteRepository, times(1)).findByUsername(username);
        verify(converter, times(1)).convert(clienteModel);
    }

    @Test
    public void testarObterPeloCpf() {
        // Arrange
        String cpf = "12345678901";
        ClienteModel clienteModel = new ClienteModel();
        Cliente cliente = new Cliente(new Cliente.Nome("nome"));

        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteModel));
        when(converter.convert(clienteModel)).thenReturn(cliente);

        // Act
        Optional<Cliente> resultado = consultarClienteRepository.obterPeloCpf(cpf);

        // Assert
        assertEquals(cliente, resultado.get());
        verify(clienteRepository, times(1)).findByCpf(cpf);
        verify(converter, times(1)).convert(clienteModel);
    }

    @Test
    public void testarExistsById() {
        // Arrange
        UUID clienteId = UUID.randomUUID();

        when(clienteRepository.existsById(clienteId)).thenReturn(true);

        // Act
        boolean resultado = consultarClienteRepository.existsById(clienteId);

        // Assert
        assertTrue(resultado);
        verify(clienteRepository, times(1)).existsById(clienteId);
    }

    @Test
    public void testarExistsByUsername() {
        // Arrange
        String username = "example_username";

        when(clienteRepository.existsByUsername(username)).thenReturn(true);

        // Act
        boolean resultado = consultarClienteRepository.existsByUsername(username);

        // Assert
        assertTrue(resultado);
        verify(clienteRepository, times(1)).existsByUsername(username);
    }

    @Test
    public void testarExistsByCpf() {
        // Arrange
        String cpf = "12345678901";

        when(clienteRepository.existsByCpf(cpf)).thenReturn(true);

        // Act
        boolean resultado = consultarClienteRepository.existsByCpf(cpf);

        // Assert
        assertTrue(resultado);
        verify(clienteRepository, times(1)).existsByCpf(cpf);
    }

}
