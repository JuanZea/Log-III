package Modelos;

public class Tripleta {
    private Integer fila;
    private Integer columna;
    private Object valor;

    public Tripleta(Integer fila, Integer columna, Object valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
    }

    public void asignaFila(Integer r) {
        this.fila = r;
    }

    public void asignaColumna(Integer c) {
        this.columna = c;
    }

    public void asignaValor(Object v) {
        this.valor = v;
    }

    public Integer retornaFila() {
        return fila;
    }

    public Integer retornaColumna() {
        return columna;
    }

    public Object retornaValor() {
        return valor;
    }
}
