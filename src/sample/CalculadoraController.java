package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculadoraController {
    @FXML
    private Label result;
    private String num1 = "";
    private String num2 = "";
    private String operador = "";
    private boolean start = true;
    Cliente cliente = new Cliente();


    public void procesarNumeros(javafx.event.ActionEvent event) {
        if (start) {
            result.setText("");
            start = false;
        }
        String valor = ((Button) event.getSource()).getText();
        result.setText(result.getText() + valor);
    }


    public void procesarOperadores(javafx.event.ActionEvent event) {
        String valor = ((Button) event.getSource()).getText();
        if (!valor.equals("=")) {
            if (!operador.isEmpty()) {
                return;
            }
            operador = valor;
            num1 = result.getText();
            result.setText("");
        } else {
            if (operador.isEmpty()) {
                return;
            }
            num2 = result.getText();
            System.out.println(num1 + " " + num2 + " " + operador);
            String result1 = cliente.clienteCalcular(num1, num2, operador);
            result.setText(result1);
            start = true;
            operador = "";
            num2 = "";
        }
    }
}
