package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;

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
			return unidades;
	    }
	    
	    public Unidad getUnidad(int codigo) {
	    	UnidadEntity unidad  = (UnidadEntity) session.load(UnidadEntity.class, codigo);
	    	return unidad.toUnidad();
	    }
	    
	    private Unidad entity2unidad(UnidadEntity entity) {
	    	Unidad unidad = entity.toUnidad();
	    	
	    }
}
