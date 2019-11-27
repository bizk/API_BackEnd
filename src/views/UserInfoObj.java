package views;

import java.util.List;

public class UserInfoObj {
	private int idEdif;
	private String nombre;
	private String direccion;
	private List<UnidadView> duenio;
	private List<UnidadView> inquilino;
	
	public UserInfoObj(int idEdif, String nombre, String direccion, List<UnidadView> duenio, List<UnidadView> inquilino) {
		this.idEdif = idEdif;
		this.nombre = nombre;
		this.direccion = direccion;
		this.duenio = duenio;
		this.inquilino = inquilino;
	}
}
