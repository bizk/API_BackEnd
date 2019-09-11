import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;

public class Init {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controlador controlador = Controlador.getInstancia();
		controlador.tryConnection();
	}	
}

