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

    // 1. Listar todas las membresías
    @GetMapping
    public String listarMembresias(Model model) {
        model.addAttribute("titulo", "Listado de Membresías");
        model.addAttribute("membresias", membresiaService.listarTodas()); // <-- OK
        return "membresia/listar";
    }

    // 2. Mostrar formulario para registrar nueva Membresía
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Membresia membresia = new Membresia();
        model.addAttribute("titulo", "Registrar Nueva Membresía");
        model.addAttribute("membresia", membresia);
        model.addAttribute("personas", personaService.listarTodas());
        return "membresia/formulario";
    }

    // 3. Guardar o actualizar la Membresía
    @PostMapping("/guardar")
    public String guardarMembresia(@ModelAttribute Membresia membresia) {
        membresiaService.guardar(membresia); // <-- OK
        return "redirect:/membresia";
    }

    // 4. Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        try {
            Membresia membresia = membresiaService.obtenerPorId(id); // <-- OK
            model.addAttribute("titulo", "Editar Membresía");
            model.addAttribute("membresia", membresia);
            model.addAttribute("personas", personaService.listarTodas());
            return "membresia/formulario";
        } catch (RuntimeException e) {
            return "redirect:/membresia";
        }
    }

    // 5. Eliminar una Membresía
    @GetMapping("/eliminar/{id}")
    public String eliminarMembresia(@PathVariable("id") Long id) {
        try {
            membresiaService.eliminar(id); // <-- OK
        } catch (RuntimeException e) {
            // Manejo silencioso
        }
        return "redirect:/membresia";
    }
}