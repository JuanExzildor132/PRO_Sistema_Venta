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
public class Inventario {
    private int id;
    private int existencia;
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getexistencia(){
        return existencia;
    }
    public void setexistencia(int existencia){
        this.existencia=existencia;
    }
}
