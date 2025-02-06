package com.prestamos.gestion_prestamos.controller;

import com.prestamos.gestion_prestamos.model.Prestamo;
import com.prestamos.gestion_prestamos.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    /**
     * Crear un nuevo préstamo (solo usuarios).
     */
    @PreAuthorize("hasRole('USUARIO')")
    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@RequestBody Prestamo prestamo) {
        Prestamo nuevoPrestamo = prestamoService.crearPrestamo(prestamo);
        return ResponseEntity.ok(nuevoPrestamo);
    }

    /**
     * Obtener todos los préstamos de un usuario (solo admins pueden ver de todos los usuarios).
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosPorUsuario(@PathVariable Long idUsuario) {
        List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorUsuario(idUsuario);
        return ResponseEntity.ok(prestamos);
    }

    /**
     * Obtener un préstamo específico por su ID.
     */
    @PreAuthorize("hasRole('USUARIO') or hasRole('ADMIN')")
    @GetMapping("/{idPrestamo}")
    public ResponseEntity<Prestamo> obtenerPrestamoPorId(@PathVariable Long idPrestamo) {
        Prestamo prestamo = prestamoService.obtenerPrestamoPorId(idPrestamo);
        return ResponseEntity.ok(prestamo);
    }
}
