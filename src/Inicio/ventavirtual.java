
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


public class ventavirtual extends javax.swing.JFrame {

    Lim lt = new Lim();
    
    private String ruta_txt = "mi.txt"; 
    
    
    Venta t;
    PILES pr;
    
    int clic_tabla;
    public ventavirtual() {
        initComponents();
        pr = new PILES();
        
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
                t = new Venta();
                t.setCode(Integer.parseInt(st.nextToken()));
                t.setProduc(st.nextToken());
                t.setCantid(Integer.parseInt(st.nextToken()));
                t.setPrecio(Double.parseDouble(st.nextToken()));
                t.setDesc(st.nextToken());
                pr.agrR(t);
            }
            bu.close();
        }catch(Exception q){
            
        }
    }
    
    public void grabar_txt(){
        FileWriter ft;
        PrintWriter py;
        try{
            ft = new FileWriter(ruta_txt);
            py = new PrintWriter(ft);
            
            for(int u = 0; u < pr.cantdRe(); u++){
                t = pr.obRe(u);
                py.println(String.valueOf(t.getProduc()+","+t.getCantid()+","+t.getPrec()+","+t.getDesc()));
            }
             py.close();
            
        }catch(Exception q){
            
        }
    }
    
    public void ingresarRegistro(File ruta){
        try{
            if(lorCode() == -666)mj("Ingresar codigo entero");
            else if(lorProduc() == null)mj("Ingresar producto");
            else if(lorCantid() == -666)mj("Ingresar cantidad");
            else if(lorPrec() == -666) mj("Ingresar Precio");
            else if(lorDesc() == null)mj("Ingresar Descripcion");
            else{
                t = new Venta(lorCode(),lorProduc(), lorCantid(), lorPrec(), lorDesc());
                if(pr.busc(t.getCode())!= -1)mj("Este Producto ya esta vendido");
                else pr.agrR(t);
                
                grabar_txt();
                listarRegistro();
                lt.Lim(panel); 
            }
        }catch(Exception q){
            mj(q.getMessage());
        }
    }
    
    public void modificarRegistro(File ruta){
        try{
            if(lorCode() == -666)mj("Ingresar codigo entero");
            else if(lorProduc() == null)mj("Ingresar Nombre");
            else if(lorCantid() == -666)mj("Ingresar codigo entero");
            else if(lorPrec() == -666) mj("Ingresar Precio");
            else if(lorDesc() == null)mj("Ingresar Descripcion");
            else{
                int codigo = pr.busc(lorCode());
                t = new Venta(lorCode(),lorProduc(),lorCantid(),lorPrec(),lorDesc());
                
                if(codigo == -1)pr.agrR(t);
                else pr.modR(codigo, t);
                
                grabar_txt();
                listarRegistro();
                lt.Lim(panel);
            }
        }catch(Exception q){
            mj(q.getMessage());
        }
    }
    
    public void eliminarRegistro(){
        try{
            if(lorCode() == -666) mj("Ingrese el codigo completo");
            
            else{
                int codigo = pr.busc(lorCode());
                if(codigo == -1) mj("no existe");
                
                else{
                    int s = JOptionPane.showConfirmDialog(null, "Quiere eliminar este producto","Si/No",0);
                    if(s == 0){
                        pr.elR(codigo);
                        
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
        dt.addColumn("Cantidad");
        dt.addColumn("Precio");
        dt.addColumn("Descripcion");
        
        tabla.setDefaultRenderer(Object.class, new imgTabla());
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < pr.cantdRe(); i++){
            t = pr.obRe(i);
            fila[0] = t.getCode();
            fila[1] = t.getProduc();
            fila[2] = t.getCantid();
            fila[3] = t.getPrec();
            fila[4] = t.getDesc();
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
            
            return 20-Cantidad;
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
    
    
    
    
  

    public void mj(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        btnverprov = new javax.swing.JButton();
        txtRuta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 51, 255));

        panel.setBackground(new java.awt.Color(255, 51, 255));

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
        jButton3.setText("VENDER");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtDescripcion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

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

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel5.setText("CODIGO");

        jButton7.setText("REGRESAR DEVOLUCIONES");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton12.setText("REGRESAR PRODUCTO");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton9.setText("BUSCAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        btnverprov.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnverprov.setText("PROVEEDORES");
        btnverprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverprovActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(54, 54, 54)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1))
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3)))))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(txtProducto))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnverprov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(735, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(7, 7, 7)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jButton1)
                                .addGap(17, 17, 17)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(btnverprov)))
                .addGap(85, 85, 85))
        );

        txtRuta.setEditable(false);
        txtRuta.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        txtRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(260, 260, 260))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 171, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 332, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            
        }catch(Exception q){
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File ruta = new File(txtRuta.getText());
        this.ingresarRegistro(ruta);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        File ruta = new File(txtRuta.getText());
        this.modificarRegistro(ruta);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.eliminarRegistro();

    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        devolucion devo = new devolucion();
        devo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        interfaz sisu = new interfaz();
        sisu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
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
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaActionPerformed

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
            java.util.logging.Logger.getLogger(ventavirtual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventavirtual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventavirtual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventavirtual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventavirtual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnverprov;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
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
