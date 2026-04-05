
package Inicio;

public class Ceu {
    private int cod;
    private String producto;
    private int cantidad;
    private double costo;
    private String Proveedor;

    public Ceu() {
    }

    public Ceu(int cod, String producto, int cantidad, double precio, String Proveedor) {
        this.cod = cod;
        this.producto = producto;
        this.cantidad = cantidad;
        this.costo = costo;
        this.Proveedor = Proveedor;
    }

    public int getCode() {
        return cod;
    }

    public void setCode(int cod) {
        this.cod = cod;
    }

    public String getProdud() {
        return producto;
    }

    public void setProdud(String producto) {
        this.producto = producto;
    }

    public int getCantd() {
        return cantidad;
    }

    public void setCantd(int cant) {
        this.cantidad = cantidad;
    }

    public double getcoste() {
        return costo;
    }

    public void setPrec(double precio) {
        this.costo = costo;
    }

    public String getProvees() {
        return Proveedor;
    }

    public void setProvees(String Proveedor) {
        this.Proveedor = Proveedor;
    }
    
    
    
}
