package Conexion;
/**
 *
 * @author Juan de Dios Ureiro Alatorre
 */
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class Validaciones {
    //Metodo para validar cajas solo numeros
    public static void esNumeroEntero(java.awt.event.KeyEvent evt, javax.swing.JTextField jt){
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9')) {            
            evt.consume();               
        }               
    }
    public static void soloRecibeTexto(java.awt.event.KeyEvent evt, javax.swing.JTextField jt){
            char c = evt.getKeyChar();
//        if ((c < 'a') || (c > 'z') && (c < 'A') || (c > 'Z') && (c != (char)KeyEvent.VK_SPACE)) {            
//            evt.consume();               
        if(!Character.isLetter(evt.getKeyChar())&&
                !(evt.getKeyChar()== KeyEvent.VK_SPACE)
                && !(evt.getKeyChar()== KeyEvent.VK_BACK_SPACE)){
            evt.consume();
        }
    }               
       
    
    public static boolean esNumeroNoValido(javax.swing.JTextField jt, int li, int ls, String msg){
        int valor=Integer.parseInt(jt.getText());
        if(valor<li || valor>ls){ 
           JOptionPane.showMessageDialog(null, msg);
           jt.setText(null);
           jt.requestFocus();
           return true;
        } 
        return false;
    }
    
    //Método para validar caja no vacia
    public static boolean esCajaVacia(javax.swing.JTextField cajaTexto, String msj){
        if(cajaTexto.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, msj);
            cajaTexto.requestFocus();
            return true;
        }
        else
            return false;        
    }
    
    //Revisar la longitud de un campo de texto
    public static void revisarLongitud(java.awt.event.KeyEvent evt, javax.swing.JTextField jt,int lim){
        if(jt.getText().length()==lim){ 
            evt.consume(); 
        } 
        
    }
}
