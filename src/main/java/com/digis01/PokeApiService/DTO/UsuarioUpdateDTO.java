package com.digis01.PokeApiService.DTO;

import com.digis01.PokeApiService.JPA.RolJPA;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuario")
public class UsuarioUpdateDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @NotNull(message = "Campo no puede ser nulo")
    @NotBlank(message = "Campo debe contener datos")
    @Size(min = 2, max = 17, message = "Entre 2 y 17")
    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @NotNull(message = "El apellido paterno no puede ser nulo")
    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(min = 2, max = 20, message = "El apellido paterno debe tener entre 2 y 20 caracteres")
    @Column(name = "apellido_paterno")
    private String apellidoPatUsuario;

    @Size(max = 20, message = "El apellido materno no debe exceder 20 caracteres")
    @Column(name = "apellido_materno")
    private String apellidoMatUsuario;

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]+$", message = "El nombre de usuario no es valido")
    @Column(name = "username")
    private String userName;

    @NotNull(message = "El teléfono no puede ser nulo")
    @NotBlank(message = "El teléfono no puede estar vacio")
    @Size(max = 20, message = "El teléfono ha sobrepasado los caracteres")
    @Column(name = "telefono")
    private String telefonoUsuario;

    @NotNull(message = "El correo no puede ser nulo")
    @NotBlank(message = "El correo es obligatorio")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "El correo electrónico ingresado tiene un formato incorrecto")
    @Column(name = "email")
    private String emailUsuario;

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

    public RolJPA getRolJPA() {
        return RolJPA;
    }

    public void setRolJPA(RolJPA RolJPA) {
        this.RolJPA = RolJPA;
    }
}
