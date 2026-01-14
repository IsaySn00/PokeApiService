package com.digis01.PokeApiService.Service;

import com.digis01.PokeApiService.DAO.IUsuarioRepositoryDAO;
import com.digis01.PokeApiService.JPA.UsuarioJPA;
import com.digis01.PokeApiService.Security.UsuarioDetails;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsJPAService implements UserDetailsService{
    
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    
    private final IUsuarioRepositoryDAO iUsuarioRepositoryDAO;
    
    public UserDetailsJPAService(IUsuarioRepositoryDAO iUsuarioRepositoryDAO){
        this.iUsuarioRepositoryDAO = iUsuarioRepositoryDAO;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        UsuarioJPA usuario = iUsuarioRepositoryDAO.findByEmailUsuario(email);
        
//        return User.withUsername(usuario.getEmailUsuario())
//                .password(usuario.getPasswordUsuario())
//                .roles(usuario.RolJPA.getNombreRol())
//                .disabled(false)
//                .build();
        return new UsuarioDetails(usuario);
    }
    
    public boolean ValidarPassword(String password){
        //obtener email del usuario por medio de la sesión activa
        String emailActivo = SecurityContextHolder.getContext().getAuthentication().getName();
        
        //buscar los datos del usuario en la base de datos
        UsuarioJPA usuario = iUsuarioRepositoryDAO.findByEmailUsuario(emailActivo);
        
        if(usuario.equals(this)){
            return passwordEncoder.matches(password, usuario.getPasswordUsuario());
        }
        
        return false;
    }

}
