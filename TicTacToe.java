import java.util.*;

public class Main {


    public static void main(String[] args) {
        char[] input = getEmptyArray(); // Calls method getInput to get correct user input
        displayBoard(input); // Calls method displayBoard to display Tic-Tack-Toe board
        String result = getResult(input); // Call getResult and gets a String of the result back
        int xORo = 0;
        while (result.equals("Draw")) {
            if (xORo == 9) { break; }
            addMove(input, xORo);
            xORo++;
            result = getResult(input);
        }
        System.out.println(result); // Prints result
    }

    public static char[] getEmptyArray() {
        char[] emptyArray = new char[9];
        emptyArray[0] = ' ';
        emptyArray[1] = ' ';
        emptyArray[2] = ' ';
        emptyArray[3] = ' ';
        emptyArray[4] = ' ';
        emptyArray[5] = ' ';
        emptyArray[6] = ' ';
        emptyArray[7] = ' ';
        emptyArray[8] = ' ';
        return emptyArray;
    }

    public static void displayBoard(char[] arr) {
        System.out.println("---------");
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 3 || i == 6) {
                System.out.print("| ");
            }
            System.out.print(arr[i] + " ");
            if (i == 2 || i == 5 || i == 8) {
                System.out.println("|");
            }
        }
        System.out.println("---------");
    }

    public static String getResult(char[] arr) {
        // Variables for keeping track of empty cells and X and O cells
        int emptyCells = 0;
        int xCt = 0;
        int oCt = 0;

        for (int i = 0; i < 9; i++) {
            char n = arr[i];
            n = Character.toUpperCase(n);
            arr[i] = n;

            if (arr[i] == '_') {
                emptyCells++;
            } else if (arr[i] == 'X') {
                xCt++;
            } else if (arr[i] == 'O') {
                oCt++;
            }
        }

        //Finds difference
        int dif = 0;
        if (xCt > oCt) {
            dif = xCt - oCt;
        } else if (xCt < oCt) {
            dif = oCt - xCt;
        }

        // Used to determine outcome
        boolean xWins = false;
        boolean oWins = false;
        if        ((arr[0] == 'X' && arr[1] == 'X' && arr[2] == 'X') // row
                || (arr[3] == 'X' && arr[4] == 'X' && arr[5] == 'X') // row
                || (arr[6] == 'X' && arr[7] == 'X' && arr[8] == 'X') // row
                || (arr[0] == 'X' && arr[3] == 'X' && arr[6] == 'X') // column
                || (arr[1] == 'X' && arr[4] == 'X' && arr[7] == 'X') // column
                || (arr[2] == 'X' && arr[5] == 'X' && arr[8] == 'X') // column
                || (arr[0] == 'X' && arr[4] == 'X' && arr[8] == 'X') // across
                || (arr[2] == 'X' && arr[4] == 'X' && arr[6] == 'X'))// across
        {
            xWins = true;
        }
        if ((arr[0] == 'O' && arr[1] == 'O' && arr[2] == 'O') // row
                || (arr[3] == 'O' && arr[4] == 'O' && arr[5] == 'O') // row
                || (arr[6] == 'O' && arr[7] == 'O' && arr[8] == 'O') // row
                || (arr[0] == 'O' && arr[3] == 'O' && arr[6] == 'O') // column
                || (arr[1] == 'O' && arr[4] == 'O' && arr[7] == 'O') // column
                || (arr[2] == 'O' && arr[5] == 'O' && arr[8] == 'O') // column
                || (arr[0] == 'O' && arr[4] == 'O' && arr[8] == 'O') // across
                || (arr[2] == 'O' && arr[4] == 'O' && arr[6] == 'O'))// across
        {
            oWins = true;
        }
        String result = null;
        if ((xWins && oWins) || (dif != 0 && dif != 1)) {
            result = "Impossible";
        } else if (xWins) {
            result = "X wins";
        } else if (oWins) {
            result = "O wins";
        }  else if (emptyCells == 0) {
            result = "Draw";
        }
        return result;
    }

    public static void addMove(char[] arr, int xORo) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        int x;
        int y;
        int count = 0;
        while (count == 0) {

            // Checks to see if both x an y are integers
            try {
                x = scanner.nextInt();
                y = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                addMove(arr, xORo);
                break;
            }

            // Checks to see if both x an y are 1, 2, or 3
            if ((x < 1 || x > 3) || (y < 1 || y > 3)) {
                System.out.println("Error, Coordinates should be from 1 to 3!");
                addMove(arr, xORo);
                break;
            }

            // Gets coordinates
            int coordinates = 8;
            if (x == 1 && y == 1) {
                coordinates = 0;
            } else if (x == 1 && y == 2) {
                coordinates = 1;
            } else if (x == 1) {
                coordinates = 2;
            } else if (x == 2 && y == 1) {
                coordinates = 3;
            } else if (x == 2 && y == 2) {
                coordinates = 4;
            } else if (x == 2) {
                coordinates = 5;
            } else if (y == 1) {
                coordinates = 6;
            } else if (y == 2) {
                coordinates = 7;
            }

            // Checks to see if coordinates are an empty spot
            if (arr[coordinates] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                addMove(arr, xORo);
                break;
            }
            if (xORo % 2 == 0){
                arr[coordinates] = 'X';
            } else {
                arr[coordinates] = 'O';
            }
            displayBoard(arr);
            count++;
        }
    }
}




