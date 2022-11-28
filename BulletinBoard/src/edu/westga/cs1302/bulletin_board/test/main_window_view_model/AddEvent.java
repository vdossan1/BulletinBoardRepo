package edu.westga.cs1302.bulletin_board.test.main_window_view_model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bulletin_board.model.Type;
import edu.westga.cs1302.bulletin_board.viewmodel.MainWindowViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class AddEvent {
	private static final String TITLE_SUMMARY = "Title: TITLE\r\n";
	private static final String DESCRIPTION_SUMMARY = "Description: DESCRIPTION\r\n";
	private static final String DESCRIPTION_EMPTY_SUMMARY = "Description: \r\n";
	private static final String DATE_SUMMARY = "Date: " + LocalDate.now() + System.lineSeparator();
	private static final String TYPE_MUSICAL_SUMMARY = "Type: MUSICAL";
	
	private static final String TITLE = "TITLE";
	private static final String DESCRIPTION = "DESCRIPTION";
	
	private MainWindowViewModel vm;
	private StringProperty titleProperty;
	private StringProperty descriptionProperty;
	private ObjectProperty<LocalDate> dateProperty;
	private ObjectProperty<Type> typeProperty;
	
	@BeforeEach
	void setupProperties() {
		this.vm = new MainWindowViewModel();
		this.titleProperty = new SimpleStringProperty("");
		this.descriptionProperty = new SimpleStringProperty("");
		this.dateProperty = new SimpleObjectProperty<LocalDate>();
		this.typeProperty = new SimpleObjectProperty<Type>();
		
		this.vm.getTitleProperty().bindBidirectional(this.titleProperty);
		this.vm.getDescriptionProperty().bindBidirectional(this.descriptionProperty);
		this.vm.getDateProperty().bind(this.dateProperty);
		this.vm.getSelectedTypeProperty().bind(this.typeProperty);
	}

	@Test
	void testAddNewValidEventNoDescription() {
		
		this.titleProperty.setValue(TITLE);
		this.dateProperty.setValue(LocalDate.now());
		this.typeProperty.setValue(Type.MUSICAL);
		
		this.vm.addEvent();
		
		String actual = this.vm.getEventListProperty().get(0).summary();
		
		String expected = TITLE_SUMMARY
				+ DESCRIPTION_EMPTY_SUMMARY
				+ DATE_SUMMARY 
				+ TYPE_MUSICAL_SUMMARY;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddNewValidEventDescription() {
		
		this.titleProperty.setValue(TITLE);
		this.descriptionProperty.setValue(DESCRIPTION);
		this.dateProperty.setValue(LocalDate.now());
		this.typeProperty.setValue(Type.MUSICAL);
		
		this.vm.addEvent();
		
		String actual = this.vm.getEventListProperty().get(0).summary();
		
		String expected = TITLE_SUMMARY
				+ DESCRIPTION_SUMMARY
				+ DATE_SUMMARY 
				+ TYPE_MUSICAL_SUMMARY;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddInvalidEventEmptyTitle() {
		MainWindowViewModel vm = new MainWindowViewModel();
		
		StringProperty titleProperty = new SimpleStringProperty("");
		StringProperty descriptionProperty = new SimpleStringProperty("");
		ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		ObjectProperty<Type> typeProperty = new SimpleObjectProperty<Type>(Type.MUSICAL);
		
		vm.getTitleProperty().bindBidirectional(titleProperty);
		vm.getDescriptionProperty().bindBidirectional(descriptionProperty);
		vm.getDateProperty().bind(dateProperty);
		vm.getSelectedTypeProperty().bind(typeProperty);
		
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addEvent();
		});
	}
	
	@Test
	void testAddInvalidEventNullType() {
		MainWindowViewModel vm = new MainWindowViewModel();
		
		StringProperty titleProperty = new SimpleStringProperty(TITLE);
		StringProperty descriptionProperty = new SimpleStringProperty("");
		ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		ObjectProperty<Type> typeProperty = new SimpleObjectProperty<Type>(null);
		
		vm.getTitleProperty().bindBidirectional(titleProperty);
		vm.getDescriptionProperty().bindBidirectional(descriptionProperty);
		vm.getDateProperty().bind(dateProperty);
		vm.getSelectedTypeProperty().bind(typeProperty);
		
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addEvent();
		});
	}
	
	@Test
	void testAddInvalidEventPastDate() {
		MainWindowViewModel vm = new MainWindowViewModel();
		
		StringProperty titleProperty = new SimpleStringProperty(TITLE);
		StringProperty descriptionProperty = new SimpleStringProperty("");
		ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<LocalDate>(LocalDate.of(2021, 10, 1));
		ObjectProperty<Type> typeProperty = new SimpleObjectProperty<Type>(Type.MUSICAL);
		
		vm.getTitleProperty().bindBidirectional(titleProperty);
		vm.getDescriptionProperty().bindBidirectional(descriptionProperty);
		vm.getDateProperty().bind(dateProperty);
		vm.getSelectedTypeProperty().bind(typeProperty);
		
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addEvent();
		});
	}
	
	@Test
	void testAddInvalidEventMultipleTitle() {
		MainWindowViewModel vm = new MainWindowViewModel();
		
		StringProperty titleProperty = new SimpleStringProperty(TITLE);
		StringProperty descriptionProperty = new SimpleStringProperty("");
		ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		ObjectProperty<Type> typeProperty = new SimpleObjectProperty<Type>(Type.MUSICAL);
		
		vm.getTitleProperty().bindBidirectional(titleProperty);
		vm.getDescriptionProperty().bindBidirectional(descriptionProperty);
		vm.getDateProperty().bind(dateProperty);
		vm.getSelectedTypeProperty().bind(typeProperty);
		
		vm.addEvent();
		
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addEvent();
		});
	}
	
	@Test
	void testAddInvalidEventNullDate() {
		MainWindowViewModel vm = new MainWindowViewModel();
		
		StringProperty titleProperty = new SimpleStringProperty(TITLE);
		StringProperty descriptionProperty = new SimpleStringProperty("");
		ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<LocalDate>(null);
		ObjectProperty<Type> typeProperty = new SimpleObjectProperty<Type>(Type.MUSICAL);
		
		vm.getTitleProperty().bindBidirectional(titleProperty);
		vm.getDescriptionProperty().bindBidirectional(descriptionProperty);
		vm.getDateProperty().bind(dateProperty);
		vm.getSelectedTypeProperty().bind(typeProperty);
		
		assertThrows(IllegalArgumentException.class, () -> {
			vm.addEvent();
		});
	}

}
