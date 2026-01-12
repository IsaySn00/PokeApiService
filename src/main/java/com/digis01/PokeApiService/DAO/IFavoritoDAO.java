package com.digis01.PokeApiService.DAO;

import com.digis01.PokeApiService.JPA.FavoritoJPA;
import com.digis01.PokeApiService.JPA.Result;


public interface IFavoritoDAO {

    Result AddFavorito(FavoritoJPA favorito, int idUsuario);
    Result GetFavoritoById(int id);
    Result isFavorito(int idUsuario, int idPokemon);
    Result DeleteFavorito(int idFavorito);
}
