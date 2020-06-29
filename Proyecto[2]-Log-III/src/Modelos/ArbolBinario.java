package Modelos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class ArbolBinario {
    private NodoDoble raiz;

    public ArbolBinario() {

    }

    public ArbolBinario(NodoDoble r) {
        this.raiz = r;
    }

    public void construirArbolAleatorio(String[] arreglo) {
        if (arreglo == null) {
            return;
        }
        this.raiz = new NodoDoble(arreglo[0]);
        Random rnd = new Random();
        NodoDoble p = this.raiz;
        NodoDoble ap = p;
        Boolean d = null;
        for (int i = 1; i < arreglo.length; i++) {
            while (p != null) {
                if (rnd.nextBoolean()) {
                    ap = p;
                    d = true;
                    p = p.retornaLD();
                } else {
                    ap = p;
                    d = false;
                    p = p.retornaLI();
                }
            }
            p = new NodoDoble(arreglo[i]);
            if (d == null) {
                return;
            }
            if (d) {
                ap.asignaLD(p);
            } else {
                ap.asignaLI(p);
            }
            p = this.raiz;
        }
    }

    public NodoDoble construirArbolVector(String[] vector, int i) {
        NodoDoble r = new NodoDoble(vector[i]);
        NodoDoble izq, der;
        try {
            izq = construirArbolVector(vector, this.retornaHijoIzquierdo(i));
            if (izq.retornaDato() != null) {
                r.asignaLI(izq);
            }
        } catch (Exception e) {
            // Nada
        }
        try {
            der = construirArbolVector(vector, this.retornaHijoDerecho(i));
            if (der.retornaDato() != null) {
                r.asignaLD(der);
            }
        } catch (Exception e) {
            // Nada
        }
        this.raiz = r;
        return r;
    }

    public void construirArbolBusqueda(Integer[] datos) {
        if (datos == null) {
            return;
        }
        this.raiz = new NodoDoble(datos[0]); // Asigna primer dato a la raíz
        NodoDoble p = this.raiz;  // Nodo que hace el recorrido por el árbol
        NodoDoble ap = p; // Nodo que sera el anterior a p
        NodoDoble x; // Nodo que alojara el nuevo dato
        for (int i = 1; i < datos.length; i++) {
            while (p != null) {
                if (datos[i] > (int) p.retornaDato()) {
                    ap = p;
                    p = p.retornaLD();
                } else {
                    ap = p;
                    p = p.retornaLI();
                }
            }
            x = new NodoDoble(datos[i]);
            if (datos[i] > (int) ap.retornaDato()) {
                ap.asignaLD(x);
            } else {
                ap.asignaLI(x);
            }
            p = this.raiz;
        }
    }

    public int retornaHijoIzquierdo(int i) {
        int pos = 2 * (i + 1);
        return pos - 1;
    }

    public int retornaHijoDerecho(int i) {
        int pos = 2 * (i + 1) + 1;
        return pos - 1;
    }

    public NodoDoble retornaRaiz() {
        return this.raiz;
    }

    public String inOrden(NodoDoble r) {
        if (r == null) {
            return "";
        }
        String cadena;
        cadena = inOrden(r.retornaLI()) + " " + r.retornaDato() + " " + inOrden(r.retornaLD());
        return cadena;
    }

    public String posOrden(NodoDoble r) {
        if (r == null) {
            return "";
        }
        String cadena;
        cadena = posOrden(r.retornaLI()) + " " + posOrden(r.retornaLD()) + " " + r.retornaDato();
        return cadena;
    }

    public String preOrden(NodoDoble r) {
        if (r == null) {
            return "";
        }
        String cadena;
        cadena = r.retornaDato() + " " + preOrden(r.retornaLI()) + " " + preOrden(r.retornaLD());
        return cadena;
    }

    public int altura() {
        if (this.raiz.retornaLI() == null) {
            return 1;
        }
        int altura = 2;
        int h = 0;
        NodoDoble r = this.raiz.retornaLI();
        Stack pila = new Stack();
        ArrayList<Integer> subAlturas = new ArrayList<>();
        while (r != null) {
            if (r.retornaLI() != null) {
                h++;
                pila.push(r);
                r = r.retornaLI();
            } else {
                r = r.retornaLD();
            }
            while (r == null && !pila.empty()) {
                r = (NodoDoble) pila.pop();
                r = r.retornaLD();
                subAlturas.add(h);
                h = 0;
            }
        }
        h = 0;
        for (int i = 0; i < subAlturas.size(); i++) {
            h = (subAlturas.get(i) > h) ? subAlturas.get(i) : h;
        }
        return altura + h;
    }

    public int hojas() {
        if (this.raiz == null) {
            return 0;
        }
        NodoDoble r = this.raiz;
        int hojas = 0;
        Stack pila = new Stack();
        while (r != null) {
            if (r.retornaLI() != null) {
                pila.push(r);
                r = r.retornaLI();
            } else {
                hojas++;
                r = r.retornaLD();
            }
            while (r == null && !pila.empty()) {
                r = (NodoDoble) pila.pop();
                r = r.retornaLD();
            }
        }
        return hojas;
    }

    public int grado() {
        if (this.raiz == null || this.raiz.retornaLI() == null) {
            return 0;
        }
        int grado = 1;
        NodoDoble p = this.raiz.retornaLI();
        Stack pilaN = new Stack();
        Stack pilaG = new Stack();
        ArrayList<Integer> grados = new ArrayList<>();
        while (p != null) {
            if (p.retornaLI() != null) {
                pilaN.push(p);
                pilaG.push(grado);
                grado = 1;
                p = p.retornaLI();
            } else {
                p = p.retornaLD();
                grado++;
            }
            while (p == null & !pilaN.empty()) {
                grados.add(grado - 1);
                grado = (int) pilaG.pop();
                p = (NodoDoble) pilaN.pop();
                p = p.retornaLD();
                grado++;
            }
        }
        grados.add(grado - 1);
        grado = 0;
        for (int i = 0; i < grados.size(); i++) {
            grado = (grados.get(i) > grado) ? grados.get(i) : grado;
        }
        return grado;
    }

    public String distancia(Integer hoja1, Integer hoja2) {
        if (hoja1 == null && hoja2 == null) {
            return distancias();
        }
        if (hoja1 == hoja2) {
            return "0";
        }
        ArrayList camino1r = camino(this.raiz, hoja1);
        ArrayList camino2r = camino(this.raiz, hoja2);
        ArrayList camino1 = new ArrayList();
        ArrayList camino2 = new ArrayList();
        for (int i = camino1r.size() - 1; i >= 0; i--) {
            camino1.add(camino1r.get(i));
        }
        for (int i = camino2r.size() - 1; i >= 0; i--) {
            camino2.add(camino2r.get(i));
        }

        int x = 0;
        for (int i = 0; i < camino1.size(); i++) {
            if (camino1.get(i) != camino2.get(i)) {
                break;
            }
            x++;
        }
        int n1 = camino1.size() - x;
        int n2 = camino2.size() - x;
        x = n1 + n2;
        return String.valueOf(x);
    }

    public String distancias() {
        if (this.raiz.retornaDato() == null) {
            return null;
        }
        ArrayList<String> hojas = new ArrayList<>();
        Stack pila = new Stack(); // pila hojas
        NodoDoble h = this.raiz;
        while (h != null) {
            if (h.retornaLI() != null) {
                pila.push(h);
                h = h.retornaLI();
                continue;
            }
            if (h.retornaLD() != null) {
                h = h.retornaLD();
                continue;
            }
            hojas.add(String.valueOf(h.retornaDato()));
            try {
                h = (NodoDoble) pila.pop();
            } catch (Exception e) {
                // Nada por acá
            }
            h = h.retornaLD();
            while (h == null && !pila.empty()) {
                h = (NodoDoble) pila.pop();
                h = h.retornaLD();
            }
        }
        ArrayList<String> parejas = new ArrayList();
        String pareja;
        for (int i = 0; i < hojas.size(); i++) {
            for (int j = i; j < hojas.size(); j++) {
                pareja = hojas.get(i) + "-" + hojas.get(j);
                parejas.add(pareja);
            }
        }
        int x, y;
        pareja = "";
        for (int i = 0; i < parejas.size(); i++) {
            x = Integer.parseInt(parejas.get(i).substring(0, parejas.get(i).indexOf('-')));
            y = Integer.parseInt(parejas.get(i).substring(parejas.get(i).indexOf('-') + 1));
            pareja += x + "<->" + y + ": " + distancia(x, y) + "\n";
        }
        return pareja;
    }

    public ArrayList<Boolean> camino(NodoDoble raiz, Integer hoja) {
        if (raiz == null) {
            return null;
        }
        if (this.raiz.retornaDato() == hoja || raiz.retornaDato() == hoja) {
            return new ArrayList<>();
        }
        try{
            if (this.raiz.retornaDato() == hoja || Integer.parseInt((String) raiz.retornaDato()) == hoja) {
            return new ArrayList<>();
        }
        }catch (Exception e){
            // Nada
        }
        NodoDoble h = raiz;
        ArrayList<Boolean> instrucciones = new ArrayList();
        ArrayList<Boolean> instruccionesTemp = new ArrayList();
        Stack pila = new Stack(); // pila hojas
        pila.push(h);
        h = h.retornaLI();
        instruccionesTemp = camino(h, hoja);
        if (instruccionesTemp != null) {
            instrucciones = instruccionesTemp;
            instrucciones.add(false);
            return instrucciones;
        }
        h = (NodoDoble) pila.pop();
        h = h.retornaLD();
        instruccionesTemp = camino(h, hoja);
        if (instruccionesTemp != null) {
            instrucciones = instruccionesTemp;
            instrucciones.add(true);
            return instrucciones;
        }
        return null;
    }
}
