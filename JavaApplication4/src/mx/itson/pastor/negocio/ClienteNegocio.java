/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pastor.negocio;

import javax.swing.JOptionPane;
import mx.itson.pastor.entidades.Cliente;
import mx.itson.pastor.persistencia.ClienteDAO;
/**
 *
 * @author pyatq
 */
public class ClienteNegocio {
    
    public static boolean guardar(String nombre, String direccion, String telefono, String gmail ){
        boolean resultado = false;
        
        try{
            
            if(!ClienteDAO.validarCorreo(gmail)){
                
            resultado = ClienteDAO.guardar(nombre, direccion, telefono, gmail);
            
            } 
        }catch (Exception ex){
            System.err.println("Ocurrid un error " + ex.getMessage());
            
        }
        return resultado; 
    }
     
    
}
