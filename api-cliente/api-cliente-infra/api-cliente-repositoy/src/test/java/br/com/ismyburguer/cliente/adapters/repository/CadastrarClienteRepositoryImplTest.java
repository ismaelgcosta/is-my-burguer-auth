package br.com.ismyburguer.cliente.adapters.repository;

import br.com.ismyburguer.cliente.adapters.converter.ClienteToClienteModelConverter;
import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastrarClienteRepositoryImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteToClienteModelConverter converter;

    @InjectMocks
    private CadastrarClienteRepositoryImpl cadastrarClienteRepository;

    @Test
    public void testarSalvarCliente() {
        // Arrange
        Cliente cliente = new Cliente(new Cliente.Nome("nome"));
        ClienteModel clienteModel = spy(new ClienteModel());
        UUID clienteId = UUID.randomUUID();

        when(converter.convert(cliente)).thenReturn(clienteModel);
        when(clienteRepository.save(clienteModel)).thenReturn(clienteModel);
        when(clienteModel.getClienteId()).thenReturn(clienteId);

        // Act
        UUID resultado = cadastrarClienteRepository.salvarCliente(cliente);

        // Assert
        assertEquals(clienteId, resultado);
        verify(converter, times(1)).convert(cliente);
        verify(clienteRepository, times(1)).save(clienteModel);
    }
}
