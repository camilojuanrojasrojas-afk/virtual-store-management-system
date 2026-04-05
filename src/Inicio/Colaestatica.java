
package Inicio;


import java.util.Queue;
import java.util.LinkedList;

public class Colaestatica {
    
    private Queue<Object> q = new LinkedList<Object>();
    
    public Colaestatica(){}
    
    public Colaestatica(Queue<Object> q){
        this.q = q;
    }
    
    public void agr_R(Ceu t){
        this.q.add(t);
    }

    public void mod_R(int u, Ceu t){
   
        this.q.remove();
       
        this.q.add(t);
    }
    
    public void elimR(int p){
        this.q.remove();
    }
    
    public Ceu tenerR(int p){
        return (Ceu)q.peek();
    }
    
    public int cantiR(){
        return this.q.size();
    }
    
    public int buscaC(int c){
        for(int p = 0; p < cantiR(); p++){
            if(c == tenerR(p).getCode())return p;
        }
        return -1;
    }
}

