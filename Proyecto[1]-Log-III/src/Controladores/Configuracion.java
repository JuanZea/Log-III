package Controladores;

import Modelo.*;
import Vistas.Plantilla;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Configuracion {

    private Plantilla plantilla;
    private ArrayList configuracion;

    private ArrayList[] inventario;

    private ArrayList[] inventario_nombres;

    public Configuracion() {
        this.plantilla = new Plantilla();
        configuracion = new ArrayList();
        inventario = new ArrayList[4];
        inventario[0] = new ArrayList<>();
        inventario[1] = new ArrayList<MatrizEnTripletas>();
        inventario[2] = new ArrayList<MatrizEnForma1>();
        inventario[3] = new ArrayList<MatrizEnForma2>();
        inventario_nombres = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            inventario_nombres[i] = new ArrayList<String>();
        }
        // Configuración por defecto
        configuracion.add(1);
        configuracion.add(false);
        configuracion.add(false);
        configuracion.add(false);
        configuracion.add(true);
    }

    public void defecto() {
        configuracion.set(0, 1);
        configuracion.set(1, false);
        configuracion.set(2, false);
        configuracion.set(3, false);
        configuracion.set(4, true);
    }

    public void crear() {
        // Casos posibles: 8 - Permitidos: 6
        boolean g, d, c;
        g = (boolean) this.configuracion.get(1);
        d = (boolean) this.configuracion.get(2);
        c = (boolean) this.configuracion.get(3);
        ArrayList<Tripleta> tripletas = null;
        int[] dim = new int[2];
        int cantidad = -1;

        // 0
        if (!g && !d && !c) {
            dim = this.generar_dimension(7); // Semilla
            cantidad = this.generar_cantidad(dim[0] * dim[1]);
            tripletas = this.generar_datos(dim[0], dim[1], cantidad);
        }
        // 1
        if (g && !d && !c) {
            tripletas = this.escuchar_datos(null);
            if (tripletas == null) {
                return;
            }
            dim[0] = tripletas.get(0).retornaFila();
            dim[1] = tripletas.get(0).retornaColumna();
            cantidad = tripletas.size() - 1;
        }
        if (!g && d && !c) {
            this.plantilla.dimension();
            dim = this.escuchar_dimension();
            if (dim == null) {
                return;
            }
            cantidad = this.generar_cantidad(dim[0] * dim[1]);
            tripletas = this.generar_datos(dim[0], dim[1], cantidad);
        }
        if (!g && !d && c) {
            cantidad = this.escuchar_cantidad(null);
            if (cantidad == 0) {
                return;
            }
            dim = this.generar_dimension(cantidad);
            tripletas = this.generar_datos(dim[0], dim[1], cantidad);
        }
        // 2
        if (g && d && !c) {
            this.plantilla.dimension();
            dim = this.escuchar_dimension();
            if (dim == null) {
                return;
            }
            tripletas = this.escuchar_datos(dim);
            if (tripletas == null) {
                return;
            }
            cantidad = tripletas.size() - 1;
        }
        if (!g && d && c) {
            this.plantilla.dimension();
            dim = this.escuchar_dimension();
            if (dim == null) {
                return;
            }
            cantidad = this.escuchar_cantidad(dim);
            if (cantidad == 0) {
                return;
            }
            tripletas = this.generar_datos(dim[0], dim[1], cantidad);
        }
        switch ((int) this.configuracion.get(0)) {
            default:
                break;
            case 1: {
                this.crear_MatrizEnTripletas(cantidad, dim, tripletas, false);
                break;
            }
            case 2: {
                this.crear_MatrizEnForma1(cantidad, dim, tripletas, false);
                break;
            }
            case 3: {
                this.crear_MatrizEnForma2(cantidad, dim, tripletas, false);
                break;
            }
        }
    } // Original

    public boolean crear_MatrizEnTripletas(int cantidad, int[] dim, ArrayList<Tripleta> tripletas, boolean convertida) {
        this.plantilla.previa(1, cantidad, dim);
//        MatrizEnTripletas m = new MatrizEnTripletas(dim[0],dim[1]); // Constructor (int m, int n)
        MatrizEnTripletas m = new MatrizEnTripletas(tripletas.get(0));  // Constructor (Tripleta t)
        for (int i = 1; i < tripletas.size(); i++) {
            m.insertaTripleta_v2(tripletas.get(i));
        }
        this.plantilla.nombre();
        String nombre = this.escuchar_nombre();
        if (nombre == null) {
            if (convertida) {
                this.plantilla.convertida(false);
            } else {
                this.plantilla.creada(false);
            }
            return false;
        }
        this.guardar(m, nombre);
        if (convertida) {
            this.plantilla.convertida(true);
        } else {
            this.plantilla.creada(true);
        }
        return true;
    }

    public boolean crear_MatrizEnForma1(int cantidad, int[] dim, ArrayList<Tripleta> tripletas, boolean convertida) {
        this.plantilla.previa(2, cantidad, dim);
        MatrizEnForma1 m = new MatrizEnForma1(dim[0], dim[1]);
        m.contruyeNodosCabeza(); // Porque no se añade esta instrucción al constructor?
        for (int i = 1; i < tripletas.size(); i++) {
            NodoDoble x = new NodoDoble(tripletas.get(i));
            m.conectaPorFilas(x);
            m.conectaPorColumnas(x);
        }
        this.plantilla.nombre();
        String nombre = this.escuchar_nombre();
        if (nombre == null) {
            if (convertida) {
                this.plantilla.convertida(false);
            } else {
                this.plantilla.creada(false);
            }
            return false;
        }
        this.guardar(m, nombre);
        if (convertida) {
            this.plantilla.convertida(true);
        } else {
            this.plantilla.creada(true);
        }
        return true;
    }

    public boolean crear_MatrizEnForma2(int cantidad, int[] dim, ArrayList<Tripleta> tripletas, boolean convertida) {
        this.plantilla.previa(3, cantidad, dim);
        MatrizEnForma2 m = new MatrizEnForma2(dim[0], dim[1]);
        for (int i = 1; i < tripletas.size(); i++) {
            NodoDoble x = new NodoDoble(tripletas.get(i));
            m.conectaPorFilas(x);
            m.conectaPorColumnas(x);
        }
        this.plantilla.nombre();
        String nombre = this.escuchar_nombre();
        if (nombre == null) {
            if (convertida) {
                this.plantilla.convertida(false);
            } else {
                this.plantilla.creada(false);
            }
            return false;
        }
        this.guardar(m, nombre);
        if (convertida) {
            this.plantilla.convertida(true);
        } else {
            this.plantilla.creada(true);
        }
        return true;
    }

    public void guardar(MatrizEnTripletas m, String nombre) {
        this.inventario[0].add(m);
        this.inventario[1].add(m);
        this.inventario_nombres[0].add(nombre);
        this.inventario_nombres[1].add(nombre);
    }

    public void guardar(MatrizEnForma1 m, String nombre) {
        this.inventario[0].add(m);
        this.inventario[2].add(m);
        this.inventario_nombres[0].add(nombre);
        this.inventario_nombres[2].add(nombre);
    }

    public void guardar(MatrizEnForma2 m, String nombre) {
        this.inventario[0].add(m);
        this.inventario[3].add(m);
        this.inventario_nombres[0].add(nombre);
        this.inventario_nombres[3].add(nombre);
    }

    public void ver() {
        int ans, ans2;
        do {
            this.plantilla.ver(inventario);
            ans = this.escuchar(3);
            if (ans == 0) {
                break;
            }
            if (ans == -1) {
                continue;
            }
            String nombre = null;
            switch (ans) {
                case 1: {
                    nombre = "Matriz en tripletas";
                    break;
                }
                case 2: {
                    nombre = "Matriz en forma 1";
                    break;
                }
                case 3: {
                    nombre = "Matriz en forma 2";
                    break;
                }
            }
            do {
                if (!inventario[ans].isEmpty()) {
                    this.plantilla.ver(1, nombre, inventario[ans], inventario_nombres[ans]);
                    ans2 = this.escuchar(inventario[ans].size());
                    if (ans2 == 0) {
                        break;
                    }
                    if (ans2 == -1) {
                        continue;
                    }
                } else {
                    this.plantilla.ver(0, nombre, null, null);
                    this.escuchar(null);
                    break;
                }
                switch (ans) {
                    case 1: {
                        this.operaciones_Mt(ans2);
                        break;
                    }
                    case 2: {
                        this.operaciones_Mf1(ans2);
                        break;
                    }
                    case 3: {
                        this.operaciones_Mf2(ans2);
                        break;
                    }
                }
            } while (true);
        } while (true);
    }

    public void operar() {
        int ans;
        do {
            this.plantilla.operar();
            ans = escuchar(1);
            if (ans == 0) {
                break;
            }
            if (ans == -1) {
                continue;
            }
            switch (ans) {
                case 1: {
                    int ans2, ans3;
                    do {
                        this.plantilla.operar(1, this.inventario_nombres[1]);
                        ans2 = escuchar(this.inventario[1].size());
                        if (ans2 == 0) {
                            break;
                        }
                        if (ans2 == -1) {
                            continue;
                        }
                        do {
                            this.plantilla.operar(2, this.inventario_nombres[2]);
                            ans3 = escuchar(this.inventario[2].size());
                            if (ans3 == 0) {
                                break;
                            }
                            if (ans3 == -1) {
                                continue;
                            }
                            this.sumar_Mt_Mf1_Mf2(ans2, ans3);
                            break;
                        } while (true);
                        break;
                    } while (true);
                    break;
                }
            }
        } while (true);
    }

    public void actualizar() {
        int ans;
        do {
            this.plantilla.configuracion(this.plantilla.traducir(configuracion));
            ans = this.escuchar(5) - 1;
            switch (ans) {
                case -2: {
                    continue;
                }
                case -1: {
                    break;
                }
                case 0: {
                    this.plantilla.tipo();
                    int ans2 = this.escuchar(3);
                    switch (ans2) {
                        default:
                            continue;
                        case 1: {
                            this.configuracion.set(0, 1);
                            break;
                        }
                        case 2: {
                            this.configuracion.set(0, 2);
                            break;
                        }
                        case 3: {
                            this.configuracion.set(0, 3);
                            break;
                        }
                    }
                    if (esDefecto()) {
                        this.configuracion.set(4, true);
                    } else {
                        this.configuracion.set(4, false);
                    }
                    continue;
                }
                case 4: {
                    this.defecto();
                    continue;
                }
                default: {
                    this.configuracion.set(ans, !(boolean) this.configuracion.get(ans));
                    if (esDefecto()) {
                        this.configuracion.set(4, true);
                    } else {
                        this.configuracion.set(4, false);
                    }
                    if (!valido()) {
                        this.defecto();
                    }
                    continue;
                }
            }
            break;
        } while (true);
    } // Original

    public boolean esDefecto() {
        if ((int) this.configuracion.get(0) != 1) {
            return false;
        }
        if ((boolean) this.configuracion.get(1)) {
            return false;
        }
        if ((boolean) this.configuracion.get(2)) {
            return false;
        }
        if (!(boolean) this.configuracion.get(3)) {
            return false;
        }
        return true;
    } // Original

    public boolean valido() {
        if ((boolean) this.configuracion.get(1) && (boolean) this.configuracion.get(2) && (boolean) this.configuracion.get(3)) {
            return false;
        }
        if ((boolean) this.configuracion.get(1) && (boolean) this.configuracion.get(3)) {
            return false;

        }
        return true;
    } // Original

    public void limpiar() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    } // Original

    public void sumar_Mt_Mf1_Mf2(int mtId, int mf1Id) {
        MatrizEnTripletas mt = (MatrizEnTripletas) this.inventario[1].get(mtId - 1);
        MatrizEnForma1 mf1 = (MatrizEnForma1) this.inventario[2].get(mf1Id - 1);
        int f = mt.retornaNumeroFilas();
        int c = mt.retornaNumeroColumnas();
        boolean sw = false;
        if (f != mf1.retornaNumeroFilas() || c != mf1.retornaNumeroColumnas()) {
            this.plantilla.error(5);
            return;
        }
        int[] dim = new int[2];
        dim[0] = f;
        dim[1] = c;
        int cantidad = 0;
        ArrayList<Tripleta> tripletas = new ArrayList<>();
        Tripleta t_mt, t_mf1, t_r;
        t_r = new Tripleta(f, c, 0);
        tripletas.add(t_r);
        for (int i = 1; i <= f; i++) {
            for (int j = 1; j <= c; j++) {
                t_mt = this.retornaCasilla(mt, i, j);
                t_mf1 = this.retornaCasilla(mf1, i, j);
                if (!((int) t_mt.retornaValor() == 0) || !((int) t_mf1.retornaValor() == 0)) {
                    int v1 = (int) t_mt.retornaValor();
                    int v2 = (int) t_mf1.retornaValor();
                    t_r = new Tripleta(i, j, v1 + v2);
                    tripletas.add(t_r);
                    cantidad++;
                }
            }
        }
        boolean creada = this.crear_MatrizEnForma2(cantidad, dim, tripletas, false);
        if (creada) {
            this.plantilla.acierto(0);
            this.plantilla.defecto(0);
            this.escuchar(null);
        }
    }

    public Tripleta retornaCasilla(MatrizEnTripletas m, int f, int c) {
        Tripleta t;
        for (int i = 1; i <= m.retornaNumeroTripletas(); i++) {
            t = m.retornaTripleta(i);
            if (t.retornaFila() == f && t.retornaColumna() == c) {
                return t;
            }
        }
        t = new Tripleta(f, c, 0);
        return t;
    }

    public Tripleta retornaCasilla(MatrizEnForma1 m, int f, int c) {
        Tripleta t;
        for (int i = 1; i <= m.retornaNumeroTripletas(); i++) {
            t = m.retornaTripleta(i);
            if (t.retornaFila() == f && t.retornaColumna() == c) {
                return t;
            }
        }
        t = new Tripleta(f, c, 0);
        return t;
    }

    public void operaciones_Mt(int matriz) {
        MatrizEnTripletas m = (MatrizEnTripletas) this.inventario[1].get(matriz - 1);
        int ans;
        do {
            try {
                this.plantilla.operaciones_individuales(1, (String) this.inventario_nombres[1].get(matriz - 1));
            } catch (IndexOutOfBoundsException e) {
                return;
            }
            ans = escuchar(6);
            if (ans == 0) {
                break;
            }
            if (ans == -1) {
                continue;
            }
            switch (ans) {
                case 1: {
                    m.muestraMatrizEnTripletas();
                    this.plantilla.defecto(0);
                    this.escuchar(null);
                    break;
                }
                case 2: {
                    m.imprimeMatriz();
                    this.plantilla.defecto(0);
                    this.escuchar(null);
                    break;
                }
                case 3: {
                    int suma = m.sumarTriangular_SupI();
                    if (suma == -1) {
                        this.plantilla.error(4);
                        this.plantilla.defecto(0);
                        this.escuchar(null);
                        break;
                    }
                    this.plantilla.sumarTriangular_SupI_Mt(suma);
                    this.plantilla.defecto(0);
                    this.escuchar(null);
                    break;
                }
                case 4: {
                    m = m.transpuesta();
                    this.plantilla.transpuesta();
                    this.plantilla.defecto(0);
                    this.escuchar(null);
                    break;
                }
                case 5: {
                    this.convertir(m);
                    break;
                }
                case 6: {
                    this.editar(m, null, null);
                    break;
                }
            }
        } while (true);
    }

    public void operaciones_Mf1(int matriz) {
        MatrizEnForma1 m = (MatrizEnForma1) this.inventario[2].get(matriz - 1);
        int ans;
        do {
            try {
                this.plantilla.operaciones_individuales(2, (String) this.inventario_nombres[2].get(matriz - 1));
            } catch (IndexOutOfBoundsException e) {
                return;
            }
            ans = escuchar(6);
            if (ans == 0) {
                break;
            }
            if (ans == -1) {
                continue;
            }
            switch (ans) {
                case 1: {
                    m.muestraMatrizEnForma1(true);
                    this.plantilla.defecto(0);
                    this.escuchar(null);
                    break;
                }
                case 2: {
                    m.imprimeMatriz();
                    this.plantilla.defecto(0);
                    this.escuchar(null);
                    break;
                }
//                case 3: {
//                    int suma = m.sumarTriangular_SupI();
//                    if (suma == -1) {
//                        this.plantilla.error(4);
//                        this.plantilla.defecto(0);
//                        this.escuchar(null);
//                        break;
//                    }
//                    this.plantilla.sumarTriangular_SupI_Mt(suma);
//                    this.plantilla.defecto(0);
//                    this.escuchar(null);
//                    break;
//                }
//                case 4: {
//                    m = m.transpuesta();
//                    this.plantilla.transpuesta();
//                    this.plantilla.defecto(0);
//                    this.escuchar(null);
//                    break;
//                }
                case 5: {
                    this.convertir(m);
                    break;
                }
                case 6: {
                    this.editar(null, m, null);
                    break;
                }
            }
        } while (true);
    }

    public void operaciones_Mf2(int matriz) {
        MatrizEnForma2 m = (MatrizEnForma2) this.inventario[3].get(matriz - 1);
        int ans;
        do {
            try {
                this.plantilla.operaciones_individuales(3, (String) this.inventario_nombres[3].get(matriz - 1));
            } catch (IndexOutOfBoundsException e) {
                return;
            }
            ans = escuchar(6);
            if (ans == 0) {
                break;
            }
            if (ans == -1) {
                continue;
            }
            switch (ans) {
                case 1: {
                    m.muestraMatriz();
                    this.plantilla.defecto(0);
                    this.escuchar(null);
                    break;
                }
                case 2: {
                    m.imprimeMatriz();
                    this.plantilla.defecto(0);
                    this.escuchar(null);
                    break;
                }
//                case 3: {
//                    int suma = m.sumarTriangular_SupI();
//                    if (suma == -1) {
//                        this.plantilla.error(4);
//                        this.plantilla.defecto(0);
//                        this.escuchar(null);
//                        break;
//                    }
//                    this.plantilla.sumarTriangular_SupI_Mt(suma);
//                    this.plantilla.defecto(0);
//                    this.escuchar(null);
//                    break;
//                }
//                case 4: {
//                    m = m.transpuesta();
//                    this.plantilla.transpuesta();
//                    this.plantilla.defecto(0);
//                    this.escuchar(null);
//                    break;
//                }
                case 5: {
                    this.convertir(m);
                    break;
                }
                case 6: {
                    this.editar(null, null, m);
                    break;
                }
            }
        } while (true);
    }

    public void editar(MatrizEnTripletas mt, MatrizEnForma1 mf1, MatrizEnForma2 mf2) {
        int ans;
        do {
            this.plantilla.editar();
            ans = escuchar(2);
            if (ans == 0) {
                break;
            }
            if (ans == -1) {
                continue;
            }
            switch (ans) {
                case 1: {
                    if (mt != null) {
                        this.agregarDato_Mt(mt);
                    }
                    if (mf1 != null) {
                        this.agregarDato_Mf1(mf1);
                    }
                    if (mf2 != null) {
                        this.agregarDato_Mf2(mf2);
                    }
                    break;
                }
                case 2: {
                    if (mt != null) {
                        this.EliminarDato_Mt(mt);
                    }
                    if (mf1 != null) {
                        this.EliminarDato_Mf1(mf1);
                    }
                    if (mf2 != null) {
                        this.EliminarDato_Mf2(mf2);
                    }
                    break;
                }
            }
        } while (true);
    }

    public void agregarDato_Mt(MatrizEnTripletas m) {
        Tripleta t = this.escuchar_dato(m, null, null, 0);
        if (t == null) {
            return;
        }
        m.borrarDato(t.retornaFila(), t.retornaColumna());
        m.insertaTripleta_v2(t);
        this.limpiar();
        this.plantilla.acierto(1);
    }

    public void agregarDato_Mf1(MatrizEnForma1 m) {
        Tripleta t = this.escuchar_dato(null, m, null, 0);
        if (t == null) {
            return;
        }
        m.borrarDato(t.retornaFila(), t.retornaColumna());
        NodoDoble x = new NodoDoble(t);
        m.conectaPorFilas(x);
        m.conectaPorColumnas(x);
        this.limpiar();
        this.plantilla.acierto(1);
    }

    public void agregarDato_Mf2(MatrizEnForma2 m) {
        Tripleta t = this.escuchar_dato(null, null, m, 0);
        if (t == null) {
            return;
        }
        m.borrarDato(t.retornaFila(), t.retornaColumna());
        NodoDoble x = new NodoDoble(t);
        m.conectaPorFilas(x);
        m.conectaPorColumnas(x);
        this.limpiar();
        this.plantilla.acierto(1);
    }

    public void EliminarDato_Mt(MatrizEnTripletas m) {
        Tripleta t = this.escuchar_dato(m, null, null, 1);
        if (t == null) {
            return;
        }
        m.borrarDato(t.retornaFila(), t.retornaColumna());
        this.limpiar();
        this.plantilla.acierto(2);
    }

    public void EliminarDato_Mf1(MatrizEnForma1 m) {
        Tripleta t = this.escuchar_dato(null, m, null, 1);
        if (t == null) {
            return;
        }
        m.borrarDato(t.retornaFila(), t.retornaColumna());
        this.limpiar();
        this.plantilla.acierto(2);
    }

    public void EliminarDato_Mf2(MatrizEnForma2 m) {
        Tripleta t = this.escuchar_dato(null, null, m, 1);
        if (t == null) {
            return;
        }
        m.borrarDato(t.retornaFila(), t.retornaColumna());
        this.limpiar();
        this.plantilla.acierto(2);
    }

    public Tripleta escuchar_dato(MatrizEnTripletas mt, MatrizEnForma1 mf1, MatrizEnForma2 mf2, int tipo) {
        Tripleta t;
        Scanner sc = new Scanner(System.in);
        int f = 0, c;
        Object v;
        int i = 0;
        int ans;
        this.plantilla.dato(tipo);
        do {
            try {
                this.plantilla.eje(i);
                ans = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                this.limpiar();
                this.plantilla.error(1);
                continue;
            }
            if (ans == 0) {
                this.limpiar();
                return null;
            }
            if (ans < 0) {
                this.limpiar();
                this.plantilla.error(2);
                continue;
            }
            if (i == 0) {
                if (mt != null) {
                    if (ans > mt.retornaNumeroFilas()) {
                        this.limpiar();
                        this.plantilla.error(0);
                        continue;
                    }
                }
                if (mf1 != null) {
                    if (ans > mf1.retornaNumeroFilas()) {
                        this.limpiar();
                        this.plantilla.error(0);
                        continue;
                    }
                }
                if (mf2 != null) {
                    if (ans > mf2.retornaNumeroFilas()) {
                        this.limpiar();
                        this.plantilla.error(0);
                        continue;
                    }
                }
                f = ans;
                i++;
            } else {
                if (mt != null) {
                    if (ans > mt.retornaNumeroColumnas()) {
                        this.limpiar();
                        this.plantilla.error(0);
                        continue;
                    }
                }
                if (mf1 != null) {
                    if (ans > mf1.retornaNumeroColumnas()) {
                        this.limpiar();
                        this.plantilla.error(0);
                        continue;
                    }
                }
                if (mf2 != null) {
                    if (ans > mf2.retornaNumeroColumnas()) {
                        this.limpiar();
                        this.plantilla.error(0);
                        continue;
                    }
                }
                c = ans;
                break;
            }
        } while (true);
        if (tipo == 1) {
            return new Tripleta(f, c, 0);
        }
        this.plantilla.eje(2);
        try {
            v = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            v = sc.nextLine();
        }
        if ((int)v == 0) {
            this.limpiar();
            return null;
        }
        t = new Tripleta(f, c, v);
        return t;
    }

    public void convertir(MatrizEnTripletas m) {
        int ans, cantidad;
        int[] dim = new int[2];
        ArrayList<Tripleta> tripletas = new ArrayList<>();
        do {
            this.plantilla.convertir(1);
            ans = this.escuchar(2);
            if (ans == 0) {
                break;
            }
            if (ans == -1) {
                continue;
            }
            switch (ans) {
                case 1: {
                    cantidad = m.retornaNumeroTripletas();
                    dim[0] = m.retornaNumeroFilas();
                    dim[1] = m.retornaNumeroColumnas();
                    for (int i = 0; i <= cantidad; i++) {
                        tripletas.add(m.retornaTripleta(i));
                    }
                    boolean creada = this.crear_MatrizEnForma1(cantidad, dim, tripletas, true);
                    if (creada) {
                        this.borrar(m);
                        return;
                    }
                    break;
                }
                case 2: {
                    cantidad = m.retornaNumeroTripletas();
                    dim[0] = m.retornaNumeroFilas();
                    dim[1] = m.retornaNumeroColumnas();
                    for (int i = 0; i <= cantidad; i++) {
                        tripletas.add(m.retornaTripleta(i));
                    }
                    boolean creada = this.crear_MatrizEnForma2(cantidad, dim, tripletas, true);
                    if (creada) {
                        this.borrar(m);
                        return;
                    }
                    break;
                }
            }
        } while (true);
    }

    public void convertir(MatrizEnForma1 m) {
        int ans, cantidad;
        int[] dim = new int[2];
        ArrayList<Tripleta> tripletas = new ArrayList<>();
        do {
            this.plantilla.convertir(2);
            ans = this.escuchar(2);
            if (ans == 0) {
                break;
            }
            if (ans == -1) {
                continue;
            }
            switch (ans) {
                case 1: {
                    cantidad = m.retornaNumeroTripletas();
                    dim[0] = m.retornaNumeroFilas();
                    dim[1] = m.retornaNumeroColumnas();
                    for (int i = 0; i <= cantidad; i++) {
                        tripletas.add(m.retornaTripleta(i));
                    }
                    boolean creada = this.crear_MatrizEnTripletas(cantidad, dim, tripletas, true);
                    if (creada) {
                        this.borrar(m);
                        return;
                    }
                    break;
                }
                case 2: {
                    cantidad = m.retornaNumeroTripletas();
                    dim[0] = m.retornaNumeroFilas();
                    dim[1] = m.retornaNumeroColumnas();
                    for (int i = 0; i <= cantidad; i++) {
                        tripletas.add(m.retornaTripleta(i));
                    }
                    boolean creada = this.crear_MatrizEnForma2(cantidad, dim, tripletas, true);
                    if (creada) {
                        this.borrar(m);
                        return;
                    }
                    break;
                }
            }
        } while (true);
    }

    public void convertir(MatrizEnForma2 m) {
        int ans, cantidad;
        int[] dim = new int[2];
        ArrayList<Tripleta> tripletas = new ArrayList<>();
        do {
            this.plantilla.convertir(2);
            ans = this.escuchar(2);
            if (ans == 0) {
                break;
            }
            if (ans == -1) {
                continue;
            }
            switch (ans) {
                case 1: {
                    cantidad = m.retornaNumeroTripletas();
                    dim[0] = m.retornaNumeroFilas();
                    dim[1] = m.retornaNumeroColumnas();
                    for (int i = 0; i <= cantidad; i++) {
                        tripletas.add(m.retornaTripleta(i));
                    }
                    boolean creada = this.crear_MatrizEnTripletas(cantidad, dim, tripletas, true);
                    if (creada) {
                        this.borrar(m);
                        return;
                    }
                    break;
                }
                case 2: {
                    cantidad = m.retornaNumeroTripletas();
                    dim[0] = m.retornaNumeroFilas();
                    dim[1] = m.retornaNumeroColumnas();
                    for (int i = 0; i <= cantidad; i++) {
                        tripletas.add(m.retornaTripleta(i));
                    }
                    boolean creada = this.crear_MatrizEnForma1(cantidad, dim, tripletas, true);
                    if (creada) {
                        this.borrar(m);
                        return;
                    }
                    break;
                }
            }
        } while (true);
    }

    public void borrar(MatrizEnTripletas m) {
        int idx = this.inventario[1].indexOf(m);
        this.inventario[0].remove(idx);
        this.inventario[1].remove(idx);
        this.inventario_nombres[0].remove(idx);
        this.inventario_nombres[1].remove(idx);
    }

    public void borrar(MatrizEnForma1 m) {
        int idx = this.inventario[2].indexOf(m);
        this.inventario[0].remove(idx);
        this.inventario[2].remove(idx);
        this.inventario_nombres[0].remove(idx);
        this.inventario_nombres[2].remove(idx);
    }

    public void borrar(MatrizEnForma2 m) {
        int idx = this.inventario[3].indexOf(m);
        this.inventario[0].remove(idx);
        this.inventario[3].remove(idx);
        this.inventario_nombres[0].remove(idx);
        this.inventario_nombres[3].remove(idx);
    }

    public int escuchar(Integer opciones) {
        Scanner sc = new Scanner(System.in);
        if (opciones == null) {
            sc.nextLine();
            this.limpiar();
            return -1;
        }
        int ans;
        // Restricciones
        try {
            ans = Integer.parseInt(sc.nextLine());
            if (ans < 0 || ans > opciones) {
                this.limpiar();
                this.plantilla.error(0);
                return -1;
            }
        } catch (NumberFormatException e) {
            this.limpiar();
            this.plantilla.error(1);
            return -1;
        }
        this.limpiar();
        return ans;
    }

    public int[] escuchar_dimension() {
        Scanner sc = new Scanner(System.in);
        int[] dim = new int[2];
        int sw = 0;
        int ans;
        do {
            this.plantilla.dimension(sw);
            try {
                ans = Integer.parseInt(sc.nextLine());
                if (ans < 0) {
                    this.limpiar();
                    this.plantilla.error(2);
                    continue;
                }
            } catch (NumberFormatException e) {
                this.limpiar();
                this.plantilla.error(1);
                continue;
            }
            if (ans == 0) {
                this.limpiar();
                return null;
            }
            if (sw == 0) {
                dim[0] = ans;
                sw = 1;
            } else {
                dim[1] = ans;
                this.limpiar();
                return dim;
            }
        } while (true);
    }

    public int escuchar_cantidad(int[] dim) {
        Scanner sc = new Scanner(System.in);
        int ans;
        int max = 0;
        if (dim != null) {
            max = dim[0] * dim[1];
        }
        do {
            this.plantilla.cantidad(dim);
            try {
                ans = Integer.parseInt(sc.nextLine());
                if (dim != null) {
                    if (ans < 0 || ans > max) {
                        this.limpiar();
                        this.plantilla.error(0);
                        continue;
                    }
                } else {
                    if (ans < 0) {
                        this.limpiar();
                        this.plantilla.error(0);
                        continue;
                    }
                }

            } catch (NumberFormatException e) {
                this.limpiar();
                this.plantilla.error(1);
                continue;
            }
            break;
        } while (true);
        this.limpiar();
        return ans;
    }

    public ArrayList escuchar_datos(int[] dim) {
        ArrayList<Tripleta> tripletas = new ArrayList();
        Scanner sc = new Scanner(System.in);
        Tripleta t;
        if (dim != null) {
            t = new Tripleta(dim[0], dim[1], 0);
        } else {
            t = new Tripleta(0, 0, 0);
        }
        tripletas.add(t);
        int ans;
        int sw = 0;
        int d = 0;
        if (dim != null) {
            d = dim[0];
        }
        int[] fc = new int[2];
        Object v;
        this.plantilla.datos(dim);
        do {
            this.plantilla.eje(sw);
            try {
                ans = Integer.parseInt(sc.nextLine());
                if (dim != null) {
                    if (ans < 0 || ans > d) {
                        this.plantilla.error(0);
                        continue;
                    }
                } else {
                    if (ans < 0) {
                        this.plantilla.error(2);
                        continue;
                    }
                }
            } catch (NumberFormatException e) {
                this.plantilla.error(1);
                continue;
            }
            if (ans == 0) {
                this.limpiar();
                if (tripletas.size() > 1) {
                    dim = this.generar_dimension(tripletas);
                    t = new Tripleta(dim[0], dim[1], 0);
                    tripletas.set(0, t);
                    return tripletas;
                }
                return null;
            }
            if (sw == 0) {
                sw = 1;
                if (dim != null) {
                    d = dim[1];
                }
                fc[0] = ans;
            } else {
                sw = 0;
                if (dim != null) {
                    d = dim[0];
                }
                fc[1] = ans;
                this.plantilla.eje(2);
                try {
                    v = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    v = sc.nextLine();
                }
                try {
                    if ((int) v == 0) {
                        this.limpiar();
                        if (tripletas.size() > 1) {
                            dim = this.generar_dimension(tripletas);
                            t = new Tripleta(dim[0], dim[1], 0);
                            tripletas.set(0, t);
                            return tripletas;
                        }
                        return null;
                    }
                } catch (ClassCastException e) {
                    // No hace nada ajja
                }
                t = new Tripleta(fc[0], fc[1], v);
                if (this.repetido(tripletas, t)) {
                    this.plantilla.error(3);
                    continue;
                }
                tripletas.add(t);
                this.plantilla.eje(3);
            }
        } while (true);
    }

    public String escuchar_nombre() {
        Scanner sc = new Scanner(System.in);
        String ans = sc.nextLine();
        if (ans.equals("0")) {
            this.limpiar();
            return null;
        }
        this.limpiar();
        return ans;
    }

    public int[] generar_dimension(int cantidad) {
        Random rnd = new Random();
        int[] dim = new int[2];
        dim[0] = rnd.nextInt(cantidad) + 1;
        int min = cantidad / dim[0] + 1;
        dim[1] = rnd.nextInt(cantidad) + min;
        return dim;
    }

    public int[] generar_dimension(ArrayList<Tripleta> datos) {
        int[] dim = new int[2];
        int f = 0;
        int c = 0;
        for (Tripleta tripleta : datos) {
            f = f > tripleta.retornaFila() ? f : tripleta.retornaFila();
        }
        for (Tripleta tripleta : datos) {
            c = c > tripleta.retornaColumna() ? c : tripleta.retornaColumna();
        }
        dim[0] = f;
        dim[1] = c;
        return dim;
    }

    public int generar_cantidad(int max) {
        Random rnd = new Random();
        return rnd.nextInt(max) + 1;
    }

    public ArrayList<Tripleta> generar_datos(int f, int c, int n) {
        ArrayList<Tripleta> datosR = new ArrayList<Tripleta>();
        Random rnd = new Random();
        int fila, columna, valor;
        Tripleta t = new Tripleta(f, c, 0);
        datosR.add(t);
        for (int i = 0; i < n; i++) {
            fila = rnd.nextInt(f) + 1;
            columna = rnd.nextInt(c) + 1;
            valor = rnd.nextInt(100) + 1;
            t = new Tripleta(fila, columna, valor);
            if (repetido(datosR, t)) {
                i--;
                continue;
            }
            datosR.add(t);
        }
        return datosR;
    }

    public boolean repetido(ArrayList<Tripleta> datosR, Tripleta t) {
        for (int i = 1; i < datosR.size(); i++) {
            if (datosR.size() != 1 && datosR.get(i).retornaFila() == t.retornaFila() && datosR.get(i).retornaColumna() == t.retornaColumna()) {
                return true;
            }
        }
        return false;
    }

    public void plantilla(String plantilla) {
        switch (plantilla) {
            case "inicio": {
                this.plantilla.inicio();
                break;
            }
            case "principal": {
                this.plantilla.principal();
                break;
            }
            case "configuracion": {
                ArrayList conf_string = this.plantilla.traducir(configuracion);
                this.plantilla.configuracion(conf_string);
            }
            case "despedida": {
                this.plantilla.defecto(-1);
            }
        }
    }
}