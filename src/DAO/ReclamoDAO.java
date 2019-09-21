package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entitys.ReclamoEntity;
import modelo.Edificio;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import utils.ConnectionUtils;

public class ReclamoDAO {
    private List<Reclamo> reclamos;
    private static Session session;
    
    public ReclamoDAO(){
    	if (session == null) ReclamoDAO.session = ConnectionUtils.getSession();
    }

   static ReclamoEntity toEntity(Reclamo recl) {
	   return new ReclamoEntity(recl.getNumero(), 
			   					PersonaDAO.toEntity(recl.getUsuario()),
			   					EdificioDAO.toEntity(recl.getEdificio()),
			   					recl.getUbicacion(),
			   					recl.getDescripcion(),
			   					UnidadDAO.toEntity(recl.getUnidad()),
			   					recl.getEstado());
   }
   static Reclamo toNegocio(ReclamoEntity recl) {
	   return new Reclamo(recl.getNumero(),
			   			PersonaDAO.toNegocio(recl.getUsuario()),
			   			EdificioDAO.toNegocio(recl.getEdificio()),
			   			recl.getUbicacion(),
			   			recl.getDescripcion(),
			   			UnidadDAO.toNegocio(recl.getUnidad()),
			   			recl.getEstado());
   }
   public List<Reclamo> getAll(){
       return reclamos;
   }
   
   public static void save(Reclamo recl) {
	   Transaction transaction = null; 
		try {
			transaction = session.beginTransaction();
			transaction.begin();
			ReclamoEntity reclent = toEntity(recl);
			session.save(reclent);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("No se pudo guardar al reclamo");
			e.printStackTrace();
		}
   }
   
   public static void delete(Reclamo recl) {
		Transaction transaction = null; 
		try {
			transaction = session.beginTransaction();
			transaction.begin();
			ReclamoEntity reclent = toEntity(recl);
			session.delete(reclent);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

   public static Reclamo getReclamoByNum(int numero) {
	   session = ConnectionUtils.getSession();
   	Transaction transaction = null; 
		try {
			transaction = session.beginTransaction();
			ReclamoEntity reclamoent = (ReclamoEntity) session.load(ReclamoEntity.class, numero);
			Reclamo recl = toNegocio(reclamoent);
	    	transaction.commit();

	    	return recl;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Error al buscar el reclamo");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
   }
   

//   public void modificarReglamo(){
//
//   }

public static List<Reclamo> getReclamosByEdificio(Edificio edificio) {
	Transaction transaction = null;
	List<Reclamo> results = null;
	try {
		Session session = ConnectionUtils.getSession();
		Transaction ts = session.beginTransaction();
		ts.begin();
		List<ReclamoEntity> recledif = (List<ReclamoEntity>)session.createSQLQuery("SELECT * FROM reclamos WHERE codigo = :edif")
					.addEntity(ReclamoEntity.class).setParameter("edif", edificio.getCodigo()).list();
		ts.commit();
		session.close();
		results = recledif.stream().map(x -> toNegocio(x))
					.collect(Collectors.toCollection(ArrayList<Reclamo>::new));
	} catch (Exception e) {
		System.out.println("Problema para acceder a la db");
		session.close();
		e.printStackTrace();
	}
	return results;
}

public static List<Reclamo> getReclamosByUnidad(Unidad unit) {
	Transaction transaction = null;
	List<Reclamo> results = null;
	try {
		Session session = ConnectionUtils.getSession();
		Transaction ts = session.beginTransaction();
		ts.begin();
		List<ReclamoEntity> recledif = (List<ReclamoEntity>)session.createSQLQuery("SELECT * FROM reclamos WHERE identificador = :unit")
					.addEntity(ReclamoEntity.class).setParameter("edif", unit.getId()).list();
		ts.commit();
		session.close();
		results = recledif.stream().map(x -> toNegocio(x))
					.collect(Collectors.toCollection(ArrayList<Reclamo>::new));
	} catch (Exception e) {
		System.out.println("Problema para acceder a la db");
		session.close();
		e.printStackTrace();
	}
	return results;
}

public static List<Reclamo> getReclamosByPersona(Persona per) {
	Transaction transaction = null;
	List<Reclamo> results = null;
	try {
		Session session = ConnectionUtils.getSession();
		Transaction ts = session.beginTransaction();
		ts.begin();
		List<ReclamoEntity> recledif = (List<ReclamoEntity>)session.createSQLQuery("SELECT * FROM reclamos WHERE documento = :dni")
					.addEntity(ReclamoEntity.class).setParameter("dni", per.getDocumento()).list();
		ts.commit();
		session.close();
		results = recledif.stream().map(x -> toNegocio(x))
					.collect(Collectors.toCollection(ArrayList<Reclamo>::new));
	} catch (Exception e) {
		System.out.println("Problema para acceder a la db");
		session.close();
		e.printStackTrace();
	}
	return results;
	
}

public static void update(Reclamo reclamo) {
		Transaction transaction = null; 
		 try {
			 transaction = session.beginTransaction();
			 transaction.begin();
			 ReclamoEntity reclent = toEntity(reclamo);
			 session.saveOrUpdate(reclent);
			 transaction.commit();
		 } catch (Exception e) {
			 if (transaction != null) {
				 transaction.rollback();
			 }
			 System.out.println("No se pudo actualizar al reclamo");
			 e.printStackTrace();
		 }
	}

}

