package episs.unaj.com.SRCAG.Controller;

import episs.unaj.com.SRCAG.Entity.Membresia;
import episs.unaj.com.SRCAG.Entity.Persona;
import episs.unaj.com.SRCAG.Service.MembresiaService;
import episs.unaj.com.SRCAG.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/membresias") // Usamos el plural siguiendo el estilo de su /productos
public class MembresiaController {

    @Autowired
    private MembresiaService membresiaService;

    @Autowired
    private PersonaService personaService;

    // 1. Listar todas las membresías
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("membresias", membresiaService.obtenerTodasLasMembresias());
        return "membresia/index";
    }

    // 2. Mostrar formulario para crear nueva membresía (Envia la lista de socios)
    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("membresia", new Membresia());
        model.addAttribute("personas", personaService.obtenerTodasLasPersonas()); // Para asignar el socio
        return "membresia/form";
    }

    // 3. Procesar el guardado asignando la Persona vía @RequestParam
    @PostMapping
    public String guardar(@ModelAttribute Membresia membresia,
                          @RequestParam Long personaId) {
        // Buscamos a la persona/socio y la vinculamos
        Persona persona = personaService.obtainerPersonaPorId(personaId);
        membresia.setPersona(persona);

        // Lógica automática de estado según las fechas
        if (membresia.getFechaFin() != null && membresia.getFechaFin().isBefore(LocalDate.now())) {
            membresia.setEstado("Vencido");
        } else {
            membresia.setEstado("Activo");
        }

        membresiaService.guardarMembresia(membresia);
        return "redirect:/membresias";
    }

    // 4. Mostrar formulario para editar membresía existente
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("membresia", membresiaService.obtenerMembresiaPorId(id));
        model.addAttribute("personas", personaService.obtenerTodasLasPersonas());
        return "membresia/form";
    }

    // 5. Procesar la actualización asignando la Persona vía @RequestParam
    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @ModelAttribute Membresia membresia,
                             @RequestParam Long personaId) {
        Persona persona = personaService.obtainerPersonaPorId(personaId);
        membresia.setPersona(persona);

        if (membresia.getFechaFin() != null && membresia.getFechaFin().isBefore(LocalDate.now())) {
            membresia.setEstado("Vencido");
        } else {
            membresia.setEstado("Activo");
        }

        membresiaService.actualizarMembresia(id, membresia);
        return "redirect:/membresias";
    }

    // 6. Eliminar membresía
    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        membresiaService.eliminarMembresia(id);
        return "redirect:/membresias";
    }
}