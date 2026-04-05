
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


public class interfaz extends javax.swing.JFrame {

   
    
    Lim lt = new Lim();
    
    private String ruta_txt = "mi.txt"; 
    
    
    Producto t;
    Prog lc;
    
    int clic_tabla;
            
    public interfaz() {
        initComponents();
        lc = new Prog();
        
        try{
            cargar_txt();
            listarReg();
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
                t = new Producto();
                t.setCod(Integer.parseInt(st.nextToken()));
                t.setNombre(st.nextToken());
                t.setCantidad(Integer.parseInt(st.nextToken()));
                t.setcosto(Double.parseDouble(st.nextToken()));
                t.setDescripcion(st.nextToken());
                lc.aggR(t);
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
            
            for(int c = 0; c < lc.cantReg(); c++){
                t = lc.obtReg(c);
                py.println(String.valueOf(t.getCod()+","+t.getNombre()+","+t.getCantidad()+","+t.getcosto()+","+t.getinformacion()));
            }
             py.close();
            
        }catch(Exception q){
            mj("Error"+q.getMessage());
            System.out.println(q.getMessage());
        }
    }
    
    public void ingReg(File ruta){
        try{
            if(lorCode() == -666)mj("Ingresar codigo entero");
            else if(lorNom() == null)mj("Ingresar producto");
            else if(lorCantid() == -666)mj("Ingresar cantidad");
            else if(lorcost() == -666) mj("Ingresar Precio");
            else if(lor_info() == null)mj("Ingresar Descripcion");
            else{
                t = new Producto(lorCode(),lorNom(),lorCantid(),lorcost(),lor_info());
                if(lc.busc(t.getCod())!= -1)mj("Este Producto ya fue vendido existe");
                else lc.aggR(t);
                
                grabar_txt();
                listarReg();
                lt.Lim(panel); 
            }
        }catch(Exception q){
            mj(q.getMessage());
        }
    }
    
    public void modiReg(File ruta){
        try{
            if(lorCode() == -666)mj("Ingresar codigo entero");
            else if(lorNom() == null)mj("Ingresar Nombre");
            else if(lorCantid() == -666)mj("Ingresar cantidad entero");
            else if(lorcost() == -666) mj("Ingresar Precio");
            else if(lor_info() == null)mj("Ingresar Descripcion");
            else{
                int codigo = lc.busc(lorCode());
                t = new Producto(lorCode(),lorNom(), lorCantid(), lorcost(), lor_info());
                
                if(codigo == -1)lc.aggR(t);
                else lc.modiR(codigo, t);
                
                grabar_txt();
                listarReg();
                lt.Lim(panel);
            }
        }catch(Exception ex){
            mj(ex.getMessage());
        }
    }
    
    public void elimReg(){
        try{
            if(lorCode() == -666) mj("Ingrese codigo entero");
            
            else{
                int codigo = lc.busc(lorCode());
                if(codigo == -1) mj("codigo no existe");
                
                else{
                    int s = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este producto","Si/No",0);
                    if(s == 0){
                        lc.elimR(codigo);
                        
                        grabar_txt();
                        listarReg();
                        lt.Lim(panel);
                    }
                }
                
                
            }
        }catch(Exception q){
            mj(q.getMessage());
        }
    }
    
    public void listarReg(){
        DefaultTableModel dt = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        dt.addColumn("Codigo");
        dt.addColumn("Nombre");
        dt.addColumn("Cantidad");
        dt.addColumn("costo");
        dt.addColumn("informacion");
        
        tabla.setDefaultRenderer(Object.class, new imgTabla());
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < lc.cantReg(); i++){
            t = lc.obtReg(i);
            fila[0] = t.getCod();
            fila[1] = t.getNombre();
            fila[2] = t.getCantidad();
            fila[3] = t.getcosto();
            fila[4] = t.getinformacion();
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
    public String lorNom(){
        try{
            String Nombre = txtNombre.getText().trim().replace(" ", "_");
                    
            return Nombre;
        }catch(Exception q){
            return null;
        }
    }
    
    public int lorCantid(){
        try{
            int Cantidad = Integer.parseInt(txtCantidad.getText().trim());
            
            return Cantidad;
        }catch(Exception q){
            return -666;
        }
    }
    
    public double lorcost(){
        try{
            double costo = Double.parseDouble(txtPrecio.getText().trim());
            return costo;
        }catch(Exception q){
            return -666;
        }
    }
    
    public Object lor_info(){
        try{
            Object informacion = txtDescripcion.getText().trim();
            return informacion;
        }catch(Exception ex){
            return null;
        }
    }
    
    
    public byte[] lorFot(File ruta){
        try{
            byte[] icono = new byte[(int) ruta.length()];
            InputStream input = new FileInputStream(ruta);
            input.read(icono);
            return icono;
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

        jPanel5 = new javax.swing.JPanel();
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
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        btnverprov = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Base de Datos con Bloc de Notas .txt");

        jPanel5.setBackground(new java.awt.Color(0, 51, 204));
        jPanel5.setForeground(new java.awt.Color(0, 51, 204));

        panel.setBackground(new java.awt.Color(0, 0, 204));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Cantidad", "Precio", "Descipcion"
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
        jButton3.setText("Eliminar");
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
        jLabel2.setText("NOMBRE");

        txtRuta.setEditable(false);
        txtRuta.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

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

        jButton7.setText("VENDER");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("EXCEL");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setText("CANTIDAD");

        jButton9.setText("BUSCAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(47, 47, 47)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(jLabel4))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jButton7)))
                .addGap(30, 30, 30)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                    .addGap(182, 182, 182)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136))))
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addGap(39, 39, 39)
                                .addComponent(jButton9)
                                .addGap(76, 76, 76)
                                .addComponent(btnverprov))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addGap(420, 420, 420))
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(btnverprov))
                .addGap(99, 99, 99)
                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(28, 28, 28)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)))
                .addGap(0, 285, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 0, 24)); // NOI18N
        jLabel5.setText("TIENDA VIRTUAL ECCI");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File rus = new File(txtRuta.getText());
        this.ingReg(rus);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        File rus = new File(txtRuta.getText());
        this.modiReg(rus);
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.elimReg();
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        
        clic_tabla = tabla.rowAtPoint(evt.getPoint());
        
        int codigo = (int)tabla.getValueAt(clic_tabla, 0);
        String nombre = ""+tabla.getValueAt(clic_tabla, 1);
        int cantidad = (int)tabla.getValueAt(clic_tabla, 2);
        double precio = (double)tabla.getValueAt(clic_tabla, 3);
        Object descripcion = ""+tabla.getValueAt(clic_tabla, 4);

        txtCodigo.setText(String.valueOf(codigo));
        txtNombre.setText(nombre);
        txtCantidad.setText(String.valueOf(cantidad));
        txtPrecio.setText(String.valueOf(precio));
        txtDescripcion.setText(String.valueOf(descripcion));
        
        try{
            JLabel lbl = (JLabel)tabla.getValueAt(clic_tabla, 5);
           
        }catch(Exception ex){
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Lim lp = new Lim();
        lp.Lim(panel);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnverprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverprovActionPerformed
        Proved prov = new Proved();
        prov.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnverprovActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ventavirtual venntica = new ventavirtual();
        venntica.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        doc obj;
        
        try{
            obj = new doc();
            obj.exp(tabla);
        
         } catch (IOException ex){
            System.out.println("error" + ex);
        }
    
    }//GEN-LAST:event_jButton8ActionPerformed

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
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton9ActionPerformed

  
    public static void main(String args[]) {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnverprov;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
