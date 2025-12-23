package br.com.pedrozef.sudoku;

import br.com.pedrozef.sudoku.model.SudokuBoard;
import br.com.pedrozef.sudoku.service.SudokuSolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolverTest {

    private SudokuSolver solver;
    private SudokuBoard board;

    @BeforeEach
    void setUp() {
        solver = new SudokuSolver();
        board = new SudokuBoard();
    }

    @Test
    void testSolve_EmptyBoard() {
        assertTrue(solver.solve(board), "An empty board should be solvable");
        // After solving, the board should be valid
        assertTrue(new br.com.pedrozef.sudoku.service.SudokuValidator().isSolved(board), "The solved board should be valid");
    }

    @Test
    void testSolve_SolvableBoard() {
        int[][] puzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        board.setBoard(puzzle);

        int[][] solution = {
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

        assertTrue(solver.solve(board), "This board should be solvable");
        assertArrayEquals(solution, board.getBoard(), "The solved board should match the solution");
    }

    @Test
    void testSolve_UnsolvableBoard() {
        int[][] puzzle = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 8} // Invalid board (two 8s in last row)
        };
        board.setBoard(puzzle);
        
        assertFalse(solver.solve(board), "This board should be unsolvable");
    }

    @Test
    void testCountSolutions_UniqueSolution() {
        int[][] puzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5}, {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        board.setBoard(puzzle);
        assertEquals(1, solver.countSolutions(board), "A standard puzzle should have a unique solution");
    }

    @Test
    void testCountSolutions_NoSolution() {
        int[][] puzzle = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2}, {6, 7, 2, 1, 9, 5, 3, 4, 8}, {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3}, {4, 2, 6, 8, 5, 3, 7, 9, 1}, {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4}, {2, 8, 7, 4, 1, 9, 6, 3, 5}, {3, 4, 5, 2, 8, 6, 1, 7, 8} // Invalid
        };
        board.setBoard(puzzle);
        assertEquals(0, solver.countSolutions(board), "An unsolvable puzzle should have 0 solutions");
    }

    @Test
    void testCountSolutions_MultipleSolutions() {
        // This board is almost empty, it will have many solutions
        int[][] puzzle = new int[9][9];
        puzzle[0][0] = 5; 
        board.setBoard(puzzle);
        assertTrue(solver.countSolutions(board) > 1, "A very sparse puzzle should have multiple solutions");
    }
}
