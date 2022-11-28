package edu.westga.cs1302.bulletin_board.viewmodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import edu.westga.cs1302.bulletin_board.model.BulletinBoard;
import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Type;
import edu.westga.cs1302.bulletin_board.model.comparator.EarliestFirstEventComparator;
import edu.westga.cs1302.bulletin_board.model.comparator.LatestFirstEventComparator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * ViewModel Class for the MainWindow
 * 
 * @author Vitor dos Santos
 * @version Fall 2022
 *
 */
public class MainWindowViewModel {

	private final StringProperty titleProperty;
	private final StringProperty descriptionProperty;
	private final ObjectProperty<LocalDate> dateProperty;
	
	private final ListProperty<Type> typeListProperty;
	private final ListProperty<Type> orderTypeListProperty;
	private final ObjectProperty<Type> selectedTypeProperty;
	private final ObjectProperty<Type> selectedOrderTypeProperty;
	
	private final ListProperty<Comparator<Event>> comparatorListProperty;
	private final ObjectProperty<Comparator<Event>> selectedComparatorProperty;
	
	private final ListProperty<Event> eventListProperty;
	private final ObjectProperty<Event> selectedEvent;
	
	private BooleanProperty eventListIsEmpty;
	
	private BulletinBoard board;
	
	/**
	 * Instantiates a new view model
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public MainWindowViewModel() {
		this.titleProperty = new SimpleStringProperty("");
		this.descriptionProperty = new SimpleStringProperty("");
		this.dateProperty = new SimpleObjectProperty<LocalDate>();
		
		this.typeListProperty = new SimpleListProperty<Type>(FXCollections.observableArrayList(Type.values()));
		this.orderTypeListProperty = new SimpleListProperty<Type>(FXCollections.observableArrayList(Type.values()));
		this.selectedTypeProperty = new SimpleObjectProperty<Type>();
		this.selectedOrderTypeProperty = new SimpleObjectProperty<Type>();
		
		ArrayList<Comparator<Event>> filterList = new ArrayList<Comparator<Event>>();
        filterList.add(null);
        filterList.add(new EarliestFirstEventComparator());
        filterList.add(new LatestFirstEventComparator());
        
		this.comparatorListProperty = new SimpleListProperty<Comparator<Event>>(FXCollections.observableArrayList(filterList));
		this.selectedComparatorProperty = new SimpleObjectProperty<Comparator<Event>>();
		
		this.eventListProperty = new SimpleListProperty<Event>(FXCollections.observableArrayList());
		this.selectedEvent = new SimpleObjectProperty<Event>();
		
		this.eventListIsEmpty = new SimpleBooleanProperty(this.eventListProperty.isEmpty());
		
		this.board = new BulletinBoard();
		
	}
	
	/**
	 * Add an event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public void addEvent() {
		String title = this.titleProperty.getValue();
		Type type = this.selectedTypeProperty.getValue();
		String description = this.descriptionProperty.getValue();
		LocalDate date = this.dateProperty.getValue();
		
		if (title.isEmpty()) {
			throw new IllegalArgumentException("Title cannot be empty"); 
		}
		
		if (type == null) {
			throw new IllegalArgumentException("Type cannot be null"); 
		}
		
		if (date == null) {
			throw new IllegalArgumentException("Date cannot be null");
		}
		
		this.board.addEvent(new Event(title, description, date, type));
		this.updateEventList();
		this.updateListIsEmpty();
	}
	
	/**
	 * Remove an event
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void removeEvent() {
		this.board.removeEvent(this.selectedEvent.getValue().getTitle());
		this.updateEventList();
		this.updateListIsEmpty();
	}
	
	/**
	 * Update the list by type and earliest/latest order
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public void updateListByTypeAndOrder() {
		this.eventListProperty.setAll(this.board.listOfEvents(this.selectedComparatorProperty.getValue(), this.selectedOrderTypeProperty.getValue()));
	}
	
	private void updateListIsEmpty() {
		this.eventListIsEmpty.setValue(this.eventListProperty.isEmpty());
	}

	private void updateEventList() {
		this.eventListProperty.setAll(this.board.listOfEvents(null, null));
	}
	
	/**
	 * Get the comparator list property
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of comparators property
	 */
	public ListProperty<Comparator<Event>> getComparatorListProperty() {
		return this.comparatorListProperty;
	}
	
	/**
	 * Get the selected comparator property
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the selected comparator property
	 */
	public ObjectProperty<Comparator<Event>> getSelectedComparatorProperty() {
		return this.selectedComparatorProperty;
	}
	
	/**
	 * Get the title property
	 * 
	 * @return the title property
	 */
	public StringProperty getTitleProperty() {
		return this.titleProperty;
	}

	/**
	 * Get the description property
	 * 
	 * @return the description property
	 */
	public StringProperty getDescriptionProperty() {
		return this.descriptionProperty;
	}

	/**
	 * Get the date property
	 * 
	 * @return the date property
	 */
	public ObjectProperty<LocalDate> getDateProperty() {
		return this.dateProperty;
	}

	/**
	 * Get the type property
	 * 
	 * @return the type property
	 */
	public ObjectProperty<Type> getSelectedTypeProperty() {
		return this.selectedTypeProperty;
	}

	/**
	 * Get the event list property
	 * 
	 * @return the event list  property
	 */
	public ListProperty<Event> getEventListProperty() {
		return this.eventListProperty;
	}

	/**
	 * Get the boolean value is the list is empty
	 * 
	 * @return the true if the list is empty, false if it is not
	 */
	public BooleanProperty getEventListIsEmpty() {
		return this.eventListIsEmpty;
	}
	
	/**
	 * Get the type list property
	 * 
	 * @return the type list  property
	 */
	public ListProperty<Type> getTypeListProperty() {
		return this.typeListProperty;
	}
	
	/**
	 * Get the order type list property
	 * 
	 * @return the order type list  property
	 */
	public ListProperty<Type> getOrderTypeListProperty() {
		return this.orderTypeListProperty;
	}
	
	/**
	 * Get the selected event property
	 * 
	 * @return the selected event property
	 */
	public ObjectProperty<Event> getSelectedEventProperty() {
		return this.selectedEvent;
	}
	
	/**
	 * Get the selected order type property
	 * 
	 * @return the selected order type property
	 */
	public ObjectProperty<Type> getSelectedOrderTypeProperty() {
		return this.selectedOrderTypeProperty;
	}
	
	
}
