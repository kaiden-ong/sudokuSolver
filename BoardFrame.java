import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
*/

public class BoardFrame extends Board {
    private JButton[][] myButtons;
    private int myDelay;
    private JFrame f;
    private static final int SIZE = 9;

    public BoardFrame(int[] board) {
        super(board);
        f = new JFrame();
        f.setSize(450, 500);
        f.setTitle("Sudoku Solver");
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();

        JPanel p = new JPanel();
        p = new JPanel(new GridLayout(SIZE, SIZE, 1, 1));
        contentPane.add(p, "Center");
        p.setBackground(Color.black);
        myButtons = new JButton[SIZE][SIZE];
        Font f24 = new Font("Serif", Font.BOLD, 24);
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                JButton b = new JButton();
                b.setFont(f24);
                p.add(b);
                myButtons[i][j] = b;
            }

        f.setVisible(true);
        f.toFront();
    }

    public void place(int row, int col, int num) {
        super.place(row, col, num);
        myButtons[row][col].setText(String.valueOf(num));
        pause();
    }

    public boolean breaksBoard(int row, int col, int num) {
        myButtons[row][col].setText(String.valueOf(num));
        pause();
        myButtons[row][col].setText("");
        return super.breaksBoard(row, col, num);
    }

    public void remove(int row, int col) {
        super.remove(row, col);
        myButtons[row][col].setText("undo");
        pause();
        myButtons[row][col].setText("");
    }

    public void print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                myButtons[i][j].setText(String.valueOf(super.getNum(i, j)));
                myButtons[i][j].setForeground(Color.BLUE);
            }
        }
        super.print();
    }

    // pause using slider setting
    private void pause() {
        pause(myDelay);
    }

    private void pause(int delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            throw new InternalError();
        }
    }
}

/*public class BoardFrame extends Board {
    private JTextField[][] textFields;
    private JButton[][] myButtons;
    private int myDelay;
    private JFrame f;
    private static final int SIZE = 9;
    private int[][] nums;

    public BoardFrame(int[] board) {
        super(board);
        nums = new int[SIZE][SIZE];
        f = new JFrame();
        f.setSize(450, 500);
        f.setTitle("Sudoku Solver");
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();
        f.setVisible(true);
        f.toFront();

        // add buttons in the middle for the chess squares
        JPanel p = new JPanel();
        p = new JPanel(new GridLayout(SIZE, SIZE, 1, 1));
        contentPane.add(p, "Center");
        p.setBackground(Color.black);
        textFields = new JTextField[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JTextField t = new JTextField();
                p.add(t);
                textFields[i][j] = t;
            }
        }
        JButton enter = new JButton("Get Solution");
        enter.setPreferredSize(new Dimension(432,50));
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked");
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        nums[i][j] = Integer.parseInt(textFields[i][j].getText());
                        f.setVisible(false);
                        f.dispose();
                    }
                }
                f = new JFrame();
                JPanel p1 = new JPanel();
                p1 = new JPanel(new GridLayout(SIZE, SIZE, 1, 1));
                contentPane.add(p1, "Center");
                p1.setBackground(Color.black);
                myButtons = new JButton[SIZE][SIZE];
                Font f24 = new Font("Serif", Font.BOLD, 24);
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        JButton b = new JButton();
                        b.setFont(f24);
                        p1.add(b);
                        myButtons[i][j] = b;
                    }
                }
            }
        });
        p.add(enter);
        
    }

    public void place(int row, int col, int num) {
        super.place(row, col, num);
        myButtons[row][col].setText(String.valueOf(num));
        pause();
    }

    public boolean breaksBoard(int row, int col, int num) {
        myButtons[row][col].setText(String.valueOf(num));
        pause();
        myButtons[row][col].setText("");
        return super.breaksBoard(row, col, num);
    }

    public void remove(int row, int col) {
        super.remove(row, col);
        myButtons[row][col].setText("undo");
        pause();
        myButtons[row][col].setText("");
    }

    public void print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                myButtons[i][j].setText(String.valueOf(super.getNum(i, j)));
                myButtons[i][j].setForeground(Color.BLUE);
            }
        }
        super.print();
    }

    // pause using slider setting
    private void pause() {
        pause(myDelay);
    }

    private void pause(int delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            throw new InternalError();
        }
    }
}
*/