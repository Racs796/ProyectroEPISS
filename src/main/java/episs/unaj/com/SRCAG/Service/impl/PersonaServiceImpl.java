package episs.unaj.com.SRCAG.Service.impl;

import episs.unaj.com.SRCAG.Entity.Persona;
import episs.unaj.com.SRCAG.Repository.PersonaRepository;
import episs.unaj.com.SRCAG.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarTodas() {
        return personaRepository.findAll();
    }

    @Override
    @Transactional
    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona obtenerPorId(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con el ID: " + id));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Persona no encontrada con el ID: " + id);
        }
        personaRepository.deleteById(id);
    }
}