package entitys;
/*** @author fedejp***/

import javax.persistence.*;

@Entity
@Table(name = "reclamos")
public class ReclamoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idReclamo")
	private Integer numero;
	@OneToOne
	@JoinColumn(name="documento")
	@Column(name="documento")
	private PersonaEntity usuario;
	@OneToOne
	@JoinColumn(name="codigoEdificio")
	@Column(name="codigo")
	private EdificioEntity edificio;
	@Column(name="ubicacion")
	private String ubicacion;
	@Column(name="descripcion")
	private String descripcion;
	@OneToOne
	@JoinColumn(name="identificador")
	@Column(name="identificador")
	private UnidadEntity unidad;
	@Column(name="Estado")
	private String estado;
	
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
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
