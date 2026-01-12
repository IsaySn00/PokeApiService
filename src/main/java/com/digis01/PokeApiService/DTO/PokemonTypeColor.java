package com.digis01.PokeApiService.DTO;

import java.util.Map;

public class PokemonTypeColor {

    private static final Map<String, String> COLORS = Map.ofEntries(
            Map.entry("normal", "#A8A878"),
            Map.entry("fire", "#F08030"),
            Map.entry("water", "#6890F0"),
            Map.entry("electric", "#F8D030"),
            Map.entry("grass", "#78C850"),
            Map.entry("ice", "#98D8D8"),
            Map.entry("fighting", "#C03028"),
            Map.entry("poison", "#A040A0"),
            Map.entry("ground", "#E0C068"),
            Map.entry("flying", "#A890F0"),
            Map.entry("psychic", "#F85888"),
            Map.entry("bug", "#A8B820"),
            Map.entry("rock", "#B8A038"),
            Map.entry("ghost", "#705898"),
            Map.entry("dragon", "#7038F8"),
            Map.entry("dark", "#705848"),
            Map.entry("steel", "#B8B8D0"),
            Map.entry("fairy", "#EE99AC")
    );
    
    public static String getColor(String type){
        return COLORS.getOrDefault(type.toLowerCase(), "#777");
    }
}
