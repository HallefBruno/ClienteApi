package com.cliente.api;

import com.cliente.api.dto.ClienteDto;
import com.cliente.api.model.Cliente;
import com.cliente.api.repository.ClienteRepository;
import com.cliente.api.service.ClienteService;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static  org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class ClienteApiApplicationTests {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUpMocks() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindById() {
        Cliente cliente = mockCliente();
        cliente.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        var result = clienteService.getCliente(1L);
        assertNotNull(result);
        assertEquals("75102-130", result.getCep());
        assertEquals("71.275.562/0001-96", result.getCnpj());
        assertEquals("-16.7008956,-49.2534187", result.getGeolocalizacao());
        assertEquals("R. 135, 419 - St. Marista, Goiânia - GO, 74180-020, Brasil", result.getLogradouro());
        assertEquals("Judith", result.getNome());
    }
    
    @Test
    void excluir() {
        Cliente cliente = mockCliente();
        cliente.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        clienteService.excluir(1L);
        verify(clienteRepository,times(1)).deleteById(1L);
    }
    
    private Cliente mockCliente() {
        Cliente cliente = new Cliente();
        cliente.setCodigo("123");
        cliente.setCep("75102-130");
        cliente.setCnpj("71.275.562/0001-96");
        cliente.setGeolocalizacao("-16.7008956,-49.2534187");
        cliente.setLogradouro("R. 135, 419 - St. Marista, Goiânia - GO, 74180-020, Brasil");
        cliente.setNome("Judith");
        return cliente;
    }
    
    private Page<Cliente> mockPage(PageRequest pageRequest) {
        List<Cliente> list = List.of(mockCliente());
        Page<Cliente> page = new PageImpl<>(List.of(mockCliente()), pageRequest, list.size());
        return page;
    }
    
    private ClienteDto mockClientedto() {
        ClienteDto cliente = new ClienteDto();
        cliente.setCodigo("123");
        cliente.setCep("75102-130");
        cliente.setCnpj("71.275.562/0001-96");
        cliente.setGeolocalizacao("-16.7008956,-49.2534187");
        cliente.setLogradouro("R. 135, 419 - St. Marista, Goiânia - GO, 74180-020, Brasil");
        cliente.setNome("Judith");
        return cliente;
    }

}
