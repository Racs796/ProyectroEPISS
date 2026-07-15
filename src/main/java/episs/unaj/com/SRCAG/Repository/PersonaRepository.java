package episs.unaj.com.SRCAG.repository;

import episs.unaj.com.SRCAG.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // Spring Data JPA genera el SQL automático para buscar por DNI
    Optional<Persona> findByDni(String dni);
}