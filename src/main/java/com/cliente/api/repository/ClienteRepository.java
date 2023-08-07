package com.cliente.api.repository;

import com.cliente.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    boolean existsByCodigo(String cod);
    boolean existsByCnpj(String cnpj);
    boolean existsByCep(String cep);
    boolean existsByLogradouroIgnoreCase(String logradouro);
    boolean existsByGeolocalizacao(String geolocalizacao);
    
}
