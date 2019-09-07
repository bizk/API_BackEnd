import controlador.Controlador;

public class Init {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controlador controlador = Controlador.getInstancia();

		controlador.tryConnection();
		//System.out.println(controlador.getEdificios());
	}

}
