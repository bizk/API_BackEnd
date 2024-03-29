package entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="imagenes")
public class ImagenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="numero")
	private int numero;
	@Column(name="path")
	private String Direccion; //ID DE IMGUR
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
