package entitys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@JoinColumn(name="codigoEdificio")
	private List<UnidadEntity> unidades = new ArrayList<UnidadEntity>();
	
	public EdificioEntity() {
	}

	public EdificioEntity(Integer codigo, String nombre, String direccion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.unidades = new ArrayList<UnidadEntity>();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public List<UnidadEntity> getUnidades() {
			return this.unidades;
	}

	public void setUnidades(List<UnidadEntity> unidades) {
		this.unidades = unidades;
	}
}
