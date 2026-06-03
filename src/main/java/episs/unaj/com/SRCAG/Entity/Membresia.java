package episs.unaj.com.SRCAG.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "membresia")
public class Membresia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long socioId;
    private Double precio;
    private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaFin;
    private String estado;

    public Membresia() {}

    public Membresia(Long id, Long socioId, Double precio, LocalDate fechaInicio, LocalDate fechaFin, String estado) {
        this.id = id;
        this.socioId = socioId;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSocioId() {
        return socioId;
    }

    public void setSocioId(Long socioId) {
        this.socioId = socioId;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
