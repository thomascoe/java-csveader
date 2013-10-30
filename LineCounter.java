import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * LineCounter class to count the number of lines in any file.
 *
 * @author Chris Deese
 * @version 1.0 10/13/13
 *
 */
public class LineCounter {

    //contains prompts for user input
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter the filename you wish to run"
                         + " LineCounter on.");
        Scanner kb = new Scanner(System.in);
        String fileName = kb.nextLine();
        System.out.println(count(fileName));
    }

    /**
     * Method to count the lines in the file.
     *
     * Initializes a new BufferedInputStream, increments the count variable for
     * each line. If the program finds no new line character it increments the
     * count anyway to avoid counting errors.
     *
     * @param filename A String representing the file line count is to be run on
     * @return An int representing the number of lines the file contains
     *
     */
    public static int count(String fileName) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(fileName));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if (endsWithoutNewLine) {
                ++count;
            }
            return count;
        } finally {
            is.close();
        }
    }
}
