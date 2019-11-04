package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entitys.DuenioEntity;
import entitys.PersonaEntity;
import entitys.UnidadEntity;
import modelo.Persona;
import modelo.Unidad;
import utils.HibernateUtils;

public class DuenioDAO {
	public DuenioDAO() {}
	
	static DuenioEntity toEntity(PersonaEntity p, UnidadEntity u) {
		return new DuenioEntity(p,
								u);
	}
	static Persona toNegocioPersona(DuenioEntity d) {
		return PersonaDAO.toNegocio(d.getPersona());
	}
	
	static Unidad toNegocioUnidad(DuenioEntity d) {
		return UnidadDAO.toNegocio(d.getUnidad());
	}
	
	//static List<DuenioEntity> getDuenio
	
	public static void save(Persona p, Unidad u) {
		Transaction transaction = null; 
		try {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			transaction.begin();
			
			DuenioEntity d = toEntity(PersonaDAO.toEntity(p),UnidadDAO.toEntity(u));
			session.saveOrUpdate(d);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("No se pudo guardar la relación de propiedad");
			e.printStackTrace();
		}
	}

	public static List<DuenioEntity> toEntityList(List<Persona> duenios, UnidadEntity unidad) {
		List<DuenioEntity> res = new ArrayList<DuenioEntity>();
		for(Persona p : duenios) {
			res.add(toEntity(PersonaDAO.toEntity(p),unidad));
		}
		return res;
	}

	public static List<Persona> toNegocioPersonaList(List<DuenioEntity> duenios) {
		List<Persona> res = new ArrayList<Persona>();
		for(DuenioEntity p : duenios) {
			res.add(toNegocioPersona(p));
		}
		return res;
	}

	public static DuenioEntity getDuenioEntity(PersonaEntity persona, UnidadEntity unidad) {
	    	Transaction transaction = null; 
			try {
				Session session = HibernateUtils.getSessionFactory().getCurrentSession();
//				transaction = session.beginTransaction();
				DuenioEntity de = (DuenioEntity) session.createQuery("from DuenioEntity where identificador = ? and documento = ?")
																					.setParameter(0, unidad)
																					.setParameter(1, persona)
																					.uniqueResult();
				return de;
			} catch (Exception exception) {
				/*if (transaction != null) {
					transaction.rollback();
				}*/
				exception.printStackTrace();
				System.out.println("No existe ningun duenio de la unidad " + unidad.getEdificio().getCodigo() + " " + unidad.getPiso()+" "+ unidad.getNumero()+" con el DNI "+ persona.getDocumento());
			}
			return null;
		
	}
}
