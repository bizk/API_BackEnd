package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entitys.PersonaEntity;
import modelo.Persona;
import utils.ConnectionUtils;

public class PersonaDAO {
	private List<Persona> personas;
	 
    public PersonaDAO() {
    }
	
	public List<Persona> getAll(){
		Session session = ConnectionUtils.getSession();

		List<PersonaEntity> results = session.createCriteria(PersonaEntity.class).list();
		this.personas = results.stream().map(x -> toNegocio(x))
				.collect(Collectors.toCollection(ArrayList<Persona>::new));

		return this.personas;
	}
	
	public Persona getPersona(String documento) {
    	Transaction transaction = null; 
		try {
			Session session = ConnectionUtils.getSession();
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
			Session session = ConnectionUtils.getSession();
			transaction = session.beginTransaction();
			transaction.begin();
			PersonaEntity personaEntity = persona.toEntity();
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
			Session session = ConnectionUtils.getSession();
			transaction = session.beginTransaction();
			transaction.begin();
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
