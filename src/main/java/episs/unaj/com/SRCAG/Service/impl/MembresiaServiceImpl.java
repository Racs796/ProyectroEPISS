package episs.unaj.com.SRCAG.Service.impl;

import episs.unaj.com.SRCAG.Entity.Membresia;
import episs.unaj.com.SRCAG.Repository.MembresiaRepository;
import episs.unaj.com.SRCAG.Service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembresiaServiceImpl implements MembresiaService {
    @Autowired
    private MembresiaRepository membresiaRepository;
    @Override
    public List<Membresia> obtenerTodasLasMembresia() {
        return membresiaRepository.findAll();
    }
    @Override
    public Membresia guardarMembresia(Membresia membresia) {
        return membresiaRepository.save(membresia);
    }
    @Override
    public Membresia buscarPorId(Long id) {
        return membresiaRepository.findById(id).orElse(null);
    }
    @Override
    public Membresia actualizarMembresia (Long id, Membresia membresia) {
        return membresiaRepository.save(membresia);
    }
    @Override
    public void borrarMembresia(Long id) {
        membresiaRepository.deleteById(id);
    }
}
