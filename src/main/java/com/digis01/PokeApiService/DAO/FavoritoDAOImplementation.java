package com.digis01.PokeApiService.DAO;

import com.digis01.PokeApiService.JPA.FavoritoJPA;
import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.JPA.UsuarioJPA;
import com.digis01.PokeApiService.Service.PokemonService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FavoritoDAOImplementation implements IFavoritoDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PokemonService pokemonService;

    @Override
    @Transactional
    public Result AddFavorito(FavoritoJPA favoritoJPA, int idUsuario) {
        Result result = new Result();

        try {
            favoritoJPA.UsuarioJPA = new UsuarioJPA();
            favoritoJPA.UsuarioJPA.setIdUsuario(idUsuario);

            entityManager.persist(favoritoJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetFavoritoById(int id) {
        Result result = new Result();

        try {
            FavoritoJPA favorito = entityManager.find(FavoritoJPA.class, id);

            HashMap<String, Object> pokemon = new HashMap();

            pokemon.put("pokemon", new Object[]{favorito.getIdFavorito(), pokemonService.GetPokemonById(favorito.getIdPokemon()).object});

            result.object = pokemon.get("pokemon");

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result isFavorito(int idUsuario, int idPokemon) {

        Result result = new Result();

        try {
            String jpql = "SELECT COUNT(f) FROM FavoritoJPA f WHERE f.UsuarioJPA.idUsuario = :idUsuario AND f.idPokemon = :idPokemon";

            Long count = entityManager.createQuery(jpql, Long.class).setParameter("idUsuario", idUsuario)
                    .setParameter("idPokemon", idPokemon).getSingleResult();

            boolean isFavorito = count > 0 ? true : false;
            
            result.correct = true;
            result.object = isFavorito;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
