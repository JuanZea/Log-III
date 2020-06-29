package Modelos;

import java.util.Stack;

public class ListaGeneralizada {
    private NodoLg primero, ultimo;

    public ListaGeneralizada() {

    }

    public ListaGeneralizada(NodoLg p) {
        this.primero = p;
        while (p.retornaLiga() != null) {
            p = p.retornaLiga();
        }
        this.ultimo = p;
    }

    /**
     * Modificado: Puede guardar cadenas de varios caracteres en vez de solo uno.
     *
     * @param s Formato: (a,juan,(j,ua,n),b,c)
     */
    public void construirLista(String s) {
        Stack pila = new Stack();
        NodoLg x = new NodoLg();
        this.primero = x;
        this.ultimo = x;
        int n = s.length();
        String dato = "";
        for (int i = 1; i < n - 1; i++) { // Se ignorarán los paréntesis frontera
            switch (s.charAt(i)) {
                default: {
                    dato += s.charAt(i);
                    break;
                }
                case ',': {
                    if (dato != null) {
                        this.ultimo.asignaDato(dato);
                    }
                    dato = "";
                    x = new NodoLg();
                    this.ultimo.asignaLiga(x);
                    ultimo = x;
                    break;
                }
                case '(': {
                    pila.push(ultimo);
                    x = new NodoLg();
                    ultimo.cambiaSw();
                    ultimo.asignaDato(x);
                    ultimo = x;
                    break;
                }
                case ')': {
                    if (dato != null) {
                        this.ultimo.asignaDato(dato);
                    }
                    dato = null;
                    ultimo = (NodoLg) pila.pop();
                    break;
                }
            }
        }
        if (dato != null) {
            this.ultimo.asignaDato(dato);
        }
    }

    public String mostrarLista() {
        String lista = "(";
        NodoLg p = this.primero;
        while (p != null) {
            if (p.retornaSw()) {
                // Crea nueva lista con primero modificado y retorna recursivo
                ListaGeneralizada lg = new ListaGeneralizada((NodoLg) p.retornaDato());
                lista += lg.mostrarLista();
            } else {
                lista += String.valueOf(p.retornaDato());
            }
            lista += ",";
            p = p.retornaLiga();
        }
        lista = lista.substring(0, lista.length()-1) + ")";
        return lista;
    }

    public ListaGeneralizada copiarLista(){
        // (._. )( ._.)
        return new ListaGeneralizada(this.primero);
    }
}
