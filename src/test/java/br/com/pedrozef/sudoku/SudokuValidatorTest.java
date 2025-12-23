package br.com.pedrozef.sudoku;

import br.com.pedrozef.sudoku.model.SudokuBoard;
import br.com.pedrozef.sudoku.service.SudokuValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuValidatorTest {

    private SudokuValidator validator;
    private SudokuBoard board;

    @BeforeEach
    void setUp() {
        validator = new SudokuValidator();
        board = new SudokuBoard();
    }

    @Test
    void testIsValidMove_EmptyBoard() {
        assertTrue(validator.isValidMove(board, 0, 0, 1), "Should be valid to place any number on an empty board");
    }

    @Test
    void testIsValidMove_RowConflict() {
        board.setValue(0, 0, 5);
        assertFalse(validator.isValidMove(board, 0, 8, 5), "Should be invalid due to row conflict");
    }

    @Test
    void testIsValidMove_ColConflict() {
        board.setValue(0, 0, 5);
        assertFalse(validator.isValidMove(board, 8, 0, 5), "Should be invalid due to column conflict");
    }

    @Test
    void testIsValidMove_BoxConflict() {
        board.setValue(0, 0, 5);
        assertFalse(validator.isValidMove(board, 1, 1, 5), "Should be invalid due to 3x3 box conflict");
    }

    @Test
    void testIsValidMove_ValidMoveOnPartiallyFilledBoard() {
        board.setValue(0, 0, 1);
        board.setValue(0, 1, 2);
        board.setValue(1, 0, 3);
        board.setValue(1, 1, 4);
        assertTrue(validator.isValidMove(board, 0, 2, 5), "This move should be valid");
    }
    
    @Test
    void testIsValidMove_NumberAlreadyInCell() {
        board.setValue(0, 0, 5);
        assertTrue(validator.isValidMove(board, 0, 0, 5), "Checking the same cell should not cause a conflict");
    }

    @Test
    void testIsSolved_EmptyBoard() {
        assertFalse(validator.isSolved(board), "Empty board should not be considered solved");
    }

    @Test
    void testIsSolved_IncompleteBoard() {
        board.setValue(0, 0, 1); // Partially filled board
        assertFalse(validator.isSolved(board), "Incomplete board should not be considered solved");
    }

    @Test
    void testIsSolved_FullButIncorrectBoard() {
        // Fill board with 1s, which is an invalid solution
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.setValue(i, j, 1);
            }
        }
        assertFalse(validator.isSolved(board), "A full but incorrect board should not be solved");
    }

    @Test
    void testIsSolved_CorrectBoard() {
        int[][] solvedPuzzle = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.setValue(i, j, solvedPuzzle[i][j]);
            }
        }
        assertTrue(validator.isSolved(board), "A correctly solved board should be considered solved");
    }
}
