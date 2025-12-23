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

    

    public int countSolutions(SudokuBoard board) {
        return countSolutionsRecursive(board);
    }

    private int countSolutionsRecursive(SudokuBoard board) {
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            for (int col = 0; col < SudokuBoard.SIZE; col++) {
                if (board.getValue(row, col) == 0) {
                    int count = 0;
                    for (int num = 1; num <= 9; num++) {
                        if (validator.isValidMove(board, row, col, num)) {
                            board.setValue(row, col, num);
                            count += countSolutionsRecursive(board);
                            board.setValue(row, col, 0); // backtrack
                            if (count > 1) { // Otimização: se encontrarmos mais de uma, podemos parar
                                return count;
                            }
                        }
                    }
                    return count;
                }
            }
        }
        return 1; // Encontrou uma solução completa
    }
}