package com.api.pokemon.controllers;

import com.api.pokemon.dto.PokemonDto;
import com.api.pokemon.dto.PokemonResponse;
import com.api.pokemon.models.Pokemon;
import com.api.pokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping("/pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto),HttpStatus.CREATED);
    }

    @GetMapping("/pokemon")
    public PokemonResponse getPokemons(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo,pageSize),HttpStatus.OK ).getBody();
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonDto> pokemonDetail(@PathVariable Long id){
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    @PutMapping("/pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") Long id){
        PokemonDto response = pokemonService.updatePokemon(pokemonDto,id);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") Long id){
        pokemonService.deletePokemon(id);
        return new ResponseEntity<>("Pokemon Delete", HttpStatus.OK);
    }
}
