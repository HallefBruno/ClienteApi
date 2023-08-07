package com.cliente.api.service;

import com.cliente.api.dto.ClienteDto;
import com.cliente.api.dto.PageRequestDto;
import com.cliente.api.model.Cliente;
import com.cliente.api.repository.ClienteRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void salvar(ClienteDto clienteDto) {
        if(Objects.isNull(clienteDto.getId())) {
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(clienteDto, cliente);
            verificarCadastro(cliente);
            clienteRepository.save(cliente);
        } else {
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(clienteDto, cliente);
            clienteRepository.save(cliente);
        }
    }

    public Page<Cliente> pageClientes(PageRequestDto pageRequestDto) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnoreCase("nome", "logradouro").withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Cliente> example = Example.of(new Cliente().getClienteFiltro(pageRequestDto), exampleMatcher);
        PageRequest pageRequest = PageRequest.of(pageRequestDto.getPagina(),pageRequestDto.getTotalPorPagina(),Sort.Direction.fromString(pageRequestDto.getOrdenacao()),pageRequestDto.getAtributo());
        Page<Cliente> page = clienteRepository.findAll(example, pageRequest);
        return page;
    }

    public Cliente getCliente(Long id) {
        if (Objects.isNull(id) || id < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identificador inválido!");
        }
        Cliente clienteAtual = clienteRepository.findById(id).map(cliente -> {
            return cliente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum cliente encontrado com esse identificador!"));
        return clienteAtual;
    }

    @Transactional
    public void excluir(Long id) {
        if (Objects.isNull(id) || id < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identificador inválido!");
        }
        clienteRepository.findById(id).map(cliente -> {
            clienteRepository.deleteById(id);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum cliente encontrado com esse identificador!"));
    }

    private void verificarCadastro(Cliente cliente) {
        String mensagem;
        if (clienteRepository.existsByCodigo(cliente.getCodigo())) {
            mensagem = "Já existe um cadastro com esse código!";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagem);
        } else if (clienteRepository.existsByCnpj(cliente.getCnpj() != null ? cliente.getCnpj().replaceAll("\\D+", "") : null)) {
            mensagem = "Já existe um cadastro com esse CNPJ!";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagem);
        } else if (clienteRepository.existsByCep(cliente.getCep() != null ? cliente.getCep().replaceAll("\\D+", "") : null)) {
            mensagem = "Já existe um cadastro com esse CEP!";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagem);
        } else if (clienteRepository.existsByGeolocalizacao(cliente.getGeolocalizacao())) {
            mensagem = "Já existe um cadastro com essa Geolocalização!";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagem);
        } else if (clienteRepository.existsByLogradouroIgnoreCase(cliente.getLogradouro())) {
            mensagem = "Já existe um cadastro com esse Logradouro!";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagem);
        }
    }

}
