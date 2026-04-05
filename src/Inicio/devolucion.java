
package Inicio;
import Otros.Lim;
import Otros.imgTabla;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class devolucion extends javax.swing.JFrame {

   
    Lim lt = new Lim();
    
    private String ruta_txt = "mi.txt"; 
    
    
    Del o;
    ProgDel rr;
    
    int clic_tabla;
    public devolucion() {
        initComponents();
        rr = new ProgDel();
        
        try{
            cargar_txt();
            listarRegistro();
        }catch(Exception ex){
            mensaje("No existe");
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
                o = new Del();
                o.setCode(Integer.parseInt(st.nextToken()));
                o.setProdud(st.nextToken());
                o.setCantd(Integer.parseInt(st.nextToken()));
                o.setPrec(Double.parseDouble(st.nextToken()));
                o.setin(st.nextToken());
                rr.agR(o);
            }
            bu.close();
        }catch(Exception q){
            
        }
    }
    
    public void grabar_txt(){
        FileWriter ft;
        PrintWriter po;
        try{
            ft = new FileWriter(ruta_txt);
            po = new PrintWriter(ft);
            
            for(int p = 0; p < rr.cantidadRegistro(); p++){
                o = rr.obtR(p);
                po.println(String.valueOf(o.getCode()+","+o.getProdud()+","+o.getCantd()+","+o.getPrec()+","+o.getin()));
            }
             po.close();
            
        }catch(Exception q){
            mensaje("Error"+q.getMessage());
            System.out.println(q.getMessage());
        }
    }
    
    public void ingRe(File ruta){
        try{
            if(lorCode() == -666)mensaje("Ingresar codigo entero");
            else if(lorProduc() == null)mensaje("Ingresar producto");
            else if(lorCantid() == -666)mensaje("Ingresar cantidad");
            else if(lorPrec() == -666) mensaje("Ingresar Precio");
            else if(lorDesc() == null)mensaje("Ingresar Descripcion");
            else{
                o = new Del(lorCode(),lorProduc(), lorCantid(), lorPrec(), lorDesc());
                if(rr.buscaCodigo(o.getCode())!= -1)mensaje("Ya no tenemos mas este producto");
                else rr.agR(o);
                
                grabar_txt();
                listarRegistro();
                lt.Lim(panel); 
            }
        }catch(Exception q){
            mensaje(q.getMessage());
        }
    }
    
    public void modR(File ruta){
        try{
            if(lorCode() == -666)mensaje("Ingresar codigo entero");
            else if(lorProduc() == null)mensaje("Ingresar Nombre");
            else if(lorCantid() == -666)mensaje("Ingresar codigo entero");
            else if(lorPrec() == -666) mensaje("Ingresar Precio");
            else if(lorDesc() == null)mensaje("Ingresar Descripcion");
            else{
                int code = rr.buscaCodigo(lorCode());
                o = new Del(lorCode(),lorProduc(), lorCantid(), lorPrec(), lorDesc());
                
                if(code == -1)rr.agR(o);
                else rr.modR(code, o);
                
                grabar_txt();
                listarRegistro();
                lt.Lim(panel);
            }
        }catch(Exception q){
            mensaje(q.getMessage());
        }
    }
    
    public void eliR(){
        try{
            if(lorCode() == -666) mensaje("Ingrese el codigo completo");
            
            else{
                int code = rr.buscaCodigo(lorCode());
                if(code == -1) mensaje("no existe");
                
                else{
                    int s = JOptionPane.showConfirmDialog(null, "Quiere eliminar este producto","Si/No",0);
                    if(s == 0){
                        rr.eliR(code);
                        
                        grabar_txt();
                        listarRegistro();
                        lt.Lim(panel);
                    }
                }
                
                
            }
        }catch(Exception q){
            mensaje(q.getMessage());
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
        dt.addColumn("Cantidad");
        dt.addColumn("Precio");
        dt.addColumn("Descripcion");
        
        tabla.setDefaultRenderer(Object.class, new imgTabla());
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < rr.cantidadRegistro(); i++){
            o = rr.obtR(i);
            fila[0] = o.getCode();
            fila[1] = o.getProdud();
            fila[2] = o.getCantd();
            fila[3] = o.getPrec();
            fila[4] = o.getin();
            dt.addRow(fila);
        }
        tabla.setModel(dt);
        tabla.setRowHeight(60);
    }
    public int lorCode(){
        try{
            int Codigo = Integer.parseInt(txtCodigo.getText().trim());
                    
            return Codigo;
        }catch(Exception q){
            return -666;
        }
    }
    public String lorProduc(){
        try{
            String Producto = txtProducto.getText().trim().replace(" ", "_");
                    
            return Producto;
        }catch(Exception q){
            return null;
        }
    }
    
    public int lorCantid(){
        try{
            int Cantidad = Integer.parseInt(txtCantidad.getText().trim());
            
            return 10+Cantidad;
        }catch(Exception q){
            return -666;
        }
    }
    
    public double lorPrec(){
        try{
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            return precio;
        }catch(Exception q){
            return -666;
        }
    }
    
    public Object lorDesc(){
        try{
            Object descripcion = txtDescripcion.getText().trim();
            return descripcion;
        }catch(Exception q){
            return null;
        }
    }
    public void mensaje(String texto){
        JOptionPane.showMessageDialog(null, texto);
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
        panel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        btnverprov = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        panel.setBackground(new java.awt.Color(255, 153, 0));

        jButton2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton2.setText("GUARDAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton3.setText("MODIFICAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton4.setText("VENDER");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtDescripcion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("DESCRIPCION");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("PRECIO");

        txtPrecio.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtCantidad.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtProducto.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("PRODUCTO");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("CANTIDAD");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar (1).png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnverprov.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnverprov.setText("PROVEEDORES");
        btnverprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverprovActionPerformed(evt);
            }
        });

        jLabel5.setText("CODIGO");

        txtRuta.setEditable(false);
        txtRuta.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jButton12.setText("REGRESA A PRODUCTO");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton1.setText("REGRESA A VENTA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setText("BUSCAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Precio", "Descripcion"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(117, 210, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(txtPrecio)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(jLabel4))
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnverprov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(108, 108, 108))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(13, 13, 13)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnverprov))
                .addGap(83, 83, 83)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 168, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ventavirtual venta = new ventavirtual();
        venta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked

        clic_tabla = tabla.rowAtPoint(evt.getPoint());

        int codigo = (int)tabla.getValueAt(clic_tabla, 0);
        String nombre = ""+tabla.getValueAt(clic_tabla, 1);
        int cantidad = (int)tabla.getValueAt(clic_tabla, 2);
        double precio = (double)tabla.getValueAt(clic_tabla, 3);
        Object descripcion = ""+tabla.getValueAt(clic_tabla, 4);

        txtCodigo.setText(String.valueOf(codigo));
        txtProducto.setText(nombre);
        txtCantidad.setText(String.valueOf(cantidad));
        txtPrecio.setText(String.valueOf(precio));
        txtDescripcion.setText(String.valueOf(descripcion));

        try{
            JLabel lbl = (JLabel)tabla.getValueAt(clic_tabla, 5);

        }catch(Exception ex){
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        File rus = new File(txtRuta.getText());
        this.ingRe(rus);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        File rus = new File(txtRuta.getText());
        this.modR(rus);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.eliR();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Lim lp = new Lim();
        lp.Lim(panel);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnverprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverprovActionPerformed
        Proved prov = new Proved();
        prov.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnverprovActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        interfaz sisu = new interfaz();
        sisu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            FileReader fr = new FileReader("mi.txt");
            BufferedReader br = new BufferedReader(fr);
            String palabraBuscada = JOptionPane.showInputDialog(null, "Ingrese la palabra que quiere buscar:");
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(palabraBuscada)) {
                    JOptionPane.showMessageDialog(null, "La palabra '" + palabraBuscada + "' se encontró en la línea: " + linea);
                }
            }
            br.close();
            fr.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

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
            java.util.logging.Logger.getLogger(devolucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(devolucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(devolucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(devolucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new devolucion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnverprov;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
