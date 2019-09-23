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
    
    public ReclamoDAO(){
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
	   System.out.println(recl);
	   Transaction transaction = null; 
		try {
    		Session session = ConnectionUtils.getSession();
			transaction = session.beginTransaction();
			transaction.begin();
			ReclamoEntity reclent = toEntity(recl);
			System.out.println("aabb" + reclent.toString());
			session.save(reclent);
			transaction.commit();
			session.close();
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
    		Session session = ConnectionUtils.getSession();
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
   	Transaction transaction = null; 
		try {
    		Session session = ConnectionUtils.getSession();
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
		} 
		return null;
   }
   

//   public void modificarReglamo(){
//
//   }

public static List<Reclamo> getReclamosByEdificio(Edificio edificio) {
	Transaction ts = null;
	List<Reclamo> results = null;
	try {
		Session session = ConnectionUtils.getSession();
		ts = session.beginTransaction();
		ts.begin();
		List<ReclamoEntity> recledif = (List<ReclamoEntity>)session.createSQLQuery("SELECT * FROM reclamos WHERE codigo = :edif")
					.addEntity(ReclamoEntity.class).setParameter("edif", edificio.getCodigo()).list();
		results = recledif.stream().map(x -> toNegocio(x))
				.collect(Collectors.toCollection(ArrayList<Reclamo>::new));
		ts.commit();
		session.close();
	} catch (Exception e) {
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
		results = recledif.stream().map(x -> toNegocio(x))
				.collect(Collectors.toCollection(ArrayList<Reclamo>::new));
		ts.commit();
		session.close();
	} catch (Exception e) {
		System.out.println("Problema para acceder a la db");
		e.printStackTrace();
	}
	return results;
	
}

public static void update(Reclamo reclamo) {
		Transaction transaction = null; 
		 try {
	    		Session session = ConnectionUtils.getSession();
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

