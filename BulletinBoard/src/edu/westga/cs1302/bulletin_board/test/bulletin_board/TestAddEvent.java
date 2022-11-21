package edu.westga.cs1302.bulletin_board.test.bulletin_board;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bulletin_board.model.BulletinBoard;
import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Type;

class TestAddEvent {

	@Test
	void testAddEmptyTitle() {
		
		BulletinBoard newBoard = new BulletinBoard();
		
		assertThrows(IllegalArgumentException.class, () -> {
			newBoard.addEvent(new Event("", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		});
	}
	
	@Test
	void testAddPastDate() {
		
		BulletinBoard newBoard = new BulletinBoard();
		
		assertThrows(IllegalArgumentException.class, () -> {
			newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.of(2021, 10, 10), Type.MUSICAL));
		});
	}
	
	@Test
	void testAddInvalidDuplicateName() {
		
		BulletinBoard newBoard = new BulletinBoard();
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		
		assertThrows(IllegalArgumentException.class, () -> {
			newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		});
	}
	
	@Test
	void testAddValidEvent() {
		
		BulletinBoard newBoard = new BulletinBoard();
		
		assertTrue(newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL)));	
	}
	
	@Test
	void testAddMultipleValidEvents() {
		
		BulletinBoard newBoard = new BulletinBoard();
		
		assertTrue(newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL)));
		assertTrue(newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL)));
		assertTrue(newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL)));
	}
	
	@Test
	void testAddMultipleOneInvalid() {
		
		BulletinBoard newBoard = new BulletinBoard();
		
		assertTrue(newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL)));
		assertTrue(newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL)));
		assertThrows(IllegalArgumentException.class, () -> {
			newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		});
	}

}
