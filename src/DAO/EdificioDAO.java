package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;

import entitys.EdificioEntity;
import modelo.Edificio;
import modelo.Unidad;
import utils.HibernateUtils;


public class EdificioDAO {
    private List<Edificio> edificios;
    
    public EdificioDAO() {
    }
    
    public List<Edificio> getAll(){
		Transaction transaction = null;
		try {
    		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			Transaction ts = session.beginTransaction();
			ts.begin();
			//List<EdificioEntity> results = (List<EdificioEntity>)session.createSQLQuery("SELECT * FROM edificios").addEntity(EdificioEntity.class).list();
			List<EdificioEntity> results = (List<EdificioEntity>)session.createCriteria(EdificioEntity.class).list();
			this.edificios = results.stream().map(x -> toNegocio(x))
					.collect(Collectors.toCollection(ArrayList<Edificio>::new));
	        ts.commit();
		} catch (Exception e) {
			System.out.println("Problema para acceder a la db");
			e.printStackTrace();
		}
		return this.edificios;
    }

    
    public static Edificio getEdificio(int codigo) {
    	Transaction ts = null;
		try {
    		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			ts = session.beginTransaction();
			//EdificioEntity edificioEntity = (EdificioEntity)session.createSQLQuery("SELECT * FROM edificios WHERE codigo = :edificio_id")
						//.addEntity(EdificioEntity.class).setParameter("edificio_id", codigo).uniqueResult();
			EdificioEntity edificioEntity = (EdificioEntity) session.createCriteria(EdificioEntity.class).add(Expression.like("codigo", codigo)).uniqueResult();
			Edificio edificio = toNegocio(edificioEntity);
			ts.commit();
			return edificio;
		} catch (Exception np) {
			np.printStackTrace();
			System.out.println("No existe un edificio para dicho codigo");
		}
		return null;
   }

//    public void modificarEdificio(){
//        Edificio edificio = edificios.stream()
//                .filter(e -> e.getCodigo() == codigo)
//                .findAny().orElse(null);
//    }

    public void eliminarEdificio(int codigo){
        Edificio edificio = edificios.stream().filter(e -> e.getCodigo() == codigo)
                .findAny().orElse(null);
        if (edificio != null) {
            edificios.remove(edificio);
        }
    }

    static EdificioEntity toEntity(Edificio edificio) {
		return new EdificioEntity(edificio.getCodigo(),
									edificio.getNombre(),
									edificio.getDireccion());
	}

    static Edificio toNegocio(EdificioEntity edificioEntity) {
		Edificio edificio = new Edificio(edificioEntity.getCodigo(),
									edificioEntity.getNombre(),
									edificioEntity.getDireccion());
		edificio.setUnidades(edificioEntity.getUnidades().stream().map(x -> UnidadDAO.toNegocioEdificio(x, edificio))
									.collect(Collectors.toCollection(ArrayList<Unidad>::new)));
		return edificio;
    }
}
