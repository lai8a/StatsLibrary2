/**--------------------------------------------------------------------
 * @author Laiba Khan, CSCI3327 Probability and Applied Statistics
 * 12/07/2023
 * 
 * StatsLibrary2: Program Plotter
 --------------------------------------------------------------------
 */

//We are going to plot f(x) = e + .5x^(sin(x))

import java.io.BufferedWriter; 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ProgramPlotter{

    public static List<double[]> ResultsInRange(int start, int stop){
        int x;
        double y; //y is going to be our result
        double e = Math.E;
        ArrayList<double[]> results = new ArrayList<double[]>();

        while (start <= stop){
            x = start;
            y = e + Math.pow(.5, Math.sin(x)); //f(x) = e + .5x^(sin(x))

            //add x and y (results) into a list of lists
            results.add(new double[] {x, y});
            
            start++;
        }
        return results;
    }
    //Utilized a reference for help: 
    //Utilized Gennadiy Shevtsov's answer at
    //https://www.quora.com/How-can-data-be-written-in-a-CSV-file-using-Java

    public static void CSVWriter(List<double[]> results){
        //write onto csv file

        int len = results.size(); //get length of arraylist
        String csvfile = "functionResults.csv"; //initialize name of csv file
        int i = 0; //counter for later

        //write each line onto csv file with loop
        try{
            FileWriter fileWriter = new FileWriter(csvfile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //writing the headers:
            bufferedWriter.write("f(x) = e + .5x^(sin(x))");
            bufferedWriter.newLine();

            bufferedWriter.write("Input,Output");
            bufferedWriter.newLine();

            while (i < len){
                double[] values = results.get(i);
                bufferedWriter.write(values[0] + "," + values[1]); // Separate values with a comma
                bufferedWriter.newLine();

                i++;
            }

            bufferedWriter.close(); 
        }
        catch (IOException e) {
            throw new RuntimeException("Error writing to CSV file", e);
        }
    }
    public static void main(String[] args) {
        //Test range 1 - 100
        List<double[]> results = ResultsInRange(1, 100); // Adjust the range as needed
        CSVWriter(results);
        System.out.println("CSV file written successfully!");
    }
}