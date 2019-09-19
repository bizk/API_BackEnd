package DAO;

import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import utils.ConnectionUtils;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entitys.ReclamoEntity;

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

   public Reclamo getReclamoByNum(int numero) {
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

   public void eliminarReclamo(int numero){
       Reclamo reclamo = reclamos.stream().filter(r -> r.getNumero() == numero).findAny().orElse(null);
       if (reclamo != null) {
           reclamos.remove(reclamo);
       }
   }

}

