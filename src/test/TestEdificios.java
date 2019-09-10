package test;

import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import views.PersonaView;

public class TestEdificios {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controlador ctrl = Controlador.getInstancia();
		
		System.out.println("### Todos los edificios:");
		System.out.println(ctrl.getEdificios() + "\n");
		
		System.out.println("### Obtener unidades por edificio");
		try {
			System.out.println(ctrl.getUnidadesPorEdificio(9)+ "\n");
		} catch (EdificioException e) {
			e.printStackTrace();
		}
		
		try {
			List<PersonaView> resultado = ctrl.habilitadosPorEdificio(1);
			System.out.println(resultado.size());
			for(PersonaView ps:resultado) {
				System.out.println(ps.toString());
			}
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
