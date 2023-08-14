package com.api.pokemon.services;

import com.api.pokemon.dto.PokemonDto;
import com.api.pokemon.dto.PokemonResponse;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);

    PokemonDto getPokemonById(Long id);

    PokemonDto updatePokemon(PokemonDto pokemonDto, Long id);

    void deletePokemon(Long id);
}
