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

public class ProductoDAO {
    conexion con = new conexion();
    PreparedStatement psql;
    ResultSet rs;
    
    public int GuardarProducto(String nombre,float precio_unitario) {
        int resultado = 0;
        Connection cn = null;

        String SentenciaSQL = "INSERT INTO producto (nombre,punitario)"
                + " VALUES (?,?)";
        
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setString(1,nombre);
            psql.setFloat(2,precio_unitario );
            resultado = psql.executeUpdate();
            if (resultado>0){ //si resultado es >0 se ejecuto la transaccion correctamente
                JOptionPane.showMessageDialog(null, "Se guardo correctamente el registro");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo guardar el registro");
            }
            psql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar almacenar guardar un registro:\n"
                    + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        } finally {
            try {
                if (cn != null) {
                    con.cerrar();
                }
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resultado;
    }
     public ArrayList<Producto> listProducto(){
        ArrayList listaProdcuto= new ArrayList();
        Producto producto;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM producto";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                producto =new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre_Product(rs.getString(2));
                producto.setPrecio_Unitario(rs.getInt(3));              
                listaProdcuto.add(producto);                
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
        }
        return listaProdcuto;        
    }
    public void editarProducto(int id,String nombre,int precio){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "UPDATE producto SET nombre=?,punitario=? WHERE idProducto=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            
            psql.setString(1,nombre);
            psql.setInt(2,precio);
            psql.setInt(3, id);
            
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
        //return resultado;
    }
    public int eliminarProducto(int id){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM producto WHERE idProducto=?";
               
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
