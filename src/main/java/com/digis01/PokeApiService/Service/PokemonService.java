package com.digis01.PokeApiService.Service;

import com.digis01.PokeApiService.DTO.ChainLinkDTO;
import com.digis01.PokeApiService.DTO.EvolutionChainDTO;
import com.digis01.PokeApiService.DTO.FlavorTextDTO;
import com.digis01.PokeApiService.DTO.NamesDTO;
import com.digis01.PokeApiService.DTO.PokemonDetailDTO;
import com.digis01.PokeApiService.DTO.PokemonDTO;
import com.digis01.PokeApiService.DTO.PokemonEvolucionDTO;
import com.digis01.PokeApiService.DTO.PokemonHabilidadDetailDTO;
import com.digis01.PokeApiService.DTO.PokemonHabilidadesDTO;
import com.digis01.PokeApiService.DTO.PokemonResponseDTO;
import com.digis01.PokeApiService.DTO.PokemonSpeciesDTO;
import com.digis01.PokeApiService.DTO.PokemonTipoDetailDTO;
import com.digis01.PokeApiService.DTO.PokemonTipoEffectDTO;
import com.digis01.PokeApiService.DTO.PokemonTypeColor;
import com.digis01.PokeApiService.JPA.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String URL = "https://pokeapi.co/api/v2/";
    private static final int limit = 20;

    @Cacheable(value = "pokemonPages", key = "#page")
    public Result GetAll(int page) {

        Result result = new Result();

        try {

            int offset = (page - 1) * limit;

            PokemonResponseDTO response = restTemplate.getForObject(URL + "pokemon?offset=" + offset + "&limit=" + limit, PokemonResponseDTO.class);

            List<PokemonDTO> pokemons = response.getResults();

            for (PokemonDTO pokemon : pokemons) {
                String url = pokemon.getUrl();
                int id = Integer.parseInt(url.replaceAll(".*/pokemon/", "").replace("/", ""));

                String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";

                pokemon.setId(id);
                pokemon.setImageUrl(imageUrl);
                pokemon.setId(id);
            }

            int totalPages = (int) Math.ceil((double) response.getCount() / limit);

            int windowSize = 2;

            int startPage = Math.max(1, page - windowSize);
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

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result GetPokemonById(int idPokemon) {

        Result result = new Result();

        try {

            PokemonDetailDTO pokemon = restTemplate.getForObject(URL + "pokemon/" + idPokemon, PokemonDetailDTO.class);
            
            List<PokemonEvolucionDTO> evoluciones = new ArrayList<>();
            List<PokemonTipoEffectDTO> fortalezas = new ArrayList<>();
            List<PokemonTipoEffectDTO> debilidades = new ArrayList<>();
            Map<String, Double> damageMap = new HashMap<>();

            pokemon.getAbilities().forEach(pa -> {
                PokemonHabilidadDetailDTO habilidad = getHabilidadByUrl(pa.getAbility().getUrl());
                
                String nombreEsp = getNombreHabilidadEsp(habilidad);
                
                habilidad.setName(nombreEsp);

                pa.setAbilityDetail(habilidad);
            });
            
            pokemon.getTypes().forEach(ty -> {
                PokemonTipoDetailDTO tipo = getTipoByUrl(ty.getType().getUrl());
                
                String nombreEsp = getNombreTipoEsp(tipo);
                
                tipo.getDamage_relations().getDouble_damage_from()
                        .forEach(t -> damageMap.merge(t.getName(), 2.0, (a, b) -> a * b));
                        
                tipo.getDamage_relations().getHalf_damage_from()
                        .forEach(t -> damageMap.merge(t.getName(), 0.5, (a, b) -> a * b));
                
                tipo.setName(nombreEsp);
                tipo.setColor(PokemonTypeColor.getColor(ty.getType().getName()));
                
                ty.setTipoDetail(tipo);
            });
            
            PokemonSpeciesDTO species = getSpeciesByUrl(idPokemon);
            EvolutionChainDTO chain = getEvolutionChaingByUrl(species);
            
            recorrerEvoluciones(chain.getChain(), evoluciones);
            
            pokemon.setEvoluciones(evoluciones);
            
            String description = getDescripcionEsp(idPokemon);
            pokemon.setDescription(description);
            
            String tipoPrincipal = pokemon.getTypes().get(0).getType().getName();
            String colorPrincipal = PokemonTypeColor.getColor(tipoPrincipal);
            
            pokemon.setPrimaryType(tipoPrincipal);
            pokemon.setPrimaryColor(colorPrincipal);
            
            pokemon.setHeightMeters(pokemon.getHeight() / 10.0);
            pokemon.setWeightKg(pokemon.getWeight() / 10.0);
            
            damageMap.forEach((tipoBase, mult) -> {
                
                PokemonTipoDetailDTO tipo = getTipoByUrl(URL + "type/" + tipoBase);
                
                String nombreEsp = getNombreTipoEsp(tipo);
                String color = PokemonTypeColor.getColor(tipoBase);
                
                PokemonTipoEffectDTO effect = new PokemonTipoEffectDTO(nombreEsp, color, mult);
                
               if(mult >= 2){
                   debilidades.add(effect);
               }else if(mult <= 0.5){
                   fortalezas.add(effect);
               }
            });
            
            pokemon.setFortalezas(fortalezas);
            pokemon.setDebilidades(debilidades);
            
            result.correct = true;
            result.object = pokemon;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

    public PokemonHabilidadDetailDTO getHabilidadByUrl(String url) {
        return restTemplate.getForObject(url, PokemonHabilidadDetailDTO.class);
    }
    
    public PokemonTipoDetailDTO getTipoByUrl(String url){
        return restTemplate.getForObject(url, PokemonTipoDetailDTO.class);
    }
    
    public EvolutionChainDTO getEvolutionChaingByUrl(PokemonSpeciesDTO species){
        return restTemplate.getForObject(species.getEvolution_chain().getUrl(), EvolutionChainDTO.class);
    }
    
    public PokemonSpeciesDTO getSpeciesByUrl(int idPokemon){
        return restTemplate.getForObject(URL + "pokemon-species/" + idPokemon, PokemonSpeciesDTO.class);
    }
    
    public String getNombreTipoEspByUrl(String url){
        PokemonTipoDetailDTO tipo = restTemplate.getForObject(url, PokemonTipoDetailDTO.class);
        
        return tipo.getNames().stream()
                .filter(n -> "es".equals(n.getLanguage().getName()))
                .map(NamesDTO::getName)
                .findFirst()
                .orElse(tipo.getName());
    }
    
    public String getNombreTipoEsp(PokemonTipoDetailDTO tipo){
        return tipo.getNames().stream()
                .filter(n -> "es".equals(n.getLanguage().getName()))
                .map(NamesDTO::getName)
                .findFirst()
                .orElse(tipo.getName());
    }

    public String getNombreHabilidadEsp(PokemonHabilidadDetailDTO habilidad) {
        return habilidad.getNames().stream()
                .filter(n -> "es".equals(n.getLanguage().getName()))
                .map(NamesDTO::getName)
                .findFirst()
                .orElse(habilidad.getName());
    }
    
    public String getDescripcionEsp(int idPokemon){
        
        PokemonSpeciesDTO species = restTemplate.getForObject(URL + "pokemon-species/" + idPokemon, PokemonSpeciesDTO.class);
        
        return species.getFlavor_text_entries().stream()
                .filter(f -> f.getLanguage() != null)
                .filter(f -> "es".equals(f.getLanguage().getName()))
                .map(FlavorTextDTO::getFlavor_text)
                .findFirst()
                .orElse("Descripción no disponible")
                .replace("\n"," ")
                .replace("\f", " ");
    }
    
    public void recorrerEvoluciones(ChainLinkDTO chain, List<PokemonEvolucionDTO> lista){
        String nombre = chain.getSpecies().getName();
        
        int id = extraerIdDesdeUrl(chain.getSpecies().getUrl());
        
        String image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
        
        lista.add(new PokemonEvolucionDTO(id,nombre, image));
        
        if(chain.getEvolves_to() != null){
            chain.getEvolves_to().forEach(e -> recorrerEvoluciones(e, lista));
        }
    }
    
    public int extraerIdDesdeUrl(String url){
        String[] parts = url.split("/");
        return Integer.parseInt(parts[parts.length -1]);
    }

    public Result Buscador(String name) {
        Result result = new Result();
        try {
            String urlFinal = URL + "pokemon/" + name.toLowerCase().trim();

            PokemonDTO pokemon = restTemplate.getForObject(urlFinal, PokemonDTO.class);

            if (pokemon != null) {
                String url = pokemon.getUrl();
                int id = pokemon.getId();
                String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";

                pokemon.setImageUrl(imageUrl);
                pokemon.setUrl(url);

                result.object = pokemon;
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
}
