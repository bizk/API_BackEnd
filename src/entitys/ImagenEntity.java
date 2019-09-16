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
	private String Dirección;
	@Column(name="tipo")
	private String tipo;
	@ManyToOne
	@JoinColumn(name="idReclamo")
	private ReclamoEntity reclamo;
	
	public ImagenEntity() {
	}
	
	public ImagenEntity(String dirección, String tipo) {
		Dirección = dirección;
		this.tipo = tipo;
	}

	public ImagenEntity(String dirección, String tipo, ReclamoEntity reclamo) {
		this.Dirección = dirección;
		this.tipo = tipo;
		this.reclamo = reclamo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDirección() {
		return Dirección;
	}

	public void setDirección(String dirección) {
		Dirección = dirección;
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
