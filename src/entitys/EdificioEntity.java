package entitys;

import java.util.List;

import entitys.UnidadEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;



import modelo.Edificio;

@Entity
@Table(name = "edificios")
public class EdificioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="codigo")
	private Integer codigo;
	@Column(name="nombre")
	private String nombre;
	@Column(name="direccion")
	private String direccion;
	
	@OneToMany
	@JoinColumn(name="identificador")
	private List<UnidadEntity> unidades;
	
	public List<UnidadEntity> getUnidades() {
		return this.unidades;
	}
	
	public Edificio toEdificio() {
		 Edificio edificio = new Edificio(this.codigo, this.nombre, this.direccion);
		 return edificio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setUnidades(List<UnidadEntity> unidades) {
		this.unidades = unidades;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
}
