package episs.unaj.com.SRCAG.Service; // <-- Asegúrate de que coincida con tus mayúsculas

import episs.unaj.com.SRCAG.Entity.Persona;
import java.util.List;

public interface PersonaService {
    List<Persona> listarTodas();
    Persona guardar(Persona persona);
    Persona obtenerPorId(Long id);
    void eliminar(Long id);
}