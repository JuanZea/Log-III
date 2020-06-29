package Modelo;

import Vistas.Plantilla;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.NoFixedFacet;

import java.util.ArrayList;

public class MatrizEnForma1 {
    private NodoDoble mat;

    public MatrizEnForma1(int m, int n) {
        Tripleta t = new Tripleta(m, n, null);
        this.mat = new NodoDoble(t);
        t.asignaValor(this.mat);
        this.mat.asignaDato(t);
    }

    public void contruyeNodosCabeza() {
        Tripleta t = (Tripleta) this.mat.retornaDato();
        int m = t.retornaFila();
        int n = t.retornaColumna();
        int p = mayor(m, n);
        NodoDoble ultimo = this.mat;
        for (int i = 0; i < p; i++) {
            t = new Tripleta(0, 0, this.mat);
            NodoDoble x = new NodoDoble(t);
            x.asignaLD(x);
            x.asignaLI(x);
            t = (Tripleta) ultimo.retornaDato();
            t.asignaValor(x);
            ultimo.asignaDato(t);
            ultimo = x;
        }
    }

    public void conectaPorFilas(NodoDoble x) {
        Tripleta t = (Tripleta) x.retornaDato();
        int f = t.retornaFila();
        int c = t.retornaColumna();
        t = (Tripleta) this.mat.retornaDato();
        NodoDoble p = (NodoDoble) t.retornaValor();
        for (int i = 1; i < f; i++) {
            t = (Tripleta) p.retornaDato();
            p = (NodoDoble) t.retornaValor();
        }
        NodoDoble aq = p;
        NodoDoble q = p.retornaLD();
        t = (Tripleta) q.retornaDato();
        int cq = t.retornaColumna();
        while (q != p && c > cq) {
            aq = q;
            q = q.retornaLD();
            t = (Tripleta) q.retornaDato();
            cq = t.retornaColumna();
        }
        aq.asignaLD(x);
        x.asignaLD(q);
        t = (Tripleta) p.retornaDato();
        f = t.retornaFila() + 1;
        t.asignaFila(f);
        p.asignaDato(t);
    }

    public void conectaPorColumnas(NodoDoble x) {
        Tripleta t = (Tripleta) x.retornaDato();
        int f = t.retornaFila();
        int c = t.retornaColumna();
        t = (Tripleta) this.mat.retornaDato();
        NodoDoble p = (NodoDoble) t.retornaValor();
        for (int i = 1; i < c; i++) {
            t = (Tripleta) p.retornaDato();
            p = (NodoDoble) t.retornaValor();
        }
        NodoDoble aq = p;
        NodoDoble q = p.retornaLI();
        t = (Tripleta) q.retornaDato();
        int fq = t.retornaFila();
        while (q != p && f > fq) {
            aq = q;
            q = q.retornaLI();
            t = (Tripleta) q.retornaDato();
            fq = t.retornaFila();
        }
        aq.asignaLI(x);
        x.asignaLI(q);
        t = (Tripleta) p.retornaDato();
        c = t.retornaColumna() + 1;
        t.asignaColumna(c);
        p.asignaDato(t);
    }

    public void recorrePorFilas() { // Modificado
        Tripleta t = (Tripleta) this.mat.retornaDato();
        NodoDoble p = (NodoDoble) t.retornaValor();
        NodoDoble q;
        int i = 1;
        // int f, c;
        // Object v;
        while (p != this.mat) {
            q = p.retornaLD();
            while (q != p) {
                t = (Tripleta) q.retornaDato();
                // f = t.retornaFila();
                // c = t.retornaColumna();
                // v = t.retornaValor();
                // escriba(f, c, v);
                escriba(t, i);
                i++;
                q = q.retornaLD();
            }
            t = (Tripleta) p.retornaDato();
            p = (NodoDoble) t.retornaValor();
            i++;
        }
    }

    public void recorrePorColumnas() { // Modificado
        Tripleta t = (Tripleta) this.mat.retornaDato();
        NodoDoble p = (NodoDoble) t.retornaValor();
        NodoDoble q;
        int i = 1;
        // int f, c;
        // Object v;
        while (p != this.mat) {
            q = p.retornaLI();
            while (q != p) {
                t = (Tripleta) q.retornaDato();
                // f = t.retornaFila();
                // c = t.retornaColumna();
                // v = t.retornaValor();
                // escriba(f, c, v);
                escriba(t, i);
                i++;
                q = q.retornaLI();
            }
            t = (Tripleta) p.retornaDato();
            p = (NodoDoble) t.retornaValor();
            i++;
        }
    }

    public void muestraMatrizEnForma1(boolean i) {
        Plantilla plantilla = new Plantilla();
        plantilla.mostrar_Mt();
        if (i) {
            recorrePorFilas();
        } else {
            recorrePorColumnas();
        }
    }

    public void escriba(Tripleta t, int i) { // Original
        Plantilla plantilla = new Plantilla();
        plantilla.mostrar_Mt(i, t.retornaFila(), t.retornaColumna(), t.retornaValor());
    }

