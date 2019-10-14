package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<PersonaEntity> results = session.createCriteria(PersonaEntity.class).list();
		return results.stream().map(x -> toNegocio(x))
				.collect(Collectors.toCollection(ArrayList<Persona>::new));
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
			if (transaction != null) {
				transaction.rollback();
			}
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
	
	public static void delete(Persona persona) {// TODO Cómo consultar a la tabla inquilinos o duenios? Pq mi mapeo directamente cada unidad guarda a sus duenios. En su defecto, como mapeo sino?
		Transaction transaction = null; 
		try {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			transaction.begin();
			//List<DuenioEntity> duenioEntities = (List<DuenioEntity>) session.createSQLQuery("SELECT * FROM duenios WHERE documento = :documento")
				//	.addEntity(DuenioEntity.class).setParameter("documento", persona.getDocumento()).list();
			List<DuenioEntity> duenioEntities = (List<DuenioEntity>) session.createQuery("from DuenioEntity where duenio = '"+ persona.getDocumento()+"' ").list();
			for (DuenioEntity de : duenioEntities) 
				session.delete(de);
		
			//List<InquilinoEntity> inquilinoEntities= (List<InquilinoEntity>) session.createSQLQuery("SELECT * FROM inquilinos WHERE documento = :documento")
				//	.addEntity(InquilinoEntity.class).setParameter("documento", persona.getDocumento()).list();
			List<InquilinoEntity> inquilinoEntities= (List<InquilinoEntity>) session.createQuery("from InquilinoEntity where inquilino = '"+persona.getDocumento()+"' ").list();
			for (InquilinoEntity ie : inquilinoEntities) {
				Unidad unidad = UnidadDAO.getUnidad(ie.getUnidad());
				List<Persona> inquilinos = unidad.getInquilinos();
				inquilinos.remove(ie);
				if (inquilinos.isEmpty()) unidad.liberar();
				else unidad.setInquilinos(inquilinos);
				session.delete(ie);
			}
				
			PersonaEntity personaEntity = toEntity(persona);
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
