package com.prestamos.gestion_prestamos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @JsonIgnore // Evita referencias circulares en JSON
    private Prestamo prestamo;

    @NotNull(message = "El número de cuota no puede ser nulo")
    @Positive(message = "El número de cuota debe ser positivo")
    private Integer numeroCuota;

    @NotNull(message = "El monto de la cuota no puede ser nulo")
    @Positive(message = "El monto de la cuota debe ser positivo")
    private Double montoCuota;

    @NotNull(message = "La fecha de vencimiento no puede ser nula")
    private LocalDate fechaVencimiento;

    @NotNull
    @Column(nullable = false)
    private Boolean esPagada = false; // Estado como booleano: false = "PENDIENTE", true = "PAGADA"

    private LocalDate fechaPago;

    // Constructor vacío necesario para JPA
    public Cuota() {}

    // Constructor con todos los campos
    public Cuota(Long idCuota, Prestamo prestamo, Integer numeroCuota, Double montoCuota, LocalDate fechaVencimiento, Boolean esPagada, LocalDate fechaPago) {
        this.idCuota = idCuota;
        this.prestamo = prestamo;
        this.numeroCuota = numeroCuota;
        this.montoCuota = montoCuota;
        this.fechaVencimiento = fechaVencimiento;
        this.esPagada = esPagada != null ? esPagada : false; // Valor por defecto
        this.fechaPago = fechaPago;
    }

    /**
     * Método para marcar la cuota como pagada.
     */
    public void marcarComoPagada() {
        if (Boolean.TRUE.equals(this.esPagada)) {
            throw new IllegalStateException("La cuota ya ha sido pagada.");
        }
        this.esPagada = true;
        this.fechaPago = LocalDate.now();
    }

    public Long getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(Long idCuota) {
        this.idCuota = idCuota;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Double getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(Double montoCuota) {
        this.montoCuota = montoCuota;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Boolean getEsPagada() {
        return esPagada;
    }

    public void setEsPagada(Boolean esPagada) {
        this.esPagada = esPagada;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }
}


