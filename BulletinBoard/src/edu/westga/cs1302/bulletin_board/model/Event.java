package edu.westga.cs1302.bulletin_board.model;

import java.time.LocalDate;

/**
 * Single Event
 * 
 * @author Vitor dos Santos
 * @version Fall 2022
 *
 */
public class Event {

	private String title;
	private String description;
	private LocalDate date;
	private Type type;
	
	/**
	 * Creates a new event
	 * 
	 * @precondition !title.isEmpty() && date >= LocalDate.now()
	 * @postcondition this.getTitle() == title && this.getDescription() == description
	 * 					&& this.getDate() == date this.Type() == type
	 * 
	 * @param title of the event
	 * @param description of the event
	 * @param date of the event
	 * @param type of the event
	 * 
	 */
	public Event(String title, String description, LocalDate date, Type type) {
		if (title.isEmpty()) {
			throw new IllegalArgumentException("Title cannot be empty");
		}
		
		if (date.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Date is invalid");
		}
		
		this.title = title;
		this.description = description;
		this.date = date;
		this.type = type;
	}

	/**
	 * Get the title of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the event's title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Get the description of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the event's description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get the date of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the event's date
	 */
	public LocalDate getDate() {
		return this.date;
	}

	/**
	 * Get the type of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the event's type
	 */
	public Type getType() {
		return this.type;
	}
}
