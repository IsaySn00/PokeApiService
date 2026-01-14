/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.PokeApiService.RestController;

import com.digis01.PokeApiService.DAO.UsuarioDAOImplementation;
import com.digis01.PokeApiService.DTO.UsuarioUpdateDTO;
import com.digis01.PokeApiService.JPA.Result;
import com.digis01.PokeApiService.JPA.UsuarioJPA;
import com.digis01.PokeApiService.Service.EmailService;
import com.digis01.PokeApiService.Service.JwtService;
import com.digis01.PokeApiService.Service.UserDetailsJPAService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private UserDetailsJPAService userDetailsJPAService;

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

    @GetMapping()
    public ResponseEntity GetAll() {
        Result result = new Result();

        try {
            result = usuarioDAOImplementation.GetAll();
            result.correct = true;
            result.status = 200;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }

        return ResponseEntity.status(result.status).body(result);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity DeleteUsuario(@PathVariable("idUsuario") int idUsuario) {
        Result result = new Result();
        try {
            result = usuarioDAOImplementation.DeleteUsuario(idUsuario);
            result.correct = true;
            result.status = 200;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }

    @PostMapping("/sendEmailRecuperacionPass")
    @PreAuthorize("permitAll()")
    public ResponseEntity SendEmailRecuperacionPassword(@RequestPart("email") String email) {
        Result result = new Result();

        try {
            String tkn = jwtService.generateRecoveryToken(email);

            String link = "http://localhost:8081/usuario/recuperarPassword?token=" + tkn;

            emailService.sendMail(email, link, "Recuperación de contraseña");

            result.correct = true;
            result.object = "Se ha enviado el correo para recuperar la contraseña";
            result.status = 200;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }

        return ResponseEntity.status(result.status).body(result);
    }

    @PostMapping("recuperarPassword")
    @PreAuthorize("hasAuthority('ROLE_Invitado')")
    public ResponseEntity RecuperarPassword(@RequestPart("password") String password) {
        Result result = new Result();

        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            String email = auth.getName();

            usuarioDAOImplementation.UpdatePassword(email, password);
            emailService.sendNotification(email, "Recuperación de contraseña");

            result.correct = true;
            result.object = "La contraseña se recuperó exitosamente";
            result.status = 200;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.status = 500;
            result.object = "Error al recuperar contraseña";
        }

        return ResponseEntity.status(result.status).body(result);
    }
    
    @PostMapping("/validarPassword")
    public ResponseEntity ValidarPassword(@RequestBody Map<String, String> body){
        Result result = new Result();
        try{
            String password = body.get("password");
            boolean isValida = userDetailsJPAService.ValidarPassword(password);
            
            if(isValida){
                return ResponseEntity.ok().body(Map.of("valid",true));
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("valid", false,
                                           "message", "Contraseña incorrecta"));
            }
        
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.status = 500;
        }
        
        return ResponseEntity.status(result.status).body(result);
    }
}
