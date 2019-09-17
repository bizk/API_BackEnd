package entitys;

import javax.persistence.*;

@Entity
@Table(name="imagenes")
public class ImagenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="numero")
	int numero;
	@Column(name="path")
	String Direccion;
	@Column(name="tipo")
	String tipo;
	@ManyToOne
	@JoinColumn(name="idReclamo")
	int idReclamo;
	
	public ImagenEntity() {
	}

	public ImagenEntity(int numero, String direccion, String tipo, int idReclamo) {
		super();
		this.numero = numero;
		this.Direccion = direccion;
		this.tipo = tipo;
		this.idReclamo = idReclamo;
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

	public int getIdReclamo() {
		return idReclamo;
	}

	public void setIdReclamo(int idReclamo) {
		this.idReclamo = idReclamo;
	}
	
	
}
