package edu.westga.cs1302.bulletin_board.model.comparator;

import java.util.Comparator;

import edu.westga.cs1302.bulletin_board.model.Event;

/**
 * Comparator to sort events by date
 * 
 * @author Vitor dos Santos
 * @version Fall 2022
 *
 */
public class LatestFirstEventComparator implements Comparator<Event> {

	@Override
	public int compare(Event eventOne, Event eventTwo) {
		if (eventOne.getDate().isAfter(eventTwo.getDate())) {
			return -1;
		} else if (eventTwo.getDate().isBefore(eventOne.getDate())) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return "Latest First";
	}
}
