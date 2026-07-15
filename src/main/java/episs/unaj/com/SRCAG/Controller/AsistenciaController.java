package episs.unaj.com.SRCAG.Controller;

import episs.unaj.com.SRCAG.Dto.AsistenciaForm; // Tu DTO auxiliar si manejas ids sueltos
import episs.unaj.com.SRCAG.Service.AsistenciaService;
import episs.unaj.com.SRCAG.Service.PersonaService; // Para listar los miembros en el formulario
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private PersonaService personaService;

    // 1. Listar el historial de asistencias del gimnasio
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("asistencias", asistenciaService.obtenerTodas());
        return "asistencias/index";
    }

    // 2. Mostrar formulario para registrar una nueva asistencia
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asistenciaForm", new AsistenciaForm());
        model.addAttribute("personas", personaService.obtenerTodasLasPersonas()); // Para el <select> en el HTML
        return "asistencias/form";
    }

    // 3. Procesar el registro de la asistencia
    @PostMapping
    public String guardar(@ModelAttribute AsistenciaForm asistenciaForm) {
        asistenciaService.registrarAsistencia(asistenciaForm);
        return "redirect:/asistencias";
    }

    // 4. Ver detalles de una asistencia específica (opcional, como el del profesor)
    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        model.addAttribute("asistencia", asistenciaService.obtenerPorId(id));
        return "asistencias/ver";
    }

    // 5. Eliminar o anular un registro de asistencia
    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        asistenciaService.eliminar(id);
        return "redirect:/asistencias";
    }
}