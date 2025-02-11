package com.prestamos.gestion_prestamos.service;

import com.prestamos.gestion_prestamos.model.Cuota;
import com.prestamos.gestion_prestamos.repository.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CuotaService {

    @Autowired
    private CuotaRepository cuotaRepository;

    /**
     * Obtener todas las cuotas de un préstamo.
     */
    public List<Cuota> obtenerCuotasPorPrestamo(Long idPrestamo) {
        return cuotaRepository.findByPrestamo_IdPrestamo(idPrestamo);
    }

    /**
     * Registrar el pago de una cuota.
     */
    public void registrarPagoCuota(Long idCuota) {
        Cuota cuota = cuotaRepository.findById(idCuota)
                .orElseThrow(() -> new RuntimeException("Cuota no encontrada con ID: " + idCuota));

        if (Boolean.TRUE.equals(cuota.getEsPagada())) {
            throw new RuntimeException("La cuota ya ha sido pagada.");
        }

        // Usamos el método del modelo para marcarla como pagada
        cuota.marcarComoPagada();
        cuotaRepository.save(cuota);
    }
}
