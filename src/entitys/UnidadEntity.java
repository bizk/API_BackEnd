package entitys;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entitys.EdificioEntity;
import entitys.DuenioEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;

@Entity
@Table(name="unidades")
public class UnidadEntity { 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@OneToMany
	@JoinColumn(name="identificador")
	private List<InquilinoEntity> inquilinos;


	public UnidadEntity() {
	}
	
	public UnidadEntity(int identificador, String piso, String numero, boolean habitado, EdificioEntity edificio,
			List<PersonaEntity> duenios, List<InquilinoEntity> inquilinos) {
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
	public List<InquilinoEntity> getInquilinosEntity() {
		return this.inquilinos;
	}
	
	public List<Persona> getInquilinos() {
		return null;
		//return this.inquilinos.stream().map(x -> x.getInquilino()).collect(Collectors.toCollection(ArrayList<Persona>::new));
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

	public void setInquilinos(List<InquilinoEntity> inquilinos) {
		this.inquilinos = inquilinos;
	}
}
