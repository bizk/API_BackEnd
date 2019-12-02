package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import entitys.DuenioEntity;
import entitys.EdificioEntity;
import entitys.InquilinoEntity;
import entitys.UnidadEntity;
import modelo.Edificio;
import modelo.Unidad;
import utils.HibernateUtils;


public class UnidadDAO {

	private List<Unidad> unidades;
	public UnidadDAO() {
	}
	public List<Unidad> getAll(){
		Session session=HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		List<UnidadEntity> unidadesEntities = session.createCriteria(UnidadEntity.class).list();
		this.unidades = unidadesEntities.stream().map(x -> toNegocio(x))
				.collect(Collectors.toCollection(ArrayList<Unidad>::new));
		return unidades;
	}
	/* public static List<Unidad> getAllFromEdificio(Edificio edif){
	    	Session session=HibernateUtils.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	List<UnidadEntity> unidadesEntities = (List<UnidadEntity>) session.createQuery("from UnidadEntity where edificio=?").setEntity(0,EdificioDAO.toEntity(edif)).list();
	    	return unidadesEntities.stream().map(x -> toNegocio(x))
					.collect(Collectors.toCollection(ArrayList<Unidad>::new));
	    }*/

	public static Unidad getUnidad(int codigo) {
		Transaction ts = null;
		try {
			Session session=HibernateUtils.getSessionFactory().getCurrentSession();
			ts = session.beginTransaction();
			//UnidadEntity unidadEntity = (UnidadEntity) session.createSQLQuery("SELECT * FROM unidades WHERE identificador = :unidad_id")
			//				.addEntity(UnidadEntity.class).setParameter("unidad_id", codigo).uniqueResult();
			UnidadEntity unidadEntity = (UnidadEntity) session.createQuery("from UnidadEntity where identificador=?").setParameter(0, codigo).uniqueResult();
			Unidad unidad = toNegocio(unidadEntity);
			ts.commit();
			return unidad;
		} catch (Exception np) {
			System.out.println("No existe una unidad para dicho codigo, piso y numero");
			return null;
		}
	}

