package com.prestamos.gestion_prestamos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestamo")
@Getter
@Setter

public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrestamo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    private Double montoSolicitado;

    private Integer plazoMeses;

    private Double tasaInteres;

    private String tipoPago; // FIJO, DECRECIENTE

    private String estado; // ACTIVO, CANCELADO

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaSolicitud;

    public Prestamo(Long idPrestamo, Usuario usuario, Double montoSolicitado, Integer plazoMeses, Double tasaInteres, String tipoPago, String estado, LocalDateTime fechaSolicitud) {
        this.idPrestamo = idPrestamo;
        this.usuario = usuario;
        this.montoSolicitado = montoSolicitado;
        this.plazoMeses = plazoMeses;
        this.tasaInteres = tasaInteres;
        this.tipoPago = tipoPago;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
    }
    public Prestamo (){

    }
}
