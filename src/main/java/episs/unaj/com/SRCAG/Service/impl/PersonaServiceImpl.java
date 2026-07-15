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
    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    @Override
    @Transactional
    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona obtenerPersonaPorId(Long id) {
        // Mantenemos el uso de getReferenceById tal como lo hace el profesor
        return personaRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public Persona actualizarPersona(Long id, Persona persona) {
        persona.setId(id);
        return personaRepository.save(persona);
    }

    @Override
    @Transactional
    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }
}
