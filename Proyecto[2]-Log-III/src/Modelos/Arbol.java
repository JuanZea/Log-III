package Modelos;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Arbol {
    private NodoLg raiz;

    public Arbol() {
    }

    public Arbol(NodoLg p) {
        this.raiz = p;
    }

    public NodoLg construirArbol(String s) {
        NodoLg x = new NodoLg();
        this.raiz = x;
        NodoLg p = x;
        int n = s.length();
        for (int i = 1; i < n - 1; i++) { // Se ignorarán los paréntesis frontera
            switch (s.charAt(i)) {
                case '(': {
                    int comodin = 0;
                    do {
                        if (s.charAt(i) == '(') {
                            comodin++;
                        }
                        if (s.charAt(i) == ')') {
                            comodin--;
                        }
                        i++;
                    } while (comodin != 0);
                    i--;
                    continue;
                }
                default: {
                    if (this.raiz.retornaDato() == null) {
                        p.asignaDato(s.charAt(i));
                        i++;  // Se salta el paréntesis siguiente
                        break;
                    }
                    if (s.charAt(i + 1) != '(') {
                        x = new NodoLg(s.charAt(i));
                        p.asignaLiga(x);
                        p = x;
                        break;
                    }
                    x = new NodoLg();
                    x.cambiaSw();
                    Arbol a = new Arbol();
                    x.asignaDato(a.construirArbol(s.substring(i - 1)));
                    p.asignaLiga(x);
                    p = x;
                    continue;
                }
                case ',': {
                    continue;
                }
                case ')': {
                    i = n - 1;
                    break;
                }
            }
        }
        return raiz;
    }

    public String mostrarArbol() {
        String cadena;
        cadena = "(" + this.raiz.retornaDato() + mostrarHijos(this.raiz) + ")";
        return cadena;
    }

    public String mostrarHijos(NodoLg raiz) {
        if (raiz.retornaLiga() == null) {
            return "";
        }
        String cadena = "(";
        NodoLg p = raiz.retornaLiga();
        while (p != null) {
            if (p.retornaSw()) {
                cadena += ((NodoLg) p.retornaDato()).retornaDato() + mostrarHijos((NodoLg) p.retornaDato());
            } else {
                cadena += p.retornaDato();
            }
            if (p.retornaLiga() != null) {
                cadena += ",";
            }
            p = p.retornaLiga();
        }
        return cadena + ")";
    }

    public int hojas(NodoLg r) {
        if (r == null) {
            return 0;
        }
        if (r.retornaLiga() == null) {
            return 1;
        }
        int hojas = 0;
        NodoLg p = r.retornaLiga();
        while (p != null) {
            if (p.retornaSw()) {
                hojas += this.hojas((NodoLg) p.retornaDato());
            } else {
                hojas++;
            }
            p = p.retornaLiga();
        }
        return hojas;
    }

    public NodoLg retornaRaiz() {
        return this.raiz;
    }

    public int grado(NodoLg raiz) {
        if (raiz == null) {
            return 0;
        }
        NodoLg p = raiz.retornaLiga();
        if (p == null) {
            return 0;
        }
        int n = 0;
        int gradoMayor = 0;
        while (p != null) {
            n++;
            if (p.retornaSw()) {
                int g = grado((NodoLg) p.retornaDato());
                if (g > gradoMayor) {
                    gradoMayor = g;
                }
            }
            p = p.retornaLiga();
        }
        if (n > gradoMayor) {
            return n;
        }
        return gradoMayor;
    }

    public Arbol copiarArbol() {
        return new Arbol(this.raiz);
    }

    public int altura(NodoLg r) {
        if (r == null) {
            return 0;
        }
        if (r.retornaLiga() == null) {
            return 1;
        }
        int nivelMaximo = 0;
        int nivel;
        NodoLg p = r.retornaLiga();
        while (p != null) {
            if (p.retornaSw()) {
                nivel = this.altura((NodoLg) p.retornaDato()) - 1;
                nivelMaximo = (nivel > nivelMaximo) ? nivel : nivelMaximo;
            }
            p = p.retornaLiga();
        }
        return nivelMaximo + 2;
    }

    public boolean primos(Character primo1, Character primo2) {
        if (primo1 == null || primo2 == null) {
            return false;
        }
        return hermanos(padre(primo1), padre(primo2));
    }

    public Character padre(Character primo1) {
        if (this.raiz.retornaDato() == primo1) {
            return null;
        }
        NodoLg p1 = this.raiz;
        NodoLg h1 = this.raiz;
        Stack pilaH = new Stack();
        Stack pilaP = new Stack();
        boolean despierto = false;
        while (h1.retornaDato() != primo1) {
            if (h1.retornaSw()) {
                despierto = true;
                pilaH.push(h1);
                h1 = (NodoLg) h1.retornaDato();
            } else {
                if (despierto && h1.retornaLiga() != null) {
                    pilaP.push(p1);
                    p1 = h1;
                    despierto = false;
                }
                h1 = h1.retornaLiga();
            }
            while (h1 == null) {
                if (pilaH.empty()) {
                    break;
                }
                h1 = (NodoLg) pilaH.pop();
                p1 = (NodoLg) pilaP.pop();
                h1 = h1.retornaLiga();
            }
        }
        return (Character) p1.retornaDato();
    }

    public boolean hermanos(Character primo1, Character primo2) {
        if (primo1 == null || primo2 == null || primo1 == primo2) {
            return false;
        }
        return padre(primo1) == padre(primo2);
    }

    public ArbolBinario convertirEnBinario() {
        if (this.raiz == null) {
            return new ArbolBinario();
        }
        NodoLg raiz = this.raiz;
        NodoDoble r = hijos(raiz);
        NodoDoble raizBinario = new NodoDoble(raiz.retornaDato());
        raizBinario.asignaLI(r);
        ArbolBinario a = new ArbolBinario(raizBinario);
        return a;
    }

    public NodoDoble hijos(NodoLg r) {
        NodoDoble raiz = new NodoDoble(r.retornaDato());
        NodoDoble ultimo = raiz;
        NodoDoble x;
        r = r.retornaLiga();
        while (r != null) {
            if (!r.retornaSw()) {
                x = new NodoDoble(r.retornaDato());
                ultimo.asignaLD(x);
            } else {
                x = new NodoDoble(((NodoLg) r.retornaDato()).retornaDato());
                ultimo.asignaLD(x);
                x.asignaLI(hijos((NodoLg) r.retornaDato()));
            }
            ultimo = x;
            r = r.retornaLiga();
        }
        return raiz.retornaLD();
    }
}
