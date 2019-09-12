package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;

import entitys.DuenioEntity;
import entitys.EdificioEntity;
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
			session.beginTransaction();
	    	UnidadEntity unidadEntity = (UnidadEntity) session.load(UnidadEntity.class, codigo);
	    	
	    	return entity2unidad(unidadEntity);
	    }
	    
	    private Unidad entity2unidad(UnidadEntity entity) {
	    	Unidad unidad = entity.toUnidad();
	    	unidad.setDuenios(entity.getDuenios());
	    	System.out.println(unidad.getDuenios().size());;
	    	return unidad;
	    	
	    }
}
