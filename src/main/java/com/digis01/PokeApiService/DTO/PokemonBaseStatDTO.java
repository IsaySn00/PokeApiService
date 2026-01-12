package com.digis01.PokeApiService.DTO;

public class PokemonBaseStatDTO {
    
    private int base_stat;
    private int effort;
    private NamedApiResourceDTO stat;

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public NamedApiResourceDTO getStat() {
        return stat;
    }

    public void setStat(NamedApiResourceDTO stat) {
        this.stat = stat;
    }
    
    
}
