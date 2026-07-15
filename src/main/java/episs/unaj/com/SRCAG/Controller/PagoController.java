package episs.unaj.com.SRCAG.Controller;

import episs.unaj.com.SRCAG.Entity.Pago;
import episs.unaj.com.SRCAG.Repository.MembresiaRepository;
import episs.unaj.com.SRCAG.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private MembresiaRepository membresiaRepository;

    @GetMapping("/lista")
    public String listarPagos(Model model) {
        model.addAttribute("listaPagos", pagoService.obtenerTodosLosPagos());
        return "Pago/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoPago(Model model) {
        model.addAttribute("pago", new Pago());
        model.addAttribute("membresias", membresiaRepository.findAll());
        return "Pago/formulario";
    }

    @PostMapping("/guardar")
    public String guardarPago(@ModelAttribute Pago pago) {
        pagoService.guardarPago(pago);
        return "redirect:/pago/lista";
    }

    @GetMapping("/editar/{id}")
    public String editarPago(@PathVariable Long id, Model model) {
        model.addAttribute("pago", pagoService.buscarPorId(id));
        model.addAttribute("membresias", membresiaRepository.findAll());
        return "Pago/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPago(@PathVariable Long id) {
        pagoService.borrarPago(id);
        return "redirect:/pago/lista";
    }
}