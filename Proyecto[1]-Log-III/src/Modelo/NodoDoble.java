package Modelo;

public class NodoDoble {
    private Object dato;
    private NodoDoble LI, LD;

    public NodoDoble() {

    }

    public NodoDoble(Object dato) {
        LI = LD = null;
        this.dato = dato;
    }

    public Object retornaDato() {
        return this.dato;
    }

    public NodoDoble retornaLI() {
        return LI;
    }

    public NodoDoble retornaLD() {
        return LD;
    }

    public void asignaDato(Object dato) {
        this.dato = dato;
    }

    public void asignaLI(NodoDoble LI) {
        this.LI = LI;
    }

    public void asignaLD(NodoDoble LD) {
        this.LD = LD;
    }
}