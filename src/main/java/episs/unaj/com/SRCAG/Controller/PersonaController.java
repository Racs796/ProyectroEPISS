package episs.unaj.com.SRCAG.Controller;

import episs.unaj.com.SRCAG.Entity.Persona;
import episs.unaj.com.SRCAG.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public String listarPersonas(Model model) {
        List<Persona> personas = personaService.obtenerTodasLasPersonas();
        model.addAttribute("personas", personas);
        return "personas/index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("persona", new Persona());
        return "personas/form";
    }

    @PostMapping
    public String guardarPersona(@ModelAttribute Persona persona) {
        personaService.guardarPersona(persona);
        return "redirect:/personas";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Persona persona = personaService.obtenerPersonaPorId(id);
        model.addAttribute("persona", persona);
        return "personas/form";
    }

    @PostMapping("/{id}")
    public String actualizarPersona(@PathVariable Long id, @ModelAttribute Persona persona) {
        personaService.actualizarPersona(id, persona);
        return "redirect:/personas";
    }

    @GetMapping("/{id}")
    public String verDetalles(@PathVariable Long id, Model model) {
        Persona persona = personaService.obtenerPersonaPorId(id);
        model.addAttribute("persona", persona);
        return "ver"; // Sigue la convención exacta del profesor
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return "redirect:/personas";
    }
}
