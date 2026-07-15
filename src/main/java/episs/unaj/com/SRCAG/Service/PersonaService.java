package episs.unaj.com.SRCAG.service;

import episs.unaj.com.SRCAG.entity.Persona;
import java.util.List;

public interface PersonaService {
    List<Persona> listarTodas();
    Persona guardar(Persona persona);
    Persona obtenerPorId(Long id);
    void eliminar(Long id);
    Persona obtenerPorDni(String dni); // Búsqueda personalizada útil para el gimnasio
}