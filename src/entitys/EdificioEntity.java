package entitys;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entitys.UnidadEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;



import modelo.Edificio;
import modelo.Unidad;

@Entity
@Table(name = "edificios")
public class EdificioEntity {
	@Id
	private Integer codigo;
	private String nombre;
	private String direccion;
	
	@OneToMany
	@JoinColumn(name="codigoEdificio")
	private List<UnidadEntity> unidades;
	
	public List<UnidadEntity> getUnidades() {
		return this.unidades;
	}
	
	public Edificio toEdificio() {
		 Edificio edificio = new Edificio(this.codigo, this.nombre, this.direccion);
		 return edificio;
	}
}
