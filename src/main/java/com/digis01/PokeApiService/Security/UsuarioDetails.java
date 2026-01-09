package com.digis01.PokeApiService.Security;

import com.digis01.PokeApiService.JPA.UsuarioJPA;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UsuarioDetails implements UserDetails{

    private Integer id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    
    public UsuarioDetails(UsuarioJPA usuario){
        this.id = usuario.getIdUsuario();
        this.email = usuario.getEmailUsuario();
        this.password = usuario.getPasswordUsuario();
        this.authorities = java.util.List.of(new SimpleGrantedAuthority("ROLE_" + usuario.RolJPA.getNombreRol()));
    }
    
    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
