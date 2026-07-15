package episs.unaj.com.SRCAG.Controller; // <-- Con 'C' mayúscula

import episs.unaj.com.SRCAG.Entity.Persona;
import episs.unaj.com.SRCAG.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/persona") // Ruta en el navegador: http://localhost:8080/persona
public class PersonaController {

    @Autowired
    private PersonaService personaService; // El controlador llama al Servicio, y este al Repositorio

    // 1. Listar todas las personas (Estilo del profesor)
    @GetMapping
    public String listarPersonas(Model model) {
        model.addAttribute("titulo", "Listado de Clientes / Personas");
        model.addAttribute("personas", personaService.listarTodas());
        return "persona/listar"; // Redirecciona al HTML: templates/persona/listar.html
    }

    // 2. Mostrar formulario para registrar una nueva Persona
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Persona persona = new Persona(); // Se crea un objeto vacío que se llenará con los datos del formulario
        model.addAttribute("titulo", "Registrar Nueva Persona");
        model.addAttribute("persona", persona);
        return "persona/formulario"; // Redirecciona al HTML: templates/persona/formulario.html
    }

    // 3. Guardar o actualizar la Persona (Procesa los datos enviados desde el formulario)
    @PostMapping("/guardar")
    public String guardarPersona(@ModelAttribute Persona persona) {
        personaService.guardar(persona);
        return "redirect:/persona"; // Vuelve a recargar la lista de personas tras guardar
    }

    // 4. Mostrar formulario para editar una Persona existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        try {
            Persona persona = personaService.obtenerPorId(id);
            model.addAttribute("titulo", "Editar Datos de Persona");
            model.addAttribute("persona", persona);
            return "persona/formulario"; // Reutiliza el mismo formulario de creación
        } catch (RuntimeException e) {
            return "redirect:/persona"; // Si hay error o no se encuentra, vuelve a la lista
        }
    }

    // 5. Eliminar una Persona de la Base de Datos
    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable("id") Long id) {
        try {
            personaService.eliminar(id);
        } catch (RuntimeException e) {
            // Manejo de errores silencioso como suele estructurar el profesor
        }
        return "redirect:/persona"; // Redirecciona a la lista actualizándose
    }
}