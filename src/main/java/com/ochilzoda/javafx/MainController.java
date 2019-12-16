package com.ochilzoda.javafx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("main-stage.fxml")
public class MainController {
    private static Logger log = LoggerFactory.getLogger(MainController.class);
    private final FxWeaver fxWeaver;
    public VBox aboutBtn;
    @FXML
    public ComboBox<Country> countriesComboBox;
    @FXML
    private Button openSimpleDialogButton;
    @Autowired
    private CountryService countryService;
    @Autowired
    private About aboutService;


    public MainController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void about(MouseEvent actionEvent) {
        this.aboutService.show(aboutBtn);
    }

    @FXML
    public void initialize() {
        countriesComboBox.setConverter(new CountryNameStringConverter());
        countriesComboBox.setItems(FXCollections.observableArrayList(countryService.getAllCountries()));
        //openSimpleDialogButton.setOnAction(actionEvent -> fxWeaver.loadController(DialogController.class).show());
    }

    private static class CountryNameStringConverter extends StringConverter<Country> {
        @Override
        public String toString(Country object) {
            return object.getName();
        }

        @Override
        public Country fromString(String string) {
            return null;
        }
    }
}
