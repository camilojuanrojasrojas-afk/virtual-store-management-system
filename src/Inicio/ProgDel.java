package Inicio;

import java.util.Stack;


public class ProgDel {
    
    private Stack<Object> q = new Stack<Object>();
    
    public ProgDel(){}
    
    public ProgDel(Stack<Object> q){
        this.q = q;
    }
    
    public void agR(Del o){
        this.q.push(o);
    }

    public void modR(int u, Del o){
       
        this.q.pop();
       
        this.q.push(o);
    }
    
    public void eliR(int u){
        this.q.pop();
    }
    
    public Del obtR(int u){
        return (Del)q.peek();
    }
    
    public int cantidadRegistro(){
        return this.q.size();
    }
    public int buscaCodigo(int code){
        for(int p = 0; p < cantidadRegistro(); p++){
            if(code == obtR(p).getCode())return p;
        }
        return -1;
    }


    
}
