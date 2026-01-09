package com.digis01.PokeApiService.DAO;

import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.JPA.UsuarioJPA;


public interface IUsuarioDAO {

    Result AddUsuario(UsuarioJPA usuario);
    Result GetUsuarioById(int id);
    Result GetUsuarioByEmail(String email);
    Result GetAll();
    Result DeleteUsuario(int id);
}
