import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;

public class Init {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controlador controlador = Controlador.getInstancia();
		try {
			controlador.getUnidadesPorEdificio(1);
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		controlador.agregarPersona("DNI666", "MAD MAX");
		controlador.tryConnection();
		try {
			controlador.eliminarPersona("DNI666");
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controlador.tryConnection();
	}	
}

