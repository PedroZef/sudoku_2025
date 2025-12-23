package br.com.pedrozef.sudoku;

import br.com.pedrozef.sudoku.model.Difficulty;
import br.com.pedrozef.sudoku.model.SudokuBoard;
import br.com.pedrozef.sudoku.service.SudokuGenerator;
import br.com.pedrozef.sudoku.service.SudokuSolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuGeneratorTest {

    private SudokuGenerator generator;
    private SudokuSolver solver;

    @BeforeEach
    void setUp() {
        generator = new SudokuGenerator();
        solver = new SudokuSolver();
    }

    @Test
    void testGenerate_ReturnsValidPuzzle() {
        SudokuBoard board = generator.generate(Difficulty.EASY);
        assertNotNull(board, "Generated board should not be null");
        assertFalse(board.isFull(), "Generated puzzle should not be full");

        int fixedCells = 0;
        for (int r = 0; r < SudokuBoard.SIZE; r++) {
            for (int c = 0; c < SudokuBoard.SIZE; c++) {
                if (board.getCell(r, c).isFixed()) {
                    fixedCells++;
                }
            }
        }
        assertTrue(fixedCells > 0, "Generated puzzle should have some fixed cells (clues)");
    }

    @Test
    void testGenerate_HasUniqueSolution_Easy() {
        SudokuBoard board = generator.generate(Difficulty.EASY);
        int solutions = solver.countSolutions(new SudokuBoard(board));
        assertEquals(1, solutions, "Easy puzzle should have a unique solution");
    }

    @Test
    void testGenerate_HasUniqueSolution_Medium() {
        SudokuBoard board = generator.generate(Difficulty.MEDIUM);
        int solutions = solver.countSolutions(new SudokuBoard(board));
        assertEquals(1, solutions, "Medium puzzle should have a unique solution");
    }

    @Test
    void testGenerate_HasUniqueSolution_Hard() {
        SudokuBoard board = generator.generate(Difficulty.HARD);
        int solutions = solver.countSolutions(new SudokuBoard(board));
        assertEquals(1, solutions, "Hard puzzle should have a unique solution");
    }

    @Test
    void testGenerate_DifficultyLevels_EmptyCells() {
        SudokuBoard easyBoard = generator.generate(Difficulty.EASY);
        SudokuBoard mediumBoard = generator.generate(Difficulty.MEDIUM);
        SudokuBoard hardBoard = generator.generate(Difficulty.HARD);

        long easyEmptyCells = countEmptyCells(easyBoard);
        long mediumEmptyCells = countEmptyCells(mediumBoard);
        long hardEmptyCells = countEmptyCells(hardBoard);

        // The number of removals is fixed, so we can check for exact numbers
        assertEquals(40, easyEmptyCells, "Easy board should have 40 empty cells");
        assertEquals(50, mediumEmptyCells, "Medium board should have 50 empty cells");
        assertEquals(60, hardEmptyCells, "Hard board should have 60 empty cells");

        assertTrue(easyEmptyCells < mediumEmptyCells, "Easy should have fewer empty cells than Medium");
        assertTrue(mediumEmptyCells < hardEmptyCells, "Medium should have fewer empty cells than Hard");
    }

    private long countEmptyCells(SudokuBoard board) {
        long count = 0;
        for (int r = 0; r < SudokuBoard.SIZE; r++) {
            for (int c = 0; c < SudokuBoard.SIZE; c++) {
                if (board.getValue(r, c) == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
