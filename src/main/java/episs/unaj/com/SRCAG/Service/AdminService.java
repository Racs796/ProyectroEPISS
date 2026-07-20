package episs.unaj.com.SRCAG.Service; // <-- Asegúrate de que coincida con tu paquete

import episs.unaj.com.SRCAG.Entity.Admin;
import java.util.List;

public interface AdminService {

    // Listar todos los administradores
    List<Admin> listarTodos();

    // Guardar o actualizar un administrador
    Admin guardar(Admin admin);

    // Obtener un administrador por su ID
    Admin obtenerPorId(Long id);

    // Eliminar un administrador por su ID
    void eliminar(Long id);

    // Buscar un administrador por su correo
    Admin buscarPorCorreo(String correo);
}