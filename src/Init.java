import controlador.Controlador;
import exceptions.PersonaException;
import exceptions.UnidadException;

public class Init {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controlador controlador = Controlador.getInstancia();
		try {
			controlador.transferirUnidad(10, null, null, "DNI11");
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	


