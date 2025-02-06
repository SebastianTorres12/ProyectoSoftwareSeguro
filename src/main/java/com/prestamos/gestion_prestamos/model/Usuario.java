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
}

