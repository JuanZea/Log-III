package Controllers;

import Modelos.MatrizDeAdyacencia;
import Modelos.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;

public class WindowController {
    
    private Storage sg = new Storage();
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfVert;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfAgregarVert;
    @FXML
    private TextArea taMostrar;
    @FXML
    private TextArea taRecorrer;
    @FXML
    private TextArea taOperaciones;
    @FXML
    private ListView listN;
    @FXML
    private ListView listN_1;
    @FXML
    private ListView listN_2;
    @FXML
    private ListView listN_3;
    @FXML
    private ListView listV;
    @FXML
    private ListView listV_1;
    @FXML
    private ComboBox cbV1;
    @FXML
    private ComboBox cbV2;
    @FXML
    private ComboBox cbV1_1;
    @FXML
    private ComboBox cbV2_1;
    @FXML
    private ComboBox cbInicio;
    
    // OnKeyPressed - open
    
    public void btnAgregar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.agregar();
        }
    }
    
    public void btnConectarV(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.conectarV();
        }
    }
    
    public void tfNom(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.agregar();
        }
    }
    
    public void tfVert(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.agregar();
        }
    }
    
    // OnKeyPressed - close
    
    public void agregar() {
        if (!this.restricciones(0)) {
            return;
        }
        int nV = Integer.parseInt(this.tfVert.getText());
        MatrizDeAdyacencia m = new MatrizDeAdyacencia(nV);
        ObservableList vts = FXCollections.observableArrayList();
        for (int i = 1; i <= nV; i++) {
            vts.add(i);
        }
        this.sg.save(this.tfNom.getText(), m, vts);
        this.listN.setItems(this.sg.returnN());
        this.listN_1.setItems(this.sg.returnN());
        this.listN_2.setItems(this.sg.returnN());
        this.listN_3.setItems(this.sg.returnN());
        this.listN.getSelectionModel().select(this.sg.returnN().size() - 1);
        this.tfNom.setText("");
        this.tfVert.setText("");
        this.tfVert.setPromptText("Agregado");
        this.actualizarVertices();
        // LLenar los combobox
        this.cbV1.setItems(this.listV.getItems());
        this.cbV2.setItems(this.listV.getItems());
        this.cbV1.setDisable(false);
        this.cbV2.setDisable(false);
    }
    
    public void actualizarVertices() {
        this.listV.setItems(this.sg.returnV(this.listN.getSelectionModel().getSelectedIndex()));
        this.cbV1.setItems(this.sg.returnV(this.listN.getSelectionModel().getSelectedIndex()));
        this.cbV2.setItems(this.sg.returnV(this.listN.getSelectionModel().getSelectedIndex()));
        this.cbV1.getSelectionModel().clearSelection();
        this.cbV2.getSelectionModel().clearSelection();
    }
    
    public void actualizarVertices_1() {
        this.listV_1.setItems(this.sg.returnV(this.listN_1.getSelectionModel().getSelectedIndex()));
        this.cbV1_1.setItems(this.sg.returnV(this.listN_1.getSelectionModel().getSelectedIndex()));
        this.cbV2_1.setItems(this.sg.returnV(this.listN_1.getSelectionModel().getSelectedIndex()));
        this.cbV1_1.getSelectionModel().clearSelection();
        this.cbV2_1.getSelectionModel().clearSelection();
    }
    
    public void actualizarVertices_2() {
        this.cbInicio.setItems(this.sg.returnV(this.listN_3.getSelectionModel().getSelectedIndex()));
    }
    
    public void conectarV() {
        if (!this.restricciones(1)) {
            return;
        }
        int select = this.listN.getSelectionModel().getSelectedIndex();
        MatrizDeAdyacencia m = this.sg.returnM(select);
        int a = cbV1.getSelectionModel().getSelectedIndex() + 1;
        int b = cbV2.getSelectionModel().getSelectedIndex() + 1;
        int price = Integer.parseInt(this.tfPrice.getText());
        m.agregarConexion(a, b, price);
        this.tfPrice.setText("");
        this.tfPrice.setPromptText("Conectado");
    }
    
    public void actualizarMuestra() {
        int select = this.listN_2.getSelectionModel().getSelectedIndex();
        MatrizDeAdyacencia m = this.sg.returnM(select);
        this.taMostrar.setText(m.retornaM1().imprimeMatriz());
    }
    
    public void DFS() {
        if (!this.restricciones(4)) {
            return;
        }
        int select = this.listN_3.getSelectionModel().getSelectedIndex();
        MatrizDeAdyacencia m = this.sg.returnM(select);
        int d = (int) this.cbInicio.getSelectionModel().getSelectedItem();
        int select2 = this.sg.searchV(d, select);
        ArrayList<Integer> o = m.DFS(select2 + 1);
        String pv = "";
        ArrayList lq = this.sg.returnVts();
        ObservableList lv = (ObservableList) lq.get(select);
        for (Integer ent : o) {
            pv += lv.get(ent - 1) + " ";
        }
        this.taRecorrer.setText(pv);
    }
    
    public void BFS() {
        if (!this.restricciones(4)) {
            return;
        }
        int select = this.listN_3.getSelectionModel().getSelectedIndex();
        MatrizDeAdyacencia m = this.sg.returnM(select);
        int select2 = this.cbInicio.getSelectionModel().getSelectedIndex();
        ArrayList<Integer> o = m.BFS(select2 + 1);
        String pv = "";
        ArrayList lq = this.sg.returnVts();
        ObservableList lv = (ObservableList) lq.get(select);
        for (int ent = o.size(); ent > 0; ent--) {
            pv += lv.get(o.get(ent - 1) - 1) + " ";
        }
        this.taRecorrer.setText(pv);
    }
    
    public void agregarVertice() {
        if (!this.restricciones(2)) {
            return;
        }
        int select = this.listN_1.getSelectionModel().getSelectedIndex();
        MatrizDeAdyacencia m = this.sg.returnM(select);
        int vt = Integer.parseInt(this.tfAgregarVert.getText());
        this.sg.addV(m, vt);
        m.retornaM1().agregarNodoCabeza();
        this.tfAgregarVert.setText("");
        this.tfAgregarVert.setPromptText("Agregado");
        this.actualizarVertices();
    }
    
    public void eliminarVertice() {
        if (!this.restricciones(3)) {
            return;
        }
        int select = this.listN_1.getSelectionModel().getSelectedIndex();
        MatrizDeAdyacencia m = this.sg.returnM(select);
        select = this.listV_1.getSelectionModel().getSelectedIndex();
        this.sg.removeV(m, select);
        m.retornaM1().eliminarNodoCabeza(select);
        this.actualizarVertices();
    }

