package sudoku;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class TestingTest {

    private Sudoku sudoku;
    private Sudoku emptySudoku;
    private int[][] solvable;
    private int[][] unsolvable;
    private int[][] zeroBoard;
    private int[][] temp;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        solvable = new int[][] {
                { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
                { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
                { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
                { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
                { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
                { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 4, 0, 0 } };

        unsolvable = new int[][] {
                { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
                { 0, 0, 8, 0, 0, 0, 0, 0, 5 },
                { 1, 0, 2, 5, 0, 0, 5, 0, 0 },
                { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
                { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
                { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
                { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
                { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 4, 4, 0, 0 } };


        zeroBoard = new int[][] {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        emptySudoku = new Sudoku();
        sudoku = new Sudoku();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

        sudoku = null;
        emptySudoku = null;
        solvable = null;
        unsolvable = null;
        zeroBoard = null;
        temp = null;
    }


    @Test
    void testEmptySudoku(){

        assertArrayEquals(emptySudoku.getMatrix(), zeroBoard);
        assertTrue(emptySudoku.solve(), "Should be able to solve an empty sudoku!");
    }

    @Test
    void testSolvable(){

        sudoku.setMatrix(solvable);
        assertTrue(sudoku.solve(), "It should be able to solve this solvable board!");
    }

    @Test
    void testUnsolvable(){

        sudoku.setMatrix(unsolvable);
        assertFalse(sudoku.solve(), "It should not be able to solve this Unsolvable board!");
    }

    @Test
    void testClear(){
        sudoku.setMatrix(solvable);
        sudoku.solve();
        sudoku.clear();

        assertArrayEquals(zeroBoard, sudoku.getMatrix());
    }

    @Test
    void testSetAndGetMatrix(){

        sudoku.setMatrix(solvable);
        assertArrayEquals(solvable, sudoku.getMatrix());
    }

    @Test
    void testSetAndGetNumber(){
        assertThrows(IllegalArgumentException.class, () -> sudoku.setNumber(10, 11, 12));
        assertThrows(IllegalArgumentException.class, () -> sudoku.getNumber(20, 30));
    }

    @Test
    void testIsValid(){
        assertThrows(IllegalArgumentException.class, () -> sudoku.isValid(20, 30, 40));
    }

    @Test
    void testisAllValid(){
        sudoku.setMatrix(unsolvable);
        assertFalse(sudoku.isAllValid(), "It should not be True!");
        sudoku.setMatrix(solvable);
        assertTrue(sudoku.isAllValid(), "It should not be False!");
    }

    @Test
    void testClearNumber(){
        assertThrows(IllegalArgumentException.class, () -> sudoku.clearNumber(20, 30));
        sudoku.setNumber(5, 5, 6);
        sudoku.clearNumber(5, 5);
        assertEquals(0, sudoku.getNumber(5, 5), "It should be zero!");
    }

}


