
package Inicio;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class doc {
    
    public void exp (JTable t) throws IOException{
        JFileChooser chooser = new JFileChooser ();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("archivos de excel ", "xls");
        chooser.setFileFilter (filter);
        chooser.setDialogTitle("salvar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if(chooser.showSaveDialog(null) ==JFileChooser.APPROVE_OPTION){
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            
             try{
                File archivoXLS = new File (ruta);
                if (archivoXLS.exists()){
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("mi hoja de excel 1");
                hoja.setDisplayGridlines(false);
                for (int r=0; r< t.getRowCount(); r++){
                    Row fil = hoja.createRow(r);
                    for(int c=0; c< t.getColumnCount(); c++){
                        Cell celda = fil.createCell(c);
                        if (r==0){
                            celda.setCellValue(t.getColumnName(c));
                        }
                    }
                }
                int FilIN = 1;
                for (int q = 0; q < t.getRowCount(); q++){
                    Row fil = hoja.createRow(FilIN);
                    FilIN++;
                    for(int e=0; e< t.getColumnCount(); e++){
                        Cell celda = fil.createCell(e);
                        if (t.getValueAt(q, e)instanceof Double){
                            celda.setCellValue(Double.parseDouble(t.getValueAt(q, e).toString()));
                        }else if (t.getValueAt(q,e)instanceof Float){
                            celda.setCellValue(String.valueOf(t.getValueAt(q, e)));
                        }else {
                            celda.setCellValue(String.valueOf(t.getValueAt(q, e)));
                        }
                    }
                    
                }
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
             }catch (IOException | NumberFormatException e ){
                 throw e;
            }
        }
        
        
    }
    
}
