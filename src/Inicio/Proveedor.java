
package Inicio;
import java.io.Serializable;

public class Proveedor {
    private int code;
    private String nom;
    private String num;
    private String fecha;

    public Proveedor() {
    }

    public Proveedor(int code, String nom, String num, String fecha) {
        this.code = code;
        this.nom = nom;
        this.num = num;
        this.fecha = fecha;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
