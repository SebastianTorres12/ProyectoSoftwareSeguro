package com.prestamos.gestion_prestamos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Getter
@Setter

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombreCompleto;

    private String numeroId;

    private LocalDate fechaNac;

    private String direccion;

    private Double ingresos;

    private String historialCred;

    private String correo;

    @JsonIgnore
    private String contrasenaHash;

    @Enumerated(EnumType.STRING)
    private Rol rol; // ADMIN, USUARIO

    private Integer intentosFallidos = 0; // Número de intentos fallidos

    private Boolean cuentaBloqueada = false;

    public Usuario(Long idUsuario, String nombreCompleto, String numeroId, LocalDate fechaNac, String direccion, Double ingresos, String historialCred, String correo, String contrasenaHash, Rol rol, Integer intentosFallidos, Boolean cuentaBloqueada) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.numeroId = numeroId;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.ingresos = ingresos;
        this.historialCred = historialCred;
        this.correo = correo;
        this.contrasenaHash = contrasenaHash;
        this.rol = rol;
        this.intentosFallidos = intentosFallidos;
        this.cuentaBloqueada = cuentaBloqueada;
    }

    public Usuario(){}

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(String numeroId) {
        this.numeroId = numeroId;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getIngresos() {
        return ingresos;
    }

    public void setIngresos(Double ingresos) {
        this.ingresos = ingresos;
    }

    public String getHistorialCred() {
        return historialCred;
    }

    public void setHistorialCred(String historialCred) {
        this.historialCred = historialCred;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Integer getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(Integer intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public Boolean getCuentaBloqueada() {
        return cuentaBloqueada;
    }

    public void setCuentaBloqueada(Boolean cuentaBloqueada) {
        this.cuentaBloqueada = cuentaBloqueada;
    }
}

