package edu.westga.cs1302.bulletin_board.test.bulletin_board;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bulletin_board.model.BulletinBoard;
import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Type;
import edu.westga.cs1302.bulletin_board.model.comparator.EarliestFirstEventComparator;
import edu.westga.cs1302.bulletin_board.model.comparator.LatestFirstEventComparator;

class TestListOfEvents {
	
	@Test
	void testListMultipleNoOrderNoType() {
		BulletinBoard newBoard = new BulletinBoard();
		
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.POLITICAL));
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.POLITICAL));
		
		List<Event> actualList = newBoard.listOfEvents(null, null);

		assertFalse(actualList.get(0).toString().isEmpty());
		assertFalse(actualList.get(1).toString().isEmpty());
		assertFalse(actualList.get(2).toString().isEmpty());
	}

	@Test
	void testListWithMultipleOrderEarliestNoType() {
		BulletinBoard newBoard = new BulletinBoard();
		EarliestFirstEventComparator earliestComparator = new EarliestFirstEventComparator();
		
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.MUSICAL));
		
		List<Event> actualList = newBoard.listOfEvents(earliestComparator, null);
		
		List<Event> expected = new ArrayList<Event>();
		expected.add(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.MUSICAL));
		expected.add(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.MUSICAL));
		expected.add(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.MUSICAL));
		
		assertEquals(expected.toString(), actualList.toString());
	}
	
	@Test
	void testListWithMultipleOrderEarliestPoliticalTypeNoReturn() {
		BulletinBoard newBoard = new BulletinBoard();
		EarliestFirstEventComparator earliestComparator = new EarliestFirstEventComparator();
		
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.MUSICAL));
		
		List<Event> actualList = newBoard.listOfEvents(earliestComparator, Type.POLITICAL);
		
		List<Event> expected = new ArrayList<Event>();
		
		assertEquals(expected.toString(), actualList.toString());
	}
	
	@Test
	void testListWithMultipleOrderEarliestPoliticalTypeReturn() {
		BulletinBoard newBoard = new BulletinBoard();
		EarliestFirstEventComparator earliestComparator = new EarliestFirstEventComparator();
		
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.POLITICAL));
		
		List<Event> actualList = newBoard.listOfEvents(earliestComparator, Type.POLITICAL);
		
		List<Event> expected = new ArrayList<Event>();
		expected.add(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.POLITICAL));
		
		assertEquals(expected.toString(), actualList.toString());
	}
	
	@Test
	void testListWithMultipleNoOrderPoliticalTypeReturn() {
		BulletinBoard newBoard = new BulletinBoard();
		
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.POLITICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.POLITICAL));
		
		List<Event> actualList = newBoard.listOfEvents(null, Type.POLITICAL);
		
		List<Event> expected = new ArrayList<Event>();
		expected.add(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.POLITICAL));
		expected.add(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.POLITICAL));
		
		assertEquals(expected.toString(), actualList.toString());
	}
	
	@Test
	void testListWithMultipleOrderLatestNoType() {
		BulletinBoard newBoard = new BulletinBoard();
		LatestFirstEventComparator latestComparator = new LatestFirstEventComparator();
		
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.MUSICAL));
		
		List<Event> actualList = newBoard.listOfEvents(latestComparator, null);
		
		List<Event> expected = new ArrayList<Event>();
		expected.add(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.MUSICAL));
		expected.add(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.MUSICAL));
		expected.add(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.MUSICAL));
		
		assertEquals(expected.toString(), actualList.toString());
	}
	
	@Test
	void testListWithMultipleOrderLatestTypePoliticalOneReturn() {
		BulletinBoard newBoard = new BulletinBoard();
		LatestFirstEventComparator latestComparator = new LatestFirstEventComparator();
		
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.POLITICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.MUSICAL));
		
		List<Event> actualList = newBoard.listOfEvents(latestComparator, Type.POLITICAL);
		
		List<Event> expected = new ArrayList<Event>();
		expected.add(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.MUSICAL));
		
		assertEquals(expected.toString(), actualList.toString());
	}
	
	@Test
	void testListWithMultipleOrderLatestTypePoliticalMultipleReturn() {
		BulletinBoard newBoard = new BulletinBoard();
		LatestFirstEventComparator latestComparator = new LatestFirstEventComparator();
		
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.of(2023, 1, 10), Type.MUSICAL));
		newBoard.addEvent(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.POLITICAL));
		newBoard.addEvent(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.POLITICAL));
		
		List<Event> actualList = newBoard.listOfEvents(latestComparator, Type.POLITICAL);
		
		List<Event> expected = new ArrayList<Event>();
		expected.add(new Event("Third Concert", "Best Event Ever", LocalDate.of(2023, 3, 10), Type.POLITICAL));
		expected.add(new Event("Second Concert", "Best Event Ever", LocalDate.of(2023, 2, 10), Type.POLITICAL));
		
		assertEquals(expected.toString(), actualList.toString());
	}
}
