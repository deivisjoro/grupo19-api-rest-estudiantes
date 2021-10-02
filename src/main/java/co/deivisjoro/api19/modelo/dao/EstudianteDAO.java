package co.deivisjoro.api19.modelo.dao;

import co.deivisjoro.api19.bd.Conexion;
import co.deivisjoro.api19.modelo.entidades.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EstudianteDAO {
    
    private Connection connection;

    public EstudianteDAO(Conexion conexion) {
        this.connection = conexion.getConexion();
    }
    
    public ArrayList<Estudiante> getEstudiantes(){
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM estudiantes";
            PreparedStatement pst;
            pst = this.connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Estudiante estudiante = new Estudiante();
             
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setNota1(rs.getFloat("nota1"));
                estudiante.setNota2(rs.getFloat("nota2"));
                estudiante.setNota3(rs.getFloat("nota3"));
                
                estudiantes.add(estudiante);
            }
            rs.close();
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return estudiantes;
    }

    public Estudiante getEstudiante(int id){
        Estudiante estudiante = new Estudiante();
        
        try{
            String sql = "SELECT * FROM estudiantes WHERE id=?";
            PreparedStatement pst;
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){                            
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setNota1(rs.getFloat("nota1"));
                estudiante.setNota2(rs.getFloat("nota2"));
                estudiante.setNota3(rs.getFloat("nota3"));
                
            }
            rs.close();
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return estudiante;
    }

    public boolean deleteEstudiante(int id){
        boolean resultado = false;
        
        try{
            String sql = "DELETE FROM estudiantes WHERE id=?";
            PreparedStatement pst;
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1, id);
            int filas = pst.executeUpdate();
            
            if(filas>0){
                resultado = true;
            }
            
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return resultado;
    }
    
    public Estudiante addEstudiante(Estudiante estudiante){
               
        try{
            String sql = "INSERT INTO estudiantes VALUES (?,?,?,?,?)";
            PreparedStatement pst;
            pst = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, 0);
            pst.setString(2, estudiante.getNombre());
            pst.setFloat(3, estudiante.getNota1());
            pst.setFloat(4, estudiante.getNota2());
            pst.setFloat(5, estudiante.getNota3());
            
            int filas = pst.executeUpdate();
            
            if(filas>0){
                ResultSet rs = pst.getGeneratedKeys();
                while(rs.next()){
                    estudiante.setId(rs.getInt(1));
                }                
            }
            
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return estudiante;
    }
    
    public boolean updateEstudiante(Estudiante estudiante){
        boolean resultado = false;       
        try{
            String sql = "UPDATE estudiantes SET nombre = ?, nota1 = ?, nota2 = ?, nota3 = ? WHERE id = ?";
            PreparedStatement pst;
            pst = this.connection.prepareStatement(sql);
            
            pst.setString(1, estudiante.getNombre());
            pst.setFloat(2, estudiante.getNota1());
            pst.setFloat(3, estudiante.getNota2());
            pst.setFloat(4, estudiante.getNota3());
            pst.setInt(5, estudiante.getId());
            int filas = pst.executeUpdate();
            
            if(filas>0){
                resultado = true;                
            }
            
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return resultado;
    }
}
