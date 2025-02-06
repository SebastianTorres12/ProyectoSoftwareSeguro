package com.prestamos.gestion_prestamos.repository;

import com.prestamos.gestion_prestamos.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    /**
     * Buscar préstamos por el ID del usuario.
     */
    List<Prestamo> findByUsuario_IdUsuario(Long idUsuario);

    /**
     * Contar préstamos activos de un usuario.
     */
    long countByUsuario_IdUsuarioAndEstado(Long idUsuario, String estado);
}
