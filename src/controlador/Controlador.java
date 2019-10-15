package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.classic.Session;

import DAO.EdificioDAO;
import DAO.PersonaDAO;
import DAO.ReclamoDAO;
import DAO.UnidadDAO;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import modelo.Edificio;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import utils.ConnectionUtils;
import views.EdificioView;
import views.Estado;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;

public class Controlador {

	private static Controlador instancia;
	private static EdificioDAO edificioDAO;
	private static PersonaDAO personaDAO;
	private static UnidadDAO unidadDAO;
	
	private Controlador() {
		Controlador.edificioDAO = new EdificioDAO();
		Controlador.personaDAO = new  PersonaDAO();
		Controlador.unidadDAO = new UnidadDAO();
	}
	
	//FOR QUICK TEST ONLY 
	public void tryConnection() { 
		//Session session = ConnectionUtils.getSession();
		//session.beginTransaction();
		//System.out.println(unidadDAO.getUnidad(2));
		try {
			ReclamoDAO.delete(this.buscarReclamo(1003));
		} catch (ReclamoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	public List<EdificioView> getEdificiosView(){
		return edificioDAO.getAll().stream().map(x -> x.toView())
				.collect(Collectors.toCollection(ArrayList<EdificioView>::new));
	}
	
	public List<UnidadView> getUnidadesPorEdificio(int codigo) throws EdificioException{
		List<UnidadView> resultado = new ArrayList<UnidadView>();
		Edificio edificio = buscarEdificio(codigo);
		List<Unidad> unidades = edificio.getUnidades();
		for(Unidad unidad : unidades) 
			resultado.add(unidad.toView());
		return resultado;
	}
	
	public List<PersonaView> habilitadosPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habilitados = edificio.habilitados();
		System.out.println(habilitados.size());
		for(Persona persona : habilitados)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> dueniosPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);		
		Set<Persona> duenios = edificio.duenios();
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> habitantesPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habitantes = edificio.habitantes();
		
		System.out.println(habitantes.size());
		
		for(Persona persona : habitantes) {
			resultado.add(persona.toView());
			System.out.println(persona.toView());
		
		}
		return resultado;
	}

	public List<PersonaView> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> duenios = unidad.getDuenios();
		for(Persona persona : duenios) 
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> inquilinos = unidad.getInquilinos();
		for(Persona persona : inquilinos) 
			resultado.add(persona.toView());
		return resultado;
	}
	
	public void transferirUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.transferir(persona);
	}

	public void agregarDuenioUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.agregarDuenio(persona);
	}

	public void alquilarUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.alquilar(persona);
	}

	public void agregarInquilinoUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.agregarInquilino(persona);
	}

	public void liberarUnidad(int codigo, String piso, String numero) throws UnidadException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.liberar();
	}
	
	public void habitarUnidad(int codigo, String piso, String numero) throws UnidadException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.habitar();;
	}

	public void agregarPersona(String documento, String nombre) {
		Persona persona = new Persona(documento, nombre);
		persona.save();
	}
		
	public void eliminarPersona(String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		persona.delete();
	}
	
	
	public List<ReclamoView> reclamosPorEdificio(int codigo){
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		Edificio edificio = edificioDAO.getEdificio(codigo);
		List<Reclamo> reclamos = ReclamoDAO.getReclamosByEdificio(edificio);
		for (Reclamo r: reclamos){
			resultado.add(r.toView());
		}
		return resultado;
	}
	
	public List<ReclamoView> reclamosPorUnidad(int codigo, String piso, String numero) {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		Unidad unit = UnidadDAO.getUnidad(codigo,piso,numero);
		List<Reclamo> reclamos = ReclamoDAO.getReclamosByUnidad(unit);
		for (Reclamo r: reclamos){
			resultado.add(r.toView());
		}
		return resultado;
	}
	
	public ReclamoView reclamosPorNumero(int numero) {
		ReclamoView resultado = ReclamoDAO.getReclamoByNum(numero).toView();
		return resultado;
	}
	
	public List<ReclamoView> reclamosPorPersona(String documento) {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		Persona per = PersonaDAO.getPersona(documento);
		List<Reclamo> reclamos = ReclamoDAO.getReclamosByPersona(per);
		for (Reclamo r: reclamos){
			resultado.add(r.toView());
		}
		return resultado;
	}
 
	public int agregarReclamo(int codigoEdificio, String piso, String numero, String documento, String ubicacion, String descripcion) throws EdificioException, UnidadException, PersonaException {
		Edificio edificio = buscarEdificio(codigoEdificio);
		Unidad unidad = buscarUnidad(codigoEdificio, piso, numero);
		Persona persona = buscarPersona(documento);
		Reclamo reclamo = new Reclamo(persona, edificio, ubicacion, descripcion, unidad);
		/*
		 * System.out.println("Datos del reclamo: "); System.out.println("nro " +
		 * reclamo.getNumero()); System.out.println("Persona "+persona.getNombre());
		 * System.out.println("edificio "+edificio.getNombre());
		 * System.out.println("ubicacion "+reclamo.getUbicacion());
		 * System.out.println("descripcion " + reclamo.getDescripcion());
		 * System.out.println("unidad  "+unidad.getId());
		 */
		reclamo.setNumero(reclamo.save());
		return reclamo.getNumero();
	}
	
	public void agregarImagenAReclamo(int numero, String direccion, String tipo) throws ReclamoException {
		Reclamo reclamo = buscarReclamo(numero);
		reclamo.agregarImagen(direccion, tipo);
	}
	
	public void cambiarEstado(int numero, Estado estado) throws ReclamoException {
		Reclamo reclamo = buscarReclamo(numero);
		reclamo.cambiarEstado(estado);
		reclamo.update();
	}
	
	private Edificio buscarEdificio(int codigo) throws EdificioException {
		return edificioDAO.getEdificio(codigo);
	}

	private Unidad buscarUnidad(int codigo, String piso, String numero) throws UnidadException{
		return UnidadDAO.getUnidad(codigo, piso, numero);
	}	
	
	private Persona buscarPersona(String documento) throws PersonaException {
		return PersonaDAO.getPersona(documento);
	}
	
	private Reclamo buscarReclamo(int numero) throws ReclamoException {
		return ReclamoDAO.getReclamoByNum(numero);
	}
}
