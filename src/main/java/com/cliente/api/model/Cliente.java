package com.cliente.api.model;

import com.cliente.api.dto.PageRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@Setter
@Entity
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false, unique = true)
    private String logradouro;

    @Column(nullable = true, unique = true)
    private String cep;

    @Column(nullable = false, unique = true)
    private String geolocalizacao;

    @PrePersist
    @PreUpdate
    private void pre() {
        if (Objects.nonNull(this.codigo) && !this.codigo.isBlank()) {
            this.codigo = this.codigo.trim();
        }
        if (Objects.nonNull(this.cep)) {
            this.cep = this.cep.replaceAll("\\D+", "");
        }
        this.nome = this.nome.trim();
        this.cnpj = this.cnpj.replaceAll("\\D+", "");
        this.logradouro = this.logradouro.trim();
        this.geolocalizacao = this.geolocalizacao.trim();
    }

    public Cliente getClienteFiltro(PageRequestDto pageRequestDto) {
        Cliente cliente = new Cliente();
        if (Objects.isNull(pageRequestDto.getQuery())) {
            pageRequestDto.setQuery("");
        }
        if ("id".equalsIgnoreCase(pageRequestDto.getAtributo()) && !pageRequestDto.getQuery().isBlank()) {
            try {
                cliente.setId(Long.valueOf(pageRequestDto.getQuery()));
            } catch (NumberFormatException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é um identificador válido: "+pageRequestDto.getQuery());
            }
        } else if ("codigo".equalsIgnoreCase(pageRequestDto.getAtributo()) && !pageRequestDto.getQuery().isBlank()) {
            cliente.setCodigo(pageRequestDto.getQuery());
        } else if ("nome".equalsIgnoreCase(pageRequestDto.getAtributo()) && !pageRequestDto.getQuery().isBlank()) {
            cliente.setNome(pageRequestDto.getQuery());
        } else if ("cnpj".equalsIgnoreCase(pageRequestDto.getAtributo()) && !pageRequestDto.getQuery().isBlank()) {
            cliente.setCnpj(pageRequestDto.getQuery());
        } else if ("logradouro".equalsIgnoreCase(pageRequestDto.getAtributo()) && !pageRequestDto.getQuery().isBlank()) {
            cliente.setLogradouro(pageRequestDto.getQuery());
        } else if ("cep".equalsIgnoreCase(pageRequestDto.getAtributo()) && !pageRequestDto.getQuery().isBlank()) {
            cliente.setCep(pageRequestDto.getQuery());
        } else if ("geolocalizacao".equalsIgnoreCase(pageRequestDto.getAtributo()) && !pageRequestDto.getQuery().isBlank()) {
            cliente.setGeolocalizacao(pageRequestDto.getQuery());
        }
        return cliente;
    }
}
