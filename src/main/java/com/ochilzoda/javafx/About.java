package com.ochilzoda.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class About {
    private static Logger log = LoggerFactory.getLogger(About.class);

    private Stage aboutStage;
    @FXML
    public Label aboutLbl;
    @FXML
    public Button clsBtn;

    public void close(ActionEvent actionEvent) {
        ((Stage) clsBtn.getScene().getWindow()).close();
    }

    public void show(VBox aboutBtn){
        if (aboutStage != null) {
            aboutStage.show();
            log.info("aboutStage created");
        } else {
            aboutStage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("about.fxml"));
                Parent root = loader.load();

                aboutStage.setScene(new Scene(root, 600, 400));
                aboutStage.setTitle("About Application");
                aboutStage.initOwner(aboutBtn.getScene().getWindow());
                aboutStage.initModality(Modality.APPLICATION_MODAL);
                aboutStage.initStyle(StageStyle.UNDECORATED);
                aboutStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
