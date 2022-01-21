/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro_sistema_venta;

/**
 *
 * @author juan de 
 */
import Conexion.InventarioDAO;
import Conexion.Validaciones;
import Conexion.Venta;
import Conexion.conexion;
import Conexion.VentaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sourceforge.barbecue.Barcode;
public class JRVenta extends javax.swing.JFrame {

    /**
     * Creates new form JRVenta
     */
     VentaDAO ventas=new VentaDAO();
     InventarioDAO producto=new InventarioDAO();
     conexion con = new conexion();
     PreparedStatement psql;
     ResultSet rs;
    public JRVenta() {
        super(" "+ "REGISTROS DE VENTAS");
        initComponents();
        personalizaTabla();
        this.setResizable(false);
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        conexion conn= new conexion();
        Connection con= conn.Conectar();
        
        Vector<Venta> ventas=new Vector<Venta>();    
        Venta ven=null;
        try{
            String sql="SELECT * FROM cliente";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            ven = new Venta();
            ven.setIdCliente(0);
            ven.setCosto("Seleccione Cliente");
            ventas.add(ven);
            
            while(rs.next()){
                
            ven = new Venta();
            ven.setIdCliente(rs.getInt("idCliente"));
            ven.setCosto(rs.getString("nombre"));
            ventas.add(ven);
            }
            rs.close();
        }catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        
        Vector< Venta> producto = new Vector<Venta>();
        Venta pro = null;
        try {
            String sql = "SELECT * FROM producto";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            pro = new Venta();
            pro.setIdProducto(0);
            pro.setCosto("Seleccione Producto");
            producto.add(pro);

            while (rs.next()) {
                pro = new Venta();
                pro.setIdProducto(rs.getInt("idProducto"));
                pro.setCosto(rs.getString("nombre"));
                producto.add(pro);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        
        DefaultComboBoxModel modeloCliente= new DefaultComboBoxModel(ventas);
        DefaultComboBoxModel modeloProducto= new DefaultComboBoxModel(producto);
        cmdIdcliente.setModel(modeloCliente);
        cmdIdproducto.setModel(modeloProducto);
        
    }
    public void personalizaTabla(){
       DefaultTableModel modeloTabla=new DefaultTableModel();
       jtbVenta.setModel(modeloTabla);
       modeloTabla.addColumn("Cliente");
       modeloTabla.addColumn("Producto");
       modeloTabla.addColumn("Costo Total");
    }
    public void limpiarCajas(){
       txtCostoTotal.setText(null);
       cmdIdcliente.setToolTipText(null);
       cmdIdproducto.setToolTipText(null);
       txtcantidad.setText(null);
    }
    public void LlenarTabla(JTable tablab){
       DefaultTableModel modeloTabla=new DefaultTableModel();
       tablab.setModel(modeloTabla);
       
       modeloTabla.addColumn("Cliente");
       modeloTabla.addColumn("Producto");
       modeloTabla.addColumn("Costo Total");
       
        TableColumnModel columnModel = jtbVenta.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        
        Object[] columna = new Object[8];
        int numReg = ventas.listVenta().size();
        for (int i = 0; i < numReg; i++) {
           
            columna[0] = ventas.listVenta().get(i).getIdCliente();
            columna[1] = ventas.listVenta().get(i).getIdProducto();
            columna[2] = ventas.listVenta().get(i).getCosto();
            modeloTabla.addRow(columna);
        }
    } 
     public void pasarDatosTabla_CajasTexto(JTable table){
        int fila=table.getSelectedRow();
        cmdIdcliente.setToolTipText(table.getModel().getValueAt(fila, 0).toString());
        cmdIdproducto.setToolTipText(table.getModel().getValueAt(fila, 1).toString());
        txtCostoTotal.setText(table.getModel().getValueAt(fila, 2).toString());
    }
     private int cantidad(){
         Venta idProducto=(Venta)cmdIdproducto.getSelectedItem();
         Connection cn=null; 
         String SentenciaSQL="SELECT existencia from inventario where idAlmacen="+idProducto.getIdProducto();
         int can=0;
         try{
             cn=con.Conectar();
             psql=cn.prepareStatement(SentenciaSQL);
             ResultSet rs=psql.executeQuery();
             while(rs.next()){
                 can=rs.getInt(1); 
             }
             }catch(Exception e){
                 
         }
             System.out.println(can);
             return can;
     }
     public void actualizar(){
         Venta idProducto=(Venta)cmdIdproducto.getSelectedItem();
         int cant = Math.abs(cantidad()-Integer.parseInt(txtcantidad.getText()));
         Connection cn=null;
         String sql="UPDATE inventario set existencia="+cant+" WHERE idAlmacen="+idProducto.getIdProducto();
          
         try{
           cn=con.Conectar();
           psql =cn.prepareStatement(sql);
           cant=psql.executeUpdate();
             System.out.println("inventario editado="+cant);
             psql.close();
          }catch(SQLException e){
              System.err.println(e);
          }
         
       }
     
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmdIdcliente = new javax.swing.JComboBox<>();
        cmdIdproducto = new javax.swing.JComboBox<>();
        txtcantidad = new javax.swing.JTextField();
        txtCostoTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbVenta = new javax.swing.JTable();
        btnCliente = new javax.swing.JButton();
        btnProducto = new javax.swing.JButton();
        btnAlmacen = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        Reporte = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("CLIENTE:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("PRODUCTO:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("CANTIDAD:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("COSTO TOTAL:");

        jLabel1.setFont(new java.awt.Font("Thames", 1, 24)); // NOI18N
        jLabel1.setText("REGISTROS DE VENTAS:");

        cmdIdcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdIdclienteActionPerformed(evt);
            }
        });

        cmdIdproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdIdproductoActionPerformed(evt);
            }
        });

        jtbVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbVenta);

        btnCliente.setText(" Registro de Cliente");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        btnProducto.setText("Registro de Producto");
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });

        btnAlmacen.setText("Registro de Almacen");
        btnAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlmacenActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        Reporte.setText("Reporte");
        Reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReporteActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmdIdcliente, 0, 230, Short.MAX_VALUE)
                                            .addComponent(cmdIdproducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(btnListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Reporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmdIdcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmdIdproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(Reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdIdclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdIdclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdIdclienteActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        JRCliente ventana1=new JRCliente();
        ventana1.LlenarTabla(jtbVenta);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnOK.setEnabled(false);
        //listas desplegables
        btnAlmacen.setEnabled(false);
        btnProducto.setEnabled(false);
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlmacenActionPerformed
      JRInventario ventana3=new JRInventario();
      ventana3.LlenarTabla(jtbVenta);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnOK.setEnabled(false);
        //listas desplegables
        btnCliente.setEnabled(false);
        btnProducto.setEnabled(false);
    }//GEN-LAST:event_btnAlmacenActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaEditar=jtbVenta.getSelectedRow();
        int numC5=jtbVenta.getSelectedRowCount();
        if (filaEditar >= 0 && numC5 == 1) {
            txtCostoTotal.setText(String.valueOf(jtbVenta.getValueAt(filaEditar, 2)));
            String id=String.valueOf(jtbVenta.getValueAt(filaEditar, 0));
            txtCostoTotal.setEditable(true);
            //botones y cajas de texto inabilitadas
            txtcantidad.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);
            pasarDatosTabla_CajasTexto(jtbVenta);
        } else {
            JOptionPane.showMessageDialog(null, "debes seleccionar solo una fila");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        LlenarTabla(jtbVenta);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnOK.setEnabled(true);
        btnCliente.setEnabled(true);
        btnProducto.setEnabled(true);
        btnAlmacen.setEnabled(true);
        txtcantidad.setEnabled(true);
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
           int filaInicio = jtbVenta.getSelectedRow();
        int numFS = jtbVenta.getSelectedRowCount();

        ArrayList<String> listId=new ArrayList();
        String idCliente="";
        String idProducto="";
        if(filaInicio>=0){
            for(int i = 0; i<numFS; i++){
                idCliente=String.valueOf(jtbVenta.getValueAt(i+filaInicio, 0));
                idProducto=String.valueOf(jtbVenta.getValueAt(i+filaInicio, 1));
                listId.add(idCliente);
                listId.add(idProducto);
            }
            System.out.println(idCliente);
            for(int i=0; i<numFS; i++){
                int rptaUsuario= JOptionPane.showConfirmDialog(null, "Quieres eleminar el registro con los id Cliente ="+" "+idCliente
                    +"Id Materia="+"  "+idProducto+"?");
                if(rptaUsuario==0){
                    ventas.eliminarVenta(Integer.parseInt(idCliente), Integer.parseInt(idProducto));
                }
            }
            LlenarTabla(jtbVenta);
        }else{
            JOptionPane.showMessageDialog(null, "Debes seleccionar solo una fila");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if(!Validaciones.esCajaVacia(txtCostoTotal,"No has Insertado un registro ")){
            int  costo=Integer.parseInt(txtCostoTotal.getText());
            Venta idCliente =(Venta)cmdIdcliente.getSelectedItem();
            Venta idProducto=(Venta)cmdIdproducto.getSelectedItem();
               
        ventas.editarVenta(idCliente.getIdCliente(),idProducto.getIdProducto(), costo);
            limpiarCajas();
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
            txtcantidad.setEnabled(true);
            btnListar.doClick();
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void ReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReporteActionPerformed
        
    }//GEN-LAST:event_ReporteActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
         if(!Validaciones.esCajaVacia(txtCostoTotal, "No has insertado el costoi")){
            if(!Validaciones.esNumeroNoValido(txtCostoTotal, 0, 1000, "Costo no acepta")){
                int  costo1=Integer.parseInt(txtCostoTotal.getText());
                Venta idCliente =(Venta)cmdIdcliente.getSelectedItem();
                Venta idProducto=(Venta)cmdIdproducto.getSelectedItem();
                ventas.GuardarVenta(idCliente.getIdCliente(),idProducto.getIdProducto(), costo1);
                actualizar();
                limpiarCajas();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        JRProducto ventana2=new JRProducto();
       ventana2.LlenarTabla(jtbVenta);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnOK.setEnabled(false);
        //listas desplegables
        btnCliente.setEnabled(false);
        btnAlmacen.setEnabled(false);
    }//GEN-LAST:event_btnProductoActionPerformed

    private void cmdIdproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdIdproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdIdproductoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JRVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JRVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JRVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JRVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JRVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Reporte;
    private javax.swing.JButton btnAlmacen;
    private javax.swing.JButton btnCliente;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnListar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnOK;
    private javax.swing.JButton btnProducto;
    private javax.swing.JComboBox<String> cmdIdcliente;
    private javax.swing.JComboBox<String> cmdIdproducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbVenta;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextField txtcantidad;
    // End of variables declaration//GEN-END:variables
}
