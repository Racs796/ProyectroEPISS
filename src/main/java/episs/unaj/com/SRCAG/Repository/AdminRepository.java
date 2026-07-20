package episs.unaj.com.SRCAG.Repository; // <-- Asegúrate de que coincida con tu paquete de repositorios

import episs.unaj.com.SRCAG.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Método personalizado para buscar un administrador por su correo electrónico
    Optional<Admin> findByCorreo(String correo);
}