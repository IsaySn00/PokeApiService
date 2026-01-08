package com.digis01.PokeApiService.DAO;

import com.digis01.PokeApiService.DTO.UsuarioUpdateDTO;
import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.JPA.UsuarioJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO {

    @Autowired
    private EntityManager entityManager;
    
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioDAOImplementation(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Result AddUsuario(UsuarioJPA usuario) {
        Result result = new Result();

        try {
            
            String pswd = usuario.getPasswordUsuario();
            
            usuario.setPasswordUsuario(passwordEncoder.encode(pswd));

            entityManager.persist(usuario);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetUsuarioById(int id) {
        Result result = new Result();
        
        try{
            UsuarioJPA usuario = entityManager.find(UsuarioJPA.class, id);
            
            result.object  = usuario;
            result.correct = true;
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetUsuarioByEmail(String email) {
        Result result = new Result();
        
        try{
            TypedQuery<UsuarioJPA> queryUsuario =  entityManager.createQuery("FROM UsuarioJPA WHERE emailUsuario = :email", UsuarioJPA.class)
                    .setParameter("email", email);
            
            UsuarioJPA usuario = queryUsuario.getSingleResult();
            
            result.correct = true;
            result.object = usuario;
            
        }catch(Exception ex){
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }

    @Override
    @Transactional
    public Result UpdateUsuario(UsuarioUpdateDTO usuario) {
        Result result = new Result();
        
        try{
            entityManager.merge(usuario);
            
            result.correct = true;
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }

}
