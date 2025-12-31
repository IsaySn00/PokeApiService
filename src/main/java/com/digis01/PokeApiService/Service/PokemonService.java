package com.digis01.PokeApiService.Service;

import com.digis01.PokeApiService.DTO.PokemonDTO;
import com.digis01.PokeApiService.DTO.PokemonResponseDTO;
import com.digis01.PokeApiService.JPA.Result;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

   private final RestTemplate restTemplate = new RestTemplate();
   
   private static final String URL = "https://pokeapi.co/api/v2/";
   
   public Result GetAll(int offset){
       
       Result result = new Result();
       
       try{
           PokemonResponseDTO response = restTemplate.getForObject(URL + "pokemon?offset=" + offset + "&limit=20", PokemonResponseDTO.class);
           
           List<PokemonDTO> pokemons = response.getResults();
           
           for(PokemonDTO pokemon : pokemons){
               String url = pokemon.getUrl();
               String id = url.replaceAll(".*/pokemon/", "").replace("/", "");
               
               String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
               
               pokemon.setImageUrl(imageUrl);
           }
           
           result.object = pokemons;
           result.correct = true;
           
       }catch(Exception ex){
           result.correct = false;
           result.errorMessage = ex.getLocalizedMessage();
           result.ex = ex;
       }
       
       return result;
   }
}
