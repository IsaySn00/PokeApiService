package com.digis01.PokeApiService.DTO;

public class FlavorTextDTO {
    
    private String flavor_text;
    private NamedApiResourceDTO language;

    public String getFlavor_text() {
        return flavor_text;
    }

    public void setFlavor_text(String flavor_text) {
        this.flavor_text = flavor_text;
    }

    public NamedApiResourceDTO getLanguage() {
        return language;
    }

    public void setLanguage(NamedApiResourceDTO language) {
        this.language = language;
    }
    
    
}
