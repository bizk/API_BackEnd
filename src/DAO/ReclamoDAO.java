package DAO;

import modelo.Reclamo;

import java.util.List;

public class ReclamoDAO {
    private List<Reclamo> reclamos;

    public List<Reclamo> getAll(){
        return reclamos;
    }

    public Reclamo getReclamo(int numero){
        return reclamos.stream().filter(r -> r.getNumero() == numero).findAny().orElse(null);
    }

//    public void modificarReglamo(){
//
//    }

    public void eliminarReclamo(int numero){
        Reclamo reclamo = reclamos.stream().filter(r -> r.getNumero() == numero).findAny().orElse(null);
        if (reclamo != null) {
            reclamos.remove(reclamo);
        }
    }
}
