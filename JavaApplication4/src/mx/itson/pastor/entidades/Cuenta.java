/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pastor.entidades;

import java.util.List;

/**
 *
 * @author pyatq
 */
public class Cuenta {
    
    private int id;
    private Cliente cliente;
    private String numero;
    private List<Movimiento> movimiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Movimiento> getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(List<Movimiento> movimiento) {
        this.movimiento = movimiento;
    }
   
}
