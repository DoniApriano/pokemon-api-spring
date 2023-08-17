package com.api.pokemon.controllers;

import com.api.pokemon.dto.ReviewDto;
import com.api.pokemon.services.ReviewService;
import com.api.pokemon.services.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "pokemonId") Long pokemonId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(pokemonId,reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public List<ReviewDto> getReviewsByPokemonId(@PathVariable(value = "pokemonId") Long pokemonId) {
        return reviewService.getReviewByPokemonId(pokemonId);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewId(@PathVariable(value = "pokemonId") Long pokemonId, @PathVariable(value = "reviewId") Long reviewId) {
        ReviewDto reviewDto = reviewService.getReviewById(pokemonId,reviewId);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @PutMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewId(@PathVariable(value = "pokemonId") Long pokemonId, @PathVariable(value = "reviewId") Long reviewId,@RequestBody ReviewDto reviewDto) {
        ReviewDto updateReview = reviewService.updateReview(pokemonId,reviewId,reviewDto);
        return new ResponseEntity<>(updateReview, HttpStatus.OK);
    }

    @DeleteMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId") Long pokemonId, @PathVariable(value = "reviewId") Long reviewId,@RequestBody ReviewDto reviewDto) {
        reviewService.deleteReview(pokemonId,reviewId);
        return new ResponseEntity<>("Review berhasil dihapus",HttpStatus.OK);
    }
}
