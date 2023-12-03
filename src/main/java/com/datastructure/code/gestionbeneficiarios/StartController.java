package com.datastructure.code.gestionbeneficiarios;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartController {



    public void initialize() {

    }

    public void OnAddPersonButtonPressed(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPerson.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage secondStage = new Stage();
            secondStage.initModality(Modality.APPLICATION_MODAL); // Configurar modal
            secondStage.setTitle("Segundo FXML");
            secondStage.setResizable(false);
            secondStage.initStyle(StageStyle.UNDECORATED);
            secondStage.setScene(scene);
            double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
            secondStage.setY(screenHeight - 430);
            double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
            secondStage.setX(500);
            secondStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}