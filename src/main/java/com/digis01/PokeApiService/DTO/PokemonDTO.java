package com.digis01.PokeApiService.DTO;


public class PokemonDTO {

    private String name;
    private String url;
    private String imageUrl;
    private String idPokemon;

    public String getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(String idPokemon) {
        this.idPokemon = idPokemon;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
}