	public static Unidad getUnidad(int codigoedif, String piso, String numero){
		try {
			EdificioEntity edif = EdificioDAO.toEntity(EdificioDAO.getEdificio(codigoedif));
			Session session=HibernateUtils.getSessionFactory().getCurrentSession();
			Transaction ts = session.beginTransaction();
			//UnidadEntity unidadEntity = (UnidadEntity) session.createUnidadEntity unidadEntity = (UnidadEntity)session.createSQLQuery("SELECT * FROM unidades WHERE codigoEdificio = ? and piso = ? and numero = ?")
			//			.addEntity(UnidadEntity.class).setParameter(0, codigoedif).setString(1, piso).setString(2, numero).uniqueResult();
			UnidadEntity unidadEntity = (UnidadEntity) session.createQuery("from UnidadEntity where edificio=? and piso=? and numero= ?")
					.setEntity(0, edif)
					.setString(1, piso)
					.setString(2, numero)
					.uniqueResult();
			Unidad unidad = toNegocioEdificio(unidadEntity, EdificioDAO.toNegocio(edif));
			return unidad;
		} catch (Exception np) {
			System.out.println("No existe una unidad para dicho codigo, piso y numero");
			return null;
		}
	}
	public static List<Unidad> getUnidadByEdificio(int codigoedif){
		try {
			EdificioEntity edif = EdificioDAO.toEntity(EdificioDAO.getEdificio(codigoedif));
			Session session=HibernateUtils.getSessionFactory().getCurrentSession();
			Transaction ts = session.beginTransaction();
			List<UnidadEntity> unidadEntities = (List<UnidadEntity>) session.createQuery("from UnidadEntity where edificio=?")
					.setEntity(0, edif).list();
			List <Unidad> unidades = unidadEntities.stream().map(x -> toNegocio(x))
					.collect(Collectors.toCollection(ArrayList<Unidad>::new));
			return unidades;
		} catch (Exception np) {
			System.out.println("No existen unidades para el edificio");
			return null;
		}
	}
	public static void eliminarDuenios(Unidad unidad) {
		Session session=HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction transaction = null; 
		try {
			transaction = session.beginTransaction();
			transaction.begin();
			UnidadEntity unidadEntity = toEntity(unidad);
			Query qd= session.createQuery("delete from DuenioEntity where unidad = ?").setEntity(0, unidadEntity);
			qd.executeUpdate();
			session.clear();
			transaction.commit();

		} 		catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Error de eliminar dueños");
			//e.printStackTrace();
		} 
			
	}
	public static void eliminarInquilinos(Unidad unidad) {
		Session session=HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction transaction = null; 
		try {
			transaction = session.beginTransaction();
			transaction.begin();
			UnidadEntity unidadEntity = toEntity(unidad);
			Query qi= session.createQuery("delete from InquilinoEntity where unidad = ?").setEntity(0, unidadEntity);
			qi.executeUpdate();
			session.clear();
			transaction.commit();

		} 		catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Error de eliminar inquilinos");
			//e.printStackTrace();
		} 
		
	}

	public static void save(Unidad unidad) {
		Session session=HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction transaction = null; 
		try {
			transaction = session.beginTransaction();
			transaction.begin();
			UnidadEntity unidadEntity = toEntity(unidad);
			List<DuenioEntity> duenios = unidadEntity.getDuenios();
			List<InquilinoEntity> inqs = unidadEntity.getInquilinos();
			for(DuenioEntity d: duenios)
			{
				//					d.setId(DuenioDAO.getDuenioEntity(d.getPersona(),d.getUnidad()).getId());
				//					session.merge(d);
				try {
				session.saveOrUpdate(d);
				}
				catch (ConstraintViolationException e) {
					
				}

			}
			for(InquilinoEntity i: inqs) {
				//					i.setId(InquilinoDAO.getInquilinoEntity(i.getPersona(),i.getUnidad()).getId());
				//					session.merge(i);
				try {
				session.saveOrUpdate(i);
				}
				catch (ConstraintViolationException e) {
					
				}

			}
			session.saveOrUpdate(unidadEntity);;
			session.clear();
			transaction.commit();

		} 		catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("No se pudo guardar la unidad");
			//e.printStackTrace();
		} 
	}
	//TODO
	static UnidadEntity toEntity(Unidad unidad) {
		UnidadEntity une = new UnidadEntity(unidad.getId(),
				unidad.getPiso(),
				unidad.getNumero(),
				unidad.estaHabitado(),
				EdificioDAO.toEntity(unidad.getEdificio()));
		List<DuenioEntity> dueniose= DuenioDAO.toEntityList(unidad.getDuenios(), une);
		List<InquilinoEntity> inqe= InquilinoDAO.toEntityList(unidad.getInquilinos(), une);
		une.setDuenios(dueniose);
		une.setInquilinos(inqe);
		return une;
	}

	static Unidad toNegocio(UnidadEntity unidadEntity) {
		return new Unidad(unidadEntity.getIdentificador(),
				unidadEntity.getPiso(),
				unidadEntity.getNumero(),
				unidadEntity.isHabitado(),
				EdificioDAO.toNegocio(unidadEntity.getEdificio()), 
				DuenioDAO.toNegocioPersonaList(unidadEntity.getDuenios()),
				InquilinoDAO.toNegocioPersonaList(unidadEntity.getInquilinos()));
	}

	static Unidad toNegocioEdificio(UnidadEntity unidadEntity, Edificio edificio) {
		return new Unidad(unidadEntity.getIdentificador(),
				unidadEntity.getPiso(),
				unidadEntity.getNumero(),
				unidadEntity.isHabitado(),
				edificio, 
				DuenioDAO.toNegocioPersonaList(unidadEntity.getDuenios()),
				InquilinoDAO.toNegocioPersonaList(unidadEntity.getInquilinos()));
	}

}
