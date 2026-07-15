package episs.unaj.com.SRCAG.Service;

import episs.unaj.com.SRCAG.Entity.Membresia;
import java.util.List;

public interface MembresiaService {
    List<Membresia> listarTodas();            // <-- Cambiado para coincidir con membresiaService.listarTodas()
    Membresia guardar(Membresia membresia);   // <-- Simplificado a guardar
    Membresia obtenerPorId(Long id);         // <-- Simplificado a obtenerPorId
    void eliminar(Long id);                  // <-- Simplificado a eliminar
}