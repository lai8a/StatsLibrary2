/**--------------------------------------------------------------------
 * @author Laiba Khan, CSCI3327 Probability and Applied Statistics
 * 12/11/2023
 * 
 * StatsLibrary2: Program Salter
 --------------------------------------------------------------------
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Salter extends ProgramPlotter {

    //Referenced tutuorialspoint
    //https://www.tutorialspoint.com/how-to-read-the-data-from-a-csv-file-in-java

    //program to read csv values
    public static List<double[]> ReadCSVFile(String csvfile){
        String delimiter = ",";
        List<double[]> resultsList = new ArrayList<>();

        try {
            File file = new File(csvfile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            double[] tempArr;

            for (int i = 0; i < 2 && br.readLine() != null; i++);

            while((line = br.readLine()) != null) {
                tempArr = Arrays.stream(line.split(delimiter))
                            .mapToDouble(Double::parseDouble)
                            .toArray();
                resultsList.add(tempArr);
            }
            br.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return resultsList;
    }

    public static double RandSalt(double saltmin, double saltmax){
        //Referenced Toperas answer to generating random doubles in java:
        //https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
        double salt = new Random().nextDouble();
        double rand = saltmin + (salt * (saltmax - saltmin));

        return rand;
    }

    public static List<double[]> Salt(List<double[]> results, double saltmin, double saltmax){
        //read through file for y values and get a i/+ num
        //from it

        double randsalt = RandSalt(saltmin, saltmax);
        //salt only the y values
        for (double[] data : results) {
            
            if (data.length > 1) { // Ensure there are at least two values in the array
                data[1] += randsalt; // Salt the second value (assuming it's the y-value)
            }
        }

        return results;
    }
    
    //main method to test different salt values
    //have high and low data points
    public static void main(String[] args){
        // Example usage
        List<double[]> readlist = ReadCSVFile("Salt and Smooth\\CSV Files tested\\functionResults-Config1.csv"); 
        List<double[]> results = Salt(readlist, 1, 10);
        CSVWriter(results, "Salt and Smooth\\Results\\SaltResults-1.csv");


        List<double[]> readlist2 = ReadCSVFile("Salt and Smooth\\CSV Files tested\\functionResults-Config2.csv"); 
        List<double[]> results2 = Salt(readlist2, 100, 1000);
        CSVWriter(results2, "Salt and Smooth\\Results\\SaltResults-2.csv");

        List<double[]> readlist3 = ReadCSVFile("Salt and Smooth\\CSV Files tested\\functionResults-Config3.csv"); 
        List<double[]> results3 = Salt(readlist3, .5, .8);
        CSVWriter(results3, "Salt and Smooth\\Results\\SaltResults-3.csv");


        /*
        //You can print the salted list to test if you want:
        for (double[] values : results) {
            for (double value : values) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        */
    }
}