    // Original
    public void imprimeMatriz() {
        Tripleta t = (Tripleta) this.mat.retornaDato();
        int filas = t.retornaFila();
        int columnas = t.retornaColumna();
        NodoDoble p = (NodoDoble) t.retornaValor();
        NodoDoble q = p.retornaLD();
        int fq, cq;
        boolean sw = false;
        for (int f = 1; f <= filas; f++) {
            for (int c = 1; c <= columnas; c++) {
                while (p != this.mat) {
                    while (q != p) {
                        t = (Tripleta) q.retornaDato();
                        fq = t.retornaFila();
                        cq = t.retornaColumna();
                        if (fq == f && cq == c) {
                            sw = true;
                            System.out.print(((Tripleta) q.retornaDato()).retornaValor() + "\t");
                            break;
                        }
                        q = q.retornaLD();
                    }
                    t = (Tripleta) p.retornaDato();
                    p = (NodoDoble) t.retornaValor();
                    q = p.retornaLD();
                }
                if (!sw) {
                    System.out.print("0\t");
                }
                sw = false;
                t = (Tripleta) p.retornaDato();
                p = (NodoDoble) t.retornaValor();
                q = p.retornaLD();
            }
            System.out.println("");
        }
    }

    public int retornaNumeroTripletas() {
        int cantidad = 0;
        Tripleta t = (Tripleta) this.mat.retornaDato();
        NodoDoble p = (NodoDoble) t.retornaValor();
        while (p != this.mat) {
            t = (Tripleta) p.retornaDato();
            cantidad += t.retornaFila();
            p = (NodoDoble) t.retornaValor();
        }
        return cantidad;
    }

    public int retornaNumeroFilas() {
        Tripleta t = (Tripleta) this.mat.retornaDato();
        return t.retornaFila();
    }

    public int retornaNumeroColumnas() {
        Tripleta t = (Tripleta) this.mat.retornaDato();
        return t.retornaColumna();
    }

    public Tripleta retornaTripleta(int i) {
        Tripleta t = (Tripleta) this.mat.retornaDato();
        NodoDoble p = (NodoDoble) t.retornaValor();
        NodoDoble q = p.retornaLD();
        int k = 0;
        Tripleta tk = null;
        if(i == 0){
            tk = new Tripleta(t.retornaFila(), t.retornaColumna(), 0);
            return tk;
        }
        while (p != this.mat && k < i) {
            while (q != p && k < i) {
                tk = (Tripleta) q.retornaDato();
                q = q.retornaLD();
                k++;
            }
            t = (Tripleta)p.retornaDato();
            p = (NodoDoble) t.retornaValor();
            q = p.retornaLD();
        }
        return tk;
    }

    public void borrarDato(int f, int c){
        if(existe(f,c)){
        borrarPorFila(f,c);
        borrarPorColumna(f,c);
        }
    }

    public void borrarPorFila(int f, int c){
        Tripleta t = (Tripleta)this.mat.retornaDato();
        NodoDoble p = (NodoDoble) t.retornaValor();
        for (int i = 1; i < f; i++) {
            t = (Tripleta)p.retornaDato();
            p = (NodoDoble)t.retornaValor();
        }
        t = (Tripleta) p.retornaDato();
        t.asignaFila(t.retornaFila() - 1);
        p.asignaDato(t);
        NodoDoble q = p.retornaLD();
        NodoDoble aq = p;
        while (true){
            t = (Tripleta)q.retornaDato();
            if(t.retornaColumna() == c){
                break;
            }
            aq = q;
            q = q.retornaLD();
        }
        aq.asignaLD(q.retornaLD());
    }

    public void borrarPorColumna(int f, int c){
        Tripleta t = (Tripleta)this.mat.retornaDato();
        NodoDoble p = (NodoDoble) t.retornaValor();
        for (int i = 1; i < c; i++) {
            t = (Tripleta)p.retornaDato();
            p = (NodoDoble)t.retornaValor();
        }
        t = (Tripleta) p.retornaDato();
        t.asignaColumna(t.retornaColumna() - 1);
        NodoDoble q = p.retornaLI();
        NodoDoble aq = p;
        while (true){
            t = (Tripleta)q.retornaDato();
            if(t.retornaFila() == f){
                break;
            }
            aq = q;
            q = q.retornaLI();
        }
        aq.asignaLI(q.retornaLI());
    }

    public boolean existe(int f, int c){
        Tripleta t = (Tripleta) this.mat.retornaDato();
        NodoDoble p = (NodoDoble) t.retornaValor();
        NodoDoble q;
        while (p != this.mat) {
            q = p.retornaLD();
            while (q != p) {
                t = (Tripleta) q.retornaDato();
                if(f == t.retornaFila() && c == t.retornaColumna()){
                    return true;
                }
                q = q.retornaLD();
            }
            t = (Tripleta) p.retornaDato();
            p = (NodoDoble) t.retornaValor();
        }
        return false;
    }

    public int mayor(int a, int b) {  // Original
        return a > b ? a : b;
    }
}