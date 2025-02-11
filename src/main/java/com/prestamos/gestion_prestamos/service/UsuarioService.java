package com.prestamos.gestion_prestamos.service;

import com.prestamos.gestion_prestamos.model.Usuario;
import com.prestamos.gestion_prestamos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Se usa PasswordEncoder en lugar de BCryptPasswordEncoder

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registrar un nuevo usuario con contraseña cifrada.
     */
    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setContrasenaHash(passwordEncoder.encode(usuario.getContrasenaHash()));
        usuario.setIntentosFallidos(0);
        usuario.setCuentaBloqueada(false);
        return usuarioRepository.save(usuario);
    }

    /**
     * Obtener un usuario por su correo electrónico.
     */
    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    /**
     * Incrementar intentos fallidos de inicio de sesión y bloquear la cuenta si es necesario.
     */
    public void incrementarIntentosFallidos(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        if (!usuario.getCuentaBloqueada()) {
            usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);

            if (usuario.getIntentosFallidos() >= 3) {
                usuario.setCuentaBloqueada(true);
                throw new RuntimeException("Cuenta bloqueada por múltiples intentos fallidos.");
            }

            usuarioRepository.save(usuario);
        }
    }

    /**
     * Desbloquear una cuenta de usuario restableciendo los intentos fallidos.
     */
    public void desbloquearCuenta(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        usuario.setIntentosFallidos(0);
        usuario.setCuentaBloqueada(false);
        usuarioRepository.save(usuario);
    }

    /**
     * Autenticar un usuario verificando la contraseña y controlando intentos fallidos.
     */
    public void autenticarUsuario(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        if (usuario.getCuentaBloqueada()) {
            throw new RuntimeException("Cuenta bloqueada. Contacte al administrador.");
        }

        if (!passwordEncoder.matches(contrasena, usuario.getContrasenaHash())) {
            incrementarIntentosFallidos(correo);
            throw new RuntimeException("Credenciales incorrectas.");
        }

        // Reiniciar intentos fallidos después de un inicio de sesión exitoso
        usuario.setIntentosFallidos(0);
        usuarioRepository.save(usuario);
    }
}
