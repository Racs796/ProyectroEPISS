package episs.unaj.com.SRCAG.Controller;

import episs.unaj.com.SRCAG.Entity.Membresia;
import episs.unaj.com.SRCAG.Service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/membresia")
public class MembresiaController {
    @Autowired
    private MembresiaService membresiaService;
    @GetMapping
    public List<Membresia> listarMembresias(){
        return membresiaService.obtenerTodasLasMembresia();
    }
    @GetMapping("/{id}")
    public Membresia obtenerPorId(@PathVariable Long id){
        return membresiaService.buscarPorId(id);
    }
    @PostMapping("/{id}")
    public Membresia guardarMembresua(@RequestBody Membresia membresia){
     if (membresia.getFechaFin().isBefore(LocalDate.now())){
         membresia.setEstado("Vencido");
     }else {
         membresia.setEstado("Activo");
     }
        return membresiaService.guardarMembresia(membresia);
    }
    @PutMapping("/{id}")
    public Membresia actualizarMembresia(@PathVariable Long id, @RequestBody Membresia membresia){
        if(membresia.getFechaFin().isBefore(LocalDate.now())){
            membresia.setEstado("Vencido");
        }else {
            membresia.setEstado("Activo");
        }
        return membresiaService.actualizarMembresia(id, membresia);
    }
    @DeleteMapping("/{id}")
    public String eliminarMembresia(@PathVariable Long id){
        membresiaService.borrarMembresia(id);
        return "Membresia eliminada";
    }
}
