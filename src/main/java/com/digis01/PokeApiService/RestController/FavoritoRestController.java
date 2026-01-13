package com.digis01.PokeApiService.RestController;

import com.digis01.PokeApiService.DAO.FavoritoDAOImplementation;
import com.digis01.PokeApiService.JPA.FavoritoJPA;
import com.digis01.PokeApiService.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favorito")
public class FavoritoRestController {

    @Autowired
    private FavoritoDAOImplementation favoritoDAOImplementation;

    @PreAuthorize("hasAuthority('ROLE_Administrador') or hasAuthority('ROLE_Usuario')")
    @GetMapping()
    public ResponseEntity GetFavorito(@RequestParam("idFavorito") int idFavorito) {
        Result result = new Result();

        try {
            result.object = favoritoDAOImplementation.GetFavoritoById(idFavorito).object;
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
    @PostMapping()
    public ResponseEntity AddFavorito(@RequestBody FavoritoJPA favorito, @RequestParam("idUsuario") int idUsuario) {
        Result result = new Result();

        try {

            favoritoDAOImplementation.AddFavorito(favorito, idUsuario);
            result.correct = true;
            result.object = "Se ha añadido al Pokémon como favorito";
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
    @GetMapping("/existe")
    public ResponseEntity ExisteFavorito(@RequestParam int idUsuario, @RequestParam int idPokemon){
        Result result = new Result();
        
        try{
            
            result.correct = true;
            result.object = favoritoDAOImplementation.isFavorito(idUsuario, idPokemon).object;
            result.status = 201;
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        
        return ResponseEntity.status(result.status).body(result);
    }

    
}
