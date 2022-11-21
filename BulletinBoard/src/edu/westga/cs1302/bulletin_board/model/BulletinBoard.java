package edu.westga.cs1302.bulletin_board.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Collection of Events
 * 
 * @author Vitor dos Santos
 * @version Fall 2022
 *
 */
public class BulletinBoard {
	private Map<String, Event> events;
	
	/**
	 * Create a new collection of events
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public BulletinBoard() {
		this.events = new HashMap<String, Event>();
	}
	
	/**
	 * Add a new title to the list
	 * 
	 * @precondition !this.events.containsKey(newEvent.getTitle());
	 * @postcondition this.events() + 1
	 * 
	 * @param newEvent event to add
	 * 
	 * @return true if event was added
	 */
	public boolean addEvent(Event newEvent) {
		
		if (this.events.containsKey(newEvent.getTitle())) {
			throw new IllegalArgumentException("Event with the same title already exists");
		}
		
		return this.events.put(newEvent.getTitle(), newEvent) == null;
	}
	
	/**
	 * Add a new title to the list
	 * 
	 * @precondition this.events.containsKey(event);
	 * @postcondition this.events() - event
	 * 
	 * @param event event to remove
	 * 
	 * @return true if event was removed
	 */
	public boolean removeEvent(String event) {
		if (this.events.isEmpty()) {
			throw new IllegalArgumentException("List is empty");
		}
		if (!this.events.containsKey(event)) {
			throw new IllegalArgumentException("Event is not on the list");
		}
		
		return this.events.remove(event) != null;
	}
	
	/**
	 * Returns a list of events that can be filtered by type
	 * and sorted by a comparator 
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param comparator the comparator to sort the events
	 * @param type the type of events to be sorted
	 * 
	 * @return the list of events filtered by the type
	 */
	public List<Event> listOfEvents(Comparator<Event> comparator, Type type) {
		
		List<Event> copyList = new ArrayList<Event>(this.events.values());
		
		if (comparator != null) {
			copyList.sort(comparator);
		}
		if (type != null) {
			for (Event event: copyList) {
				if (!event.getType().equals(type)) {
					copyList.remove(event);
				}
			}
		}
		return copyList;
	}
}
