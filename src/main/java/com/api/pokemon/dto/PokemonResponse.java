package com.api.pokemon.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {

    private List<PokemonDto> contents;
    private long pageNo;
    private long pageSize;
    private long totalElements;
    private long totalPages;
    private boolean last;

}
