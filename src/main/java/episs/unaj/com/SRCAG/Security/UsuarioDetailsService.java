package episs.unaj.com.SRCAG.Security;

import episs.unaj.com.SRCAG.Entity.Usuario;               // 👈 Soluciona 'Usuario' rojo
import episs.unaj.com.SRCAG.Repository.UsuarioRepository; // 👈 Soluciona 'UsuarioRepository' rojo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority; // 👈 Soluciona 'SimpleGrantedAuthority' rojo
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 👈 Soluciona '@Transactional' rojo

import java.util.ArrayList; // 👈 Soluciona 'ArrayList' rojo
import java.util.List;      // 👈 Soluciona 'List' rojo

@Service("userDetailsService")// Nombre del Bean que suele pedir el profesor
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository; // Inyección clásica por @Autowired

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. Buscamos al usuario en la base de datos
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // 2. Mapeamos los roles a GrantedAuthority (Lista de autoridades) al estilo del profesor
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Se le agrega el prefijo "ROLE_" tal como lo maneja Spring de forma explícita
        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));

        // 3. Retornamos la instancia de User de Spring Security con sus parámetros
        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                true,          // enabled (usuario activo)
                true,          // accountNonExpired
                true,          // credentialsNonExpired
                true,          // accountNonLocked
                authorities    // Roles asignados
        );
    }
}
