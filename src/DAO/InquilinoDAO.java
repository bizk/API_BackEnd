package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entitys.DuenioEntity;
import entitys.InquilinoEntity;
import entitys.PersonaEntity;
import entitys.UnidadEntity;
import modelo.Persona;
import modelo.Unidad;
import utils.HibernateUtils;

public class InquilinoDAO {

	public InquilinoDAO() {}
	
	static InquilinoEntity toEntity(PersonaEntity p, UnidadEntity u) {
		return new InquilinoEntity(p,u);
	}
	static Persona toNegocioPersona(InquilinoEntity i) {
		return PersonaDAO.toNegocio(i.getPersona());
	}
	
	static Unidad toNegocioUnidad(InquilinoEntity i) {
		return UnidadDAO.toNegocio(i.getUnidad());
	}
	
	public static void save(Persona p, Unidad u) {
		Transaction transaction = null; 
		try {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			transaction.begin();
			InquilinoEntity i = toEntity(PersonaDAO.toEntity(p),UnidadDAO.toEntity(u));
			
			session.saveOrUpdate(i);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("No se pudo guardar la relación de inquilinos");
			e.printStackTrace();
		}
	}

	public static List<InquilinoEntity> toEntityList(List<Persona> inquilinos, UnidadEntity unidad) {
		List<InquilinoEntity> res = new ArrayList<InquilinoEntity>();
		for(Persona p : inquilinos) {
			res.add(toEntity(PersonaDAO.toEntity(p),unidad));
		}
		return res;
	}

	public static List<Persona> toNegocioPersonaList(List<InquilinoEntity> inquilinos) {
		List<Persona> res = new ArrayList<Persona>();
		for(InquilinoEntity i: inquilinos) {
			res.add(toNegocioPersona(i));
		}
		return res;
	}

	public static InquilinoEntity getInquilinoEntity(PersonaEntity persona, UnidadEntity unidad) {
    	Transaction transaction = null; 
		try {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			InquilinoEntity ie = (InquilinoEntity) session.createQuery("from InquilinoEntity where identificador = ? and documento = ?")
																				.setParameter(0, unidad.getIdentificador())
																				.setParameter(1, persona.getDocumento())
																				.uniqueResult();
			transaction.commit();
			return ie;
		} catch (Exception exception) {
			/*if (transaction != null) {
				transaction.rollback();
			}*/
			System.out.println("No existe ningun Inquilino de la unidad " + unidad.getEdificio().getCodigo() + " " + unidad.getPiso()+" "+ unidad.getNumero()+" con el DNI "+ persona.getDocumento());
		}
		return null;
	
}
}
