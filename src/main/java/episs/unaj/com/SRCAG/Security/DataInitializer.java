package episs.unaj.com.SRCAG.Security; // <-- Asegúrate de que coincida con tu paquete

import episs.unaj.com.SRCAG.Entity.Usuario;
import episs.unaj.com.SRCAG.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    // Cambia "UsuarioRepository" por el nombre exacto de tu repositorio de usuarios
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Si no existe el usuario admin en la base de datos, lo creamos
        // Cambia la línea del if por esta:
        if (usuarioRepository.findByUsername("admin") == null) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            // Encriptamos la contraseña "admin123" usando tu codificador BCrypt
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol("ROLE_ADMIN");

            usuarioRepository.save(admin);
            System.out.println("✅ [Seguridad] Usuario 'admin' creado exitosamente con contraseña 'admin123'");
        }
    }
}