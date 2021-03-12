package cl.jvalladares.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author jvalladares
 */
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id_menu")
    private Integer idMenu;

    @Column(name = "icono", length = 45)
    private String icono;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "url", length = 50)
    private String url;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
            (
                    name = "menu_rol",
                    joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"),
                    inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol")
            )
    private List<Rol> roles;
}
