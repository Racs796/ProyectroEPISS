package episs.unaj.com.SRCAG.Service;

import episs.unaj.com.SRCAG.Entity.Asistencia;
import java.util.List;

public interface AsistenciaService {
    List<Asistencia> listarTodas();
    Asistencia guardar(Asistencia asistencia);
    Asistencia obtenerPorId(Long id);
    void eliminar(Long id);
}