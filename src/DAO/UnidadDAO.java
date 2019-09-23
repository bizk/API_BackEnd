package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entitys.UnidadEntity;
import modelo.Edificio;
import modelo.Unidad;
import utils.ConnectionUtils;


public class UnidadDAO {
	 private Session session;
	 
	 private List<Unidad> unidades;
	    public UnidadDAO() {
	    	if (session == null) this.session = ConnectionUtils.getSession();
	    }
	    public List<Unidad> getAll(){
			session.beginTransaction();

	    	List<UnidadEntity> unidadesEntities = session.createCriteria(UnidadEntity.class).list();
	    	this.unidades = unidadesEntities.stream().map(x -> toNegocio(x))
					.collect(Collectors.toCollection(ArrayList<Unidad>::new));
	    	session.close();
			return unidades;
	    }
	    
	    public Unidad getUnidad(int codigo) {
	    	Transaction ts = null;
	    	try {
	    		Session session = ConnectionUtils.getSession();
	    		ts = session.beginTransaction();
				UnidadEntity unidadEntity = (UnidadEntity) session.createSQLQuery("SELECT * FROM unidades WHERE identificador = :unidad_id")
								.addEntity(UnidadEntity.class).setParameter("unidad_id", codigo).uniqueResult();
	    		//UnidadEntity unidadEntity = (UnidadEntity)session.createSQLQuery("SELECT * FROM unidades WHERE identificador = :unidad_id AND piso = :piso_id AND numero = :numero_id")
				//			.addEntity(EdificioEntity.class).setParameter("edificio_id", codigo).setParameter("piso_id", piso).setParameter("numero_id", numero).uniqueResult();
				Unidad unidad = toNegocio(unidadEntity);
				ts.commit();
				session.close();
				return unidad;
			} catch (Exception np) {
				System.out.println("No existe una unidad para dicho codigo, piso y numero");
				return null;
			}
		}
		
		public static Unidad getUnidad(int codigoedif, String piso, String numero){ //TODO probar
			try {
	    		Session session = ConnectionUtils.getSession();
	    		Transaction ts = session.beginTransaction();
	    		UnidadEntity unidadEntity = (UnidadEntity)session.createSQLQuery("SELECT * FROM unidades WHERE codigoEdificio = :edificio_id AND piso = :piso_id AND numero = :numero_id")
							.addEntity(UnidadEntity.class).setParameter("edificio_id", codigoedif).setParameter("piso_id", piso).setParameter("numero_id", numero).uniqueResult();
				Unidad unidad = toNegocio(unidadEntity);
				session.close();
				return unidad;
			} catch (Exception np) {
				System.out.println("No existe una unidad para dicho codigo, piso y numero");
				return null;
			}
		}
	    
	    public static void save(Unidad unidad) {
	    	Session session = ConnectionUtils.getSession();
			Transaction transaction = null; 
			try {
				transaction = session.beginTransaction();
				transaction.begin();
				UnidadEntity unidadEntity = unidad.toEntity();
				session.saveOrUpdate(unidadEntity);
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				System.out.println("No se pudo guardar la unidad");
				//e.printStackTrace();
			} finally {
				session.close();
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
			System.out.println("are" + unidadEntity.getDuenios().size());
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
