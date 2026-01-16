package com.digis01.PokeApiService.DTO;

import java.util.List;

public class PokemonTipoDetailDTO {
    
    private int id;
    private String name;
    private String originalName;
    public List<NamesDTO> names;
    private DamageRelationsDTO damage_relations;
    private String color;
    private List<ResourceEntryDTO> pokemon;

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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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

    public List<ResourceEntryDTO> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<ResourceEntryDTO> pokemon) {
        this.pokemon = pokemon;
    }
    
    
    
}
