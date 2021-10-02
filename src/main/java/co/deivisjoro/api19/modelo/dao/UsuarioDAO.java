package co.deivisjoro.api19.modelo.dao;

import co.deivisjoro.api19.bd.Conexion;
import co.deivisjoro.api19.modelo.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {
    
    private Connection connection;

    public UsuarioDAO(Conexion conexion) {
        this.connection = conexion.getConexion();
    }
    
    public Usuario login(Usuario usuario){
        
        try{
            String sql = "SELECT * FROM usuarios WHERE username=? AND password=?";
            PreparedStatement pst;
            pst = this.connection.prepareStatement(sql);
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                usuario.setId(rs.getInt("id"));
            }
            rs.close();
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return usuario;
    }

}
