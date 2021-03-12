/**
 * 
 */
package cl.jvalladares.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.jvalladares.model.Consulta;
import cl.jvalladares.model.Examen;

/**
 * @author josevalladares
 *
 */
public class ConsultaListaExamenDTO {
	
	private Consulta consulta;
	
	@JsonProperty("lista_examenes")
	private List<Examen> listaExamen;
	
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public List<Examen> getListaExamen() {
		return listaExamen;
	}
	public void setListaExamen(List<Examen> listaExamen) {
		this.listaExamen = listaExamen;
	}

	
}
