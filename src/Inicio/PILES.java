
package Inicio;



import java.io.Serializable;
import java.util.Stack;


public class PILES {
    
    private Stack<Object> q = new Stack<Object>();
    
    public PILES(){}
    
    public PILES(Stack<Object> q){
        this.q = q;
    }
    
    public void agrR(Venta o){
        this.q.push(o);
    }

    public void modR(int d, Venta o){

        this.q.pop();
 
        this.q.push(o);
    }
    
    public void elR(int d){
        this.q.pop();
    }
    
    public Venta obRe(int d){
        return (Venta)q.peek();
    }
    
    public int cantdRe(){
        return this.q.size();
    }
    public int busc(int code){
        for(int p = 0; p < cantdRe(); p++){
            if(code == obRe(p).getCode())return p;
        }
        return -1;
    }


    
}

    
    

