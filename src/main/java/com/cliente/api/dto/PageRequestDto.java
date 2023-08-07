
package com.cliente.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequestDto {
    
    private int totalPorPagina;
    private int pagina;
    private String ordenacao;
    private String atributo;
    private String query;

    public PageRequestDto() {
    }

    public PageRequestDto(int totalPorPagina, int pagina, String ordenacao, String atributo, String query) {
        this.totalPorPagina = totalPorPagina;
        this.pagina = pagina;
        this.ordenacao = ordenacao;
        this.atributo = atributo;
        this.query = query;
    }
    
}
