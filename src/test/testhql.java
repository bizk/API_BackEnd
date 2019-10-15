package test;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import DAO.PersonaDAO;
import DAO.UnidadDAO;
import controlador.Controlador;
import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;
import views.EdificioView;
import views.PersonaView;
import views.UnidadView;

public class testhql {
	public static void main(String[] args) throws Exception {
		Controlador ctrl = Controlador.getInstancia();
	/*	List<EdificioView> edifis= ctrl.getEdificiosView();
		for(EdificioView e : edifis) {
			System.out.println(e.getNombre());
		}*/
	/*	PersonaDAO p = new PersonaDAO();
		List<Persona> pers = p.getAll();
		List<PersonaView> pview = new ArrayList<PersonaView>();
		for (Persona pe : pers) {
			pview.add(pe.toView());
		}
		for (PersonaView pe : pview) {
			System.out.println(pe.getNombre());
		}*/
		/*Edificio edif = ctrl.buscarEdificio(4);
		System.out.println(edif.getCodigo()+ " " + edif.getNombre() + " " + edif.getDireccion());
		List<Unidad> unit = UnidadDAO.getAllFromEdificio(edif);
		for(Unidad u : unit )
			System.out.println(u.getId());*/
		
		Unidad unit = UnidadDAO.getUnidad(1, "1", "1");
		System.out.println(unit.getId());
		
		/*Unidad unit = UnidadDAO.getUnidad(4);
		System.out.println(unit.getId());*/
	}
}
