package com.digis01.PokeApiService.DTO;

public class NamesDTO {
    
    private String name;
    public NamedApiResourceDTO Language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NamedApiResourceDTO getLanguage() {
        return Language;
    }

    public void setLanguage(NamedApiResourceDTO Language) {
        this.Language = Language;
    }
    
    
}
