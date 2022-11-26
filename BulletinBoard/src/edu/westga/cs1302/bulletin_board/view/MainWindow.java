package edu.westga.cs1302.bulletin_board.view;


import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.ResourceBundle;

import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Type;
import edu.westga.cs1302.bulletin_board.viewmodel.MainWindowViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
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

    @FXML private ComboBox<Type> cmbTypeFilter;

    @FXML private ComboBox<Comparator<Event>> cmbOrderFilter;

    @FXML private DatePicker datePickerField;

    @FXML private TextField descriptionTextField;

    @FXML private ListView<Event> eventListView;

    @FXML private Button removeButton;

    @FXML private TextField titleTextField;
    
    @FXML private MenuItem removeContextMenu;
    
    private MainWindowViewModel vm;
    
    @FXML
    void initialize() {
    	this.vm = new MainWindowViewModel();
    	
        this.titleTextField.textProperty().bindBidirectional(this.vm.getTitleProperty());
        this.descriptionTextField.textProperty().bindBidirectional(this.vm.getDescriptionProperty());
        this.datePickerField.valueProperty().bindBidirectional(this.vm.getDateProperty());
        
        this.eventListView.setItems(this.vm.getEventListProperty());
        this.cmbAddType.setItems(this.vm.getTypeListProperty());
        this.cmbTypeFilter.setItems(this.vm.getOrderTypeListProperty());
        this.cmbTypeFilter.getItems().add(0, null);
        
        this.vm.getSelectedTypeProperty().bind(this.cmbAddType.getSelectionModel().selectedItemProperty());
        this.vm.getSelectedOrderTypeProperty().bind(this.cmbTypeFilter.getSelectionModel().selectedItemProperty());
        this.vm.getSelectedEventProperty().bind(this.eventListView.getSelectionModel().selectedItemProperty());
        
        this.cmbOrderFilter.setItems(this.vm.getComparatorListProperty());
        this.vm.getSelectedComparatorProperty().bind(this.cmbOrderFilter.getSelectionModel().selectedItemProperty());

        this.removeButton.disableProperty().bind(this.eventListView.getSelectionModel().selectedItemProperty().isNull());
        this.removeContextMenu = new MenuItem();
        this.removeContextMenu.disableProperty().bind(this.eventListView.getSelectionModel().selectedItemProperty().isNull());
        
        this.addEventButton.disableProperty().bind(this.titleTextField.textProperty().isEmpty());
        
        this.addListenerToDatePicker();
    }

    private void addListenerToDatePicker() {
    	this.datePickerField.valueProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			try {
        			if (newValue.isBefore(LocalDate.now())) {
            			Alert alert = new Alert(AlertType.INFORMATION);
                		alert.setTitle("Error");
                		alert.setHeaderText("Error Details");
                		alert.setContentText("This is an invalid date, please select a date at today or after");

                		alert.showAndWait();
            		}
        		} catch (NullPointerException e) {
        			System.out.println(e.getMessage());
        		}
    		}
    	});
    }

    @FXML
    void handleAddEvent(ActionEvent event) {
    	try {
    		this.vm.addEvent();
    	} catch (NullPointerException e) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText("Error Details");
    		alert.setContentText("Please select a valid date");

    		alert.showAndWait();
    	}
    }

    @FXML
    void handleRemoveEvent(ActionEvent event) {
    	try {
    		this.vm.removeEvent();
    	} catch (NullPointerException e) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText("Error Details");
    		alert.setContentText("The list is empty, please add an event");

    		alert.showAndWait();
    	}
    }

    @FXML
    void orderList(ActionEvent event) {
    	try {
    		this.vm.updateListByTypeAndOrder();
    	} catch (Exception e) {
    		
    	}
    }
    
    @FXML
    void handleFilterByType(ActionEvent event) {
    	try {
    		this.vm.updateListByTypeAndOrder();
    	} catch (Exception e) {
    		
    	}
    }
    
    @FXML
    void removeEventContextMenu(ActionEvent event) {
    	try {
    		this.vm.removeEvent();
    	} catch (NullPointerException e) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText("Error Details");
    		alert.setContentText("The list is empty, please add an event");

    		alert.showAndWait();
    	}
    }
    
    @FXML
    void displayEventDetails(ActionEvent event) {
    	try {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Event Details");
    		alert.setHeaderText("Those are the details for the selected event");
    		alert.setContentText(this.vm.getSelectedEventProperty().getValue().summary());

    		alert.showAndWait();
    	} catch (Exception e) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText("Error Details");
    		alert.setContentText("The list is empty, please add an event");

    		alert.showAndWait();
    	}
    }

}