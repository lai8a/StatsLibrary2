/**--------------------------------------------------------------------
 * @author Laiba Khan, CSCI3327 Probability and Applied Statistics
 * 12/07/2023
 * 
 * StatsLibrary2: Program Plotter
 --------------------------------------------------------------------
 */

//We are going to plot f(x) = e + x^(sin(x))

import java.io.BufferedWriter; 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ProgramPlotter{

    public static List<double[]> ResultsInRange(int start, int stop, double step){
        double x;
        double y; //y is going to be our result
        ArrayList<double[]> results = new ArrayList<double[]>();

        for (x = start; x <= stop; x += step){
            double expon = Math.sin(x);
            y = Math.E + Math.pow(x, expon); //f(x) = e + x^(sin(x))

            //add x and y (results) into a list of lists
            results.add(new double[] {x, y});
        }
        return results;
    }

    //Utilized a reference for help: 
    //Utilized Gennadiy Shevtsov's answer at
    //https://www.quora.com/How-can-data-be-written-in-a-CSV-file-using-Java

    public static void CSVWriter(List<double[]> resultslist, String filename){
        //write onto csv file

        int len = resultslist.size(); //get length of arraylist
        String csvfile = filename + ".csv"; //initialize name of csv file
        int i = 0; //counter for later

        //write each line onto csv file with loop
        try{
            FileWriter fileWriter = new FileWriter(csvfile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //writing the headers:
            bufferedWriter.write("f(x) = e + x^(sin(x))");
            bufferedWriter.newLine();

            bufferedWriter.write("Input,Output");
            bufferedWriter.newLine();

            while (i < len){
                double[] values = resultslist.get(i);
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
        //Test range 1 - 100, with .5 step
        List<double[]> results = ResultsInRange(1, 100, 0.5); 
        CSVWriter(results, "functionResults-Config1");
        System.out.println("CSV file written successfully!");

        //Test range 1 - 1000, with 1 step
        List<double[]> results2 = ResultsInRange(1, 1000, 1); 
        CSVWriter(results2, "functionResults-Config2");
        System.out.println("CSV file written successfully!");

        //Test range -100 - 1, with 2 step
        //this one outputs mostly NaN because the results are undefined
        List<double[]> results3 = ResultsInRange(-100, 1, 2); 
        CSVWriter(results3, "functionResults-Config3");
        System.out.println("CSV file written successfully!");
    }
}