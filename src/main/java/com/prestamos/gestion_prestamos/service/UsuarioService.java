package com.prestamos.gestion_prestamos.service;

import com.prestamos.gestion_prestamos.model.Usuario;
import com.prestamos.gestion_prestamos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registrar un nuevo usuario.
     */
    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setContrasenaHash(new BCryptPasswordEncoder().encode(usuario.getContrasenaHash()));
        return usuarioRepository.save(usuario);
    }

    /**
     * Obtener un usuario por correo.
     */
    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    /**
     * Incrementar intentos fallidos de inicio de sesión.
     */
    public void incrementarIntentosFallidos(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        if (!usuario.getCuentaBloqueada()) {
            usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);

            if (usuario.getIntentosFallidos() >= 3) {
                usuario.setCuentaBloqueada(true);
            }

            usuarioRepository.save(usuario);
        }
    }

    /**
     * Desbloquear una cuenta de usuario.
     */
    public void desbloquearCuenta(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        usuario.setIntentosFallidos(0);
        usuario.setCuentaBloqueada(false);

        usuarioRepository.save(usuario);
    }
}
