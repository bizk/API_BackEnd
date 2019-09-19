package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entitys.DuenioEntity;
import modelo.Persona;

public class DuenioDAO {
	
	static Persona toNegocio(DuenioEntity duenioEntity) {
		return new Persona(duenioEntity.getDuenio().getDocumento(), duenioEntity.getDuenio().getNombre());
	}
	
	static List<Persona> toNegocio(List<DuenioEntity> dueniosEntity) {
		return dueniosEntity.stream().map(x -> toNegocio(x)).collect(Collectors.toCollection(ArrayList<Persona>::new));
	}
}
