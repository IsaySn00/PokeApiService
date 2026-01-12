package com.digis01.PokeApiService.DTO;

import java.util.List;

public class PokemonSpeciesDTO {
    
    private List<FlavorTextDTO> flavor_text_entries;
    private NamedApiResourceDTO evolution_chain;

    public List<FlavorTextDTO> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries(List<FlavorTextDTO> flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }

    public NamedApiResourceDTO getEvolution_chain() {
        return evolution_chain;
    }

    public void setEvolution_chain(NamedApiResourceDTO evolution_chain) {
        this.evolution_chain = evolution_chain;
    }
    
    
}
