package com.digis01.PokeApiService.DTO;

import java.util.List;

public class DamageRelationsDTO {
    
    private List<NamedApiResourceDTO> double_damage_to;
    private List<NamedApiResourceDTO> double_damage_from;
    private List<NamedApiResourceDTO> half_damage_from;
    private List<NamedApiResourceDTO> half_damage_to;

    public List<NamedApiResourceDTO> getDouble_damage_to() {
        return double_damage_to;
    }

    public void setDouble_damage_to(List<NamedApiResourceDTO> double_damage_to) {
        this.double_damage_to = double_damage_to;
    }

    public List<NamedApiResourceDTO> getDouble_damage_from() {
        return double_damage_from;
    }

    public void setDouble_damage_from(List<NamedApiResourceDTO> double_damage_from) {
        this.double_damage_from = double_damage_from;
    }

    public List<NamedApiResourceDTO> getHalf_damage_from() {
        return half_damage_from;
    }

    public void setHalf_damage_from(List<NamedApiResourceDTO> half_damage_from) {
        this.half_damage_from = half_damage_from;
    }

    public List<NamedApiResourceDTO> getHalf_damage_to() {
        return half_damage_to;
    }

    public void setHalf_damage_to(List<NamedApiResourceDTO> half_damage_to) {
        this.half_damage_to = half_damage_to;
    }
    
    
}
