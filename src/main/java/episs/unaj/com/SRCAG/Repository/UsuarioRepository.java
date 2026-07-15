package episs.unaj.com.SRCAG.Repository;

import episs.unaj.com.SRCAG.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método clave que usa tu UsuarioDetailsService para el Login
    Usuario findByUsername(String username);
}