package entitys;

import javax.persistence.*;

import modelo.Reclamo;

@Entity
@Table(name="imagenes")
public class ImagenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="numero")
	private int numero;
	@Column(name="path")
	private String Direccion;
	@Column(name="tipo")
	private String tipo;
	@ManyToOne
	@JoinColumn(name="idReclamo")
	private ReclamoEntity reclamo;
	
	public ImagenEntity() {
	}
	
	public ImagenEntity(String direccion, String tipo, ReclamoEntity reclamo) {
		this.Direccion = direccion;
		this.tipo = tipo;
		this.reclamo = reclamo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion= direccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ReclamoEntity getReclamo() {
		return reclamo;
	}

	public void setReclamo(ReclamoEntity reclamo) {
		this.reclamo = reclamo;
	}
	
	
}
