package Controladores;

import Modelos.Arbol;
import Modelos.ArbolBinario;
import Modelos.ListaGeneralizada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<ListaGeneralizada> lg = new ArrayList<>();
    private ObservableList<String> nomLg = FXCollections.observableArrayList();

    private ArrayList<Arbol> a = new ArrayList<>();
    private ObservableList<String> nomA = FXCollections.observableArrayList();

    private ArrayList<ArbolBinario> ac = new ArrayList<>();
    private ObservableList<String> nomAc = FXCollections.observableArrayList();
    private ObservableList<String> strAc = FXCollections.observableArrayList();

    private ArrayList<ArbolBinario> ab = new ArrayList<>();
    private ObservableList<String> nomAb = FXCollections.observableArrayList();

    public void guardarLg(String nom, ListaGeneralizada lg) {
        this.lg.add(lg);
        this.nomLg.add(nom);
    }

    public ListaGeneralizada retornaLg(int i) {
        return this.lg.get(i);
    }

    public ObservableList<String> retornaNombresLg() {
        return this.nomLg;
    }

    public void guardarA(String nom, Arbol a) {
        this.a.add(a);
        this.nomA.add(nom);
    }

    public Arbol retornaA(int i) {
        return this.a.get(i);
    }

    public ObservableList<String> retornaNombresA() {
        return this.nomA;
    }

    public void guardarAb(String nom, ArbolBinario a) {
        this.ab.add(a);
        this.nomAb.add(nom);
    }

    public ArbolBinario retornaAb(int i) {
        return this.ab.get(i);
    }

    public ObservableList<String> retornaNombresAb() {
        return this.nomAb;
    }

    public void guardarAc(String nom, ArbolBinario a) {
        this.ac.add(a);
        this.nomAc.add(nom);
    }

    public ArbolBinario retornaAc(int i) {
        return this.ac.get(i);
    }

    public ObservableList<String> retornaNombresAc() {
        return this.nomAc;
    }
}
