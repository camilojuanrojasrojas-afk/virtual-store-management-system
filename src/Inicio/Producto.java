
package Inicio;

import java.io.Serializable;


public class Producto {
    
    private int cod;
    private String nombre;
    private int cantidad;
    private double costo;
    private Object informacion;

    public Producto(){}
    
    public Producto(int cod, String nombre,int cantidad, double costo, Object informacion){
        this.cod = cod;
        this.nombre = nombre;
        this.cantidad=cantidad;
        this.costo = costo;
        this.informacion = informacion;
    }
 
    public int getCod() {
        return cod;
    }

   
    public void setCod(int cod) {
        this.cod = cod;
    }

  
    public String getNombre() {
        return nombre;
    }

  
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCantidad() {
        return cantidad;
    }

    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getcosto() {
        return costo;
    }

 
    public void setcosto(double costo) {
        this.costo = costo;
    }


    public Object getinformacion() {
        return informacion;
    }

 
    public void setDescripcion(Object informacion) {
        this.informacion = informacion;
    }
    
}
