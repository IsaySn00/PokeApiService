package com.digis01.PokeApiService.DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonHabilidadDetailDTO {
    
    private int id;
    private String name;
    public List<NamedApiResourceDTO> names;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NamedApiResourceDTO> getNames() {
        return names;
    }

    public void setNames(List<NamedApiResourceDTO> names) {
        this.names = names;
    }
    
    
}
