package com.digis01.PokeApiService.DTO;


public class ResourceEntryDTO {
    
    private NamedApiResourceDTO pokemon;
    private int slot;

    public NamedApiResourceDTO getPokemon() {
        return pokemon;
    }

    public void setPokemon(NamedApiResourceDTO pokemon) {
        this.pokemon = pokemon;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
    
    

}