//    public void puntosDeArticulacion(){
//        int select = this.listN_1.getSelectionModel().getSelectedIndex();
//        MatrizDeAdyacencia m = this.sg.returnM(select);
//        Stack pila = new Stack();
//        ArrayList lq = this.sg.returnVts();
//        ObservableList lv = (ObservableList) lq.get(select);
//        m.puntosdeArticulacion();
//
//    }
    
    public void eliminarLado() {
        if (!this.restricciones(5)) {
            return;
        }
        int select = this.listN_1.getSelectionModel().getSelectedIndex();
        MatrizDeAdyacencia m = this.sg.returnM(select);
        int a = this.cbV1_1.getSelectionModel().getSelectedIndex() + 1;
        int b = this.cbV2_1.getSelectionModel().getSelectedIndex() + 1;
        m.retornaM1().eliminarLado(a, b);
        actualizarVertices_1();
    }
    
    public void esLibre() {
        if (!restricciones(6)) {
            return;
        }
        int select = this.listN_1.getSelectionModel().getSelectedIndex();
        MatrizDeAdyacencia m = this.sg.returnM(select);
        if (m.esLibre()) {
            this.taOperaciones.setText("Si");
        } else {
            this.taOperaciones.setText("No");
        }
    }
    
    public void spanningTree() {
        this.taRecorrer.setText("Lo intentamos...");
        int select = this.listN_1.getSelectionModel().getSelectedIndex();
        MatrizDeAdyacencia m = this.sg.returnM(select);
        select = this.cbInicio.getSelectionModel().getSelectedIndex() + 1;
        m.prim(select);
        if (!this.restricciones(4)) {
            return;
        }
        select = this.listN_3.getSelectionModel().getSelectedIndex();
        m = this.sg.returnM(select);
        int d = (int) this.cbInicio.getSelectionModel().getSelectedItem();
        int select2 = this.sg.searchV(d, select);
        ArrayList<Integer> o = m.DFS(select2 + 1);
        String pv = "";
        ArrayList lq = this.sg.returnVts();
        ObservableList lv = (ObservableList) lq.get(select);
        for (Integer ent : o) {
            pv += lv.get(ent - 1) + " ";
        }
        this.taRecorrer.setText(pv);
    }
    
    public void articulacion() {
        if (!restricciones(6)) {
            return;
        }
        int select = this.listN_1.getSelectionModel().getSelectedIndex();
        MatrizDeAdyacencia m = this.sg.returnM(select);
        ArrayList<Integer> art = m.retornaArticulaciones();
        String pv = "";
        ArrayList lq = this.sg.returnVts();
        ObservableList lv = (ObservableList) lq.get(select);
        for (Integer ent : art) {
            pv += lv.get(ent - 1) + " ";
        }
        if(art.size() == 0){
            this.taOperaciones.setText("El grafo no tiene articulaciones");
        } else {
            this.taOperaciones.setText(pv);
        }
        
    }
    
    /**
     * Restricciones de la aplicación
     *
     * @param tipo 0: Crear, 1: Conectar, 2: Agregar, 3: Selección-listV_1, 4: Selección-cbInicio, 5: Eliminar Lado
     */
    public Boolean restricciones(int tipo) {
        switch (tipo) {
            case 0: {
                if (this.tfNom.getText().isEmpty()) {
                    errors(0);
                    return false;
                }
                if (this.tfVert.getText().isEmpty()) {
                    errors(1);
                    return false;
                }
                int n;
                try {
                    n = Integer.parseInt(this.tfVert.getText());
                } catch (NumberFormatException exception) {
                    errors(4);
                    return false;
                }
                if (n < 0) {
                    errors(4);
                    return false;
                }
                break;
            }
            case 1: {
                if (this.cbV1.getSelectionModel().getSelectedIndex() == -1 || this.cbV2.getSelectionModel().getSelectedIndex() == -1) {
                    errors(2);
                    return false;
                }
                if (this.cbV1.getSelectionModel().getSelectedIndex() == this.cbV2.getSelectionModel().getSelectedIndex()) {
                    errors(3);
                    return false;
                }
                if (this.tfPrice.getText().isEmpty()) {
                    errors(5);
                    return false;
                }
                int n;
                try {
                    n = Integer.parseInt(this.tfPrice.getText());
                } catch (NumberFormatException exception) {
                    errors(6);
                    return false;
                }
                if (n < 0) {
                    errors(6);
                    return false;
                }
                break;
            }
            case 2: {
                if (this.listN_1.getSelectionModel().getSelectedIndex() == -1) {
                    errors(7);
                    return false;
                }
                if (this.tfAgregarVert.getText().isEmpty()) {
                    errors(9);
                    return false;
                }
                try {
                    int n = Integer.parseInt(this.tfAgregarVert.getText());
                } catch (NumberFormatException exception) {
                    errors(10);
                    return false;
                }
                break;
            }
            case 3: {
                if (this.listV_1.getSelectionModel().getSelectedIndex() == -1) {
                    errors(7);
                    return false;
                }
                break;
            }
            case 4: {
                if (this.cbInicio.getSelectionModel().getSelectedIndex() == -1) {
                    errors(8);
                    return false;
                }
                break;
            }
            case 5: {
                if (this.listN_1.getSelectionModel().getSelectedIndex() == -1) {
                    errors(7);
                    return false;
                }
                if (this.cbV1_1.getSelectionModel().getSelectedIndex() == -1 || this.cbV2_1.getSelectionModel().getSelectedIndex() == -1) {
                    errors(2);
                    return false;
                }
                break;
            }
            case 6: {
                if (this.listN_1.getSelectionModel().getSelectedIndex() == -1) {
                    errors(7);
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    /**
     * Errores de la aplicación
     *
     * @param tipo 0: Sin nombre, 1: Vértices vacíos, 2: Faltan vértices, 3: Mismo vértice, 4: Formato vértices, 5: Sin Precio, 6: Formato Precio, 7: Sin Selección, 8: Sin Inicio, 9: Sin vértice, 10: Formato Vértice
     */
    public void errors(int tipo) {
        String message = "error";
        switch (tipo) {
            case 0: {
                message = "Ingrese un nombre primero";
                break;
            }
            case 1: {
                message = "Falta el número de vértices";
                break;
            }
            case 2: {
                message = "Seleccione 2 vértices";
                break;
            }
            case 3: {
                message = "No se permiten loops en las matrices";
                break;
            }
            case 4: {
                message = "El número de vértices debe ser un entero positivo";
                break;
            }
            case 5: {
                message = "Ingrese un precio";
                break;
            }
            case 6: {
                message = "El precio debe ser un entero positivo";
                break;
            }
            case 7: {
                message = "No se ha seleccionado ningún elemento";
                break;
            }
            case 8: {
                message = "Seleccione la posición inicial";
                break;
            }
            case 9: {
                message = "Ingrese un vértice";
                break;
            }
            case 10: {
                message = "El vértice debe ser un entero";
                break;
            }
        }
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}