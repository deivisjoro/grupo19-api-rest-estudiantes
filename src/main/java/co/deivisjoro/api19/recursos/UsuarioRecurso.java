package co.deivisjoro.api19.recursos;

import co.deivisjoro.api19.bd.Conexion;
import co.deivisjoro.api19.modelo.dao.UsuarioDAO;
import co.deivisjoro.api19.modelo.entidades.Usuario;
import co.deivisjoro.api19.recursos.filtros.Autorizacion;
import co.deivisjoro.api19.utilidades.Mensaje;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/usuarios")
public class UsuarioRecurso {
    
    Conexion conexion = new Conexion();
    UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario login(Usuario usuario){
        
        Usuario u = usuarioDAO.login(usuario);
        String hash = "";
        if(u.getId()!=0){
            long tiempo = System.currentTimeMillis();
            hash = Jwts.builder()
                       .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
                       .setSubject(u.getUsername())
                       .setIssuedAt(new Date(tiempo))
                       .setExpiration(new Date(tiempo + 120000))
                       .claim("username", u.getUsername())
                       .claim("id", u.getId())
                       .compact();
        }
                
        u.setHash(hash);
        
        return usuarioDAO.login(u);
    }
    
    
}
