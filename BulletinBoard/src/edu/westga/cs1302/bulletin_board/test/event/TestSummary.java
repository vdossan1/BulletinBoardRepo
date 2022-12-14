package edu.westga.cs1302.bulletin_board.test.event;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Type;

class TestSummary {

	@Test
	void testWithAllFields() {
		Event newEvent = new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL);
		
		String summary = "Title: Concert" + System.lineSeparator()
							+ "Description: Best Event Ever" + System.lineSeparator()
							+ "Date: " + LocalDate.now() + System.lineSeparator()
							+ "Type: " + Type.MUSICAL;
		
		assertEquals(summary, newEvent.summary());
	}
	
	@Test
	void testEmptyDescription() {
		Event newEvent = new Event("Concert", "", LocalDate.now(), Type.MUSICAL);
		
		String summary = "Title: Concert" + System.lineSeparator()
							+ "Description: " + System.lineSeparator()
							+ "Date: " + LocalDate.now() + System.lineSeparator()
							+ "Type: " + Type.MUSICAL;
		
		assertEquals(summary, newEvent.summary());
	}

}
