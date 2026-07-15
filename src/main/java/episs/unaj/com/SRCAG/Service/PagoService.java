package episs.unaj.com.SRCAG.Service;

import episs.unaj.com.SRCAG.Entity.Pago;
import java.util.List;

public interface PagoService {

    List<Pago> obtenerTodosLosPagos();

    Pago guardarPago(Pago pago);

    Pago buscarPorId(Long id);

    Pago actualizarPago(Long id, Pago pago);

    void borrarPago(Long id);
}