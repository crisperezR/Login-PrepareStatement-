package org.example.u2p7_logincss;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;

public class Controller {
    private Stage stage;
    @FXML private Label label;
    @FXML private TextField userField;
    @FXML private PasswordField passwordField;

    @FXML
    private void initialize(){
        label.requestFocus();
    }

    @FXML
    private void handleClose(MouseEvent event){
        stage.close();
    }

    @FXML
    private void handleLogin(ActionEvent event){
        String consult = "SELECT * FROM Users WHERE Users.username = ? AND Users.password = ?";
        try(
                Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/db/users.db");
                PreparedStatement statement = connection.prepareStatement(consult);
        ) {
            statement.setQueryTimeout(30);
            String queryUsername = userField.getText();
            String queryPassword = Hash.hashString(passwordField.getText());
            System.out.println(Hash.hashString(passwordField.getText()));

            statement.setString(1, queryUsername);
            statement.setString(2, queryPassword);

            ResultSet result = statement.executeQuery();
            if(result.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Inicio de sesion");
                alert.setHeaderText("Inicio de sesion exitoso");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inicio de sesion");
                alert.setHeaderText("No se pudo iniciar sesion");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }
}