package modelo;

import java.util.ArrayList;
import java.util.List;

import DAO.ReclamoDAO;
import views.Estado;
import views.ImagenView;
import views.ReclamoView;

public class Reclamo {

	private int numero;
	private Persona usuario;
	private Edificio edificio;
	private String ubicacion;
	private String descripcion;
	private Unidad unidad;
	private Estado estado;
	private List<Imagen> imagenes;
	
	public Reclamo(Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad)  { // es para reclamos nuevos
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = Estado.nuevo;
		imagenes = new ArrayList<Imagen>();
	}

	public Reclamo(Integer numero2, Persona usr, Edificio edificio, String ubicacion2, String descripcion2,
			Unidad unidad, String estado2) { //Es para reclamos recuperados del DAO
		this.numero=numero2;
		this.usuario=usr;
		this.edificio=edificio;
		this.ubicacion=ubicacion2;
		this.descripcion=descripcion2;
		this.unidad=unidad;
		this.estado=Estado.valueOf(estado2);
		this.imagenes = new ArrayList<Imagen>();
	}


	public void agregarImagen(String direccion, String tipo) {
		Imagen imagen = new Imagen(direccion, tipo);
		imagenes.add(imagen);
		imagen.save(numero);
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Persona getUsuario() {
		return usuario;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public List<Imagen> getImagenes(){ //Esto hay que verificar que el DAO los tenga
		return this.imagenes;
	}
	
	public void cambiarEstado(Estado estado) {
		this.estado = estado;
	}

	public int save() {
		return ReclamoDAO.save(this);
	}
	
	public void update() {
		ReclamoDAO.update(this);
	}
	
	public ReclamoView toView() {
		if(this.getUnidad() != null)
		return new ReclamoView(this.getNumero(), this.getUsuario().toView(),this.getEdificio().toView(),
								this.getUbicacion(),this.getDescripcion(),this.getUnidad().toView(),this.getEstado()
								);
		else return new ReclamoView(this.getNumero(), this.getUsuario().toView(),this.getEdificio().toView(),
				this.getUbicacion(),this.getDescripcion(),null,this.getEstado()
				);
	}
}
