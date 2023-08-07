package com.cliente.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteDto {
    
    private Long id;
    
    private String codigo;
    
    @Size(min = 3, max = 255, message = "O Nome deve estar entre 3 e 255 caracteres")
    @NotBlank(message = "Nome do Cliente é obrigatório")
    private String nome;
    
    @Size(min = 14, max = 18, message = "O CNPJ deve ter no mínino 14 caracteres e no máximo 18")
    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;

    @NotBlank(message = "Logradouro é obrigatório")
    private String logradouro;
    
    private String cep;
    
    @NotBlank(message = "Geolocalização é obrigatória")
    private String geolocalizacao;

    public ClienteDto() {
    }

    public ClienteDto(Long id, String codigo, String nome, String cnpj, String logradouro, String cep, String geolocalizacao) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.cnpj = cnpj;
        this.logradouro = logradouro;
        this.cep = cep;
        this.geolocalizacao = geolocalizacao;
    }
    
    
    
}
