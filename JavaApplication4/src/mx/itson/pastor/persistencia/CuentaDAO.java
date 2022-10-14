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
import mx.itson.pastor.entidades.Cuenta;

/**
 *
 * @author pyatq
 */
public class CuentaDAO {
    
        public static List<Cuenta> obtenerCuenta(){
        
        List<Cuenta> cuentas= new ArrayList<>();
        
        try{
            Connection connection = Conexion.obtener();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT cu.id, cu.numero, cl.id, cl.nombre, cl.direccion, cl.telefono, cl.gmail \n" +
                                                          "FROM cuenta cu INNER JOIN cliente cl\n" +
                                                          "ON cu.idCliente = cl.id");
            
            while(resultSet.next()){
                
                Cuenta cuenta = new Cuenta();
                cuenta.setId(resultSet.getInt(1));
                cuenta.setNumero(resultSet.getString(2));
                
                
                Cliente c = new Cliente();
                c.setId(resultSet.getInt(3));
                c.setNombre(resultSet.getString(4));
                c.setDireccion(resultSet.getString(5));
                c.setTelefono(resultSet.getString(6));
                c.setGmail(resultSet.getString(7));
                
                cuenta.setCliente(c);
                cuentas.add(cuenta);
                
               
            }
        } catch (Exception ex){
            System.err.print("Ocurrio un Error:" + ex.getMessage());
        }
        return cuentas;
}
        
         public static boolean guardar(String numero, int clid){
              boolean resultado = false;
    
        try {
            
            Connection connection = Conexion.obtener();
            String consulta = "INSERT INTO cuenta (numero, idCliente) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, numero);
            statement.setInt(2, clid);
            statement.execute();
            
            
            resultado = statement.getUpdateCount() == 1;
            
        } catch (Exception e) {
            System.out.println("Ocurrio un error " + e);
        }
          return resultado;
    
    }
     
     public static Cuenta validar(String gmail){
   
        Cuenta c = new Cuenta();
        
        try {
        
            Connection coneccion = Conexion.obtener();
            String consulta = "SELECT cu.id, cu.numero, cl.id, cl.nombre, cl.direccion, cl.telefono, cl.gmail FROM cuenta cu INNER JOIN cliente cl ON cu.idCliente = cl.id where cl.gmail like ?";
            PreparedStatement statement = coneccion.prepareStatement(consulta);
            statement.setString(1, gmail);
            
            ResultSet resultSet = statement.executeQuery();
            
             while (resultSet.next()) {

                c.setId(resultSet.getInt(1));
                c.setNumero(resultSet.getString(2));
                
                Cliente cl = new Cliente();
                cl.setId(resultSet.getInt(3));
                cl.setNombre(resultSet.getString(4));
                cl.setDireccion(resultSet.getString(5));
                cl.setTelefono(resultSet.getString(6));
                cl.setGmail(resultSet.getString(7));
                c.setCliente(cl);
                
            }
            
        } catch (Exception e) {
            System.out.println("Hubo un error al validar " + e.getMessage());
            
        }return c;
   }
         
}
