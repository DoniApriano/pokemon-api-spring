package com.api.pokemon.repositories;

import com.api.pokemon.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon,Long> {
}
