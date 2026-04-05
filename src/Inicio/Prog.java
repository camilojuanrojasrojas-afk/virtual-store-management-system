
package Inicio;

import java.io.Serializable;
import java.util.ArrayList;


public class Prog{
    
    private ArrayList<Object> q = new ArrayList<Object>();

    
    public Prog(){}
    
    public Prog(ArrayList<Object> q){
        this.q = q;
    }
    
    public void aggR(Producto o){
        this.q.add(o);
    }

    public void modiR(int u, Producto o){
        this.q.set(u, o);
    }
    
    public void elimR(int u){
        this.q.remove(u);
    }
    
    public Producto obtReg(int u){
        return (Producto)q.get(u);
    }
    
    public int cantReg(){
        return this.q.size();
    }
    
    public int busc(int code){
        for(int p = 0; p < cantReg(); p++){
            if(code == obtReg(p).getCod())return p;
        }
        return -1;
    }

    void aggR(Proveedor o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    void modiR(int codigo, Proveedor o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}
