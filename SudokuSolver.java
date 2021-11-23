import java.util.*;

public class SudokuSolver {
    private static final int TOTAL_SPOTS = 81;
    private static final int SIZE = 9;
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("This program solves sudoku boards");
        System.out.println("Print out sudoku board with spaces seperating each number and for empty cells enter a 0: ");
        int[] nums = new int[TOTAL_SPOTS];
        for(int i = 0; i < TOTAL_SPOTS; i++) {
            nums [i] = console.nextInt();
        }
        Board b = new BoardFrame(nums);
        System.out.print("\nOriginal Board:");
        b.print();
        if(solve(b)) {
            System.out.println("\nSolving...\n");
            System.out.print("Solution:");
            b.print();
        } else {
            System.out.println("This board has no solution");
        }
    }

    public static boolean solve(Board b) {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(b.emptySpot(i, j)) {
                    for(int num = 1; num <= SIZE; num++) {
                        if(!b.breaksBoard(i, j, num)) {
                            b.place(i, j, num);
                            if(solve(b)) {
                                return true;
                            } else {
                                b.remove(i, j); 
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}