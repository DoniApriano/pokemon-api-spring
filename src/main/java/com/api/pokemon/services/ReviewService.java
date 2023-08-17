package com.api.pokemon.services;

import com.api.pokemon.dto.ReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(Long pokemonId, ReviewDto reviewDto);

    List<ReviewDto> getReviewByPokemonId(Long pokemonId);

    ReviewDto getReviewById(Long reviewId, Long pokemonId);

    ReviewDto updateReview(Long pokemonId, Long reviewId, ReviewDto reviewDto);

    void deleteReview(Long pokemonId, Long reviewId);

}
