package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entitys.ImagenEntity;
import entitys.ReclamoEntity;
import modelo.Imagen;
import modelo.Reclamo;
import utils.ConnectionUtils;
import utils.HibernateUtils;

public class ImagenDAO {

	public ImagenDAO (){
	}

	static ImagenEntity toEntity(Imagen imagenes, Reclamo reclamo) {
		return new ImagenEntity(imagenes.getDireccion(),
								imagenes.getTipo(),
								ReclamoDAO.toEntity(reclamo));
	}

	static Imagen toNegocio(ImagenEntity imagenes) {
		return new Imagen(imagenes.getDireccion(),
							imagenes.getTipo()); //El numero no es necesario ya no importa
	}

	public static void save(Imagen imagen, int numeroReclamo) {
		Transaction transaction = null; 
		Reclamo recl = ReclamoDAO.getReclamoByNum(numeroReclamo);
		try {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			transaction.begin();
			ImagenEntity image = toEntity(imagen, recl);
			session.save(image);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("No se pudo guardar la imagen");
			e.printStackTrace();
		}
	}

	public static List<Imagen> getImagenesReclamo(Reclamo recl2) {
		ReclamoEntity reclent = ReclamoDAO.toEntity(recl2);
		Transaction transaction = null; 
		List<Imagen> results = null;
		try {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			transaction.begin();
			List<ImagenEntity> imgrecl = (List<ImagenEntity>) session.createQuery("from ImagenEntity where reclamo=?").setEntity(0, reclent).list();
			results = imgrecl.stream().map(x -> toNegocio(x))
					.collect(Collectors.toCollection(ArrayList<Imagen>::new));
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	

}
