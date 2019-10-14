package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import entitys.EdificioEntity;
import entitys.PersonaEntity;
import entitys.ReclamoEntity;
import entitys.UnidadEntity;
import modelo.Edificio;
import modelo.Reclamo;
import modelo.Unidad;
import utils.ConnectionUtils;
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
	    public static List<Unidad> getAllFromEdificio(Edificio edif){
	    	Session session=HibernateUtils.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	List<UnidadEntity> unidadesEntities = (List<UnidadEntity>) session.createQuery("from UnidadEntity where edificio=?").setEntity(0,EdificioDAO.toEntity(edif)).list();
	    	return unidadesEntities.stream().map(x -> toNegocio(x))
					.collect(Collectors.toCollection(ArrayList<Unidad>::new));
	    }
	    
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
				Unidad unidad = toNegocio(unidadEntity);
				return unidad;
			} catch (Exception np) {
				System.out.println("No existe una unidad para dicho codigo, piso y numero");
				return null;
			}
		}
		

	    public static void save(Unidad unidad) {
	    	Session session=HibernateUtils.getSessionFactory().getCurrentSession();
			Transaction transaction = null; 
			try {
				transaction = session.beginTransaction();
				transaction.begin();
				UnidadEntity unidadEntity = toEntity(unidad);
				session.saveOrUpdate(unidadEntity);
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				System.out.println("No se pudo guardar la unidad");
				//e.printStackTrace();
			} 
		}
	    //TODO
		static UnidadEntity toEntity(Unidad unidad) {
			return new UnidadEntity(unidad.getId(),
									unidad.getPiso(),
									unidad.getNumero(),
									unidad.estaHabitado(),
									EdificioDAO.toEntity(unidad.getEdificio()), 
									PersonaDAO.toEntity(unidad.getDuenios()),
									PersonaDAO.toEntity(unidad.getInquilinos()));
		}
		
	static Unidad toNegocio(UnidadEntity unidadEntity) {
			return new Unidad(unidadEntity.getIdentificador(),
								unidadEntity.getPiso(),
								unidadEntity.getNumero(),
								unidadEntity.isHabitado(),
								EdificioDAO.toNegocio(unidadEntity.getEdificio()), 
								PersonaDAO.toNegocio(unidadEntity.getDuenios()),
								PersonaDAO.toNegocio(unidadEntity.getInquilinos()));
		}
		
		static Unidad toNegocioEdificio(UnidadEntity unidadEntity, Edificio edificio) {
			return new Unidad(unidadEntity.getIdentificador(),
					unidadEntity.getPiso(),
					unidadEntity.getNumero(),
					unidadEntity.isHabitado(),
					edificio, 
					PersonaDAO.toNegocio(unidadEntity.getDuenios()),
					PersonaDAO.toNegocio(unidadEntity.getInquilinos()));
		}
}
