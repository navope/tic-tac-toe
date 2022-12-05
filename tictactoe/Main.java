package tictactoe;

import java.util.Scanner;

public class Main {
    final static int sizeOfPlayingField = 3;

    public static void main(String[] args) {
        boolean play = true;
        HaveThree haveThree = new HaveThree();
        boolean haveEmptySpace = false;
        boolean xMove = true;
        char[][] grid = new char[sizeOfPlayingField][sizeOfPlayingField];
        initializeGrid(grid);
        haveEmptySpace = printGrid(grid);//output of the playing field and grid fullness check
        while (play) {
            xMove = enteringNewCellValue(grid, xMove); //entering a new cell value
            scanningTheFieldToAchieveVictory(grid,haveThree); /*checking the game grid to achieve a sequence of three X or three O*/
            haveEmptySpace = printGrid(grid); //output of the playing field
            play = currentGameResult(grid,haveThree,haveEmptySpace); //game completion check
        }
    }
    public static void initializeGrid(char[][] grid){
        int index = 0;
        String gameGrid = "         ";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (gameGrid.charAt(index) != '_') {
                    grid[i][j] = gameGrid.charAt(index);
                } else {
                    grid[i][j] = ' ';
                }
                index++;
            }
        }
    }
    public static boolean printGrid(char[][]grid){
        boolean haveEmptySpace = false;
        String horizontalBorder = "---------";
        System.out.println(horizontalBorder);
        int index =0;
        for (int i = 0; i < sizeOfPlayingField; i++) {
            System.out.println("| " + grid[i][index++] + " " + grid[i][index++] + " " + grid[i][index] + " |");
            if (grid[i][0] == ' ' || grid[i][1] == ' ' || grid[i][2] == ' ') {
                haveEmptySpace = true;
            }
            index = 0;
        }
        System.out.println(horizontalBorder);
        return haveEmptySpace;
    }
    public static boolean currentGameResult(char[][]grid, HaveThree haveThree, boolean haveEmptySpace){
        boolean play = true;
        if (!haveThree.getInfoX() && !haveThree.getInfoO() && !haveEmptySpace) {
            System.out.println("Draw");
            play = false;
        }
        if (haveThree.getInfoX()) {
            System.out.println("X wins");
            play = false;
        }
        if (haveThree.getInfoO()) {
            play = false;
            System.out.println("O wins");
        }
        return play;
    }
    public static void scanningTheFieldToAchieveVictory(char[][] grid, HaveThree haveThree){
        for (int i = 0; i < grid.length; i++) {
            //there are trinity horizontally
            if (grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2] && grid[i][0] != ' ') {
                if (grid[i][0] == 'X') {
                    haveThree.setX(true);
                } else {
                    haveThree.setO(true);
                }
            }
            //there are trinities vertically
            if (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i] && grid[0][i] != ' ') {
                if (grid[0][i] == 'X') {
                    haveThree.setX(true);
                } else {
                    haveThree.setO(true);
                }
            }
        }
        //main diagonal
        if (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] != ' ') {
            if (grid[0][0] == 'X') {
                haveThree.setX(true);
            } else {
                haveThree.setO(true);
            }
        }
        //secondary diagonal
        if (grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0] && grid[0][2] != ' ') {
            if (grid[0][2] == 'X') {
                haveThree.setX(true);
            } else {
                haveThree.setO(true);
            }
        }
    }
    public static boolean enteringNewCellValue(char[][]grid , boolean xMove){
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        String inputLine;
        String[] input = new String[2];
        int[] move = new int[2];
        boolean enteredNumbers = false;
        //Input Validity Check X
        while (!validInput) {
            inputLine = scanner.nextLine();
            input[0] = String.valueOf(inputLine.charAt(0));
            input[1] = String.valueOf(inputLine.charAt(inputLine.length() - 1));
            if (input[0].matches("\\d+") && input[1].matches("\\d+")) {
                move[0] = Integer.parseInt(input[0]) - 1;// (0-2) - back grid , (1-3) - front grid
                move[1] = Integer.parseInt(input[1]) - 1;
                enteredNumbers = true;
            } else {
                System.out.println("You should enter numbers!");
            }
            if ((move[0] > 2 || move[0] < 0 || move[1] > 2 || move[1] < 0) && enteredNumbers) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                validInput = true;
            }
            if (validInput) {
                if (grid[move[0]][move[1]] != ' ') {
                    validInput = false;
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if (xMove) {
                        grid[move[0]][move[1]] = 'X';
                        xMove = false;
                    } else {
                        grid[move[0]][move[1]] = 'O';
                        xMove = true;
                    }
                }
            }
        }
        return xMove;
    }
}

