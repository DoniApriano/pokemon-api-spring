package com.api.pokemon.dto;

import lombok.Data;

@Data
public class ReviewDto {

    private Long id;
    private String title;
    private String content;
    private int stars;

}
