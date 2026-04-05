/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Camilo
 */
public class Lim {
    
    public void Lim(JPanel panel){
        for(int p = 0; panel.getComponents().length > p; p++){
            if(panel.getComponents()[p] instanceof JTextField){
                ((JTextField)panel.getComponents()[p]).setText("");
            }
            else if(panel.getComponents()[p] instanceof JPasswordField){
                ((JPasswordField)panel.getComponents()[p]).setText("");
            }
        }
    }
    
}
