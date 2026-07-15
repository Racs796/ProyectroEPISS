package episs.unaj.com.SRCAG.Repository;

import episs.unaj.com.SRCAG.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // Hereda automáticamente todos los métodos CRUD gracias a JpaRepository
}