package com.digis01.PokeApiService.RestController;

import com.digis01.PokeApiService.DTO.PokemonDetailDTO;
import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonRestController {
    
    @Autowired
    private PokemonService pokemonService;
    
    @GetMapping()
    public ResponseEntity GetAll(@RequestParam(defaultValue = "1") int page) {
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
    
    @GetMapping("/{Id_Pokemon}")
    public ResponseEntity GetByIdPokemon(@PathVariable("Id_Pokemon")int Id_Pokemon){
        Result result = new Result();
        
        try{
            result.object = pokemonService.GetPokemonById(Id_Pokemon).object;
            result.correct = true;
            result.status = 200;
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }     
      return ResponseEntity.status(result.status).body(result);
    }
    
    @GetMapping("/buscador")
    public ResponseEntity Buscador(@RequestParam("name")String name){
        Result result = new Result();
        
        try{
            result.object = pokemonService.Buscador(name).object;
            result.correct = true;
            result.status = 200;
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }
}
