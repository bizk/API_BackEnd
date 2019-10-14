package entitys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="unidades")
public class UnidadEntity { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="identificador")
	private int identificador;
	@Column(name="piso")
	private String piso;
	@Column(name="numero")
	private String numero;
	@Column(name="habitado")
	private boolean habitado;
	@ManyToOne
	@JoinColumn(name="codigoEdificio")
	private EdificioEntity edificio;
	
	@ManyToMany
	@JoinTable(name="duenios", joinColumns=@JoinColumn(name="identificador"), inverseJoinColumns=@JoinColumn(name="documento"))  
	private List<PersonaEntity> duenios = new ArrayList<PersonaEntity>();
	
	@ManyToMany
    @JoinTable(name="inquilinos", joinColumns=@JoinColumn(name="identificador"), inverseJoinColumns=@JoinColumn(name="documento"))  
	private List<PersonaEntity> inquilinos= new ArrayList<PersonaEntity>();


	public UnidadEntity() {
	}
	
	public UnidadEntity(int identificador, String piso, String numero, boolean habitado, EdificioEntity edificio,
			List<PersonaEntity> duenios, List<PersonaEntity> inquilinos) {
		super();
		this.identificador = identificador;
		this.piso = piso;
		this.numero = numero;
		this.habitado = habitado;
		this.edificio = edificio;
		this.duenios = duenios;
		this.inquilinos = inquilinos;
	}

	
	public List<PersonaEntity> getDuenios(){
		return this.duenios;
	}
	
	public List<PersonaEntity> getInquilinos() {
		return this.inquilinos;
	}
	
	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isHabitado() {
		return habitado;
	}

	public void setHabitado(boolean habitado) {
		this.habitado = habitado;
	}

	public EdificioEntity getEdificio() {
		return edificio;
	}

	public void setEdificio(EdificioEntity edificio) {
		this.edificio = edificio;
	}

	public void setDuenios(List<PersonaEntity> duenios) {
		this.duenios = duenios;
	}

	public void setInquilinos(List<PersonaEntity> inquilinos) {
		this.inquilinos = inquilinos;
	}
}
