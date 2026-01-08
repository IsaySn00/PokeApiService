package com.digis01.PokeApiService.RestController;

import com.digis01.PokeApiService.DAO.UsuarioDAOImplementation;
import com.digis01.PokeApiService.DTO.UsuarioUpdateDTO;
import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.JPA.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @PostMapping()
    public ResponseEntity AddUsuario(@RequestBody UsuarioJPA usuario) {

        Result result = new Result();

        try {
            result = usuarioDAOImplementation.AddUsuario(usuario);
            result.correct = true;
            result.object = "Se registró el usuario con exito";
            result.status = 200;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }

        return ResponseEntity.status(result.status).body(result);
    }

    @PreAuthorize("hasAuthority('ROLE_Administrador') or hasAuthority('ROLE_Usuario')")
    @GetMapping("/{id}")
    public ResponseEntity GetById(@PathVariable("id") int id) {
        Result result = new Result();

        try {
            result.object = usuarioDAOImplementation.GetUsuarioById(id).object;
            result.correct = true;
            result.status = 201;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }

    @PreAuthorize("hasAuthority('ROLE_Administrador') or hasAuthority('ROLE_Usuario')")
    @PatchMapping()
    public ResponseEntity UpdateUsuario(@RequestBody UsuarioUpdateDTO usuario) {
        Result result = new Result();

        try {
            usuarioDAOImplementation.UpdateUsuario(usuario);
            result.correct = true;
            result.status = 202;
            result.object = "Usuario actualizado correctamemte";

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }

        return ResponseEntity.status(result.status).body(result);
    }
}
