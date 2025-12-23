package br.com.pedrozef.sudoku.controller;

import br.com.pedrozef.sudoku.exception.InvalidMoveException;
import br.com.pedrozef.sudoku.model.Difficulty;
import br.com.pedrozef.sudoku.model.SudokuBoard;
import br.com.pedrozef.sudoku.service.SudokuGenerator;
import br.com.pedrozef.sudoku.service.SudokuSolver;
import br.com.pedrozef.sudoku.service.SudokuValidator;

public class SudokuGame {
    private SudokuBoard board;
    private SudokuBoard originalBoard; // O puzzle original sem modificações do usuário
    private final SudokuValidator validator = new SudokuValidator();
    private final SudokuGenerator generator = new SudokuGenerator();
    private final SudokuSolver solver = new SudokuSolver();

    public void start(Difficulty difficulty) {
        this.originalBoard = generator.generate(difficulty);
        this.board = new SudokuBoard(originalBoard); // O usuário joga em uma cópia
    }

    public SudokuBoard getBoard() {
        return board;
    }

    public boolean solve() {
        if (originalBoard == null) return false;
        // Resolve o puzzle a partir do estado original
        SudokuBoard solvedBoard = new SudokuBoard(originalBoard);
        boolean isSolved = solver.solve(solvedBoard);
        if (isSolved) {
            this.board = solvedBoard; // Atualiza o tabuleiro de jogo com a solução
        }
        return isSolved;
    }

    public void play(int row, int col, int value) {
        if (board.getCell(row, col).isFixed()) {
            throw new InvalidMoveException("Não é permitido alterar uma dica fixa.");
        }
        // Permite apagar um valor
        if (value == 0) {
            board.setValue(row, col, 0);
            return;
        }
        if (!validator.isValidMove(board, row, col, value)) {
            throw new InvalidMoveException("Jogada inválida para a posição.");
        }
        board.setValue(row, col, value);
    }

    public boolean isCompleted() {
        return board != null && board.isFull() && validator.isSolved(board);
    }
}
