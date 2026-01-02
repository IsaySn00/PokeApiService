package com.digis01.PokeApiService.DAO;

import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.JPA.UsuarioJPA;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Result AddUsuario(UsuarioJPA usuario) {
        Result result = new Result();

        try {

            entityManager.persist(usuario);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
