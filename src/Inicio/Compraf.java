
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
public class Compraf extends javax.swing.JFrame {


    Lim lt = new Lim();
    
    private String ruta_txt = "mil.txt"; 


    Ceu g;
    Colaestatica pl;

    int clic_tabla;
    public Compraf() {
        initComponents();
        pl = new Colaestatica();

        try{
            cargar_txt();
            listarRegistro();
        }catch(Exception q){
            mensaje("No existe");
        }
    }    
    public void cargar_txt(){
        File rus = new File(ruta_txt);
        try{
            
            FileReader fi = new FileReader(rus);
            BufferedReader bu = new BufferedReader(fi);
            
            
            String linea = null;
            while((linea = bu.readLine())!=null){
                StringTokenizer st = new StringTokenizer(linea, ",");
                g = new Ceu();
                g.setProdud(st.nextToken());
                g.setCantd(Integer.parseInt(st.nextToken()));
                g.setPrec(Double.parseDouble(st.nextToken()));
                g.setProvees(st.nextToken());
                pl.agr_R(g);
            }
            bu.close();
        }catch(Exception q){
            mensaje("Error"+q.getMessage());
            System.out.println(q.getMessage());
        }
    }
    
    public void grabar_txt(){
        FileWriter gr;
        PrintWriter po;
        try{
            gr = new FileWriter(ruta_txt);
            po = new PrintWriter(gr);
            
            for(int p = 0; p < pl.cantiR(); p++){
                g = pl.tenerR(p);
                po.println(String.valueOf(g.getProdud()+", "+g.getCantd()+", "+g.getcoste()+", "+g.getProvees()));
            }
             po.close();
            
        }catch(Exception q){
            mensaje("Error: "+q.getMessage());
            System.out.println(q.getMessage());
        }
    }
    
    public void Res(File rus){
        try{
            if(lorC()==-666)mensaje("ingresa code");
            else if(lor_prod() == null)mensaje("Ingresar producto");
            else if(lor_Cantd() == -666)mensaje("Ingresar cantidad");
            else if(lor_pre() == -666) mensaje("Ingresar Precio");
            else if(lor_Provees() == null)mensaje("Ingresar Prov");
            else{
                g = new Ceu(lorC(),lor_prod(), lor_Cantd(), lor_pre(), lor_Provees());
                if(pl.buscaC(g.getCode())!= -1)mensaje("Ya no tenemos mas este producto");
                else pl.agr_R(g);
                
                grabar_txt();
                listarRegistro();
                lt.Lim(panel); 
            }
        }catch(Exception q){
            mensaje(q.getMessage());
        }
    }
    
    public void modR(File rus){
        try{
            if(lorC()==-666)mensaje("ingresa code");
            else if(lor_prod() == null)mensaje("Ingresar producto");
            else if(lor_Cantd() == -666)mensaje("Ingresar cantidad");
            else if(lor_pre() == -666) mensaje("Ingresar Precio");
            else if(lor_Provees() == null)mensaje("Ingresar Prov");
            else{
                int code = pl.buscaC(lorC());
                g = new Ceu(lorC(),lor_prod(), lor_Cantd(), lor_pre(), lor_Provees());
                
                if(code == -1)pl.agr_R(g);
                else pl.mod_R(code, g);
                
                grabar_txt();
                listarRegistro();
                lt.Lim(panel);
            }
        }catch(Exception q){
            mensaje(q.getMessage());
        }
    }
    
    public void elR(){
        try{
            if(lorC() == -666) mensaje("Ingrese el codigo completo");
            
            else{
                int code = pl.buscaC(lorC());
                if(code == -1) mensaje("codigo no existente");
                
                else{
                    int s = JOptionPane.showConfirmDialog(null, "Quiere eliminar este producto","Si/No",0);
                    if(s == 0){
                        pl.elimR(code);
                        
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
        
        Object fil[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < pl.cantiR(); i++){
            g = pl.tenerR(i);
            fil[0] = g.getCode();
            fil[1] = g.getProdud();
            fil[2] = g.getCantd();
            fil[3] = g.getcoste();
            fil[4] = g.getProvees();
            dt.addRow(fil);
        }
        tabla.setModel(dt);
        tabla.setRowHeight(60);
    }
    public int lorC(){
        try{
            int Code = Integer.parseInt(txtCodigo.getText().trim());
                    
            return Code;
        }catch(Exception q){
            return -666;
        }
    }
    public String lor_prod(){
        try{
            String Prod = txtProducto.getText().trim().replace(" ", "_");
                    
            return Prod;
        }catch(Exception q){
            return null;
        }
    }
    
    public int lor_Cantd(){
        try{
            int Cantd = Integer.parseInt(txtCantidad.getText().trim());
            
            return Cantd-1;
        }catch(Exception q){
            return -666;
        }
    }
    
    public double lor_pre(){
        try{
            double prec = Double.parseDouble(txtPrecio.getText().trim());
            return prec;
        }catch(Exception q){
            return -666;
        }
    }
    
    public String lor_Provees(){
        try{
            String desc = txtDescripcion.getText().trim();
            return desc;
        }catch(Exception q){
            return null;
        }
    }
    
    
    
 
    public void mensaje(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        txtRuta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        btnverprov = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 0, 204));

        panel.setBackground(new java.awt.Color(204, 0, 204));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar (1).png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtRuta.setEditable(false);
        txtRuta.setBackground(new java.awt.Color(204, 0, 204));
        txtRuta.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "PRECIO", "Descripcion"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jButton1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton2.setText("Modificar");
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

        jButton10.setText("jButton10");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jButton1)
                                .addGap(58, 58, 58)
                                .addComponent(jButton2)
                                .addGap(49, 49, 49)
                                .addComponent(jButton3)
                                .addGap(51, 51, 51)
                                .addComponent(jButton5))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(601, 601, 601)
                        .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(41, 41, 41)
                        .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3)))
                .addContainerGap(876, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel5.setText("CODIGO");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("PRODUCTO");

        txtProducto.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("CANTIDAD");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("PRECIO");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("DESCRIPCION");

        txtCantidad.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtPrecio.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtDescripcion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jButton7.setText("DEVOLUCIONES");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton12.setText("BUSCAR");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        btnverprov.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnverprov.setText("PROVEEDORES");
        btnverprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverprovActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(98, 1193, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnverprov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12)
                        .addGap(18, 18, 18)
                        .addComponent(btnverprov))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked

        clic_tabla = tabla.rowAtPoint(evt.getPoint());

        int codigo = (int)tabla.getValueAt(clic_tabla, 0);
        String producto = ""+tabla.getValueAt(clic_tabla, 1);
        int cantidad = (int)tabla.getValueAt(clic_tabla, 2);
        double precio = (double)tabla.getValueAt(clic_tabla, 3);
        Object descripcion = ""+tabla.getValueAt(clic_tabla, 4);
        
        txtCodigo.setText(producto);
        txtProducto.setText(String.valueOf(codigo));
        txtCantidad.setText(producto);
        txtPrecio.setText(String.valueOf(precio));
        txtDescripcion.setText(String.valueOf(descripcion));

        try{
            JLabel lbl = (JLabel)tabla.getValueAt(clic_tabla, 5);

        }catch(Exception ex){
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
File ruta = new File(txtRuta.getText());
        this.modR(ruta);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
File ruta = new File(txtRuta.getText());
        this.modR(ruta);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.elR();
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
            try {
            FileReader fr = new FileReader("mil.txt");
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
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton12ActionPerformed

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
            java.util.logging.Logger.getLogger(Compraf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compraf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compraf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compraf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compraf().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnverprov;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
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
