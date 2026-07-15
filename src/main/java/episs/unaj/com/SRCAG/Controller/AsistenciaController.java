package episs.unaj.com.SRCAG.Controller; // <-- Con 'C' mayúscula como tus carpetas

import episs.unaj.com.SRCAG.Entity.Asistencia;
import episs.unaj.com.SRCAG.Service.AsistenciaService;
import episs.unaj.com.SRCAG.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asistencia") // Ruta en el navegador: http://localhost:8080/asistencia
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private PersonaService personaService; // Lo necesitamos para cargar la lista de personas en el formulario

    // 1. Listar todas las asistencias (Estilo del profesor)
    @GetMapping
    public String listarAsistencias(Model model) {
        model.addAttribute("titulo", "Listado de Asistencias del Gimnasio");
        model.addAttribute("asistencias", asistenciaService.listarTodas());
        return "asistencia/listar"; // Redirecciona al archivo HTML: templates/asistencia/listar.html
    }

    // 2. Mostrar formulario para registrar nueva asistencia
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Asistencia asistencia = new Asistencia();
        model.addAttribute("titulo", "Registrar Nueva Asistencia");
        model.addAttribute("asistencia", asistencia);
        // Enviamos la lista de personas para que se puedan seleccionar en un <select> de HTML
        model.addAttribute("personas", personaService.listarTodas());
        return "asistencia/formulario"; // templates/asistencia/formulario.html
    }

    // 3. Guardar o actualizar la asistencia (Procesa el formulario)
    @PostMapping("/guardar")
    public String guardarAsistencia(@ModelAttribute Asistencia asistencia) {
        asistenciaService.guardar(asistencia);
        return "redirect:/asistencia"; // Redirecciona a la lista de asistencias tras guardar
    }

    // 4. Mostrar formulario para editar una asistencia existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        try {
            Asistencia asistencia = asistenciaService.obtenerPorId(id);
            model.addAttribute("titulo", "Editar Registro de Asistencia");
            model.addAttribute("asistencia", asistencia);
            model.addAttribute("personas", personaService.listarTodas());
            return "asistencia/formulario"; // Reutiliza el mismo formulario de creación
        } catch (RuntimeException e) {
            return "redirect:/asistencia"; // Si hay error, vuelve a la lista
        }
    }

    // 5. Eliminar una asistencia
    @GetMapping("/eliminar/{id}")
    public String eliminarAsistencia(@PathVariable("id") Long id) {
        try {
            asistenciaService.eliminar(id);
        } catch (RuntimeException e) {
            // Manejo de errores silencioso o puedes añadir logs aquí si el profesor lo pide
        }
        return "redirect:/asistencia"; // Vuelve a recargar la lista actualizándose
    }
}