package entitys;
/*** @author fedejp***/

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import views.Estado;

@Entity
@Table(name = "reclamos")
public class ReclamoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idReclamo")
	private Integer numero;
	@OneToOne
	@JoinColumn(name="documento")
	private PersonaEntity usuario;
	@OneToOne
	@JoinColumn(name="codigo")
	private EdificioEntity edificio;
	@Column(name="ubicacion")
	private String ubicacion;
	@Column(name="descripcion")
	private String descripcion;
	@OneToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidad;
	
	
	@Column (name="estado")
	private String estado;
	@OneToMany
	@JoinColumn(name="numero")
	private List<ImagenEntity> imagenes; 
	 // TODO Verificar que esto funcione
	
	public ReclamoEntity() {	
	}
	public ReclamoEntity(Integer numero, PersonaEntity usuario, EdificioEntity edificio, String ubicacion,
			String descripcion, UnidadEntity unidad, String estado) {
		super();
		this.numero = numero;
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = estado;
		this.imagenes = new ArrayList<ImagenEntity>();
	}

	public String getDescripcion() {
		return descripcion;
	}
	public EdificioEntity getEdificio() {
		return edificio;
	}
	public String getEstado() {
		return estado;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public PersonaEntity getUsuario() {
		return usuario;
	}
	public void setUsuario(PersonaEntity usuario) {
		this.usuario = usuario;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public UnidadEntity getUnidad() {
		return unidad;
	}
	public void setUnidad(UnidadEntity unidad) {
		this.unidad = unidad;
	}
	public void setEdificio(EdificioEntity edificio) {
		this.edificio = edificio;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setEstado(String string) {
		this.estado = string;
	}
	public List<ImagenEntity> getImagenes() {
		return this.imagenes;
	}
	public void setImagenes(List<ImagenEntity> imagenes) {
		this.imagenes= imagenes;
	}
	
}
