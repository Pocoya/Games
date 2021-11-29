package sudoku;

import javax.swing.*;
import java.awt.*;

/**
 * Creates a grid using GridLayout
 */
public class Grid extends JPanel {

    public Grid(JTextField[][] field){

        setLayout(new GridLayout(9, 9));

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                field[i][j] = new JTextField();
                field[i][j].setText("");

                if(i/3 == 1 && j/3 == 1 || i/3 != 1 && j/3 != 1){
                    field[i][j].setBackground(Color.PINK);
                }
                add(field[i][j]);
            }
        }
    }
}


