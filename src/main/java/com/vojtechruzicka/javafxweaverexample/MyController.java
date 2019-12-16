package com.vojtechruzicka.javafxweaverexample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("main-stage.fxml")
public class MyController {
    private static Logger log = LoggerFactory.getLogger(MyController.class);

    public AnchorPane mainPane;
    public Pane noDataPane;
    public TabPane tabPane;
    public Tab fileId;
    public Tab helpId;
    public VBox aboutBtn;
    public VBox helpBtn;

    @FXML
    public ComboBox<Country> countriesComboBox;

    @Autowired
    private CountryService countryService;
    @Autowired
    private About aboutService;

    public void about(MouseEvent actionEvent) {
        this.aboutService.show(aboutBtn);
    }

    @FXML
    public void initialize() {
        countriesComboBox.setConverter(new CountryNameStringConverter());
        countriesComboBox.setItems(FXCollections.observableArrayList(countryService.getAllCountries()));
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
