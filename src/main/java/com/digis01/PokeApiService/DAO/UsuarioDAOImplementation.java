/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.PokeApiService.DAO;

import com.digis01.PokeApiService.DTO.UsuarioUpdateDTO;
import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.JPA.UsuarioJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
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
    public Result GetAll() {
        Result result = new Result();
        
        try{
            TypedQuery<UsuarioJPA> queryUsuario = entityManager.createQuery("FROM UsuarioJPA ORDER BY idUsuario", UsuarioJPA.class);
            
            List<UsuarioJPA> usuarios = queryUsuario.getResultList();
            result.object = usuarios;
            result.correct = true;
            result.status = 200;
        
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.object = null;
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

    
    @Override
    @Transactional
    public Result DeleteUsuario(int id){
        Result result = new Result();
            
        try{
            UsuarioJPA usuario = entityManager.find(UsuarioJPA.class, id);
            
            if(usuario != null){
               entityManager.remove(usuario);
               result.correct = true;
               result.status = 2001;
            }
        
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.object = null;
        }
        
        return result;
    }
}
