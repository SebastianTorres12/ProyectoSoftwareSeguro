package com.prestamos.gestion_prestamos.repository;

import com.prestamos.gestion_prestamos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /**
     * Buscar un usuario por correo.
     */
    Optional<Usuario> findByCorreo(String correo);

    /**
     * Verificar si existe un usuario por correo.
     */
    boolean existsByCorreo(String correo);
}
