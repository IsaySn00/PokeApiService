package com.digis01.PokeApiService.RestController;

import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonRestController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping()
    public ResponseEntity GetAll(@RequestParam("page") int page) {
        Result result = new Result();

        try {
            result.object = pokemonService.GetAll(page).object;
            result.correct = true;
            result.status = 201;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }
}
