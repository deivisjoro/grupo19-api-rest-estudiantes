package co.deivisjoro.api19.recursos;

import co.deivisjoro.api19.bd.Conexion;
import co.deivisjoro.api19.modelo.dao.EstudianteDAO;
import co.deivisjoro.api19.modelo.entidades.Estudiante;
import co.deivisjoro.api19.utilidades.Mensaje;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/estudiantes")
public class EstudianteRecurso {
    
    Conexion conexion = new Conexion();
    EstudianteDAO estudianteDAO = new EstudianteDAO(conexion);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Estudiante> getEstudiantes(){
        ArrayList<Estudiante> estudiantes = estudianteDAO.getEstudiantes();
        return estudiantes;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Estudiante getEstudiante(@PathParam("id") int id){
        Estudiante estudiante = estudianteDAO.getEstudiante(id);
        return estudiante;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Estudiante addEstudiante(Estudiante estudiante){
        return estudianteDAO.addEstudiante(estudiante);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje updateEstudiante(Estudiante estudiante){
        boolean resultado = estudianteDAO.updateEstudiante(estudiante);
        String texto = "ERROR";
        if(resultado){
            texto = "OK";
        }
        Mensaje mensaje = new Mensaje(texto);
        return mensaje;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje deleteEstudiante(@PathParam("id") int id){
        boolean resultado = estudianteDAO.deleteEstudiante(id);
        String texto = "ERROR";
        if(resultado){
            texto = "OK";
        }
        Mensaje mensaje = new Mensaje(texto);
        return mensaje;
    }
}
