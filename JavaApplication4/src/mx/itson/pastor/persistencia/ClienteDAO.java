/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pastor.persistencia;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.itson.pastor.entidades.Cliente;
/**
 *
 * @author pyatq
 */
public class ClienteDAO {
    
    public static List<Cliente> obtenerTodos(){
        
        List<Cliente> cliente = new ArrayList<>();
        try{
            Connection connection = Conexion.obtener();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nombre, direccion, telefono, gmail FROM cliente");
            
            while(resultSet.next()){
                Cliente c = new Cliente();
                c.setId(resultSet.getInt(1));
                c.setNombre(resultSet.getString(2));
                c.setDireccion(resultSet.getString(3));
                c.setTelefono(resultSet.getString(4));
                c.setGmail(resultSet.getString(5));
                cliente.add(c);
            }
            
        } catch (Exception ex){
            System.err.print("Ocurrio un Error:" + ex.getMessage());
        }
        return cliente;
    }
        public static boolean guardar (String nombre, String direccion, String telefono, String gmail){
          boolean resultado = false;
          
          try{
    Connection connection = Conexion.obtener();
    String consulta = "INSERT INTO cliente(nombre, direccion, telefono, gmail) VALUES (?, ? , ? ,?)";
    PreparedStatement statement = connection.prepareStatement(consulta);
    statement.setString(1, nombre);
    statement.setString(2, direccion);
    statement.setString(3, telefono);
    statement.setString(4, gmail);
    statement.execute();
    
    resultado = statement.getUpdateCount()== 1;
    
    }catch (Exception ex){
    System.err.print("Ocurrio un error:" + ex.getMessage());
    
}
       return resultado;   
        }
 public static boolean validarCorreo(String gmail) {
        
        boolean existente = false;
        
        try{
            Connection conneccion = Conexion.obtener();
            String consulta = "SELECT * FROM cliente WHERE gmail = ?";
            PreparedStatement statement = conneccion.prepareStatement(consulta);
            statement.setString(1, gmail);
            
            ResultSet resultSet = statement.executeQuery();
            existente = resultSet.next();
            
        }catch (Exception ex){
            
            System.err.println("Hubo un error al validar" + ex.getMessage());
        }
            
             return existente;
            }
    
}
