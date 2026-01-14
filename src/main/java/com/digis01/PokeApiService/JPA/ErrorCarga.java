package com.digis01.PokeApiService.JPA;

public class ErrorCarga {

    public String campo;
    public String descripcion;

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
