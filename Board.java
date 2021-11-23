public class Board {
    private int[][] board;  // stores board info
    public static final int BOARD_SIZE = 9;
    private static final int UNASSIGNED = 0; // unassigned column
    
    public Board(int[] numbers) {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                int num = numbers[i * BOARD_SIZE + j];
                if(num > 9 || num < 0) {
                    throw new IllegalArgumentException();
                }
                board[i][j] = num;
            }
        }

    }

    /**
     * Returns <tt>true</tt> if it is safe to place a queen at position (row,
     *         col).
     *
     * @param row of position to check.
     * @param col column of position to check.
     * @return <tt>true</tt> if it is safe to place a queen at position (row,
     *         col).
     * @exception IllegalArgumentException if row and col do not represent a
     *                                     legal board position.
     */
    public boolean breaksBoard(int row, int col, int num) {
        return numInRow(row, num) || numInCol(col, num) || numInGrid(row, col, num);
    }
    
    private boolean numInRow(int row, int num) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(num == board[row][i]) {
                return true;
            }
        }
        return false;
    }

    private boolean numInCol(int col, int num) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(num == board[i][col]) {
                return true;
            }
        }
        return false;
    }
    
    private boolean numInGrid(int row, int col, int num) {
        int gridCol = (col / 3) * 3;
        int gridRow = (row / 3) * 3;
        for(int i = gridRow; i <= gridRow + 2; i++) {
            for(int j = gridCol; j <= gridCol + 2; j++) {
                if(board[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int getNum(int row, int col) {
        return board[row][col];
    }
    
    public boolean emptySpot(int row, int col) {
        return board[row][col] == 0;
    }
    
    /**
     * Places a queen at position (row, col).
     *
     * @param row row of position to check.
     * @param col column of position to check.
     * @exception IllegalArgumentException if it is not safe to place a queen
     *                                     at the given position.
     */
    public void place(int row, int col, int num) {
        if (breaksBoard(row, col, num)) {
            throw new IllegalArgumentException();
        }
        board[row][col] = num;
    }

    /**
     * Removes the queen at position (row, col).
     *
     * @param row row of position to check.
     * @param col column of position to check.
     * @exception IllegalArgumentException if there is no queen at the given
     *            position.
     */
    public void remove(int row, int col) {
        board[row][col] = UNASSIGNED;
    }

    /**
     * Displays the current board to System.out.
     */
    public void print() {
        System.out.println();
        System.out.println("-------------------------------------");
        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for(int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            if(i != 0 && (i + 1) % 3 == 0) {
                System.out.println("-------------------------------------");
            }
        }

    }
}