/**--------------------------------------------------------------------
 * @author Laiba Khan, CSCI3327 Probability and Applied Statistics
 * 12/13/2023
 * 
 * StatsLibrary2: RSI Calculator
 --------------------------------------------------------------------
 */
import java.util.ArrayList;
import java.util.List;

//Referenced: https://www.macroption.com/rsi-calculation/#up-down-moves

//""RSI = 100 – 100 / ( 1 + RS )
//  RS = Relative Strength = AvgU / AvgD
//  AvgU = average of all up moves in the last N price bars
//  AvgD = average of all down moves in the last N price bars
//  N = the period of RSI ""

//""Use the original period length of 14 (Welles Wilder Jr.'s method)""
//  N=14

public class RSICalculator{

    private static List<Double> calculateRSI(List<double[]> stocks, int period) {
        
        List<Double> rsiList = new ArrayList<>();

        if (stocks.size() < period + 1) {
            return rsiList; //Check if lists big enough to calculate RSI
        }

        //Calculate the average gain and average loss for the initial period
        double avgGain = 0;
        double avgLoss = 0;

        for (int i = 1; i <= period; i++) {
            double[] currentStock = stocks.get(i);
            double[] previousStock = stocks.get(i - 1);
            double change = currentStock[1] - previousStock[1];

            if (change > 0) {
                avgGain += change;
            } 
            else {
                avgLoss += Math.abs(change);
            }
        }

        avgGain /= period;
        avgLoss /= period;

        //Calculate the RSI for the subsequent periods
        for (int i = period + 1; i < stocks.size(); i++) {
            double[] currentStock = stocks.get(i);
            double[] previousStock = stocks.get(i - 1);
            double change = currentStock[1] - previousStock[1];

            if (change > 0) {
                avgGain = (avgGain * (period - 1) + change) / period;
                avgLoss = (avgLoss * (period - 1)) / period;
            } 
            else {
                avgGain = (avgGain * (period - 1)) / period;
                avgLoss = (avgLoss * (period - 1) + Math.abs(change)) / period;
            }

            //Calculate the RSI using the rolling mean method
            double relativeStrength = avgGain / avgLoss;
            double rsi = 100 - (100 / (1 + relativeStrength));

            rsiList.add(rsi);
        }
        return rsiList;
    }

    public static void main(String[] args){
        //""Use the original period length of 14 (Welles Wilder Jr.'s method)""
        List<double[]> stocks = Salter.ReadCSVFile("RSIDataUsed.csv");
        List<Double> rsiList = calculateRSI(stocks, 14);

        //You can use this code to print RSI values if needed:
        //System.out.println("RSI values:");
        //for (double rsi : rsiList) {
        //    System.out.println(rsi);
        //}
    }
}