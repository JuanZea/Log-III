package Modelo;

import Vistas.Plantilla;

/**
 * @author JuanZea
 */
public class MatrizEnTripletas {

    private Tripleta[] V;

    // Modificado
    public MatrizEnTripletas(Tripleta t) {
        int n = t.retornaColumna();
        int m = t.retornaFila();
        int p = n * m + 1;
        // se usa en docs
        // int i;
        V = new Tripleta[p];
        V[0] = t;
        // se usa en docs
        /* for (i = 1; i <= p; i++) {
        }*/
    }

    public MatrizEnTripletas(int m, int n) {
        int p = m * n;
        Tripleta[] V = new Tripleta[p + 1];
        V[0] = new Tripleta(m, n, 0);
        this.V = V;
    }

    public void asignaNumeroTripletas(int n) {
        this.V[0].asignaValor(n);
    }

    public void asignaTripleta(Tripleta t, int i) {
        this.V[i] = t;
    }

    public int retornaNumeroFilas() {
        return this.V[0].retornaFila();
    }

    public int retornaNumeroColumnas() {
        return this.V[0].retornaColumna();
    }

    public int retornaNumeroTripletas() {
        return (int) this.V[0].retornaValor();
    }

    public Tripleta retornaTripleta(int i) {
        return this.V[i];
    }

    // Modificado
    public void muestraMatrizEnTripletas() {
        Plantilla plantilla = new Plantilla();
        plantilla.mostrar_Mt();
        for (int i = 1; i <= (int) this.V[0].retornaValor(); i++) {
            plantilla.mostrar_Mt(i, this.V[i].retornaFila(), this.V[i].retornaColumna(), this.V[i].retornaValor());
        }
    }

    // Original
    public void imprimeMatriz() {
        for (int f = 1; f <= this.V[0].retornaFila(); f++) {
            for (int c = 1; c <= this.V[0].retornaColumna(); c++) {
                for (int i = 1; i <= (int) this.V[0].retornaValor(); i++) {
                    if (f == this.V[i].retornaFila() && c == this.V[i].retornaColumna()) {
                        System.out.print(this.V[i].retornaValor() + "\t");
                        break;
                    } else if (i == (int) this.V[0].retornaValor()) {
                        System.out.print("0\t");
                        break;
                    }
                }
            }
            System.out.println("");
        }
    }

    // Defectuoso (83)
    public void insertaTripleta(Tripleta t) {
        int f = t.retornaFila();
        int c = t.retornaColumna();
        int i = 0;
        int p = (int) this.V[0].retornaValor();
        while (i <= p && f > this.V[i].retornaFila()) {
            i++;
        }
        while (i <= p && f == this.V[i].retornaFila() && c > this.V[i].retornaColumna()) {
            i++;
        }
        int j = p;
        while (j >= i) {
            this.V[j + 1] = this.V[j];
            j--;
        }
        this.V[i] = t;
        p++;
        this.V[0].asignaValor(p);
    }

    /*
     *  i comienza en 1, para que las tripletas a insertar no se comparen con V[0]
     */
    public void insertaTripleta_v2(Tripleta t) {
        int f = t.retornaFila();
        int c = t.retornaColumna();
        int i = 1;
        int p = (int) this.V[0].retornaValor();
        while (i <= p && f > this.V[i].retornaFila()) {
            i++;
        }
        while (i <= p && f == this.V[i].retornaFila() && c > this.V[i].retornaColumna()) {
            i++;
        }
        int j = p;
        while (j >= i) {
            this.V[j + 1] = this.V[j];
            j--;
        }
        this.V[i] = t;
        p++;
        this.V[0].asignaValor(p);
    }

    public MatrizEnTripletas suma(MatrizEnTripletas b) {
        return null;
    }

    public MatrizEnTripletas multiplicar(MatrizEnTripletas b) {
        return null;
    }

    // Modificado (140)
    public MatrizEnTripletas transpuesta_obsoleto() {
        int m = this.V[0].retornaFila();
        int n = this.V[0].retornaColumna();
        int p = (int) this.V[0].retornaValor();
        Tripleta tx = new Tripleta(n, m, 0);
        MatrizEnTripletas at = new MatrizEnTripletas(tx);
        int i = 1;
        while (i <= p) {
            int f = this.V[i].retornaFila();
            int c = this.V[i].retornaColumna();
            Object v = this.V[i].retornaValor();
            Tripleta t = new Tripleta(c, f, v);
            at.insertaTripleta_v2(t);
            i++;
        }
        return at;
    }

    // Modificado (165)
    public MatrizEnTripletas transpuesta() {
        int m = this.V[0].retornaFila();
        int n = this.V[0].retornaColumna();
        Object p = this.V[0].retornaValor();
        Tripleta t = new Tripleta(n, m, p);
        MatrizEnTripletas at = new MatrizEnTripletas(t);
        int[] s = new int[n + 1];
        for (int i = 1; i <= (int) p; i++) {
            s[this.V[i].retornaColumna()] = s[this.V[i].retornaColumna()] + 1;

        }
        int[] pos = new int[n + 1];
        pos[0] = 1;
        for (int i = 1; i <= n; i++) {
            pos[i] = pos[i - 1] + s[i - 1];
        }
        for (int i = 1; i <= (int) p; i++) {
            int f = this.V[i].retornaFila();
            int c = this.V[i].retornaColumna();
            Object v = this.V[i].retornaValor();
            t = new Tripleta(c, f, v);
            int k = pos[c];
            at.asignaTripleta(t, k);
            pos[c] = pos[c] + 1;
        }
        return at;
    }

    public MatrizEnTripletas transpuestaMedia() {
        return null;
    }

    public MatrizEnTripletas transpuestaRapida() {
        return null;
    }

    public void borrarDato(int f, int c) {
        Tripleta t;
        int pos = 0;
        for (int i = 1; i <= this.retornaNumeroTripletas(); i++) {
            t = this.retornaTripleta(i);
            if (t.retornaFila() == f && t.retornaColumna() == c) {
                pos = i;
                break;
            }
        }
        if(pos == 0){
            return;
        }
        t = this.retornaTripleta(0);
        int cantidad = (int)t.retornaValor();
        t.asignaValor(cantidad - 1);
        this.asignaTripleta(t, 0);
        for (int i = pos; i <= this.retornaNumeroTripletas(); i++) {
            if(this.retornaTripleta(i+1) == null){
                this.asignaTripleta(null,i);
                return;
            }
            t = this.retornaTripleta(i+1);
            this.asignaTripleta(t,i);
        }
    }

    public int sumarTriangular_SupI() {
        if (!this.esCuadrada()) {
            return -1;
        }
        int acumulado = 0;
        int n = this.V[0].retornaFila();
        for (int i = 1; i <= n; i++) {
            if (this.V[i].retornaFila() + this.V[i].retornaColumna() <= (n + 1)) {
                acumulado += Integer.parseInt((String) this.V[i].retornaValor()) ;
            }
        }
        return acumulado;
    }

    public int sumarTriangular_InfD() {
        if (!this.esCuadrada()) {
            return -1;
        }
        int acumulado = 0;
        int n = this.V[0].retornaFila();
        for (int i = 1; i <= n; i++) {
            if (this.V[i].retornaFila() + this.V[i].retornaColumna() >= (n + 1)) {
                acumulado += (int) this.V[i].retornaValor();
            }
        }
        return acumulado;
    }

    public boolean esCuadrada() {
        return this.V[0].retornaFila() == this.V[0].retornaColumna();
    }
}