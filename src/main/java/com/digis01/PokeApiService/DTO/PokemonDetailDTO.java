/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.PokeApiService.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonDetailDTO {

    private int id;
    private String name;
    private int base_experience;
    private int height;
    private int weight;
    private double heightMeters;
    private double weightKg;
    private String description;
    private String primaryType;
    private String primaryColor;
    private List<PokemonTipoEffectDTO> fortalezas;
    private List<PokemonTipoEffectDTO> debilidades;
    public List<PokemonHabilidadesDTO> abilities;
    public List<PokemonTiposDTO> types;
    public List<PokemonBaseStatDTO> stats;
    public List<PokemonEvolucionDTO> evoluciones;
//    Map<String, Object> sprites = new HashMap<>();
    public PokemonSpritesDTO sprites;

    public PokemonDetailDTO() {

    }

    public PokemonDetailDTO(int id, String name, int base_experience, int height, int weight) {
        this.id = id;
        this.name = name;
        this.base_experience = base_experience;
        this.height = height;
        this.weight = weight;
//        sprites.put("back_default", "");
//        sprites.put("back_female", "");
//        sprites.put("back_shiny", "");
//        sprites.put("back_shiny_female", "");
//        sprites.put("front_default", "");
//        sprites.put("front_female", "");
//        sprites.put("front_shiny", "");
//        sprites.put("front_shiny_female", "");
//        sprites.remove("other");
//        sprites.remove("home");
//        sprites.remove("official-artwork");
//        sprites.remove("showdown");
    }

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

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getHeightMeters() {
        return heightMeters;
    }

    public void setHeightMeters(double heightMeters) {
        this.heightMeters = heightMeters;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PokemonTipoEffectDTO> getFortalezas() {
        return fortalezas;
    }

    public void setFortalezas(List<PokemonTipoEffectDTO> fortalezas) {
        this.fortalezas = fortalezas;
    }

    public List<PokemonTipoEffectDTO> getDebilidades() {
        return debilidades;
    }

    public void setDebilidades(List<PokemonTipoEffectDTO> debilidades) {
        this.debilidades = debilidades;
    }

    public List<PokemonHabilidadesDTO> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<PokemonHabilidadesDTO> abilities) {
        this.abilities = abilities;
    }

    public List<PokemonTiposDTO> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonTiposDTO> types) {
        this.types = types;
    }

    public List<PokemonEvolucionDTO> getEvoluciones() {
        return evoluciones;
    }

    public void setEvoluciones(List<PokemonEvolucionDTO> evoluciones) {
        this.evoluciones = evoluciones;
    }

    public PokemonSpritesDTO getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSpritesDTO sprites) {
        this.sprites = sprites;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    
    
}
