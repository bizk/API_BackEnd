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
		
		System.out.println("### Obtener unidades por edificio");
		try {
			System.out.println(ctrl.getUnidadesPorEdificio(1)+ "\n");
			getUnidadesPorEdificio = true;
		} catch (Exception e) {
			System.out.println("No anda getUnidades por edificio");
			e.printStackTrace();
		}	

		System.out.println("### Obtener duenios por edificio");
		try {
			List<PersonaView> resultado = ctrl.dueniosPorEdificio(1);
			System.out.println(resultado.size());
			//comentado para ahorrar espacio de pantalla
			//			for(PersonaView ps:resultado) {
//				System.out.println(ps);
//			}
			DueniosPorEdifico = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No anda duenios por edificio");
			e.printStackTrace();
		}
			

		// Esto no se muestra dado a que la base de datos tiene marcada a todas las unidades como inhabilitadas
		//		 System.out.println("- habitantesPorEdificioFunc: " +
		//		 habitantesPorEdificioFunc(ctrl));

		System.out.println("- dueniosPorUnidadFunc: " + dueniosPorUnidad(ctrl));
		try {
			ctrl.transferirUnidad(2, "10", "5", "DNI30012288");
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("- dueniosPorUnidadFunc: " + dueniosPorUnidad(ctrl));

		System.out.println("### Inquilinos por unidad ###");
		System.out.println(ctrl.inquilinosPorUnidad(1,"9", "5"));
		
		System.out.println("agrego"); 
		ctrl.agregarPersona("DNI1", "Mirtha legrand");
		 System.out.println(ctrl.dueniosPorUnidad(1, "1", "1"));
		 ctrl.agregarDuenioUnidad(1, "1", "1", "DNI1");
		 System.out.println(ctrl.dueniosPorUnidad(1, "1", "1"));
		System.out.println("elimino"); 
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
			System.out.println(ctrl.dueniosPorUnidad(2, "10", "5"));
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
