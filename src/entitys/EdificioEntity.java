package entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Entity;
import javax.persistence.Table;

import modelo.Unidad;

@Entity
@Table(name = "edificios")
public class EdificioEntity {
	@Id
	private Integer codigo;
	private String nombre;
	private String direccion;
	
	//@OneToMany
	private List<Unidad> unidades;
	public String getNombre() {
		return this.nombre;
	}
}
