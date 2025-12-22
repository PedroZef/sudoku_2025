package br.com.pedrozef.sudoku.ui;

import br.com.pedrozef.sudoku.controller.SudokuGame;
import br.com.pedrozef.sudoku.exception.InvalidMoveException;
import br.com.pedrozef.sudoku.model.SudokuBoard;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SudokuGrid extends GridPane {
    public SudokuGrid(SudokuGame game) {
        setHgap(2);
        setVgap(2);
        setAlignment(Pos.CENTER);

        SudokuBoard board = game.getBoard();

        for (int r = 0; r < SudokuBoard.SIZE; r++) {
            for (int c = 0; c < SudokuBoard.SIZE; c++) {
                TextField cell = new TextField();
                cell.setPrefWidth(50);
                cell.setPrefHeight(50);
                cell.setAlignment(Pos.CENTER);

                int value = board.getValue(r, c);
                boolean fixed = board.getCell(r, c).isFixed();

                if (value != 0)
                    cell.setText(String.valueOf(value));
                cell.setEditable(!fixed);

                final int row = r, col = c;
                cell.textProperty().addListener((obs, oldV, newV) -> {
                    if (newV.length() > 1 || (newV.length() == 1 && !newV.matches("[1-9]"))) {
                        cell.setText(oldV);
                        return;
                    }
                    int num = (newV.isEmpty()) ? 0 : Integer.parseInt(newV);
                    try {
                        game.play(row, col, num);
                        if (game.isCompleted()) {
                            Alert a = new Alert(Alert.AlertType.INFORMATION, "Parabéns! Você concluiu o Sudoku.");
                            a.showAndWait();
                        }
                    } catch (InvalidMoveException ex) {
                        cell.setText(oldV);
                        Alert a = new Alert(Alert.AlertType.WARNING, ex.getMessage());
                        a.showAndWait();
                    }
                });

                // Estilo simples: destaque de blocos 3x3
                String bg = ((r / 3 + c / 3) % 2 == 0) ? "#f0f0f0" : "#ffffff";
                cell.setStyle("-fx-font-size: 18; -fx-background-color: " + bg + ";");

                add(cell, c, r);
            }
        }
    }
}