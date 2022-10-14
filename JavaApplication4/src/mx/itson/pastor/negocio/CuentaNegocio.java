/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pastor.negocio;


import mx.itson.pastor.persistencia.CuentaDAO;

/**
 *
 * @author pyatq
 */
public class CuentaNegocio {
    
    public static boolean guardar(String numero, int clid){
       
        boolean resultado = false;
            
        try {
        
            resultado = CuentaDAO.guardar(numero, clid);
                
        } catch (Exception e) {
            System.out.println("A ocurrido un error al guardar " + e.getMessage());
     
        } return resultado;
    }
  
}
