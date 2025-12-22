package br.com.pedrozef.sudoku;


import br.com.pedrozef.sudoku.model.SudokuBoard;
import br.com.pedrozef.sudoku.service.SudokuSolver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolverTest {
    @Test
    void solveSimpleBoard() {
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new SudokuSolver();
        assertTrue(solver.solve(board)); // tabuleiro vazio é solucionável
    }
}