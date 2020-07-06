package Modelos;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MatrizDeAdyacencia {
    
    
    private MatrizForma1 p;
    private int lados = 0;
    
    public MatrizDeAdyacencia(MatrizForma1 p, int lados) {
        this.p = p;
        this.lados = lados;
    }
    
    public MatrizDeAdyacencia(int n) {
        this.p = new MatrizForma1(n, n);
        p.contruyeNodosCabeza();
    }
    
    public MatrizForma1 retornaM1() {
        return p;
    }
    
    public int retornaNv() {
        return this.p.retornaNumeroFilas();
    }
    
    public ArrayList<Integer> retornaArticulaciones() {
        ArrayList articulaciones = new ArrayList();
        ArrayList componentes = new ArrayList();
        ArrayList componente;
        for (int i = 1; i <= this.retornaNv(); i++) {
            componente = this.DFS(i);
            if (!this.pertenece(componentes, componente)) {
                componentes.add(componente);
            }
        }
        int numComponentes = componentes.size();
        for (int i = 1; i <= this.retornaNv(); i++) {
            if (esArticulacion(i, numComponentes)) {
                articulaciones.add(i);
            }
        }
        return articulaciones;
    }
    
    public boolean esArticulacion(int vt, int componentes) {
        return this.componentesResultantes(vt) > componentes;
    }
    
    public int componentesResultantes(int pos) {
        ArrayList componentes = new ArrayList();
        ArrayList componente;
        for (int i = 1; i <= this.retornaNv(); i++) {
            if (i != pos) {
                componente = this.p.DFS_art(i, new ArrayList(), pos);
                if (!this.pertenece(componentes, componente)) {
                    componentes.add(componente);
                }
            }
        }
        return componentes.size();
    }
    
    public boolean pertenece(ArrayList<ArrayList> U, ArrayList x) {
        if (U.isEmpty()) {
            return false;
        }
        ArrayList temp;
        for (int i = 0; i < U.size(); i++) {
            temp = U.get(i);
            if (this.isomorfo(temp, x)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isomorfo(ArrayList x, ArrayList y) {
        if (x.size() != y.size()) {
            return false;
        }
        for (int i = 0; i < x.size(); i++) {
            if (!y.contains(x.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    public void agregarConexion(int i, int j, int price) {
        Tripleta t = new Tripleta(i, j, price);
        NodoDoble x = new NodoDoble(t);
        this.p.conectaPorFilas(x);
        this.p.conectaPorColumnas(x);
        t = new Tripleta(j, i, price);
        x = new NodoDoble(t);
        this.p.conectaPorFilas(x);
        this.p.conectaPorColumnas(x);
        this.lados++;
    }
    
    public ArrayList DFS(int v) {
        ArrayList<Integer> l = new ArrayList<>();
        ArrayList<Integer> o;
        o = this.p.DFS(v, l);
        return o;
    }
    
    public ArrayList BFS(int v) {
        ArrayList<Integer> l = new ArrayList<>();
        ArrayList<Integer> o = new ArrayList<>();
        o = this.p.BFS(v, l, o);
        return o;
    }
    
    public boolean esConectado() {
        int v = 1;
        ArrayList<Integer> l = new ArrayList<>();
        ArrayList<Integer> o;
        o = this.p.DFS(v, l);
        return o.size() == this.p.retornaNumeroFilas();
    }
    
    public int retornaLados() {
        return this.lados;
    }
    
    public boolean esCiclico() {
        return retornaLados() >= this.p.retornaNumeroFilas();
    }
    
    public boolean esLibre() {
        return esConectado() && !esCiclico();
    }
    
    public ArrayList puntosdeArticulacion() {
        ArrayList<Integer> j = new ArrayList<>();
        MatrizDeAdyacencia l = this;
        for (int i = 0; i < this.retornaNv(); i++) {
            l.retornaM1().eliminarNodoCabeza(i);
            if (l.esConectado()) {
                j.add(i);
            }
            i++;
        }
        return j;
    }
    
    public void prim(int inicio) {
        int[] conectados = new int[this.retornaNv() + 1];
        int conMax, desMax, con, des,baratoTemp;
        int barato = 2147483647; // Infinito
        conectados[inicio] = 1;
        for (int i = 1; i <= this.retornaNv(); i++) {
            if (conectados[i] == 1) {
                con = conectados[i];
                for (int j = 1; j <= this.retornaNv(); j++) {
                    if (conectados[j] == 0) {
                        des = conectados[j];
                        baratoTemp = this.precio(con, des);
                        if(baratoTemp < barato){
                            barato = baratoTemp;
                            conMax = con;
                            desMax = des;
                        }
                    }
                }
            }
            System.out.println(":D");
        }
        
        
//        ArrayList adyacentes = this.p.adyacentes(inicio);
//        conectados[pos] = 1;
    }
    
    public int precio(int a, int b){
        int precio = this.p.retornaDato(a,b);
        if(precio == -1){
            return 2147483647;
        } else {
            return precio;
        }
    }
    
//    public int masBarato(int inicio, int adyacente) {
//        int costo = 2147483647; // Infinito
//        int costoTemp, vt = -1;
//        for (int i = 0; i < adyacentes.size(); i++) {
//            costoTemp = this.p.retornaDato(inicio, (int) adyacentes.get(i));
//            if (costo > costoTemp) {
//                costo = costoTemp;
//                vt = (int) adyacentes.get(i);
//            }
//        }
//        return vt;
//    }
}
