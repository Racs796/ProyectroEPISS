package episs.unaj.com.SRCAG.Service.Impl; // <-- Asegúrate de que coincida con tu paquete

import episs.unaj.com.SRCAG.Entity.Admin;
import episs.unaj.com.SRCAG.Repository.AdminRepository;
import episs.unaj.com.SRCAG.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Admin> listarTodos() {
        return adminRepository.findAll();
    }

    @Override
    @Transactional
    public Admin guardar(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public Admin obtenerPorId(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        // Verificamos primero si existe antes de eliminar
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Administrador no encontrado con el ID: " + id);
        }
        adminRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Admin buscarPorCorreo(String correo) {
        return adminRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con el correo: " + correo));
    }
}