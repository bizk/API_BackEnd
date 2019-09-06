package DAO;

import modelo.Edificio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class EdificioDAO {
    private List<Edificio> edificios;

    public List<Edificio> getAll(){
        return this.edificios;
    }

    public Edificio getEdificio(int codigo){
        Edificio edificio = edificios.stream()
                .filter(e -> e.getCodigo() == codigo)
                .findAny().orElse(null);
        return edificio;
    }

//    public void modificarEdificio(){
//        Edificio edificio = edificios.stream()
//                .filter(e -> e.getCodigo() == codigo)
//                .findAny().orElse(null);
//    }

    public void eliminarEdificio(int codigo){
        Edificio edificio = edificios.stream().filter(e -> e.getCodigo() == codigo)
                .findAny().orElse(null);
        if (edificio != null) {
            edificios.remove(edificio);
        }
    }
}
