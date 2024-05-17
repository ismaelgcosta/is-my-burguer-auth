package br.com.ismyburguer.cliente.adapters.repository;

import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlterarClienteRepositoryImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private AlterarClienteRepositoryImpl alterarClienteRepository;

    @Test
    public void testarAlterarClienteExistente() {
        // Arrange
        UUID clienteId = UUID.randomUUID();
        Cliente cliente = new Cliente(new Cliente.Nome("John", "Doe"));

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setClienteId(clienteId);

        when(clienteRepository.existsById(clienteId)).thenReturn(true);
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteModel));

        // Act
        alterarClienteRepository.alterarCliente(clienteId.toString(), cliente);

        // Assert
        verify(clienteRepository, times(1)).save(clienteModel);
        assertEquals("John", clienteModel.getNome());
        assertEquals("Doe", clienteModel.getSobrenome());
    }

    @Test
    public void testarAlterarClienteInexistente() {
        // Arrange
        UUID clienteId = UUID.randomUUID();
        Cliente cliente = new Cliente(new Cliente.Nome("John", "Doe"));

        when(clienteRepository.existsById(clienteId)).thenReturn(false);

        // Act & Assert
        assertThrows(EntityNotFoundException.class,
                () -> alterarClienteRepository.alterarCliente(clienteId.toString(), cliente));
        verify(clienteRepository, never()).findById(any());
        verify(clienteRepository, never()).save(any());
    }
}
