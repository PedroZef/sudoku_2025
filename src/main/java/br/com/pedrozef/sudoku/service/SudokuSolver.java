package br.com.pedrozef.sudoku.service;

import br.com.pedrozef.sudoku.model.SudokuBoard;

public class SudokuSolver {
    private final SudokuValidator validator = new SudokuValidator();

    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            for (int col = 0; col < SudokuBoard.SIZE; col++) {
                if (board.getValue(row, col) == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (validator.isValidMove(board, row, col, num)) {
                            board.setValue(row, col, num);
                            if (solve(board))
                                return true;
                            board.setValue(row, col, 0); // backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true; // sem vazios
    }
}