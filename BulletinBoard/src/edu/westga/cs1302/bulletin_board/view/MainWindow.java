package edu.westga.cs1302.bulletin_board.view;


import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2022
 */
public class MainWindow {

    @FXML private ResourceBundle resources;

    @FXML private URL location;

    @FXML private Button addEventButton;

    @FXML private ComboBox<Type> cmbAddType;

    @FXML private ComboBox<Comparator<Event>> cmbOrderFilter;

    @FXML private ComboBox<Type> cmbTypeFilter;

    @FXML private DatePicker datePickerField;

    @FXML private TextField descriptionTextField;

    @FXML private ListView<Event> eventListView;

    @FXML private Button removeButton;

    @FXML private TextField titleTextField;

    @FXML
    void filterType(ActionEvent event) {

    }

    @FXML
    void handleAddEvent(ActionEvent event) {
    	System.out.println("Add event");
    }

    @FXML
    void handleRemoveEvent(ActionEvent event) {

    }

    @FXML
    void orderList(ActionEvent event) {

    }

    @FXML
    void selectedType(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert this.addEventButton != null : "fx:id=\"addEventButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.cmbAddType != null : "fx:id=\"cmbAddType\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.cmbOrderFilter != null : "fx:id=\"cmbOrderFilter\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.cmbTypeFilter != null : "fx:id=\"cmbTypeFilter\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.datePickerField != null : "fx:id=\"datePickerField\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.descriptionTextField != null : "fx:id=\"descriptionTextField\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.eventListView != null : "fx:id=\"eventListView\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.titleTextField != null : "fx:id=\"titleTextField\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }

}