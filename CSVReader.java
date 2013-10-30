import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/**
 * CSVReader class to print out passed in CSVFiles as well as return 2d array
 * representations of them.
 *
 * REQUIRES LineCounter.java
 *
 * @author Chris Deese
 * @version 1.1 10/20/2013
 *
 */
public class CSVReader {

    //jagged array to represent the CSV file
    private static int[][] arr;

    public static void main(String[] args) {
        CSVReader obj = new CSVReader();
        obj.passInCSV();
        printer(arr);
    }

    /**
     * Method to prompt for user input and return the file path passed in.
     *
     * @return A String representing the path of the passed in CSV file
     *
     */
    public void passInCSV() {
        System.out.println("Please enter CSV file name. If the file is not in"
                           + " this Folder please type the entire file path.");
        Scanner kb = new Scanner(System.in);
        String fileName = "";
        try {
            fileName = kb.nextLine();
            arr = new int[LineCounter.count(fileName)][];
            run(fileName);
        } catch (IOException e) {
            System.out.println("File not found, please try again");
            passInCSV();
        }
    }

    /**
     * Method to run most of the program, scans through the file.
     *
     * Creates Strings of the lines of the CSV file, splits those Strings by
     * commas to create a String array. The resulting array is then converted to
     * an int array and passed into the correct column of the jagged array.
     *
     */
    public void run(String fileName) {
        try {
            Scanner nl = new Scanner(new File(fileName));
            int count = 0;
            while (nl.hasNextLine()) {
                String str = nl.nextLine();
                Scanner comma = new Scanner(str);
                String[] split = str.split(",");
                int[] numbers = new int[split.length];
                for (int i = 0; i < split.length; i++) {
                    numbers[i] = Integer.parseInt(split[i]);
                }
                arr[count] = numbers;
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    /**
     * Method to return the created 2d int array.
     *
     * For use by other classes.
     *
     * @return An int[][] that is the CSV file passed into the program
     *
     */
    public static int[][] getCSVArr() throws IOException {
        CSVReader obj = new CSVReader();
        obj.passInCSV();
        return arr;
    }

    /**
     * Method to print the 2d array reperesenting the CSV file.
     *
     * @param arr An int[][] representing the array to be printed
     *
     */
    public static void printer(int[][] arr) {
        System.out.println("CSV Output:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
