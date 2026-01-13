package com.digis01.PokeApiService.DTO;

public class NamesDTO {
    
    private String name;
    public NamedApiResourceDTO language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NamedApiResourceDTO getLanguage() {
        return language;
    }

    public void setLanguage(NamedApiResourceDTO Language) {
        this.language = Language;
    }
    
    
}
