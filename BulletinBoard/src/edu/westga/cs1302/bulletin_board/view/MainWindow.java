package edu.westga.cs1302.bulletin_board.view;


import java.net.URL;
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

    }

    @FXML
    void handleAddEvent(ActionEvent event) {
    	try {
    		this.vm.addEvent();
    	} catch (Exception e) {
    		
    	}
    }

    @FXML
    void handleRemoveEvent(ActionEvent event) {
    	try {
    		this.vm.removeEvent();
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
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
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
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
    		
    	}
    }

}