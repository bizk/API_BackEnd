package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entitys.DuenioEntity;
import entitys.EdificioEntity;
import entitys.PersonaEntity;
import entitys.UnidadEntity;
import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;

import utils.ConnectionUtils;
import views.Estado;



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
			session.beginTransaction();
	    	UnidadEntity unidadEntity = (UnidadEntity) session.load(UnidadEntity.class, codigo);
	    	return entity2unidad(unidadEntity);
	    }
	    
	    private Unidad entity2unidad(UnidadEntity entity) {
	    	session = ConnectionUtils.getSession();
	    	Transaction transaction = null; 
			try {
				transaction = session.beginTransaction();
				Unidad unidad = entity.toUnidad();
		    	unidad.setDuenios(entity.getDuenios());
		    	unidad.setInquilinos(entity.getInquilinos());
		    	transaction.commit();

		    	return unidad;
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				System.out.println("Error al buscar la unidad");
				e.printStackTrace();
			} finally {
				session.close();
			}
			return null;
	    }
	        
	    public void save(Unidad unidad) {
	    	session = ConnectionUtils.getSession();
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
		static UnidadEntity toEntity(Unidad unidad) {
			return new UnidadEntity(unidad.getId(),
									unidad.getPiso(),
									unidad.getNumero(),
									unidad.estaHabitado(),
									EdificioDAO.toEntity(unidad.getEdificio()),
									DuenioDAO.toEntity(unidad.getDuenios(), unidad),
									InquilinoDAO.toEntity(unidad.getInquilinos(), unidad)); //TODO Como implementar �sto? 
																							//un duenioEntity solo tiene una persona y una unidad
																							//y no hay duenio en el negocio, porque en el negocio son personas nom�s
		}
		
		static Unidad toNegocio(UnidadEntity unidad) {
			return new Unidad(unidad.getIdentificador(),
								unidad.getPiso(),
								unidad.getNumero(),
								unidad.isHabitado(),
								EdificioDAO.toNegocio(unidad.getEdificio()),
								DuenioDAO.toNegocio(unidad.getDuenios()),
								InquilinoDAO.toNegocio(unidad.getInquilinos())); //TODO evaluar si esto es muy lento. Sino, cambiar a inicio diferido
																				// cabe observaci�n anterior.
		}
}
