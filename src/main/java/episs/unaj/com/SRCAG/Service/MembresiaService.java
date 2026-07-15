package episs.unaj.com.SRCAG.Service;

import episs.unaj.com.SRCAG.Entity.Membresia;
import java.util.List;

public interface MembresiaService {
    List<Membresia> obtenerTodasLasMembresia();
    Membresia guardarMembresia(Membresia membresia);
    Membresia buscarPorId(Long id);
    void borrarMembresia(Long id);
}