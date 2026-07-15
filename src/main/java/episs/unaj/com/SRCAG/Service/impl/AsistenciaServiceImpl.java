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
    public List<Asistencia> obtenerTodasLasAsistencias() {
        return asistenciaRepository.findAll();
    }

    @Override
    @Transactional
    public Asistencia registrarAsistencia(Asistencia asistencia) {
        // Al usar el constructor vacío de Asistencia se asigna LocalDateTime.now() automáticamente
        return asistenciaRepository.save(asistencia);
    }

    @Override
    @Transactional(readOnly = true)
    public Asistencia obtenerAsistenciaPorId(Long id) {
        return asistenciaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarAsistencia(Long id) {
        asistenciaRepository.deleteById(id);
    }
}