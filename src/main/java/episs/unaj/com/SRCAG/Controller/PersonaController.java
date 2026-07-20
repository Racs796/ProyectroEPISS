package episs.unaj.com.SRCAG.Controller; // Asegúrate de que apunte a tu paquete de controladores

import episs.unaj.com.SRCAG.Entity.Persona;
import episs.unaj.com.SRCAG.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    // Listar todos los socios
    @GetMapping
    public String listarPersonas(Model model) {
        model.addAttribute("titulo", "Listado de Socios");
        // Usamos listarTodas() que es como está en tu interfaz en la imagen
        model.addAttribute("personas", personaService.listarTodas());
        return "persona/listar"; // Apunta a templates/persona/listar.html
    }

    // Mostrar formulario de registro
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("titulo", "Registrar Nuevo Socio");
        model.addAttribute("persona", new Persona());
        return "persona/formulario"; // Apunta a templates/persona/formulario.html
    }

    // Guardar el socio en la base de datos
    @PostMapping("/guardar")
    public String guardarPersona(@ModelAttribute("persona") Persona persona) {
        personaService.guardar(persona);
        return "redirect:/persona"; // Redirige al listado tras salvar
    }
}