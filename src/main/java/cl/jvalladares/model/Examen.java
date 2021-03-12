package cl.jvalladares.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jvalladares
 */

@Entity
@Table
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_examen")
    @JsonProperty("id_examen")
    private Integer idExamen;

    @Column(name="nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name="descripcion", nullable = false, length = 50)
    private String descripcion;

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Examen)) return false;
        Examen examen = (Examen) o;
        return getIdExamen().equals(examen.getIdExamen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdExamen());
    }
}
