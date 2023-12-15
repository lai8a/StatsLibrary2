/**--------------------------------------------------------------------
 * @author Laiba Khan, CSCI3327 Probability and Applied Statistics
 * 12/13/2023
 * 
 * StatsLibrary2: StatsLibrary Functions
 --------------------------------------------------------------------
 */
import java.lang.Math;
//-Uniform Prob Dirstribution
//-Normal Prob Distribution

//Bivariate and Multivariate Prob
//-Marginal and Conditional
//-Independent Random Variables

//-Expected Value of Function w Random Variables
//-Covariance of two random vars
//-Expected Value and Variance of Two Random Variables
//-Multinomail Prob Distribution


public class StatsLibrary{
    //-Uniform Prob Dirstribution
    //This method shows CALCULATION of uniform uniform density on the interval (01, 02)
    public static double UniformProbDistr(double intervalOne, double intervalTwo){
        double continiousdistr = 0;
        
        if (intervalOne < intervalTwo){
            double fy =  1.0 / (intervalTwo - intervalOne);

            if (intervalOne<= fy && fy <= intervalTwo){ //assume 0 elsewhere
              continiousdistr = fy;
            }
        }
        return continiousdistr;
    }    

    //-Normal Prob Distribution
    //This method shows CALCULATION of Normal Density
    public static double NormalProbDistr(double standdev, double mean, double y){
        double normaldistr = 0;

        double body = 1 / (standdev * (Math.sqrt(2 * Math.PI)));
        double e = Math.pow(Math.E, -Math.pow((y - mean), 2) / (2 * Math.pow(standdev, 2)));
        double fy = body * e;
        if ( Double.NEGATIVE_INFINITY < fy && fy < Double.POSITIVE_INFINITY){
            normaldistr = fy;
        }
        return normaldistr;
    }


    public static double JointProbability(double[][] probabilities) {
        //The joint (or bivariate) probability function for Y1 and Y2 is given by
        //p(y1, y2) = P(Y1 = y1, Y2 = y2), −∞ < y1 < ∞, −∞ < y2 < ∞int numVariables = probabilities.length;
            int numVariables = probabilities.length;
            int numOutcomes = probabilities[0].length;

            double jointProbability = 1.0;

            for (int variable = 0; variable < numVariables; variable++) {        
                for (int outcome = 0; outcome < numOutcomes; outcome++) {
                    jointProbability *= probabilities[variable][outcome];
                }
            }

            return jointProbability;
    }
    
    public static double MarginalProbaility(double[][] probabilities, int variable){
        //Basically, this is adding all of the probalities in a single row (reffering to graph)
        //, or a single variables combinations with other variables.
        int numOutcomes = probabilities[0].length;

        double marginalProbability = 0;

            for (int outcome = 0; outcome < numOutcomes; outcome++) {
                marginalProbability += probabilities[variable][outcome];
            }

        return marginalProbability;

    }
        public static void main(String[] args) {
            double uniformResult = UniformProbDistr(2, 5);
            System.out.println("Uniform Probability Distribution: " + uniformResult);

            double normalResult = NormalProbDistr(2, 10, 12);
            System.out.println("Normal Probability Distribution: " + normalResult);

            double[][] probabilities = {
                    {0.1, 0.2, 0.1},
                    {0.1, 0.3, 0.2}
            };
            double[][] probabilities2 = {
                    {0.0, 0.4, 0.1},
                    {0.1, 0.4, 0.0}
            };

            double jpresult = JointProbability(probabilities);
            System.out.println("Joint Probability: " + jpresult);
            double jpresult2 = JointProbability(probabilities2);
            System.out.println("Joint Probability: " + jpresult2);

            double mpresult = MarginalProbaility(probabilities, 1);
            System.out.println("Marginal Probability for variable = 1: " + mpresult);
            double mpresult2 = MarginalProbaility(probabilities, 2);
            System.out.println("Marginal Probability for variable = 2: " + mpresult2);


        }
}

