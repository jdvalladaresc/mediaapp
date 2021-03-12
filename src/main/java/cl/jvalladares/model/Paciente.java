/**
 *
 */
package cl.jvalladares.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author jvalladares
 *
 */

@Schema(description="Información del paciente")
@Entity
@Table(name = "Paciente") //,scheme
public class Paciente {

	@Schema(description = "Identificador del paciente")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
	@JsonProperty("id_paciente")
    private Integer idPaciente;

	@Schema(description = "Nombres del paciente")
    @Size(min = 3, message = "Nombres debe tener minimo 3 caracteres")
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

	@Schema(description = "Apellidos del paciente")
    @Size(min = 3, message = "Apellidos debe tener minimo 3 caracteres")
    @Column(name = "apellido", nullable = false, length = 45)
    private String apellido;

	@Schema(description = "Rut del paciente")
    @Size(min = 8, message = "El rut debe tener 9 caracteres sin punto y sin guion")
    @Column(name = "rut", nullable = false, length = 9, unique = true)
    private String rut;

	@Schema(description = "Dirección del paciente")
    @Size(min=3, max = 150, message = "Dirección debe tener minimo 3 caracteres y máximo 150")
    @Column(name = "direccion", nullable = false, length = 70)
    private String direccion;

	@Schema(description = "Telefono del paciente")
    @Size(min = 9, message = "El telefono debe tener minimo 9 caracteres.")
    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;

	@Schema(description = "Email del paciente")
    @Email()
    @Column(name = "email", nullable = false, length = 70)
    private String email;

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
