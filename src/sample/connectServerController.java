package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class connectServerController implements Initializable {

    public TextField puerto;
    public TextField ip;
    public Button bDisconnect;
    public Button bConnect;
    Cliente cliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buttonConnect(javafx.event.ActionEvent event) throws IOException {
        if (validateIP(ip.getText()) && validatePort(puerto.getText())) {

            System.out.println(ip.getText() + puerto.getText());
            cliente = new Cliente();
            boolean comp = cliente.clienteConnect(ip.getText(), Integer.parseInt(puerto.getText()));
            if (comp == false) {
                return;
            }
            Stage stage2 = (Stage) bConnect.getScene().getWindow();
            stage2.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("calculadora.fxml").openStream());
            stage.setScene(new Scene(root, 300, 300));
            stage.showAndWait();
        } else if (!validatePort(puerto.getText())) {
            System.out.println("El puerto es erróneo.");
        } else if (!validateIP(ip.getText())) {
            System.out.println("La ip es errónea.");
        }
    }

    public boolean validateIP(String ip) {
        Pattern pattern;
        Matcher matcher;
        String IPADDRESS_PATTERN
                = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        pattern = Pattern.compile(IPADDRESS_PATTERN);
        matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    public boolean validatePort(String port) {
        Pattern pattern;
        Matcher matcher;
        String PORT_PATTERN
                = "^()([1-9]|[1-5]?[0-9]{2,4}|6[1-4][0-9]{3}|65[1-4][0-9]{2}|655[1-2][0-9]|6553[1-5])$";
        pattern = Pattern.compile(PORT_PATTERN);
        matcher = pattern.matcher(port);
        return matcher.matches();
    }
}
