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
import mx.itson.pastor.entidades.Cuenta;
import mx.itson.pastor.entidades.Movimiento;

/**
 *
 * @author pyatq
 */
public class MovimientoDAO {
    
    public static List<Movimiento> obtenerTodos(){
        
    List<Movimiento> movimientos= new ArrayList<>();
        
        try{
            Connection connection = Conexion.obtener();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, concepto, fecha, importe, tipo FROM movimiento");
            
            while(resultSet.next()){
              Movimiento m = new Movimiento();  
              m.setId(resultSet.getInt(1));
              m.setConcepto(resultSet.getString(2));
              m.setFecha(resultSet.getDate(3));
              m.setImporte(resultSet.getDouble(4));
              m.setTipo(resultSet.getInt(5));
              movimientos.add(m);
                
            }
        }catch (Exception ex){
            System.err.print("Ocurrio un Error:" + ex.getMessage());
        }
        return movimientos;
}
      public static List<Movimiento>obtenerPorId(int cuenta){
    
        List<Movimiento> movimientos = new ArrayList<>();
        try {
           
            Connection connection = Conexion.obtener();
            String consulta = "SELECT id, concepto, fecha, importe, tipo, idcuenta FROM pastordb.movimiento where idCuenta = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setInt(1, cuenta);
            
            statement.execute();
          
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                
                 Movimiento m = new Movimiento();
                m.setId(resultSet.getInt(1));
                m.setConcepto(resultSet.getString(2));
                m.setFecha(resultSet.getDate(3));
                m.setImporte(resultSet.getInt(4));
                m.setTipo(resultSet.getInt(5));
                
                Cuenta cu = new Cuenta();
                cu.setId(resultSet.getInt(6));
                
                m.setCuenta(cu);
                
                movimientos.add(m);
                
            }
            
        } catch (Exception e) {
            System.err.println("Ocurrio un error: " + e.getMessage());
        }      
    return movimientos;
    }
        
        
         public static boolean guardar(String numero, int clId){
    boolean resultado = false;
    
        try {
            
            Connection connection = Conexion.obtener();
            String consulta = "";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, numero);
            statement.setInt(2, clId);
            statement.execute();
            
            
            resultado = statement.getUpdateCount() == 1;
            
        } catch (Exception e) {
            System.out.println("Ocurrio un error " + e);
        }
    return resultado;
    
    }
    
}

