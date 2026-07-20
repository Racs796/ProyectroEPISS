package episs.unaj.com.SRCAG.Controller; // <-- Asegúrate de que coincida con tu paquete

import episs.unaj.com.SRCAG.Service.MembresiaService;
import episs.unaj.com.SRCAG.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private MembresiaService membresiaService;

    // Vista principal del Panel de Administración (Dashboard)
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("titulo", "Panel de Administración - Gimnasio");

        // Enviamos datos rápidos para mostrar estadísticas sencillas en tarjetas (widgets)
        try {
            model.addAttribute("totalSocios", personaService.listarTodas().size());
        } catch (Exception e) {
            model.addAttribute("totalSocios", 0);
        }

        try {
            model.addAttribute("totalMembresias", membresiaService.listarTodas().size());
        } catch (Exception e) {
            model.addAttribute("totalMembresias", 0);
        }

        // Redirecciona a la plantilla: src/main/resources/templates/admin/dashboard.html
        return "admin/dashboard";
    }
}