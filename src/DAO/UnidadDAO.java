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
	    	this.unidades = unidadesEntities.stream().map(x -> x.toUnidad())
					.collect(Collectors.toCollection(ArrayList<Unidad>::new));
	    	session.close();
			return unidades;
	    }
	    
	    public Unidad getUnidad(int codigo) {
	    	session = ConnectionUtils.getSession();
	    	Transaction transaction = null; 
			try {
				transaction = session.beginTransaction();
				UnidadEntity unidadEntity = (UnidadEntity) session.load(UnidadEntity.class, codigo);
				Unidad unidad = unidadEntity.toUnidad();
		    	unidad.setDuenios(unidadEntity.getDuenios());
		    	unidad.setInquilinos(unidadEntity.getInquilinos());
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
}
