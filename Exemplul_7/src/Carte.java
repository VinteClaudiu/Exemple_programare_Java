package CarteClientServerDB;

import java.io.Serializable;


public class Carte implements Serializable {
    private String cota;
    private String titlu;
    private String autori;
    private int an;

    public String getCota() {
        return cota;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutori() {
        return autori;
    }

    public void setAutori(String autori) {
        this.autori = autori;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    @Override
    public String toString() {
        return this.cota + "," + 
                this.titlu + "," +
                this.autori + "," +
                this.an;
    }
    
}

