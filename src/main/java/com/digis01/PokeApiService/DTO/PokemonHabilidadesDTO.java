/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.PokeApiService.DTO;

public class PokemonHabilidadesDTO {
    private boolean is_hidden;
    private int slot;
    private NamedApiResourceDTO ability;
    
    private PokemonHabilidadDetailDTO abilityDetail;
    
    public PokemonHabilidadesDTO(){
    
    }
    
    public PokemonHabilidadesDTO(boolean is_hidden, int slot, NamedApiResourceDTO ability){
        this.is_hidden = is_hidden;
        this.slot = slot;
        this.ability = ability;
    }

    public boolean getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public NamedApiResourceDTO getAbility() {
        return ability;
    }

    public void setAbility(NamedApiResourceDTO ability) {
        this.ability = ability;
    }

    public PokemonHabilidadDetailDTO getAbilityDetail() {
        return abilityDetail;
    }

    public void setAbilityDetail(PokemonHabilidadDetailDTO abilityDetail) {
        this.abilityDetail = abilityDetail;
    }
    
    
    
}
