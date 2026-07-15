package episs.unaj.com.SRCAG.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "membresia") // En singular y minúsculas
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;        // Ej: Mensual, Anual, Semanal
    private Double precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;      // Activo o Vencido

    // Relación ManyToOne idéntica a Producto-Categoria del profesor
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    public Membresia() {}

    public Membresia(String tipo, Double precio, LocalDate fechaInicio,
                     LocalDate fechaFin, String estado, Persona persona) {
        this.tipo = tipo;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.persona = persona;
    }

    // Getters y Setters limpios en una sola línea
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
}