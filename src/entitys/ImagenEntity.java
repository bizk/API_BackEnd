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
	String Direcci�n;
	@Column(name="tipo")
	String tipo;
	@ManyToOne
	@JoinColumn(name="idReclamo")
	int idReclamo;
	
	public ImagenEntity() {
	}

	public ImagenEntity(int numero, String direcci�n, String tipo, int idReclamo) {
		super();
		this.numero = numero;
		Direcci�n = direcci�n;
		this.tipo = tipo;
		this.idReclamo = idReclamo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDirecci�n() {
		return Direcci�n;
	}

	public void setDirecci�n(String direcci�n) {
		Direcci�n = direcci�n;
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
