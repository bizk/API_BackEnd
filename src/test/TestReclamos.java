package test;

import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.UnidadException;
import views.ReclamoView;

/**
 * TestReclamos
 */
public class TestReclamos {

    public static void main(String[] args) {
        Controlador ctrl = Controlador.getInstancia();
        int nroreclamo= 0;
        List<ReclamoView> recls;        
       System.out.println("El siguiente proceso evaluará el funcionamiento de varios métodos relacionados con la creación, recuperación y eliminación de reclamos. La base de datos no sufriría modificaciones.");
       System.out.println("Creación de un reclamo para la unidad 1 del piso 1 del edificio 1");
       try {
		nroreclamo=ctrl.agregarReclamo(1, "1","1", "CI 13230978", "Cocina", "Mal olor");
	} catch (EdificioException e) {
		e.printStackTrace();
	} catch (UnidadException e) {
		e.printStackTrace();
	} catch (PersonaException e) {
		e.printStackTrace();
	}
       System.out.println("Creado el reclamo: "+nroreclamo);
       System.out.println("Recuperación de la descripción del reclamo nro: "+nroreclamo);
       System.out.println(ctrl.reclamosPorNumero(nroreclamo).getDescripcion());
       

       System.out.println("Recuperación de la descripción de los reclamos de la unidad 1 del piso 1 del edificio 1");
       recls=ctrl.reclamosPorUnidad(1, "1", "1");
       for (ReclamoView r: recls){
    	   System.out.println(r.getDescripcion());
       }
       
       
       System.out.println("Recuperación de los reclamos del edificio 1");
       recls = ctrl.reclamosPorEdificio(1);
       for (ReclamoView r: recls) {
    	   System.out.println("Piso: "+r.getUnidad().getPiso() + " Numero: "+r.getUnidad().getNumero());
    	   System.out.println("Descripcion: "+ r.getDescripcion());
       }
     

    }
}