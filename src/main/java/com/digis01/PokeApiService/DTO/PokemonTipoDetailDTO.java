package com.digis01.PokeApiService.DTO;

import java.util.List;

public class PokemonTipoDetailDTO {
    
    private int id;
    private String name;
    public List<NamesDTO> names;
    private DamageRelationsDTO damage_relations;
    private String color;

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

    public List<NamesDTO> getNames() {
        return names;
    }

    public void setNames(List<NamesDTO> names) {
        this.names = names;
    }

    public DamageRelationsDTO getDamage_relations() {
        return damage_relations;
    }

    public void setDamage_relations(DamageRelationsDTO damage_relations) {
        this.damage_relations = damage_relations;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
}
