package com.digis01.PokeApiService.JPA;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;
    
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    
    @Column(name = "apellido_paterno")
    private String apellidoPatUsuario;
    
    @Column(name = "apellido_materno")
    private String apellidoMatUsuario;
    
    @Column(name = "username")
    private String userName;
    
    @Column(name = "telefono")
    private String telefonoUsuario;
    
    @Column(name = "email")
    private String emailUsuario;
    
    @Column(name = "password")
    private String passwordUsuario;
    
    @OneToMany(mappedBy = "UsuarioJPA", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("favoritos")
    public List<FavoritoJPA> FavoritoJPA = new ArrayList<>();
    
    @ManyToOne()
    @JoinColumn(name = "id_rol", nullable = false)
    public RolJPA RolJPA;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPatUsuario() {
        return apellidoPatUsuario;
    }

    public void setApellidoPatUsuario(String apellidoPatUsuario) {
        this.apellidoPatUsuario = apellidoPatUsuario;
    }

    public String getApellidoMatUsuario() {
        return apellidoMatUsuario;
    }

    public void setApellidoMatUsuario(String apellidoMatUsuario) {
        this.apellidoMatUsuario = apellidoMatUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public RolJPA getRolJPA() {
        return RolJPA;
    }

    public void setRolJPA(RolJPA RolJPA) {
        this.RolJPA = RolJPA;
    }
   
}
