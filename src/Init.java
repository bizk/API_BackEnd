import controlador.Controlador;
import exceptions.EdificioException;

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
		controlador.tryConnection();
	}	
}

