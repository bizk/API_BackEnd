package DAO;

import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;
import utils.ConnectionUtils;
import views.EdificioView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.Session;

import entitys.EdificioEntity;
import entitys.PersonaEntity;
import entitys.UnidadEntity;
import org.hibernate.Transaction;


public class EdificioDAO {
    private List<Edificio> edificios;
    private Session session;
    
    public EdificioDAO() {
    	if (session == null) this.session = ConnectionUtils.getSession();
    }
    
    public List<Edificio> getAll(){
		session.beginTransaction();

		List<EdificioEntity> results = session.createCriteria(EdificioEntity.class).list();
		//This function converts the results from entitys into a list
		this.edificios = results.stream().map(x -> toNegocio(x))
				.collect(Collectors.toCollection(ArrayList<Edificio>::new));
        return this.edificios;
    }

    public List<EdificioView> getAllViews(){
		if (edificios == null) getAll();
		//This function converts the results from entitys into a list
		List<EdificioView> edificiosView = edificios.stream().map(x -> x.toView())
				.collect(Collectors.toCollection(ArrayList<EdificioView>::new));
        return edificiosView;
    }
    
    public Edificio getEdificio(int codigo) {
    	Transaction ts = null;
		try {
			ts = session.beginTransaction();
			EdificioEntity edificioEntity = (EdificioEntity) session.get(EdificioEntity.class, codigo);
			List<UnidadEntity> unidadesEntity = edificioEntity.getUnidades();
			Edificio edificio = toNegocio(edificioEntity);
			edificio.setUnidades(
					unidadesEntity.stream().map(x->UnidadDAO.toNegocio(x)).collect(Collectors.toCollection(ArrayList<Unidad>::new))
			);
			session.close();
			return edificio;
		} catch (Exception np) {
			System.out.println("No existe un edificio para dicho codigo");
		}
		session.close();
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

    static Edificio toNegocio(EdificioEntity edificio) {
		return new Edificio(edificio.getCodigo(),
							edificio.getNombre(),
							edificio.getDireccion());
	}
}
