package edu.westga.cs1302.bulletin_board.test.event;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Type;

class TestConstructor {

	@Test
	void testEmptyDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Event("", "Best Event Ever", LocalDate.of(2022, 12, 30), Type.MUSICAL);
		});
	}

}
