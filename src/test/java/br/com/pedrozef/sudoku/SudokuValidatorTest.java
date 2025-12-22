package br.com.pedrozef.sudoku;

import br.com.pedrozef.sudoku.model.SudokuBoard;
import br.com.pedrozef.sudoku.service.SudokuValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuValidatorTest {
	@Test
	void validMoveOnEmptyBoard() {
		SudokuBoard board = new SudokuBoard();
		SudokuValidator validator = new SudokuValidator();
		assertTrue(validator.isValidMove(board, 0, 0, 5));
	}
}