package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import DAO.UnidadDAO;
import entitys.EdificioEntity;
import entitys.PersonaEntity;
import entitys.UnidadEntity;
import exceptions.UnidadException;
import views.EdificioView;
import views.UnidadView;

public class Unidad {

	private int id;
	private String piso;
	private String numero;
	private boolean habitado;
	private Edificio edificio;
	private List<Persona> duenios;
	private List<Persona> inquilinos;

	public Unidad(int id, String piso, String numero, Edificio edificio) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = false;
		this.edificio = edificio;
		this.duenios = new ArrayList<Persona>();
		this.inquilinos = new ArrayList<Persona>();
	}

	public Unidad(int id, String piso, String numero, boolean habitado, Edificio edificio, List<Persona> duenios,
			List<Persona> inquilinos) {
		super();
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = habitado;
		this.edificio = edificio;
		this.duenios = duenios;
		this.inquilinos = inquilinos;
	}

	public void transferir(Persona nuevoDuenio) {
		duenios = new ArrayList<Persona>();
		duenios.add(nuevoDuenio);
		this.save();
	}

	public void agregarDuenio(Persona duenio) {
		duenios.add(duenio);
		this.save();
	}

	public void alquilar(Persona inquilino) throws UnidadException {
		if (!this.habitado) {
			this.habitado = true;
			inquilinos = new ArrayList<Persona>();
			inquilinos.add(inquilino);
			this.save();
		} else
			throw new UnidadException("La unidad esta ocupada");
	}

	public void agregarInquilino(Persona inquilino) {
		inquilinos.add(inquilino);
		this.save();
	}

	public boolean estaHabitado() {
		return habitado;
	}

	public void liberar() {
		this.inquilinos = new ArrayList<Persona>();
		this.habitado = false;
		this.save();
	}

	public void habitar() throws UnidadException {
		if (this.habitado)
			throw new UnidadException("La unidad ya esta habitada");
		else {
			this.habitado = true;
			this.save();
		}
	}

	public int getId() {
		return id;
	}

	public String getPiso() {
		return piso;
	}

	public String getNumero() {
		return numero;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public List<Persona> getDuenios() {
		return duenios;
	}

	public List<Persona> getInquilinos() {
		return inquilinos;
	}

	public void setDuenios(List<Persona> duenios) {
		this.duenios.addAll(duenios);
	}

	public void setInquilinos(List<Persona> inquilinos) {
		this.inquilinos.addAll(inquilinos);
	}

	public UnidadEntity toEntity() {
		UnidadEntity unidadEntity = new UnidadEntity();
		unidadEntity.setIdentificador(this.id);
		unidadEntity.setNumero(this.numero);
		unidadEntity.setPiso(this.piso);
		unidadEntity.setHabitado(this.habitado);

		EdificioEntity edificioEntity = new EdificioEntity();
		edificioEntity.setCodigo(this.edificio.getCodigo());
		edificioEntity.setDireccion(this.edificio.getDireccion());
		edificioEntity.setNombre(this.edificio.getNombre());
		unidadEntity.setEdificio(edificioEntity);

		unidadEntity.setDuenios(this.duenios.stream().map(x -> x.toEntity())
				.collect(Collectors.toCollection(ArrayList<PersonaEntity>::new)));
		unidadEntity.setInquilinos(this.inquilinos.stream().map(x -> x.toEntity())
				.collect(Collectors.toCollection(ArrayList<PersonaEntity>::new)));
		return unidadEntity;
	}

	public UnidadView toView() {
		EdificioView auxEdificio = edificio.toView();
		return new UnidadView(id, piso, numero, habitado, auxEdificio);
	}

	public void save() {
		UnidadDAO.save(this);
	}
}
