package episs.unaj.com.SRCAG.Security;

import episs.unaj.com.SRCAG.Entity.Usuario;
import episs.unaj.com.SRCAG.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    // Inyección por constructor, tal como prefiere el profesor en esta sección
    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario: " + username));

        // Construye el objeto de seguridad que Spring entiende perfectamente
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())   // Contraseña encriptada en base de datos (BCrypt)
                .roles(usuario.getRol())            // Spring le antepone "ROLE_" automáticamente
                .build();
    }
}