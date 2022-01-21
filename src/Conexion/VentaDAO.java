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

public class VentaDAO {
    conexion con = new conexion();
    PreparedStatement psql;
    ResultSet rs;
    public int GuardarVenta(int idCliente,int idProducto,int costo){
     int resultado=0;
     Connection cn=null;
     
     String SentenciaSQL="INSERT INTO venta(idCliente,idProducto,precioventa)"+
             "VALUES (?,?,?)";
     try{
         cn=con.Conectar();
         psql=cn.prepareStatement(SentenciaSQL);
         psql.setInt(1,idCliente);
         psql.setInt(2,idProducto);
         psql.setInt(3,costo);
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
    public ArrayList<Venta> listVenta(){
        ArrayList listaVenta= new ArrayList();
        Venta venta;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM venta";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                 venta =new Venta();
                 venta.setIdCliente(rs.getInt("idCliente"));
                 venta.setIdProducto(rs.getInt("idProducto"));
                 venta.setCosto(rs.getString("precioventa"));
                 listaVenta.add(venta);
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
        return listaVenta;        
    }
  public void editarVenta( int idCliente,int idProducto, int costo){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "UPDATE venta SET precioventa=? WHERE idCliente=? AND idProducto=?   ";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1,costo);
            psql.setInt(2,idCliente);
            psql.setInt(3,idProducto);
            
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
public int eliminarVenta(int idCliente,int idProducto){
       
     int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM  venta WHERE idCliente=? AND idProducto=?";
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1,idCliente);
            psql.setInt(2,idProducto);
            
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
