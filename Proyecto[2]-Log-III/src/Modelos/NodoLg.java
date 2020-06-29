package Modelos;

public class NodoLg {
    private boolean sw;
    private Object dato;
    private NodoLg liga;

    public NodoLg() {
        this.sw = false;
        this.liga = null;
        this.dato = null;
    }

    public NodoLg(Object dato) {
        this.sw = false;
        this.liga = null;
        this.dato = dato;
    }

    public Object retornaDato() {
        return this.dato;
    }

    public NodoLg retornaLiga() {
        return this.liga;
    }

    public boolean retornaSw() {
        return this.sw;
    }

    public void asignaDato(Object dato) {
        this.dato = dato;
    }

    public void asignaLiga(NodoLg liga) {
        this.liga = liga;
    }

    public void asignaSw(boolean sw) {
        this.sw = sw;
    }

    // Original
    public void cambiaSw(){
    this.sw = !this.sw;
    }
}