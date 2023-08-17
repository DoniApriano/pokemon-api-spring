package com.api.pokemon.services.impl;

import com.api.pokemon.dto.ReviewDto;
import com.api.pokemon.exceptions.PokemonNotFoundException;
import com.api.pokemon.exceptions.ReviewNotFoundException;
import com.api.pokemon.models.Pokemon;
import com.api.pokemon.models.Review;
import com.api.pokemon.repositories.PokemonRepository;
import com.api.pokemon.repositories.ReviewRepository;
import com.api.pokemon.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDto createReview(Long pokemonId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon id tidak ditemukan"));

        review.setPokemon(pokemon);

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);

    }

    @Override
    public List<ReviewDto> getReviewByPokemonId(Long pokemonId) {
        List<Review> reviews = reviewRepository.findByPokemonId(pokemonId);

        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(Long reviewId, Long pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon Id tidak ditemukan"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review id tidak ditemukan"));

        if (review.getPokemon().getId() !=pokemon.getId()) {
            throw new RuntimeException("Review ini tidak dengan pokemon");
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(Long pokemonId, Long reviewId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon Id tidak ditemukan"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review id tidak ditemukan"));

        if (review.getPokemon().getId() !=pokemon.getId()) {
            throw new RuntimeException("Review ini tidak dengan pokemon");
        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        Review updateReview = reviewRepository.save(review);

        return mapToDto(updateReview);
    }

    @Override
    public void deleteReview(Long pokemonId, Long reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon Id tidak ditemukan"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review id tidak ditemukan"));

        if (review.getPokemon().getId() !=pokemon.getId()) {
            throw new RuntimeException("Review ini tidak dengan pokemon");
        }

        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());

        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();

        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        return review;
    }
}
