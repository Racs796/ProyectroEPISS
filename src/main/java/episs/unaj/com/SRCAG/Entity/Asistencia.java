package episs.unaj.com.SRCAG.Entity; // <-- Primera línea

import episs.unaj.com.SRCAG.Entity.Persona; // 👈 ¡AÑADE ESTA LÍNEA AQUÍ!
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "asistencia")
public class Asistencia {
    // ... todo tu código restante igual ...

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_entrada", nullable = false)
    private LocalTime horaEntrada;

    // Relación ManyToOne con Persona (Muchos registros de asistencia pertenecen a una Persona)
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    // Constructor vacío
    public Asistencia() {
    }

    // Constructor con parámetros
    public Asistencia(LocalDate fecha, LocalTime horaEntrada, Persona persona) {
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.persona = persona;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalTime horaEntrada) { this.horaEntrada = horaEntrada; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }

    @Override
    public String toString() {
        return "Asistencia{" + "id=" + id + ", fecha=" + fecha + ", horaEntrada=" + horaEntrada + '}';
    }
}