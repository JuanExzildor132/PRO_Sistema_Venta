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
public class Producto {
    private int id;
    private String Nombre_Producto;
    private float Precio_Unitario;
    private int existencia;
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getNombre_Producto(){
        return Nombre_Producto;
    }
    public void setNombre_Product(String Nombre_Producto){
        this.Nombre_Producto=Nombre_Producto;
    }
    public float getPrecio_Unitario(){
        return Precio_Unitario;
    }
    public void setPrecio_Unitario(float Precio_Unitario){
        this.Precio_Unitario=Precio_Unitario;
    }
    public int getexistencia(){
        return existencia;
    }
    public void setexistencia(int existencia){
        this.existencia=existencia;
    }
}
