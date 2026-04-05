
package Inicio;


public class Venta {
    private int code;
    private String produc;
    private int cantid;
    private double prec;
    private Object desc;
    

    public Venta() {
    }

    public Venta(int code,String produc, int cantid, double prec, Object desc) {
        this.code=code;
        this.produc = produc;
        this.cantid = cantid;
        this.prec = prec;
        this.desc = desc;
        
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getProduc() {
        return produc;
    }

    public void setProduc(String produc) {
        this.produc = produc;
    }

    public int getCantid() {
        return cantid;
    }

    public void setCantid(int cantid) {
        this.cantid = cantid;
    }

    public double getPrec() {
        return prec;
    }

    public void setPrecio(double prec) {
        this.prec = prec;
    }
    public Object getDesc() {
        return desc;
    }

   
    public void setDesc(Object desc) {
        this.desc = desc;
    }
}
