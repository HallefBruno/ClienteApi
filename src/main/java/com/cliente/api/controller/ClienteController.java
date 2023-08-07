package com.cliente.api.controller;

import com.cliente.api.dto.ClienteDto;
import com.cliente.api.dto.PageRequestDto;
import com.cliente.api.model.Cliente;
import com.cliente.api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
@RequiredArgsConstructor
public class ClienteController {
    
    private final ClienteService clienteService;
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastrar Cliente", description = "Cadastra um único cliente no sistema correspondente às informações fornecidas")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!"),
    @ApiResponse(responseCode = "409", description = "Esse usuário já existe!")})
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> criar(@Valid @RequestBody(required = true) final ClienteDto clienteDto) {
        clienteService.salvar(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping(path = "/page")
    @Operation(summary = "Página de clientes", description = "Página de clientes contendo todos os registro")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Página de clientes retornada com sucesso!"),
    @ApiResponse(responseCode = "204", description = "Sem informações para esse cliente!"),
    @ApiResponse(responseCode = "400", description = "Identificador inválido!")})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Page<Cliente>> pageClientes(
        @RequestParam(required = true) int pagina, 
        @RequestParam(required = true) int totalPorPagina, 
        @RequestParam(required = true) String ordenacao, 
        @RequestParam(required = true) String atributo, 
        @RequestParam(required = true) String query) {
        
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setTotalPorPagina(totalPorPagina);
        pageRequestDto.setPagina(pagina);
        pageRequestDto.setAtributo(atributo);
        pageRequestDto.setOrdenacao(ordenacao);
        pageRequestDto.setQuery(query);
        
        Page<Cliente> page = clienteService.pageClientes(pageRequestDto);
        return ResponseEntity.ok(page);
    }
    
    @GetMapping(path = "buscar/{id}")
    @Operation(summary = "Buscar um usuário pelo identificador", description = "Buscar um usuário pelo identificador")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado!"), 
    @ApiResponse(responseCode = "400", description = "Identificador inválido!")})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Cliente> getCliente(@PathVariable(required = true) Long id) {
        return ResponseEntity.ok(clienteService.getCliente(id));
    }
    
    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Exclusão de cliente", description = "Excluir um cliente")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Exclusão realizada com sucesso!"),
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado!"), 
    @ApiResponse(responseCode = "400", description = "Identificador inválido!")})
    @ResponseStatus(value = HttpStatus.OK)
    public void excluir(@PathVariable(required = true) Long id) {
        clienteService.excluir(id);
    }
    
}
