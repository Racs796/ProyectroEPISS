package episs.unaj.com.SRCAG.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;

    // Relaciones para el funcionamiento de tu gimnasio
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Membresia> membresias;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Asistencia> asistencias;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String email, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters y Setters standard (Estilo del profesor)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<Membresia> getMembresias() { return membresias; }
    public void setMembresias(List<Membresia> membresias) { this.membresias = membresias; }

    public List<Asistencia> getAsistencias() { return asistencias; }
    public void setAsistencias(List<Asistencia> asistencias) { this.asistencias = asistencias; }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre='" + nombre + '\'' + ", apellido='" + apellido + '\'' + ", email='" + email + '\'' + ", telefono='" + telefono + '\'' + ", direccion='" + direccion + '\'' + '}';
    }
}