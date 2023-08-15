package com.api.pokemon.repositories;

import com.api.pokemon.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByPokemonId(Long pokemonId);

}
