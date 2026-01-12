package com.digis01.PokeApiService.DTO;

import java.util.List;

public class ChainLinkDTO {
    private NamedApiResourceDTO species;
    private List<ChainLinkDTO> evolves_to;

    public NamedApiResourceDTO getSpecies() {
        return species;
    }

    public void setSpecies(NamedApiResourceDTO species) {
        this.species = species;
    }

    public List<ChainLinkDTO> getEvolves_to() {
        return evolves_to;
    }

    public void setEvolves_to(List<ChainLinkDTO> evolves_to) {
        this.evolves_to = evolves_to;
    }
    
    
}
