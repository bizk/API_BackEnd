package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entitys.InquilinoEntity;
import entitys.PersonaEntity;
import entitys.UnidadEntity;
import modelo.Persona;
import modelo.Unidad;



public class InquilinoDAO {
	List<Persona> inquilinos;
	Unidad unit;
	Session session;
	public static List<InquilinoEntity> toEntity(List<Persona> list, Unidad unidad) {
		List<InquilinoEntity> inq = new ArrayList<InquilinoEntity>();
		UnidadEntity unent = UnidadDAO.toEntity(unidad);
		for (Persona p:list) {
			inq.add(new InquilinoEntity(unent,PersonaDAO.toEntity(p)));
		}
		return inq;
	}
	public static List<Persona> toNegocioPersona(List<InquilinoEntity> inquilinos2) {
		List<Persona> per =new ArrayList<Persona>();
		for (InquilinoEntity i: inquilinos2) {
			per.add(PersonaDAO.toNegocio(i.getInquilino()));
		}
		return per;
	}
	
	public static List<Unidad> toNegocioUnidad(List<InquilinoEntity> inquilinos){
		List<Unidad> un = new ArrayList<Unidad>();
		for (InquilinoEntity i:inquilinos) {
			un.add(UnidadDAO.toNegocio(i.getUnidad()));
		}
		return un;
	}
}
