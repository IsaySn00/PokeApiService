package com.digis01.PokeApiService.Service;

import com.digis01.PokeApiService.DAO.IUsuarioRepositoryDAO;
import com.digis01.PokeApiService.JPA.UsuarioJPA;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsJPAService implements UserDetailsService{
    
    private final IUsuarioRepositoryDAO iUsuarioRepositoryDAO;
    
    public UserDetailsJPAService(IUsuarioRepositoryDAO iUsuarioRepositoryDAO){
        this.iUsuarioRepositoryDAO = iUsuarioRepositoryDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        UsuarioJPA usuario = iUsuarioRepositoryDAO.findByEmailUsuario(email);
        
        return User.withUsername(usuario.getEmailUsuario())
                .password(usuario.getPasswordUsuario())
                .roles(usuario.RolJPA.getNombreRol())
                .disabled(false)
                .build();
    }
    
    

}
