package com.digis01.PokeApiService.DTO;

import java.util.List;


public class PokemonTypeResponseDTO {

    private int count;
    private String next;
    private String previous;
    private List<NamedApiResourceDTO> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<NamedApiResourceDTO> getResults() {
        return results;
    }

    public void setResults(List<NamedApiResourceDTO> results) {
        this.results = results;
    }
    
    
}
