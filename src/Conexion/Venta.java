/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

/**
 *
 * @author Juan de Dios Ureiro Alatorre
 */
import Conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Venta {
   private int idCliente;
   private int idProducto;
   private String costo;
   
   public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public int getIdProducto(){
        return idProducto;
    }
    public void setIdProducto(int idProducto){
        this.idProducto=idProducto;
    } 
    public String toString(){
        return costo;
    }
    public String getCosto(){
        return costo;
    }
    public void  setCosto(String costo){
        this.costo=costo;
        } 
}
   