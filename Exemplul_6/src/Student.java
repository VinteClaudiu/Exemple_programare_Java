/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.Serializable;


public class Student implements Serializable {
    private String nume;
    private int grupa;
    private int notaPoo;
    private int notaPaw;
    private int notaJava;

    public Student() {
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public int getNotaPoo() {
        return notaPoo;
    }

    public void setNotaPoo(int notaPoo) {
        this.notaPoo = notaPoo;
    }

    public int getNotaPaw() {
        return notaPaw;
    }

    public void setNotaPaw(int notaPaw) {
        this.notaPaw = notaPaw;
    }

    public int getNotaJava() {
        return notaJava;
    }

    public void setNotaJava(int notaJava) {
        this.notaJava = notaJava;
    }

    @Override
    public String toString() {
        return nume + "," + grupa + "," + notaPoo + "," + notaPaw + "," + notaJava;
    }
    
}
