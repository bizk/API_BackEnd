package test;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.UnidadException;

/**
 * TestReclamos
 */
public class TestReclamos {

    public static void main(String[] args) {
        Controlador ctrl = Controlador.getInstancia();
        
        
//        try {
//			ctrl.agregarReclamo(1, 1, "N'10'", "N'6'", "DNI29988738", "Techo", "Grietas!");
//		} catch (EdificioException | UnidadException | PersonaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();//		}
         //       System.out.println(ctrl.reclamosPorEdificio(1));
             //   System.out.println(ctrl.reclamosPorNumero(3));
       // System.out.println("a : "+ctrl.reclamosPorPersona("DNI29988738"));
     //   System.out.println(ctrl.reclamosPorNumero(2).getDescripcion());
    }
}