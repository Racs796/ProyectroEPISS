package episs.unaj.com.SRCAG.Service.impl;

import episs.unaj.com.SRCAG.Entity.Membresia;
import episs.unaj.com.SRCAG.Repository.MembresiaRepository;
import episs.unaj.com.SRCAG.Service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    @Service
public class MembresiaServiceImpl implements MembresiaService {

    @Autowired
    private MembresiaRepository membresiaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Membresia> obtenerTodasLasMembresia() {
        return membresiaRepository.findAll();
    }

    @Override
    @Transactional
    public Membresia guardarMembresia(Membresia membresia) {
        return membresiaRepository.save(membresia);
    }

    @Override
    @Transactional(readOnly = true)
    public Membresia buscarPorId(Long id) {
        return membresiaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void borrarMembresia(Long id) {
        membresiaRepository.deleteById(id);
    }
}