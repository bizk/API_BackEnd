import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
<<<<<<< HEAD
import views.UnidadView;
=======
import exceptions.UnidadException;
>>>>>>> 45a91589ca0ff9a59ff2500e7f420e8f9d1f4a97

public class Init {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controlador controlador = Controlador.getInstancia();
		try {
<<<<<<< HEAD
			List<UnidadView> unidad = controlador.getUnidadesPorEdificio(1);
			for (UnidadView uv : unidad)
				System.out.println(uv);
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
=======
			controlador.transferirUnidad(10, null, null, "DNI11");
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}	
>>>>>>> 45a91589ca0ff9a59ff2500e7f420e8f9d1f4a97
}

