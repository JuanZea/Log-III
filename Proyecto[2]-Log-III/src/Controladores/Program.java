package Controladores;

import Modelos.ArbolBinario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Program extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/Ventana.fxml"));
        primaryStage.setTitle("Proyecto [2]");
        Scene scene = new Scene(root);
        Image icon = new Image(getClass().getResourceAsStream("/Vistas/img/icon.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
