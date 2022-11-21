package edu.westga.cs1302.bulletin_board.test.bulletin_board;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bulletin_board.model.BulletinBoard;
import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Type;

class TestListOfEvents {

	@Test
	void testListWithOneValueNoComparatorNoType() {
		BulletinBoard newBoard = new BulletinBoard();
		newBoard.addEvent(new Event("Concert", "Best Event Ever", LocalDate.now(), Type.MUSICAL));
		
	}

}
