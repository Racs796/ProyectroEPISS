package episs.unaj.com.SRCAG.Service.impl;

import episs.unaj.com.SRCAG.Entity.Pago;
import episs.unaj.com.SRCAG.Repository.PagoRepository;
import episs.unaj.com.SRCAG.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Impl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago buscarPorId(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    @Override
    public Pago actualizarPago(Long id, Pago pago) {
        Pago pagoExistente = pagoRepository.findById(id).orElse(null);

        if (pagoExistente != null) {
            pagoExistente.setMonto(pago.getMonto());
            pagoExistente.setFormaPago(pago.getFormaPago());
            pagoExistente.setFechaPago(pago.getFechaPago());
            pagoExistente.setEstado(pago.getEstado());
            pagoExistente.setDescripcion(pago.getDescripcion());

            return pagoRepository.save(pagoExistente);
        }

        return null;
    }

    @Override
    public void borrarPago(Long id) {
        pagoRepository.deleteById(id);
    }
}