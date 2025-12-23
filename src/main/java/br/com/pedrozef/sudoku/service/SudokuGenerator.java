package br.com.pedrozef.sudoku.service;



import br.com.pedrozef.sudoku.model.Difficulty;
import br.com.pedrozef.sudoku.model.SudokuBoard;

import java.util.Random;

public class SudokuGenerator {
    private final SudokuSolver solver = new SudokuSolver();
    private final SudokuValidator validator = new SudokuValidator();
    private final Random random = new Random();

    public SudokuBoard generate(Difficulty difficulty) {
        SudokuBoard board = new SudokuBoard();
        // Passo 1: gerar solução completa inicial
        fillDiagonalBlocks(board);
        solver.solve(board);

        // Passo 2: remover números garantindo que a solução seja única
        int removals = switch (difficulty) {
            case EASY -> 40;
            case MEDIUM -> 50;
            case HARD -> 60; // Máximo de 64 remoções para garantir uma única solução
        };

        int cellsToRemove = removals;
        java.util.List<Integer> positions = new java.util.ArrayList<>();
        for (int i = 0; i < SudokuBoard.SIZE * SudokuBoard.SIZE; i++) {
            positions.add(i);
        }
        java.util.Collections.shuffle(positions, random);

        for (int pos : positions) {
            if (cellsToRemove == 0) break;

            int r = pos / SudokuBoard.SIZE;
            int c = pos % SudokuBoard.SIZE;

            if (board.getValue(r, c) != 0) {
                int backup = board.getValue(r, c);
                board.setValue(r, c, 0);

                SudokuBoard boardCopy = new SudokuBoard(board); // usa o construtor de cópia
                int solutions = solver.countSolutions(boardCopy);

                if (solutions != 1) {
                    // Se não houver uma solução única, reverta
                    board.setValue(r, c, backup);
                } else {
                    cellsToRemove--;
                }
            }
        }

        // marca dicas restantes como fixas
        for (int r = 0; r < SudokuBoard.SIZE; r++) {
            for (int c = 0; c < SudokuBoard.SIZE; c++) {
                if (board.getValue(r, c) != 0) {
                    board.getCell(r, c).setFixed(true);
                } else {
                    board.getCell(r, c).setFixed(false);
                }
            }
        }
        return board;
    }

    private void fillDiagonalBlocks(SudokuBoard board) {
        java.util.List<Integer> numbers = new java.util.ArrayList<>(java.util.Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < SudokuBoard.SIZE; i += 3) {
            java.util.Collections.shuffle(numbers, random);
            int k = 0;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    board.setValue(i + row, i + col, numbers.get(k++));
                }
            }
        }
    }
}