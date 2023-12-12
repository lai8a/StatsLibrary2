/**--------------------------------------------------------------------
 * @author Laiba Khan, CSCI3327 Probability and Applied Statistics
 * 12/11/2023
 * 
 * StatsLibrary2: Program Smoother
 --------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;


public class Smoother {

    //at the left of the graph take the right values up to the range and divide by x+1
    //calculate smoothing average between an inputted range of indexes
        //ex: if it was 3 then take the 3 values from left and 3 from right and take 
        //average by dividing by 7

    public static List<double[]> Smooth(List<double[]> input, int windowValue){
        //read values from list/file
        //iterate over each y value
        int len = input.size();
        List<double[]> smoothedData = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            double[] data = input.get(i);
    
            if (data.length > 1) {
                double sum = data[1];
                int count = 1;
    
                //Sum values within the inputted range
                for (int j = i - windowValue; j <= i + windowValue; j++) {
                    if (j != i && j >= 0 && j < len) {
                        double[] adjacentData = input.get(j);
                        if (adjacentData.length > 1) {
                            sum += adjacentData[1];
                            count++;
                        }
                    }
                }
    
                //Calculate average and store the result in a new array
                double[] smoothedPoint = {data[0], sum / count};  //data[0] is x value
                smoothedData.add(smoothedPoint);
            }
        }
        return smoothedData;
    }

    public static void main(String[] args){
        //input salt files using file reader
        List<double[]> readlist = Salter.ReadCSVFile("Salt and Smooth\\SaltResults\\SaltResults-1.csv");
        List<double[]> smoothlist = Smooth(readlist, 3); //smooth values and write them    
        ProgramPlotter.CSVWriter(smoothlist, "Salt and Smooth\\Smooth Results\\SmoothResults-1");
        
        //try smoothing data more than once.
        List<double[]> readlist2 = Salter.ReadCSVFile("Salt and Smooth\\Smooth Results\\SmoothResults-1.csv");
        List<double[]> smoothlist2 = Smooth(readlist2, 3); //smooth values and write them    
        ProgramPlotter.CSVWriter(smoothlist2, "Salt and Smooth\\Smooth Results\\SmoothResults-2");
        
    }
}