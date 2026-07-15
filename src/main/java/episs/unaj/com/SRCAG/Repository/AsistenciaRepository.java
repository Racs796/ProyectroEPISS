package episs.unaj.com.SRCAG.Repository;

import episs.unaj.com.SRCAG.Entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    // Hereda de forma automática todos los métodos para Guardar, Buscar, Listar y Eliminar
}