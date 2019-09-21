package DAO;

import java.util.List;

import org.hibernate.Session;

import entitys.ImagenEntity;
import modelo.Imagen;
import modelo.Reclamo;
import utils.ConnectionUtils;

public class ImagenDAO {
	List<Imagen> imagenes;
	Session session;
	
	public ImagenDAO (){
		if (session == null) this.session = ConnectionUtils.getSession();
	}

	static ImagenEntity toEntity(Imagen imagenes, Reclamo reclamo) {
		return new ImagenEntity(imagenes.getDireccion(),
								imagenes.getTipo(),
								ReclamoDAO.toEntity(reclamo));
	}

	static Imagen toNegocio(ImagenEntity imagenes) {
		return new Imagen(imagenes.getDireccion(),
							imagenes.getTipo());  //TODO Ac� el problema est� en relacionar imagen con reclamo
												//Godio dice que quiz� no sea necesario, pero si lo es, podemos agregarlo al negocio.
	}

}
