
package Inicio;
import Otros.Lim;
import Otros.imgTabla;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Proved extends javax.swing.JFrame {


    Lim lt = new Lim();
    
    private String ruta_txt = "miprov.txt"; 
    
    
    Proveedor  ee;
    Progresproducto to;
    
    int clic_tabla;
    public Proved() {
        initComponents();
        to = new Progresproducto();
        
        try{
            cargar_txt();
            listarRegistro();
        }catch(Exception q){
            mj("No existe");
        }
    }
    public void cargar_txt(){
        File ruta = new File(ruta_txt);
        try{
            
            FileReader fi = new FileReader(ruta);
            BufferedReader bu = new BufferedReader(fi);
            
            
            String linea = null;
            while((linea = bu.readLine())!=null){
                StringTokenizer st = new StringTokenizer(linea, ",");
                ee = new Proveedor();
                ee.setCode(Integer.parseInt(st.nextToken()));
                ee.setNom(st.nextToken());
                ee.setNum(st.nextToken());
                ee.setFecha(st.nextToken());
                to.aggReg(ee);
            }
            bu.close();
        }catch(Exception q){
            mj("Error"+q.getMessage());
            System.out.println(q.getMessage());
        }
    }
    
    public void grabar_txt(){
        FileWriter fw;
        PrintWriter pw;
        try{
            fw = new FileWriter(ruta_txt);
            pw = new PrintWriter(fw);
            
            for(int q = 0; q < to.cantReg(); q++){
                ee = to.obtReg(q);
                pw.println(String.valueOf(ee.getCode()+", "+ee.getNom()+", "+ee.getNum()+", "+ee.getFecha()));
            }
             pw.close();
            
        }catch(Exception q){
            mj("Error"+q.getMessage());
            System.out.println(q.getMessage());
        }
    }
    
    public void ingRegistro(File ruta){
        try{
            if(lorCode() == -666)mj("Ingresar codigo entero");
            else if(lorNom() == null)mj("Ingresar Nombre");
            else if(lorNum() == null) mj("Ingresar Precio");
            else if(lorfecha() == null)mj("Ingresar Descripcion");
            else{
                ee = new Proveedor(lorCode(), lorNom(), lorNum(), lorfecha());
                if(to.busc(ee.getCode())!= -1)mj("Este codigo ya existe");
                else to.aggReg(ee);
                
                grabar_txt();
                listarRegistro();
                lt.Lim(panel); 
            }
        }catch(Exception ex){
            mj(ex.getMessage());
        }
    }
    
    public void modiRegistro(File ruta){
        try{
            if(lorCode() == -666)mj("Ingresar codigo entero");
            else if(lorNom() == null)mj("Ingresar Nombre");
            else if(lorNum() == null) mj("Ingresar Numero");
            else if(lorfecha() == null)mj("Ingresar Fecha");
            else{
                int codigo = to.busc(lorCode());
                ee = new Proveedor(lorCode(), lorNom(), lorNum(), lorfecha());
                
                if(codigo == -1)to.aggReg(ee);
                else to.modiReg(codigo, ee);
                
                grabar_txt();
                listarRegistro();
                lt.Lim(panel);
            }
        }catch(Exception ex){
            mj(ex.getMessage());
        }
    }
    
    public void elimRegistro(){
        try{
            if(lorCode() == -666) mj("Ingrese el codigo completo");
            
            else{
                int codigo = to.busc(lorCode());
                if(codigo == -1) mj("no existe");
                
                else{
                    int s = JOptionPane.showConfirmDialog(null, "Quiere eliminar este producto","Si/No",0);
                    if(s == 0){
                        to.elimReg(codigo);
                        
                        grabar_txt();
                        listarRegistro();
                        lt.Lim(panel);
                    }
                }
                
                
            }
        }catch(Exception q){
            mj(q.getMessage());
        }
    }
    
    public void listarRegistro(){
        DefaultTableModel dt = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        dt.addColumn("Codigo");
        dt.addColumn("Nombre");
        dt.addColumn("numero");
        dt.addColumn("fecha");
        
        tabla2.setDefaultRenderer(Object.class, new imgTabla());
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < to.cantReg(); i++){
            ee = to.obtReg(i);
            fila[0] = ee.getCode();
            fila[1] = ee.getNom();
            fila[2] = ee.getNum();
            fila[3] = ee.getFecha();
            dt.addRow(fila);
        }
        tabla2.setModel(dt);
        tabla2.setRowHeight(60);
    }
    
    public int lorCode(){
        try{
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            return codigo;
        }catch(Exception q){
            return -666;
        }
    }
    
    public String lorNom(){
        try{
            String nombre = txtNombre.getText().trim().replace(" ", "_");
            return nombre;
        }catch(Exception q){
            return null;
        }
    }
    
    public String lorNum(){
        try{
            String numero = txtNumero.getText().trim().replace(" ", "_");
            return numero;
        }catch(Exception q){
            return null;
        }
    }
    
    public String lorfecha(){
        try{
            String fecha = txtFecha.getText().trim().replace(" ", "_");
            return fecha;
        }catch(Exception q){
            return null;
        }
    }

 
    public void mj(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        btnvuelveaproducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 0));

        panel.setBackground(new java.awt.Color(255, 255, 0));

        jButton1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtFecha.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("FECHA");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("NUMERO");

        txtNumero.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtNombre.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("CODIGO");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("NOMBRE:");

        txtRuta.setEditable(false);
        txtRuta.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar (1).png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnvuelveaproducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnvuelveaproducto.setText("Regresar a productos");
        btnvuelveaproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvuelveaproductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnvuelveaproducto)
                .addGap(231, 231, 231))
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(47, 47, 47)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addComponent(txtNumero)
                            .addComponent(txtFecha)
                            .addComponent(txtCodigo)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(jButton1)
                        .addGap(51, 51, 51)
                        .addComponent(jButton2)
                        .addGap(55, 55, 55)
                        .addComponent(jButton3)
                        .addGap(32, 32, 32)
                        .addComponent(jButton5)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnvuelveaproducto)
                .addGap(173, 173, 173))
        );

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "NUMERO", "FECHA"
            }
        ));
        tabla2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(389, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(59, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(417, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(71, Short.MAX_VALUE)))
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

    private void tabla2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla2MouseClicked

        clic_tabla = tabla2.rowAtPoint(evt.getPoint());

        int codigo = (int)tabla2.getValueAt(clic_tabla, 0);
        String nombre = ""+tabla2.getValueAt(clic_tabla, 1);
        String numero = ("")+tabla2.getValueAt(clic_tabla, 2);
        String fecha = ""+tabla2.getValueAt(clic_tabla, 3);

        txtCodigo.setText(String.valueOf(codigo));
        txtNombre.setText(nombre);
        txtNumero.setText((numero));
        txtFecha.setText(String.valueOf(fecha));

        try{
            JLabel lbl = (JLabel)tabla2.getValueAt(clic_tabla, 4);
            
        }catch(Exception ex){
        }
    }//GEN-LAST:event_tabla2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File ruta = new File(txtRuta.getText());
        this.ingRegistro(ruta);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        File ruta = new File(txtRuta.getText());
        this.modiRegistro(ruta);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.elimRegistro();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Lim lp = new Lim();
        lp.Lim(panel);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnvuelveaproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvuelveaproductoActionPerformed
        interfaz vi = new interfaz();
        vi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnvuelveaproductoActionPerformed

  
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
            java.util.logging.Logger.getLogger(Proved.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proved.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proved.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proved.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proved().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnvuelveaproducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tabla2;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
