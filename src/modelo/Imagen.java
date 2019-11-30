package modelo;

import java.util.List;

import DAO.ImagenDAO;
import DAO.ReclamoDAO;
import views.ImagenView;

public class Imagen {

	private int numero;
	private String direccion;
	private String tipo;
	
	public Imagen(String direccion, String tipo) {
		this.direccion = direccion;
		this.tipo = tipo;
	}
	
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDireccion() { //ID De IMGUR
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void save(int numeroReclamo) {
		ImagenDAO.save(this, numeroReclamo);
	}

	public ImagenView toView() {
		return new ImagenView(this.getNumero(), this.getDireccion(), this.getTipo());
	}

}
