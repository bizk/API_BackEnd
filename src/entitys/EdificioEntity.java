package entitys;

import java.util.List;

import entitys.UnidadEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
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
	
	@OneToMany(mappedBy="edificio", cascade=CascadeType.ALL)
	private List<UnidadEntity> unidades;
	
	public Edificio toEdificio() {
	//	return new Edificio(this.codigo, this.nombre, this.direccion);
	return null;
	}
}
