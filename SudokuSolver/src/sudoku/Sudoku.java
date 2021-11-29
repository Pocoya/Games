package sudoku;

import java.util.Arrays;

public class Sudoku implements SudokuSolver{

    private final int[][] sudoku;

    public Sudoku(){
        sudoku = new int[9][9];
    }

    @Override
    public void setNumber(int r, int c, int nbr) {
        if(!isValidPosition(r, c) && !isValidNbr(nbr)){
            throw new IllegalArgumentException("Wrong in setNumber!");
        }
        sudoku[r][c] = nbr;
    }

    @Override
    public int getNumber(int r, int c) {
        if(!isValidPosition(r, c)){
            throw new IllegalArgumentException("Wrong in getNumber!");
        } else if(sudoku[r][c] == 0) {
            return 0;
        }
        return sudoku[r][c];
    }

    @Override
    public void clearNumber(int r, int c) {
        if((r > 9 || r < 1) || (c > 9 || c < 1)){
            throw new IllegalArgumentException();
        } else{
            sudoku[r][c] = 0;
        }
    }

    @Override
    public boolean isValid(int r, int c, int nbr) {
        if(isValidNbr(nbr) && isValidPosition(r, c)){
            return (isValidRow(r, nbr) && isValidCol(c, nbr) && isValidBox(r, c, nbr));
        } else {
            throw new IllegalArgumentException("isValid is not Valid :D");
        }
    }

    //checking the nbr
    private boolean isValidNbr(int nbr){
        return nbr <= 9 && nbr >= 0;
    }


    //checking the position
    private boolean isValidPosition(int r, int c){
        return (r >= 0 && r <= 8) && (c >= 0 && c <= 8);
    }

    //checking the row
    private boolean isValidRow(int r, int nbr){
        for (int i = 0; i < 9; i++) {
            if (getNumber(r, i) == nbr) {
                return false;
            }
        }
        return true;
    }

    //checking the column
    private boolean isValidCol(int c, int nbr){
        for (int i = 0; i < 9; i++) {
            if (getNumber(i, c) == nbr) {
                return false;
            }
        }
        return true;
    }

    //checking the box
    private boolean isValidBox(int r, int c, int nbr){
        int row = r - r % 3;
        int col = c - c % 3;

        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (getNumber(i, j) == nbr) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isAllValid(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(!checkInput(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkInput(int r, int c) {
        if(getNumber(r, c) == 0){
            return true;
        }

        for (int i = 0; i < 9; i++) {
            if (i != c) {
                // checking if there is any duplicated in rows
                if (getNumber(r, i) == getNumber(r, c)) {
                    return false;
                }
            }
            // checking if there is any duplicated in columns
            if (i != r) {
                if (getNumber(i, c) == getNumber(r, c)) {
                    return false;
                }
            }
        }

        int row = r - r % 3;
        int col = c - c % 3;
        // checking if there is any duplicated in box
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (getNumber(i, j) == getNumber(r, c) && i != r && j != c) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean solve() {
        return solve(0, 0);
    }

    private boolean solve(int r, int c) {
        if(r == 8 && c == 9) {
            return true;
        }
        if(c == 9) {
            r++;
            c = 0;
        }
        if(getNumber(r, c) != 0) {
            return solve(r,c + 1);
        }

        for(int nbr = 1; nbr < 10; nbr++) {
            if(isValid(r, c, nbr)) {
                setNumber(r, c, nbr);
                if(solve(r,c + 1)) {
                    return true;
                }
            }
            setNumber(r, c, 0);
        }
        return false;
    }


    @Override
    public void clear() {
        Arrays.stream(sudoku).forEach(x -> Arrays.fill(x, 0));
    }

    @Override
    public int[][] getMatrix() {
        int[][] newMatrix = new int[getDimension()][getDimension()];
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                newMatrix[i][j] = sudoku[i][j];
            }
        }
        return newMatrix;
    }

    @Override
    public void setMatrix(int[][] nbrs) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(nbrs.length > getDimension() || nbrs[i].length > getDimension()){
                    throw new IllegalArgumentException("Wrong dimension!");
                }
                else if(nbrs[i][j] < 0 || nbrs[i][j] > 9){
                    throw new IllegalArgumentException("Wrong in setMatrix!");
                } else {
                    sudoku[i][j] = nbrs[i][j];
                }
            }
        }
    }
}

