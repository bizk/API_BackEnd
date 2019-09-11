import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
import views.UnidadView;

public class Init {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controlador controlador = Controlador.getInstancia();
		try {
			List<UnidadView> unidad = controlador.getUnidadesPorEdificio(1);
			for (UnidadView uv : unidad)
				System.out.println(uv);
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}

