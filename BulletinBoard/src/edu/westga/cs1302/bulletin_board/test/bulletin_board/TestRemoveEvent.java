package edu.westga.cs1302.bulletin_board.test.bulletin_board;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bulletin_board.model.BulletinBoard;
import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Type;

class TestRemoveEvent {

	@Test
	void testRemoveFromEmptyList() {
		BulletinBoard newBoard = new BulletinBoard();
		
		assertThrows(IllegalArgumentException.class, () -> {
			newBoard.removeEvent("Concert");
		});
	}
	
	@Test
	void testInvalidTitle() {
		BulletinBoard newBoard = new BulletinBoard();
		
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		
		assertThrows(IllegalArgumentException.class, () -> {
			newBoard.removeEvent("Second Concert");
		});
	}
	
	@Test
	void testRemoveValid() {
		BulletinBoard newBoard = new BulletinBoard();
		
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		
		assertTrue(newBoard.removeEvent("Concert"));
	}
	
	@Test
	void testRemoveFirstFromMultipleList() {
		BulletinBoard newBoard = new BulletinBoard();
		
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		
		assertTrue(newBoard.removeEvent("Concert"));
	}
	
	@Test
	void testRemoveSecondFromMultipleList() {
		BulletinBoard newBoard = new BulletinBoard();
		
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		
		assertTrue(newBoard.removeEvent("Second Concert"));
	}
	
	@Test
	void testRemoveThirdFromMultipleList() {
		BulletinBoard newBoard = new BulletinBoard();
		
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		
		assertTrue(newBoard.removeEvent("Third Concert"));
	}
	
	

}
