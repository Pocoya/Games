package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class View implements ActionListener {

    private JPanel panel;
    private JTextField[][] field;
    private Grid grid;
    private JButton solve, clear;
    SudokuSolver sudoku = new Sudoku();
    String[] tal = new String[]{"1","2","3","4","5","6","7","8","9"};

    /**
     * The constructure
     */
    public View(){
        field = new JTextField[9][9];
        grid = new Grid(field);
        panel = new JPanel();
        solve = new JButton("Solve");
        clear = new JButton("Clear");
        clear.addActionListener(e -> {
            sudoku.clear();
            updateGUI();
        });

        solve.addActionListener(this);
        panel.add(solve);
        panel.add(clear);

        JFrame frame = new JFrame("Sudoku");
        frame.add(grid, BorderLayout.NORTH);
        frame.add(panel,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(310, 300);
    }

    /**
     *Updates the GUI when it's called by the clear button
     */
    public void updateGUI(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j].setText("");
            }
        }
    }
    /**
     *ActionPerformed, called by solve button
     * It handles everything that has to do with solving
     * by calling the appropriate methods in Sudoku
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(solve)) {
            int[][] temp = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (field[i][j].getText().equals("")) {
                        temp[i][j] = 0;
                    } else if(Arrays.asList(tal).contains(field[i][j].getText())){
                        temp[i][j] = Integer.parseInt(field[i][j].getText());
                    }
                    else {
                        System.out.println("Felaktigt tal/input!");
                        return;
                    }
                }
            }
            sudoku.setMatrix(temp);
            if (sudoku.isAllValid()) {
                if (sudoku.solve()) {
                    int[][] matrix = sudoku.getMatrix();
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            field[i][j].setText(Integer.toString(matrix[i][j]));
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Solution found!");
                } else {
                    JOptionPane.showMessageDialog(null, "Unsolvable!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Unsolvable!");
            }
        }
    }

    /*
     * The main method to start the program
     */
    public static void main(String[] args) {
        View view = new View();
    }
}







