package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Controller {
    @FXML
    TextField in_n;
    @FXML
    TextField in_k;
    @FXML
    TextField in_string;
    @FXML
    TextField in_r;
    @FXML
    TextField in_pesos;
    @FXML
    TextField in_beneficios;
    @FXML
    TextField in_capacidad;

    @FXML
    TextField out_cantidad;

    @FXML
    TextArea tb_1;
    @FXML
    TextArea tb_2;
    @FXML
    TextArea tb_3;
    @FXML
    TextArea tb_4;

    @FXML
    ComboBox cb_rango;

    private int n;
    private int[] v;
    private int[] vUI;
    private int[] primos;
    private int[] pesos;
    private int[] beneficios;
    private int cantidad;
    private int beneMax;
    private int capacidad;
    private ArrayList<boolean[]> soluciones;

    public void generarEntero() {
        Random rnd = new Random();
        this.n = rnd.nextInt(41) + 10;
        String ans = "El número n es: " + this.n + "\n";
        this.tb_1.setText(ans);
    }

    public void generarVector() {
        Random rnd = new Random();
        if (this.n == 0) {
            JOptionPane.showMessageDialog(null, "Primero genere un entero con la opción 1.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.v = new int[this.n + 1];
        int candidato;
        for (int i = 1; i < v.length; i++) { // Modificado para que no genere datos repetidos
            candidato = rnd.nextInt(100) + 1;
            if (this.pertenece(candidato, this.v)) {
                i--;
                continue;
            }
            v[i] = candidato;
        }
        this.mostrar(1);
    }

    public void Ordenar() {
        if (this.v == null) {
            JOptionPane.showMessageDialog(null, "No se ha generado ningún vector", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Stack pila = new Stack();
        int n = this.n;
        int size = 1;
        int avance_mitad = size - 1;
        int avance_final = size * 2 - 1;
        // Separación en parejas
        int i;
        while (size < n) {
            i = 1;
            while (i + avance_mitad < n) {
                if (i + avance_final > n) {
                    int pasados = i + avance_final - n;
                    this.intercalar(i, i + avance_mitad, i + avance_final - pasados);
                    pila.push(this.copiar(v));
                    break;
                }
                this.intercalar(i, i + avance_mitad, i + avance_final);
                pila.push(this.copiar(v));
                i += size * 2;
            }
            size *= 2;
            avance_mitad = size - 1;
            avance_final = size * 2 - 1;
        }
        pila.pop();
        this.vUI = (int[]) pila.pop();
        this.mostrar(2);
    }

    public void intercalar(int inicio, int mitad, int fin) {
        int[] w = new int[v.length];
        for (int i = inicio; i <= fin; i++) {
            w[i] = v[i];
        }
        int i = inicio;
        int j = mitad + 1;
        int k = inicio - 1;
        while (i <= mitad && j <= fin) {
            k++;
            if (w[i] <= w[j]) {
                v[k] = w[i];
                i++;
            } else {
                v[k] = w[j];
                j++;
            }
        }
        while (i <= mitad) {
            k++;
            v[k] = w[i];
            i++;
        }
        while (j <= fin) {
            k++;
            v[k] = w[j];
            j++;
        }
    }

    public void combinaciones() {
        int r;
        try {
            r = Integer.parseInt(this.in_r.getText());
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "El formato de r es incorrecto o no se ha ingresado", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String s = this.in_string.getText();
        int n = s.length();
        String ans = "";
        if (r > n || r < 1) {
            JOptionPane.showMessageDialog(null, "r debe ser positivo y menor que el tamaño del string", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int j, t;
        int[] b = new int[r];
        for (int i = 0; i < r; i++) {
            b[i] = i;
        }
        j = r - 1;
        while (b[0] <= n - r) {
            t = 0;
            while (t < r) {
                ans += s.charAt(b[t]);
                t++;
            }
            ans += "\n";
            b[j]++;
            while (b[j] > n + j - r) {
                if (r == 1) {
                    this.tb_3.setText(ans);
                    return;
                }
                b[j - 1]++;
                if (b[j - 1] >= n + j - r) {
                    j--;
                    if (j == 0) {
                        this.tb_3.setText(ans);
                        return;
                    }
                    continue;
                } else
                    while (j < r) {
                        b[j] = b[j - 1] + 1;
                        j++;
                    }
                j--;
            }
        }
    }

    public void validarAlgoritmoNK() {
        int n, k;
        try {
            n = Integer.parseInt(this.in_n.getText());
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "El formato de la variable n es incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (n <= 0) {
            JOptionPane.showMessageDialog(null, "El valor de n debe ser un número mayor que 0", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            k = Integer.parseInt(this.in_k.getText());
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "El formato de la variable k es incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.n = n;
        this.generarVector();
        if (k > n || k <= 0) {
            JOptionPane.showMessageDialog(null, "El dato k indica una posición prohibida en el vector", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int valor = this.algoritmoNK(n, k);
        String ans = this.tb_1.getText();
        ans += "\nEl valor: "+ valor +" estará en la posición " + k + " si el vector se ordenara descendentemente";
        this.tb_1.setText(ans);
    }

    public int algoritmoNK(int n, int k) {
      int candidato;
      int posicion;
      for (int i = 1; i < this.v.length ; i++) {
        candidato = this.v[i];
        posicion = 1;
        for (int j = 1; j < this.v.length ; j++) {
          if(this.v[j] > candidato){
            posicion++;
          }
        }
        if((posicion) == k){
          return candidato;
        }
      }
      return -1;
    }

    public boolean pertenece(int k, int[] v) {
        for (int dato : v) {
            if (dato == k) {
                return true;
            }
        }
        return false;
    }

    public void calcularPrimos() {
        int[] primos = new int[1000];
        primos[0] = 2;
        int i = 1;
        int j;
        for (int num = 3; num <= 1000; num++) {
            j = 0;
            while (primos[j] <= Math.sqrt(num)) {
                if (num % primos[j] == 0) {
                    break;
                }
                j++;
            }
            if (primos[j] > Math.sqrt(num)) {
                primos[i] = num;
                i++;
            }
        }
        this.primos = primos;
        this.mostrarPrimos();
    }

    /**
     * @param ansI 1: Generado, 2: Ordenado
     */
    public void mostrar(int ansI) {
        String ans = "";
        switch (ansI) {
            case 1: {
                ans = "El vector ha sido generado correctamente\nEl número n es: " + this.n + "\n";
                break;
            }
            case 2: {
                ans = "El vector en antes de su última intercalación estaba así:\n";
                ans += "Índice | Valor\n";
                for (int i = 1; i < vUI.length; i++) {
                    ans += i + " | " + vUI[i] + "\n";
                }
                ans += "El vector ha sido ordenado correctamente\nEl número n es: " + this.n + "\n";
                break;
            }
        }
        ans += "Índice | Valor\n";
        for (int i = 1; i < v.length; i++) {
            ans += i + " | " + v[i] + "\n";
        }
        this.tb_1.setText(ans);
    }

    public int[] copiar(int[] v) {
        int[] vCopia = new int[v.length];
        for (int i = 0; i < v.length; i++) {
            vCopia[i] = v[i];
        }
        return vCopia;
    }

    public boolean[] copiar(boolean[] v) {
        boolean[] vCopia = new boolean[v.length];
        for (int i = 0; i < v.length; i++) {
            vCopia[i] = v[i];
        }
        return vCopia;
    }

    public void mostrarPrimos() {
        String ans = "Los números primos entre 1 y 1000 son:\n";
        for (int i = 0; i < this.primos.length; i++) {
            if (i % 20 == 0) {
                ans += "\n";
            }
            if (this.primos[i] != 0) {
                ans += this.primos[i] + "\t";
            } else {
                break;
            }
        }
        this.tb_2.setText(ans);
        ObservableList rangos = FXCollections.observableArrayList();
        for (int i = 1; i < 1000; i += 100) {
            rangos.add(i + " - " + (i + 99));
        }
        this.cb_rango.setItems(rangos);
    }

    public void actualizarCantidad() {
        int select = this.cb_rango.getSelectionModel().getSelectedIndex();
        if (select == -1) {
            JOptionPane.showMessageDialog(null, "Primero debe elegir un rango", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cotaInf, cotaSup;
        int k;
        int i = 0;
        cotaInf = select * 100 + 1;
        cotaSup = select * 100 + 100;
        while (this.primos[i] < cotaInf) {
            i++;
        }
        k = i;
        while (this.primos[i] <= cotaSup && this.primos[i] != 0) {
            i++;
        }
        int num = i - k;
        this.out_cantidad.setText(String.valueOf(num));
    }

    public void calcularFormas() {
        if (this.in_pesos.getText().isEmpty() || this.in_beneficios.getText().isEmpty() || this.in_capacidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hay datos sin ingresar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] pesos_string = this.in_pesos.getText().split(",");
        String[] beneficios_string = this.in_beneficios.getText().split(",");
        pesos = new int[pesos_string.length];
        beneficios = new int[beneficios_string.length];
        if (pesos.length != beneficios.length) {
            JOptionPane.showMessageDialog(null, "Algo anda mal.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.cantidad = pesos.length;
        for (int i = 0; i < pesos_string.length; i++) {
            try {
                pesos[i] = Integer.parseInt(pesos_string[i]);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "El formato en el que se ingresó el vector es incorrecto.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        for (int i = 0; i < beneficios_string.length; i++) {
            try {
                beneficios[i] = Integer.parseInt(beneficios_string[i]);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "El formato en el que se ingresó el vector es incorrecto.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        // Verificar entradas negativas
        boolean[] x = new boolean[cantidad];
        for (int i = 0; i < x.length; i++) {
            x[i] = false;
        }
        try {
            capacidad = Integer.parseInt(this.in_capacidad.getText());
            if (capacidad <= 0) {
                JOptionPane.showMessageDialog(null, "La capacidad debe ser mayor que 0", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "El formato de la capacidad no es válido", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        soluciones = new ArrayList();
        this.beneMax = 0;
        this.cargarMochila(0, x, 0, 0);
        String ans = "Con un beneficio máximo de " + beneMax + ", las soluciones son:\n";
        ans += "Las soluciones posibles son:\n\n";
        for (int i = 0; i < soluciones.size(); i++) {
            ans += "Solución #" + (i + 1) + ": ";
            for (int j = 0; j < soluciones.get(i).length; j++) {
                if (soluciones.get(i)[j] == true)
                    ans += "Objeto #" + (j + 1) + ", ";
            }
            ans = ans.substring(0,ans.length() - 2);
            ans += ".\n\n";
        }
        this.tb_4.setText(ans);
    }
    
    public void cargarMochila(int i, boolean[] x, int beneT, int pesoT) {
        if (this.cantidad <= i) {
            if (beneT == beneMax) {
                soluciones.add(this.copiar(x));
            } else if (beneT > beneMax) {
                soluciones = new ArrayList<>();
                soluciones.add(this.copiar(x));
                beneMax = beneT;
            }
        } else {
            if (pesoT + pesos[i] <= capacidad) {
                x[i] = true;
                pesoT += this.pesos[i];
                beneT += this.beneficios[i];
                cargarMochila(i + 1, x, beneT, pesoT);
                x[i] = false;
                pesoT -= pesos[i];
                beneT -= beneficios[i];
            }
            cargarMochila(i + 1, x, beneT, pesoT);
        }
    }
}
