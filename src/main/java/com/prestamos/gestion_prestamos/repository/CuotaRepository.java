package com.prestamos.gestion_prestamos.repository;

import com.prestamos.gestion_prestamos.model.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Long> {
    /**
     * Buscar cuotas por el ID del préstamo.
     */
    List<Cuota> findByPrestamo_IdPrestamo(Long idPrestamo);

    /**
     * Contar cuotas pendientes de un préstamo.
     */
    long countByPrestamo_IdPrestamoAndEsPagada(Long idPrestamo, Boolean esPagada);

}
