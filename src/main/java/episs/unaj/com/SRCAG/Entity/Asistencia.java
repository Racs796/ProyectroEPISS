package episs.unaj.com.SRCAG.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "asistencia") // En singular y minúsculas
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    public Asistencia() {
        // Inicialización automática al momento del registro, como el profesor
        this.fechaIngreso = LocalDateTime.now();
    }

    // Campo calculado que no se guarda en la base de datos (para usarlo directo en Thymeleaf)
    @Transient
    public String getFechaTexto() {
        if (fechaIngreso == null) return "";
        return fechaIngreso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    // Getters y Setters limpios
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDateTime fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
}