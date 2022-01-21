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
public class Cliente {
    private int id;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String ntelefono;
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getaPaterno(){
        return aPaterno;
    }
    public void setApaterno(String apaterno){
        this.aPaterno=apaterno;
    }
    public String getaMaterno(){
        return aMaterno;
    }
    public void setAmaterno(String amaterno){
        this.aMaterno=amaterno;
    }
    public String getntelefono(){
        return ntelefono;
    }
    public void setntelefono(String ntelefono){
        this.ntelefono=ntelefono;
    }
}
