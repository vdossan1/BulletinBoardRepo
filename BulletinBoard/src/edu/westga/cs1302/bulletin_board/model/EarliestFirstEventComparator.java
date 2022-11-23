package edu.westga.cs1302.bulletin_board.model;

import java.util.Comparator;

/**
 * Comparator to sort events by date
 * 
 * @author Vitor dos Santos
 * @version Fall 2022
 *
 */
public class EarliestFirstEventComparator implements Comparator<Event> {

	@Override
	public int compare(Event eventOne, Event eventTwo) {
		if (eventOne.getDate().isBefore(eventTwo.getDate())) {
			return -1;
		} else if (eventTwo.getDate().isAfter(eventOne.getDate())) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return "Earliest First";
	}
}