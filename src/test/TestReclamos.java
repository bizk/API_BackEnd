package test;

import controlador.Controlador;

/**
 * TestReclamos
 */
public class TestReclamos {

    public static void main(String[] args) {
        Controlador ctrl = Controlador.getInstancia();
        
        System.out.println(ctrl.reclamosPorNumero(2).getDescripcion());
    }
}