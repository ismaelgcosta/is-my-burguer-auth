package br.com.ismyburguer.cliente.usecase.impl.steps;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.CadastrarClienteRepository;
import br.com.ismyburguer.cliente.gateway.out.ConsultarClienteRepository;
import br.com.ismyburguer.cliente.usecase.impl.CadastrarClienteUseCaseImpl;
import br.com.ismyburguer.core.exception.BusinessException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CadastrarClienteSteps {

    @Mock
    private CadastrarClienteRepository cadastrarClienteRepository;

    @Mock
    private ConsultarClienteRepository consultarClienteRepository;

    @InjectMocks
    private CadastrarClienteUseCaseImpl cadastrarClienteUseCase;

    private Cliente cliente;
    private UUID clienteId;
    private Exception excecao;

    @Given("que o email do cliente não está cadastrado")
    public void que_o_email_do_cliente_nao_esta_cadastrado() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(new Cliente.Nome("nome"), new Cliente.Email("email@example.com"), new Cliente.CPF("12345678909"));
        when(consultarClienteRepository.obterPeloEmail(cliente.getEmail().getEndereco())).thenReturn(Optional.empty());
        when(cadastrarClienteRepository.salvarCliente(any())).thenReturn(UUID.randomUUID());
    }

    @Given("que o email do cliente já está cadastrado")
    public void que_o_email_do_cliente_ja_esta_cadastrado() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(new Cliente.Nome("nome"), new Cliente.Email("email@example.com"), new Cliente.CPF("12345678909"));
        when(consultarClienteRepository.obterPeloEmail(cliente.getEmail().getEndereco()))
                .thenReturn(Optional.of(EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class)));
    }

    @Given("que o username do cliente já está cadastrado")
    public void que_o_username_do_cliente_ja_esta_cadastrado() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(new Cliente.Nome("nome"), new Cliente.Email("email@example.com"), new Cliente.CPF("12345678909"));
        cliente.setUsername(new Cliente.Username("username"));
        when(consultarClienteRepository.existsByUsername(cliente.getUsername().get().getUsername())).thenReturn(true);
    }

    @Given("que o CPF do cliente já está cadastrado")
    public void que_o_cpf_do_cliente_ja_esta_cadastrado() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(new Cliente.Nome("nome"), new Cliente.Email("email@example.com"), new Cliente.CPF("12345678909"));
        when(consultarClienteRepository.obterPeloCpf(cliente.getCpf().get().getNumero()))
                .thenReturn(Optional.of(EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class)));
    }

    @When("eu cadastro um novo cliente")
    public void eu_cadastro_um_novo_cliente() {
        clienteId = cadastrarClienteUseCase.cadastrar(cliente);
    }

    @When("eu tento cadastrar um novo cliente")
    public void eu_tento_cadastrar_um_novo_cliente() {
        excecao = assertThrows(BusinessException.class, () -> cadastrarClienteUseCase.cadastrar(cliente));
    }

    @Then("o ID do cliente deve ser retornado")
    public void o_id_do_cliente_deve_ser_retornado() {
        assertNotNull(clienteId);
    }

    @Then("o email do cliente deve ser consultado")
    public void o_email_do_cliente_deve_ser_consultado() {
        verify(consultarClienteRepository, times(1)).obterPeloEmail(cliente.getEmail().getEndereco());
    }

    @Then("o cliente deve ser salvo")
    public void o_cliente_deve_ser_salvo() {
        verify(cadastrarClienteRepository, times(1)).salvarCliente(cliente);
    }

    @Then("uma exceção de negócio deve ser lançada")
    public void uma_excecao_de_negocio_deve_ser_lancada() {
        assertNotNull(excecao);
    }

    @Then("o username do cliente deve ser consultado")
    public void o_username_do_cliente_deve_ser_consultado() {
        verify(consultarClienteRepository, times(1)).existsByUsername(cliente.getUsername().get().getUsername());
    }

    @Then("o cliente não deve ser salvo")
    public void o_cliente_nao_deve_ser_salvo() {
        verify(cadastrarClienteRepository, never()).salvarCliente(cliente);
    }

    @Then("o CPF do cliente deve ser consultado")
    public void o_cpf_do_cliente_deve_ser_consultado() {
        verify(consultarClienteRepository, times(1)).obterPeloCpf(cliente.getCpf().get().getNumero());
    }
}
