package DAO;

import modelo.Reclamo;
import utils.ConnectionUtils;

import java.util.List;

import org.hibernate.Session;

import entitys.ReclamoEntity;

public class ReclamoDAO {
    private List<Reclamo> reclamos;
    private Session session;
    
    public ReclamoDAO(){
    	if (session == null) this.session = ConnectionUtils.getSession();
    }

   static ReclamoEntity toEntity(Reclamo recl) {
	   return new ReclamoEntity(recl.getNumero(), 
			   					PersonaDAO.toEntity(recl.getUsuario()),
			   					EdificioDAO.toEntity(recl.getEdificio()),
			   					recl.getUbicacion(),
			   					recl.getDescripcion(),
			   					UnidadDAO.toEntity(recl.getUnidad()),
			   					recl.getEstado());
   }
   static Reclamo toNegocio(ReclamoEntity recl) {
	   return new Reclamo(recl.getNumero(),
			   			PersonaDAO.toNegocio(recl.getUsuario()),
			   			EdificioDAO.toNegocio(recl.getEdificio()),
			   			recl.getUbicacion(),
			   			recl.getDescripcion(),
			   			UnidadDAO.toNegocio(recl.getUnidad()),
			   			recl.getEstado());
   }
   public List<Reclamo> getAll(){
       return reclamos;
   }

   public Reclamo getReclamo(int numero){
       return reclamos.stream().filter(r -> r.getNumero() == numero).findAny().orElse(null);
   }

//   public void modificarReglamo(){
//
//   }

   public void eliminarReclamo(int numero){
       Reclamo reclamo = reclamos.stream().filter(r -> r.getNumero() == numero).findAny().orElse(null);
       if (reclamo != null) {
           reclamos.remove(reclamo);
       }
   }

}
