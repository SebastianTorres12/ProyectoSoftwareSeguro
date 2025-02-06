package com.prestamos.gestion_prestamos.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "cuota")
@Getter
@Setter

public class Cuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuota;

    @ManyToOne
    @JoinColumn(name = "id_prestamo", nullable = false)
    private Prestamo prestamo;

    private Integer numeroCuota;

    private Double montoCuota;

    private LocalDate fechaVencimiento;

    private String estado; // PENDIENTE, PAGADA

    private LocalDate fechaPago;

    public Cuota(Long idCuota, Prestamo prestamo, Integer numeroCuota, Double montoCuota, LocalDate fechaVencimiento, String estado, LocalDate fechaPago) {
        this.idCuota = idCuota;
        this.prestamo = prestamo;
        this.numeroCuota = numeroCuota;
        this.montoCuota = montoCuota;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.fechaPago = fechaPago;
    }
    public Cuota(){}
}
