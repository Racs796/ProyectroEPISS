package episs.unaj.com.SRCAG.Controller;

import episs.unaj.com.SRCAG.Entity.Membresia;
import episs.unaj.com.SRCAG.Service.MembresiaService;
import episs.unaj.com.SRCAG.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/membresia")
public class MembresiaController {

    @Autowired
    private MembresiaService membresiaService;

    @Autowired
    private PersonaService personaService;

    // Listar todas las membresías
    @GetMapping
    public String listarMembresias(Model model) {
        model.addAttribute("titulo", "Listado de Membresías");
        model.addAttribute("membresias", membresiaService.listarTodas());
        return "membresia/Listar"; // Apunta a templates/membresia/Listar.html
    }

    // Mostrar formulario para registrar una nueva membresía
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("titulo", "Registrar Nueva Membresía");
        model.addAttribute("membresia", new Membresia());

        // Enviamos la lista completa de socios para llenar el menú desplegable del HTML
        model.addAttribute("personas", personaService.listarTodas());

        return "membresia/formulario"; // Apunta a templates/membresia/formulario.html
    }

    // Procesar y guardar la membresía
    @PostMapping("/guardar")
    public String guardarMembresia(@ModelAttribute("membresia") Membresia membresia) {
        membresiaService.guardar(membresia);
        return "redirect:/membresia";
    }
}