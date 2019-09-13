import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.UnidadException;
import views.PersonaView;
import views.UnidadView;

public class Init {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controlador controlador = Controlador.getInstancia();
		try {
			List<PersonaView> r = controlador.dueniosPorUnidad(120, "", "");
			for (PersonaView ar : r)
				System.out.println(ar);
			List<PersonaView> unidad = controlador.inquilinosPorUnidad(120, "", "");
			for (PersonaView uv : unidad)
				System.out.println(uv);
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}

