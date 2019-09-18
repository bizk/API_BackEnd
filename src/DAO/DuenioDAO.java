package DAO;

import java.util.ArrayList;
import java.util.List;

import entitys.DuenioEntity;
import entitys.InquilinoEntity;
import entitys.UnidadEntity;
import modelo.Persona;
import modelo.Unidad;

class DuenioDAO {

	public static List<DuenioEntity> toEntity(List<Persona> duenios, Unidad unidad) {
		List<DuenioEntity> du = new ArrayList<DuenioEntity>();
		UnidadEntity unent = UnidadDAO.toEntity(unidad);
		for (Persona p:duenios) {
			du.add(new DuenioEntity(unent,PersonaDAO.toEntity(p)));
		}
		return du;
	}

	public static List<Persona> toNegocioPersona(List<DuenioEntity> duenios) {
		List<Persona> per =new ArrayList<Persona>();
		for (DuenioEntity i: duenios) {
			per.add(PersonaDAO.toNegocio(i.getDuenio()));
		}
		return per;
	}
	
	public static List<Unidad> toNegocioUnidad(List<DuenioEntity> duenios){
		List<Unidad> un = new ArrayList<Unidad>();
		for (DuenioEntity i:duenios) {
			un.add(UnidadDAO.toNegocio(i.getUnidad()));
		}
		return un;
	}


}
