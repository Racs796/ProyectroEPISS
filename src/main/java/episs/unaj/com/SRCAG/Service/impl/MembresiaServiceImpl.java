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
    private MembresiaRepository membresiaRepository; // Inyección del repositorio clásico

    @Override
    @Transactional(readOnly = true) // Lectura de datos (estilo del profesor)
    public List<Membresia> listarTodas() {
        return membresiaRepository.findAll();
    }

    @Override
    @Transactional // Escritura de datos
    public Membresia guardar(Membresia membresia) {
        return membresiaRepository.save(membresia);
    }

    @Override
    @Transactional(readOnly = true)
    public Membresia obtenerPorId(Long id) {
        return membresiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!membresiaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Membresía no encontrada con ID: " + id);
        }
        membresiaRepository.deleteById(id);
    }
}