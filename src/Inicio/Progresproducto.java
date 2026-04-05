
package Inicio;


import java.io.Serializable;
import java.util.ArrayList;


public class Progresproducto {
    
    private ArrayList<Object> q = new ArrayList<Object>();
    
    public Progresproducto(){}
    
    public Progresproducto(ArrayList<Object> q){
        this.q = q;
    }
    
    public void aggReg(Proveedor ee){
        this.q.add(ee);
    }

    public void modiReg(int i, Proveedor ee){
        this.q.set(i, ee);
    }
    
    public void elimReg(int i){
        this.q.remove(i);
    }
    
    public Proveedor obtReg(int i){
        return (Proveedor)q.get(i);
    }
    
    public int cantReg(){
        return this.q.size();
    }
    
    public int busc(int code){
        for(int h = 0; h < cantReg(); h++){
            if(code == obtReg(h).getCode())return h;
        }
        return -1;
    }
    
    
}
