package Modelo;

import Vistas.Plantilla;

public class MatrizEnForma2 {
    NodoDoble mat;

    public MatrizEnForma2(int m, int n) {
        Tripleta t = new Tripleta(m, n, 0);
        mat = new NodoDoble(t);
        Tripleta tx = new Tripleta(null, null, null);
        NodoDoble x = new NodoDoble(tx);
        x.asignaLD(x);
        x.asignaLI(x);
        mat.asignaLD(x);
    }

    public void conectaPorFilas(NodoDoble x) {
        NodoDoble p, q, anterior;
        Tripleta tq, tx;
        int i;
        tx = (Tripleta) x.retornaDato();
        p = this.nodoCabeza();
        anterior = p;
        q = p.retornaLD();
        tq = (Tripleta) q.retornaDato();
        while (q != p && tq.retornaFila() < tx.retornaFila()) {
            anterior = q;
            q = q.retornaLD();
            tq = (Tripleta) q.retornaDato();
        }
        while (q != p && tq.retornaFila() == tx.retornaFila() && tq.retornaColumna() < tx.retornaColumna()) {
            anterior = q;
            q = q.retornaLD();
            tq = (Tripleta) q.retornaDato();
        }
        anterior.asignaLD(x);
        x.asignaLD(q);
//        p = this.mat.retornaLD();
//        tx = (Tripleta) p.retornaDato();
//        tx.asignaValor((int) tx.retornaValor() + 1);
//        this.mat.asignaDato(tx);
    }

    public void conectaPorColumnas(NodoDoble x) {
        NodoDoble p, q, anterior;
        Tripleta tq, tx;
        int i;
        tx = (Tripleta) x.retornaDato();
        p = this.nodoCabeza();
        anterior = p;
        q = p.retornaLI();
        tq = (Tripleta) q.retornaDato();
        while (q != p && tq.retornaColumna() < tx.retornaColumna()) {
            anterior = q;
            q = q.retornaLI();
            tq = (Tripleta) q.retornaDato();
        }
        while (q != p && tq.retornaColumna() == tx.retornaColumna() && tq.retornaFila() < tx.retornaFila()) {
            anterior = q;
            q = q.retornaLI();
            tq = (Tripleta) q.retornaDato();
        }
        anterior.asignaLI(x);
        x.asignaLI(q);
    }

    public NodoDoble primerNodo() {
        return mat;
    }

    public NodoDoble nodoCabeza() {
        return mat.retornaLD();
    }

    public boolean esVacia() {
        NodoDoble p = mat.retornaLD();
        return p.retornaLI() == p && p.retornaLD() == p; // ¿Con una no es suficiente?
    }

    public boolean finDeRecorrido(NodoDoble p) {
        return p == this.nodoCabeza();
    }

    public void muestraMatriz() { // Modificado
        // int qf, qc, qv;
        Plantilla plantilla = new Plantilla();
        plantilla.mostrar_Mt();
        NodoDoble q;
        Tripleta tq;
        q = nodoCabeza().retornaLD();
        int i = 1;
        while (!finDeRecorrido(q)) {
            tq = (Tripleta) q.retornaDato();
            // qf = tq.retornaFila();
            // qc = tq.retornaColumna();
            // qv = (int)tq.retornaValor();
            // escribe(qf, qc, qv);
            escribe(tq, i);
            i++;
            q = q.retornaLD();
        }
    }

    public void imprimeMatriz() {
        Tripleta t = (Tripleta) this.mat.retornaDato();
        int filas = t.retornaFila();
        int columnas = t.retornaColumna();
        int fp, cp;
        NodoDoble p = this.mat.retornaLD().retornaLD();
        boolean sw = false;
        for (int f = 1; f <= filas; f++) {
            for (int c = 1; c <= columnas; c++) {
                while (!this.finDeRecorrido(p)) {
                    t = (Tripleta) p.retornaDato();
                    fp = t.retornaFila();
                    cp = t.retornaColumna();
                    if (fp == f && cp == c) {
                        sw = true;
                        System.out.print(t.retornaValor() + "\t");
                        break;
                    }
                    p = p.retornaLD();
                }
                if (!sw) {
                    System.out.print("0\t");
                }
                sw = false;
                p = this.mat.retornaLD().retornaLD();
            }
            System.out.println("");
        }
    }

    public int retornaNumeroTripletas() {
        int cantidad = 1;
        NodoDoble p = this.mat.retornaLD().retornaLD().retornaLD();
        while (!this.finDeRecorrido(p)) {
            cantidad++;
            p = p.retornaLD();
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
        NodoDoble p = this.mat.retornaLD();
        if (i == 0) {
            Tripleta t = (Tripleta) this.mat.retornaDato();
            Tripleta tk = new Tripleta(t.retornaFila(), t.retornaColumna(), 0);
            return tk;
        }
        for (int j = 0; j < i; j++) {
            p = p.retornaLD();
        }
        Tripleta t = (Tripleta) p.retornaDato();
        return t;
    }

    public void borrarDato(int f, int c) {
        if (existe(f, c)) {
            borrarPorFila(f, c);
            borrarPorColumna(f, c);
        }
    }

    public void borrarPorFila(int f, int c) {
        NodoDoble p = (NodoDoble) this.mat.retornaLD().retornaLD();
        NodoDoble ap = (NodoDoble) this.mat.retornaLD();
        Tripleta t;
        while (!this.finDeRecorrido(p)) {
            t = (Tripleta) p.retornaDato();
            if (f == t.retornaFila() && c == t.retornaColumna()) {
                break;
            }
            ap = p;
            p = p.retornaLD();
        }
        ap.asignaLD(p.retornaLD());
    }

    public void borrarPorColumna(int f, int c) {
        NodoDoble p = (NodoDoble) this.mat.retornaLD().retornaLI();
        NodoDoble ap = (NodoDoble) this.mat.retornaLD();
        Tripleta t;
        while (!this.finDeRecorrido(p)) {
            t = (Tripleta) p.retornaDato();
            if (f == t.retornaFila() && c == t.retornaColumna()) {
                break;
            }
            ap = p;
            p = p.retornaLI();
        }
        ap.asignaLI(p.retornaLI());
    }

    public boolean existe(int f, int c) {
        NodoDoble p = (NodoDoble) this.mat.retornaLD().retornaLD();
        Tripleta t;
        while (!this.finDeRecorrido(p)) {
            t = (Tripleta) p.retornaDato();
            if (f == t.retornaFila() && c == t.retornaColumna()) {
                return true;
            }
            p = p.retornaLD();
        }
        return false;
    }

    public void escribe(Tripleta t, int i) {
        Plantilla plantilla = new Plantilla();
        plantilla.mostrar_Mt(i, t.retornaFila(), t.retornaColumna(), t.retornaValor());
    }
}