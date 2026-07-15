package episs.unaj.com.SRCAG.Service;

import episs.unaj.com.SRCAG.Entity.Asistencia;
import java.util.List;

public interface AsistenciaService {
    List<Asistencia> obtenerTodasLasAsistencias();
    Asistencia registrarAsistencia(Asistencia asistencia);
    Asistencia obtenerAsistenciaPorId(Long id);
    void eliminarAsistencia(Long id);
}
