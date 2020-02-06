package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class connectServerController implements Initializable {

    public TextField puerto;
    public TextField ip;
    Cliente cliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buttonConnect(javafx.event.ActionEvent event) throws IOException {
        System.out.println(ip.getText() + puerto.getText());
        cliente = new Cliente();
        cliente.clienteConnect(ip.getText(), Integer.parseInt(puerto.getText()));

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("calculadora.fxml").openStream());
        stage.setScene(new Scene(root, 300, 300));
        stage.showAndWait();
    }

    public void buttonDisconnect(javafx.event.ActionEvent event) throws IOException {
        cliente = new Cliente();
        cliente.clienteDisconnect(ip.getText(), Integer.parseInt(puerto.getText()));
    }
}
