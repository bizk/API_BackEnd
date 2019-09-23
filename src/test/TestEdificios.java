package test;

import java.util.List;

import controlador.Controlador;
import exceptions.PersonaException;
import exceptions.UnidadException;
import views.PersonaView;

public class TestEdificios {

	public static void main(String[] args) throws Exception {
		Controlador ctrl = Controlador.getInstancia();

		boolean getEdificios = false;
		boolean getUnidadesPorEdificio = false;
		boolean habilitadosPorEdificio = false;
		boolean DueniosPorEdifico = false;

		System.out.println("### Todos los edificios:");
		System.out.println(ctrl.getEdificios() + "\n");
		getEdificios = true;
//		
//		System.out.println("### Obtener unidades por edificio");
//		try {
//			System.out.println(ctrl.getUnidadesPorEdificio(1)+ "\n");
//			getUnidadesPorEdificio = true;
//		} catch (Exception e) {
//			System.out.println("No anda getUnidades por edificio");
//			e.printStackTrace();
//		}

//		System.out.println("### Obtener habilitados por edificio");
//		try {
//			List<PersonaView> resultado = ctrl.habilitadosPorEdificio(1);
//			for(PersonaView ps:resultado) {
//				System.out.println(ps);
//			}
//			habilitadosPorEdificio = true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("No anda habilitados por edificio");
//			e.printStackTrace();
//		}

//		System.out.println("### Obtener duenios por edificio");
//		try {
//			List<PersonaView> resultado = ctrl.dueniosPorEdificio(1);
//			for(PersonaView ps:resultado) {
//				System.out.println(ps);
//			}
//			DueniosPorEdifico = true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("No anda duenios por edificio");
//			e.printStackTrace();
//		}
//			
//		System.out.println("\n #### Resultado ###");
//		System.out.println("- getEdificios: " + getEdificios);
//		System.out.println("- getUNidadesPorEdificios: " + getUnidadesPorEdificio);
//		System.out.println("- habilitadosPorEdificio: " + habilitadosPorEdificio);
//		System.out.println("- dueniosPorEdificio: " + DueniosPorEdifico);

		// System.out.println("- habitantesPorEdificioFunc: " +
		// habitantesPorEdificioFunc(ctrl));

//		System.out.println("- dueniosPorUnidadFunc: " + dueniosPorUnidad(ctrl));
//		try {
//			ctrl.transferirUnidad(2, "10", "5", "DNI30108780");
//		} catch (UnidadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (PersonaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("- dueniosPorUnidadFunc: " + dueniosPorUnidad(ctrl));

		// ctrl.liberarUnidad(28, null, null);
		// ctrl.habitarUnidad(28, null, null);
		// ctrl.habitarUnidad(28, null, null);
		// ctrl.agregarPersona("DNI1", "Mirtha legrand");
		// System.out.println(ctrl.dueniosPorUnidad(1, "1", "1"));
		// ctrl.agregarDuenioUnidad(1, "1", "1", "DNI1");
		 System.out.println(ctrl.dueniosPorUnidad(1, "1", "1"));
		 ctrl.eliminarPersona("DNI1");
		 System.out.println(ctrl.dueniosPorUnidad(1, "1", "1"));
	}

	private static boolean habitantesPorEdificioFunc(Controlador ctrl) {
		boolean bandera = false;
		try {
			ctrl.habitantesPorEdificio(1);
			bandera = true;
		} catch (Exception e) {

		}
		return bandera;
	}

	private static boolean dueniosPorUnidad(Controlador ctrl) {
		boolean bandera = false;
		try {
			System.out.println(ctrl.dueniosPorUnidad(6, "5", "1"));
			;
			bandera = true;
		} catch (Exception e) {

		}
		return bandera;
	}

	private static boolean inquilinosPorUnidad(Controlador ctrl) {
		boolean bandera = false;
		try {
			ctrl.inquilinosPorUnidad(15, "1", "13");
			bandera = true;
		} catch (Exception e) {

		}
		return bandera;
	}
}
