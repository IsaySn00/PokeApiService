package com.digis01.PokeApiService.DAO;

import com.digis01.PokeApiService.JPA.UsuarioJPA;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUsuarioRepositoryDAO extends JpaRepository<UsuarioJPA, Integer>{

    UsuarioJPA findByUserName(String username);
    UsuarioJPA findByEmailUsuario(String email);
}
