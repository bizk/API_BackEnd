package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entitys.DuenioEntity;
import entitys.InquilinoEntity;
import entitys.PersonaEntity;
import entitys.UnidadEntity;
import modelo.Persona;
import modelo.Unidad;
import utils.ConnectionUtils;
import utils.HibernateUtils;

public class PersonaDAO {
	private List<Persona> personas;
	 
    public PersonaDAO() {
    }
	
	public List<Persona> getAll(){
		Transaction transaction = null; 
		try {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			List<PersonaEntity> results = session.createCriteria(PersonaEntity.class).list();
			return results.stream().map(x -> toNegocio(x))
					.collect(Collectors.toCollection(ArrayList<Persona>::new));
		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
			exception.printStackTrace();
		}
		return null;
	}
	
	public static Persona getPersona(String documento) {
    	Transaction transaction = null; 
		try {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			PersonaEntity personaEntity = (PersonaEntity) session.get(PersonaEntity.class, documento);
			transaction.commit();
			return toNegocio(personaEntity);
		} catch (Exception exception) {
			/*if (transaction != null) {
				transaction.rollback();
			}*/
			System.out.println("No existe ninguna persona con el dni: " + documento);
		}
		return null;
	}
	
	public static void save(Persona persona) {
		Transaction transaction = null; 
		try {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			transaction.begin();
			PersonaEntity personaEntity = toEntity(persona);
			personaEntity.setDocumento(persona.getDocumento());
			personaEntity.setNombre(persona.getNombre());
			session.saveOrUpdate(personaEntity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("No se pudo guardar a la persona");
			e.printStackTrace();
		}
	}
	
	public static void delete(Persona persona) {
		Transaction transaction = null; 
		try {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			transaction.begin();
			PersonaEntity personaEntity = toEntity(persona);
			//List<DuenioEntity> duenioEntities = (List<DuenioEntity>) session.createSQLQuery("SELECT * FROM duenios WHERE documento = :documento")
				//	.addEntity(DuenioEntity.class).setParameter("documento", persona.getDocumento()).list();
			Query qd= session.createQuery("delete from DuenioEntity where duenio = ?").setEntity(0, personaEntity);
			qd.executeUpdate();
			session.clear();
		
			//List<InquilinoEntity> inquilinoEntities= (List<InquilinoEntity>) session.createSQLQuery("SELECT * FROM inquilinos WHERE documento = :documento")
				//	.addEntity(InquilinoEntity.class).setParameter("documento", persona.getDocumento()).list();
			Query qi1 = session.createQuery("from InquilinoEntity where inquilino =?").setEntity(0, personaEntity);
			List<InquilinoEntity> inquilinoEnitities = (List<InquilinoEntity>) qi1.list();
			session.clear();
			/*for (InquilinoEntity ie : inquilinoEnitities) { TODO arreglar la verificación para liberar unidades
				Unidad unidad = InquilinoDAO.toNegocioUnidad(ie);
				List<Persona> inquilinos = unidad.getInquilinos();
				inquilinos.remove(toNegocio(ie.getPersona()));
				if (inquilinos.isEmpty()) unidad.liberar();
			}*/
			Query qi2 = session.createQuery("delete from InquilinoEntity where inquilino =?").setEntity(0, personaEntity);
			qi2.executeUpdate();
				
			
			session.clear();
			session.delete(personaEntity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	static PersonaEntity toEntity(Persona usuario) {
		return new PersonaEntity(usuario.getDocumento(),
									usuario.getNombre());
	}
	
	static List<PersonaEntity> toEntity(List<Persona> personasEntity) {
		return personasEntity.stream().map(x->toEntity(x)).collect(Collectors.toCollection(ArrayList<PersonaEntity>::new));
	}

	static Persona toNegocio(PersonaEntity usuariosEntity) {
		return new Persona(usuariosEntity.getDocumento(),
					usuariosEntity.getNombre());
	}

	static List<Persona> toNegocio(List<PersonaEntity> usuariosEntity) {
		return usuariosEntity.stream().map(x->toNegocio(x)).collect(Collectors.toCollection(ArrayList<Persona>::new));
	}
}
