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
        // Passo 1: gerar solução completa inicial (usando solver + preenchimento
        // aleatório)
        fillDiagonalBlocks(board);
        solver.solve(board);

        // Passo 2: remover números conforme dificuldade
        int removals = switch (difficulty) {
            case EASY -> 40; // menos remoções = mais dicas
            case MEDIUM -> 50;
            case HARD -> 60;
        };

        while (removals > 0) {
            int r = random.nextInt(SudokuBoard.SIZE);
            int c = random.nextInt(SudokuBoard.SIZE);
            if (board.getValue(r, c) != 0) {
                int backup = board.getValue(r, c);
                board.setValue(r, c, 0);
                // Opcional: checar unicidade; aqui simplificamos
                removals--;
                // torna não fixa ao remover
                board.getCell(r, c).setFixed(false);
            }
        }

        // marca dicas restantes como fixas
        for (int r = 0; r < SudokuBoard.SIZE; r++)
            for (int c = 0; c < SudokuBoard.SIZE; c++)
                if (board.getValue(r, c) != 0)
                    board.getCell(r, c).setFixed(true);

        return board;
    }

    private void fillDiagonalBlocks(SudokuBoard board) {
        Random rnd = new Random();
        for (int block = 0; block < 9; block += 3) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    int row = block + r, col = block + c;
                    int num;
                    do {
                        num = rnd.nextInt(9) + 1;
                    } while (!new SudokuValidator().isValidMove(board, row, col, num));
                    board.setValue(row, col, num);
                }
            }
        }
    }
}