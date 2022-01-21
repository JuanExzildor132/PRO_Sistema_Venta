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
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {
    conexion con = new conexion();
    PreparedStatement psql;
    ResultSet rs;
    
    public int GuardarCliente(String nombre, String apaterno,String amaterno,String ntelefono){
     int resultado=0;
     Connection cn=null;
    
     String SentenciaSQL ="INSERT INTO cliente(nombre,apaterno,amaterno,ntelefono)"
             +"VALUES(?,?,?,?)";
     try{
         cn =con.Conectar();
         psql=cn.prepareStatement(SentenciaSQL);
         psql.setString(1, nombre);
         psql.setString(2, apaterno);
         psql.setString(3, amaterno);
         psql.setString(4, ntelefono);
         
         resultado=psql.executeUpdate();
         if(resultado>0){
               JOptionPane.showMessageDialog(null, "Se guardo correctamente el registro");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo guardar el registro");
            }
         psql.close();
     }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar almacenar guardar un registro:\n"
                    + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        } finally{
         
     }try {
                if (cn != null) {
                    con.cerrar();
                }
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }return resultado;
    }
    public ArrayList<Cliente> listCliente(){
        ArrayList listaCliente=new ArrayList();
        Cliente cliente;
        
        Connection cn = null;
        String SentenciaSQL="SELECT * FROM cliente";
        
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
        ResultSet rs=psql.executeQuery();
        while(rs.next()){
            cliente =new Cliente();
            cliente.setId(rs.getInt(1));
            cliente.setNombre(rs.getString(2));
            cliente.setApaterno(rs.getString(3));
            cliente.setAmaterno(rs.getString(4));
            cliente.setntelefono(rs.getString(5));
            listaCliente.add(cliente);
        }
    }catch(SQLException e){
        System.err.println(e);
    }
        finally {
            try {
                if (cn != null) {
                    con.cerrar();
                }
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }return listaCliente;
}
    public void editarCliente(int id,String nombre,String apaterno,String amaterno,String ntelefono){
     int resultado=0;   
     Connection cn=null;
     String SentenciaSQL="UPDATE cliente SET nombre=?,apaterno=?,amaterno=?,ntelefono=? WHERE idCliente=?";
     
      try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            
            psql.setString(1,nombre);
            psql.setString(2,apaterno);
            psql.setString(3,amaterno);
            psql.setString(4,ntelefono);
            psql.setInt(5, id);
            
            resultado = psql.executeUpdate();
            System.out.println("resultdo edit="+resultado);
            if (resultado>0){ //si resultado es >0 se ejecuto la transaccion correctamente
                JOptionPane.showMessageDialog(null, "El registro se actualizó correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro");
            }
            psql.close();
        } catch (SQLException e){
            System.err.println(e);
        } 
    }
    public int eliminarCliente(int id){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM cliente WHERE idCliente=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1,id);
            
            resultado = psql.executeUpdate();
            if (resultado>0){ //si resultado es >0 se ejecuto la transaccion correctamente
                JOptionPane.showMessageDialog(null, "El registro se eliminó correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
            }
            psql.close();
        } catch (SQLException e){
            System.err.println(e);
        } 
        return resultado;
    }
}

