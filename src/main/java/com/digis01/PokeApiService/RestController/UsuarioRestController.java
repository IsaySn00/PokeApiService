/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.PokeApiService.RestController;

import com.digis01.PokeApiService.DAO.UsuarioDAOImplementation;
import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.JPA.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {
    
     @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    
    @PostMapping()
    public ResponseEntity AddUsuario(@RequestBody UsuarioJPA usuario){
        
        Result result = new Result();
        
        try{
            result = usuarioDAOImplementation.AddUsuario(usuario);
            result.correct = true;
            result.object = "Se registró el usuario con exito";
            result.status = 200;
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        
        return ResponseEntity.status(result.status).body(result);
    }
    
    @GetMapping()
    public ResponseEntity GetAll(){
        Result result = new Result();
        
        try{
            result = usuarioDAOImplementation.GetAll();
            result.correct = true;
            result.status = 200;
        
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        
        return ResponseEntity.status(result.status).body(result);
    }
    
}
