package com.prestamos.gestion_prestamos.service;

import com.prestamos.gestion_prestamos.model.Prestamo;
import com.prestamos.gestion_prestamos.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    /**
     * Crear un nuevo préstamo.
     */
    public Prestamo crearPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    /**
     * Obtener todos los préstamos asociados a un usuario.
     */
    public List<Prestamo> obtenerPrestamosPorUsuario(Long idUsuario) {
        return prestamoRepository.findByUsuario_IdUsuario(idUsuario);
    }

    /**
     * Obtener un préstamo por su ID.
     */
    public Prestamo obtenerPrestamoPorId(Long idPrestamo) {
        return prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + idPrestamo));
    }
}
