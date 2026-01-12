/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.PokeApiService.DTO;

public class PokemonTiposDTO {
    private int slot;
    private NamedApiResourceDTO type;
    
    public PokemonTipoDetailDTO tipoDetail;
    
    public PokemonTiposDTO(){
    
    }
    
    public PokemonTiposDTO(int slot, NamedApiResourceDTO type){
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public NamedApiResourceDTO getType() {
        return type;
    }

    public void setType(NamedApiResourceDTO type) {
        this.type = type;
    }

    public PokemonTipoDetailDTO getTipoDetail() {
        return tipoDetail;
    }

    public void setTipoDetail(PokemonTipoDetailDTO tipoDetail) {
        this.tipoDetail = tipoDetail;
    }
    
    
    
}
