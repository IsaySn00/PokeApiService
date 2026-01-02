package com.digis01.PokeApiService.Service;

import com.digis01.PokeApiService.DTO.PokemonDTO;
import com.digis01.PokeApiService.DTO.PokemonResponseDTO;
import com.digis01.PokeApiService.JPA.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

   private final RestTemplate restTemplate = new RestTemplate();
   
   private static final String URL = "https://pokeapi.co/api/v2/";
   private static final int limit = 20;
   
   @Cacheable(value = "pokemonPages", key = "#page")
   public Result GetAll(int page){
       
       Result result = new Result();
       
       try{
           
           int offset = (page - 1) * limit;
           
           PokemonResponseDTO response = restTemplate.getForObject(URL + "pokemon?offset=" + offset + "&limit=" + limit, PokemonResponseDTO.class);
           
           List<PokemonDTO> pokemons = response.getResults();
           
           for(PokemonDTO pokemon : pokemons){
               String url = pokemon.getUrl();
               String id = url.replaceAll(".*/pokemon/", "").replace("/", "");
               
               pokemon.setIdPokemon(id);
               
               String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
               
               pokemon.setImageUrl(imageUrl);
           }
           
           int totalPages = (int) Math.ceil((double) response.getCount() / limit);
           
           int windowSize = 2;
           
           int startPage = Math.max(1,page - windowSize);
           int endPage = Math.min(totalPages, page + windowSize);
           
           Map<String, Object> data = new HashMap<>();
           data.put("pokemons", pokemons);
           data.put("page", page);
           data.put("hasNext", response.getNext() != null);
           data.put("hasPrevious", response.getPrevious() != null);
           data.put("totalPages", totalPages);
           data.put("startPage", startPage);
           data.put("endPage", endPage);
           
           result.object = data;
           result.correct = true;
           
       }catch(Exception ex){
           result.correct = false;
           result.errorMessage = ex.getLocalizedMessage();
           result.ex = ex;
       }
       
       return result;
   }
}
