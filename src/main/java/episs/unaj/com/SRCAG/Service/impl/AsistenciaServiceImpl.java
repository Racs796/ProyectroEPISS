package episs.unaj.com.SRCAG.Service.impl;

import episs.unaj.com.SRCAG.Entity.Asistencia;
import episs.unaj.com.SRCAG.Repository.AsistenciaRepository;
import episs.unaj.com.SRCAG.Service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Asistencia> listarTodas() {
        return asistenciaRepository.findAll();
    }

    @Override
    @Transactional
    public Asistencia guardar(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Override
    @Transactional(readOnly = true)
    public Asistencia obtenerPorId(Long id) {
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de asistencia no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Registro de asistencia no encontrado con ID: " + id);
        }
        asistenciaRepository.deleteById(id);
    }
}