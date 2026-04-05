
package Inicio;


public class Del {
    private int code;
    private String produc;
    private int cantid;
    private double cost;
    private Object in;

    public Del() {
    }

    public Del(int code, String produc, int cantid, double cost, Object descre) {
        this.code = code;
        this.produc = produc;
        this.cantid = cantid;
        this.cost= cost;
        this.in = in;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code= code;
    }

    public String getProdud() {
        return produc;
    }

    public void setProdud(String produc) {
        this.produc = produc;
    }

    public int getCantd() {
        return cantid;
    }

    public void setCantd(int cantid) {
        this.cantid = cantid;
    }

    public double getPrec() {
        return cost;
    }

    public void setPrec(double prec) {
        this.cost = cost;
    }

    public Object getin() {
        return in;
    }

    public void setin(Object in) {
        this.in = in;
    }
    
    
}
