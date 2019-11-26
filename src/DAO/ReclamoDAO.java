package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entitys.ReclamoEntity;
import entitys.UnidadEntity;
import modelo.Edificio;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import utils.ConnectionUtils;
import utils.HibernateUtils;
import views.Estado;

public class ReclamoDAO {
    
    public ReclamoDAO(){
    }

   static ReclamoEntity toEntity(Reclamo recl) {
	   UnidadEntity unitplaceholder;
	   if(recl.getUnidad()==null) {
		   unitplaceholder = null;
	   } else
		   unitplaceholder = UnidadDAO.toEntity(recl.getUnidad());
		   
	   return new ReclamoEntity(recl.getNumero(), 
			   					PersonaDAO.toEntity(recl.getUsuario()),
			   					EdificioDAO.toEntity(recl.getEdificio()),
			   					recl.getUbicacion(),
			   					recl.getDescripcion(),
			   					unitplaceholder,
			   					recl.getEstado().name());
   }
   static Reclamo toNegocio(ReclamoEntity recl) {
	   Unidad unitplaceholder;
	   if(recl.getUnidad()==null) {
		   unitplaceholder = null;
	   } else
		   unitplaceholder = UnidadDAO.toNegocio(recl.getUnidad());
	   return new Reclamo(recl.getNumero(),
			   			PersonaDAO.toNegocio(recl.getUsuario()),
			   			EdificioDAO.toNegocio(recl.getEdificio()),
			   			recl.getUbicacion(),
			   			recl.getDescripcion(),
			   			unitplaceholder,
			   			recl.getEstado());
   }
   public List<Reclamo> getAll(){
	   Session session = HibernateUtils.getSessionFactory().getCurrentSession();
	   Transaction t = session.beginTransaction();
	   t.begin();
	   List<ReclamoEntity> reclamos =  (List<ReclamoEntity>)session.createCriteria(ReclamoEntity.class).list();
	   t.commit();
       return reclamos.stream().map(x -> toNegocio(x))
				.collect(Collectors.toCollection(ArrayList<Reclamo>::new));
   }
   
   public static int save(Reclamo recl) {
	   System.out.println(recl);
	   Transaction transaction = null; 
		try {
			 Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			transaction.begin();
			ReclamoEntity reclent = toEntity(recl);
			//System.out.println("aabb" + reclent.toString());
			session.save(reclent);
			transaction.commit();
			return reclent.getNumero();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("No se pudo guardar al reclamo");
			e.printStackTrace();
			return -1;
		}
		
   }
   
   public static void delete(Reclamo recl) {
		Transaction transaction = null; 
		try {
			 Session session = HibernateUtils.getSessionFactory().getCurrentSession();
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
			 Session session = HibernateUtils.getSessionFactory().getCurrentSession();
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
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		ts = session.beginTransaction();
		ts.begin();
		//List<ReclamoEntity> recledif = (List<ReclamoEntity>)session.createSQLQuery("SELECT * FROM reclamos WHERE codigo = :edif")
		//			.addEntity(ReclamoEntity.class).setParameter("edif", edificio.getCodigo()).list();
		List<ReclamoEntity> recledif = (List<ReclamoEntity>)session.createQuery("from ReclamoEntity where edificio=?").setEntity(0, EdificioDAO.toEntity(edificio)).list();
		results = recledif.stream().map(x -> toNegocio(x))
				.collect(Collectors.toCollection(ArrayList<Reclamo>::new));
		ts.commit();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return results;
}

public static List<Reclamo> getReclamosByUnidad(Unidad unit) {
	Transaction transaction = null;
	List<Reclamo> results = null;
	try {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction ts = session.beginTransaction();
		ts.begin();
		//List<ReclamoEntity> recledif = (List<ReclamoEntity>)session.createSQLQuery("SELECT * FROM reclamos WHERE identificador = :unit")
			//		.addEntity(ReclamoEntity.class).setParameter("unit", unit.getId()).list();
		List<ReclamoEntity> recledif = (List<ReclamoEntity>)session.createQuery("from ReclamoEntity where unidad=?").setEntity(0, UnidadDAO.toEntity(unit)).list();
		//ts.commit();
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
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction ts = session.beginTransaction();
		ts.begin();
		//List<ReclamoEntity> recledif = (List<ReclamoEntity>)session.createSQLQuery("SELECT * FROM reclamos WHERE documento = :dni")
		//			.addEntity(ReclamoEntity.class).setParameter("dni", per.getDocumento()).list();
		List<ReclamoEntity> recledif = (List<ReclamoEntity>)session.createQuery("from ReclamoEntity where usuario=?").setEntity(0, PersonaDAO.toEntity(per)).list();
		results = recledif.stream().map(x -> toNegocio(x))
				.collect(Collectors.toCollection(ArrayList<Reclamo>::new));
		ts.commit();
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
//			 ReclamoEntity reclent = toEntity(reclamo);
//			 System.out.println(reclent.toString());
			 ReclamoEntity reclent2 = (ReclamoEntity) session.load(ReclamoEntity.class, reclamo.getNumero());
			 System.out.println(reclent2.toString());
//			 Query q = session.createQuery("UPDATE ReclamoEntity SET"
//			 		+ " usuario=:usuario,"
//			 		+ " codigo=:codigo,"
//			 		+ " ubicacion=:ubic,"
//			 		+ " descripcion=:desc,"
//			 		+ " identificador=:unit,"
//			 		+ " estado=:estado,"
//			 		+ " imagenes=:img"
//			 		+ " WHERE numero=:numero");
//			 q.setEntity("usuario", reclent.getUsuario());
//			 q.setEntity("codigo",reclent.getEdificio());
//			 q.setString("ubic", reclent.getDescripcion());
//			 q.setString("desc", reclent.getDescripcion());
//			 q.setEntity("unit", reclent.getUnidad());
//			 q.setParameter("estado", "Estado."+reclent.getEstado());
//			 q.setParameter("img", reclent.getImagenes());
//			 q.setInteger("numero", reclent.getNumero());
//			 q.executeUpdate();
			 reclent2.setEstado(reclamo.getEstado().name());
			 session.update(reclent2);
			 
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

