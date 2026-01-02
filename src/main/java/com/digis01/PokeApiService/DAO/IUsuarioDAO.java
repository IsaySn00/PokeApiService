package com.digis01.PokeApiService.DAO;

import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.JPA.UsuarioJPA;


public interface IUsuarioDAO {

    Result AddUsuario(UsuarioJPA usuario);
}
