import controlador.Controlador;

public class Init {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("perro");
		Controlador controlador = Controlador.getInstancia();

		controlador.setupConnection();
	}

}
