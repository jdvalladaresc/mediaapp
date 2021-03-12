package cl.jvalladares.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author jvalladares
 */
@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    @JsonProperty("id_consulta")
    private Integer idConsulta;

    @ManyToOne //FK
    @JoinColumn(name = "id_Paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_paciente"))
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_especialidad", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_especialidad"))
    private Especialidad especialidad;

    @JsonProperty("numero_consultorio")
    @Column(name = "numero_consultorio", nullable = true, length = 3)
    private String numConsultorio;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    //Este atributo es a nivel logico
    @OneToMany(mappedBy = "consulta", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JsonProperty("detalle_consulta")
    private List<DetalleConsulta> detalleConsultas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consulta)) return false;
        Consulta consulta = (Consulta) o;
        return getIdConsulta().equals(consulta.getIdConsulta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdConsulta());
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getNumConsultorio() {
        return numConsultorio;
    }

    public void setNumConsultorio(String numConsultorio) {
        this.numConsultorio = numConsultorio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<DetalleConsulta> getDetalleConsultas() {
        return detalleConsultas;
    }

    public void setDetalleConsultas(List<DetalleConsulta> detalleConsultas) {
        this.detalleConsultas = detalleConsultas;
    }
}
