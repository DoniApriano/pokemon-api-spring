package com.api.pokemon.services;

import com.api.pokemon.dto.ReviewDto;
import org.springframework.stereotype.Service;

public interface ReviewService {

    ReviewDto createReview(Long pokemonId, ReviewDto reviewDto);

}
