/**--------------------------------------------------------------------
 * @author Laiba Khan, CSCI3327 Probability and Applied Statistics
 * 10/30/2023
 * StatsLibrary: SetOperations
 --------------------------------------------------------------------
 */
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class SetOperations {

//Chapter 1: Mean Median Mode ----------------------------------------
    public static List<Double> StringToDoubleList(String listofnums){
        List<Double> doublelist = Arrays.stream(listofnums.split(","))
            .map(String::trim)
            .map(Double::parseDouble)
            .collect(Collectors.toList());

            return doublelist;
    }

    public static Double Mean(String listofnums){
        //Return a 0 if there is no numbers within the list. 
        //This way, you can avoid doing unnecessary math.

        List<Double> numslist = StringToDoubleList(listofnums);

        if (numslist.size() == 0){
            return Double.NaN;
        }
        else{
            double numerator = 0.0;
            //Iterates through list and adds each value to 
            //create the numerator for the function.
            for (double value : numslist){
                numerator += value;
            }
            //Sum of all values divided by number of terms.
            return numerator/numslist.size();            
        }
    }
    
    public static Double Median(String listofnums){

        List<Double> numslist = StringToDoubleList(listofnums);

        if (numslist.size() == 0){
            return Double.NaN;
        }
        //Calculates the index for value in the middle of the array.
        //If the index is even, it takes the mean of the two middle numbers.
        //If not, the value of the index is simply returned.
        int medianindex = numslist.size()/2;
        if (numslist.size() % 2 == 0){
            double median = (numslist[medianindex] + numslist[medianindex - 1]) / 2;
            return median;
        }
        else {
            double median = numslist[medianindex];
            return median;
        }
    }

    public static List<Double> Mode(double[] listofnums){
        //Create a new list to track the occurences of each value.
        List<Double> Mode = new ArrayList<>();
        int maxOccurrences = 0;
        
        //Return alternate values or a string if there is 0 or 1 values in list.
        //This way, you avoid any unecessary math.
        if (listofnums.length == 0){
            Mode.add(Double.NaN);
        }
        else if (listofnums.length == 1){
            Mode.add(listofnums[0]);
        }

        else{
            for (double value : listofnums){
                int occurrences = 0;
            
                for (double num : listofnums){
                    if (value == num){
                        occurrences++;
                    }
                }

                if (occurrences > maxOccurrences){
                    maxOccurrences = occurrences;
                    Mode.clear();
                    Mode.add(value);
                }
                else if (occurrences == maxOccurrences && !Mode.contains(value)){
                    Mode.add(value);
                }
            }    
        }
    
    return Mode;  
    } 

//Chapter 1: Standard Deviation / Variance ----------------------------------------

    public static Double StandDev(double[] listofnums){
        double N, mean, SD;
        double sum = 0.0;
        //Find mean.
        mean = Mean(listofnums);
        N = listofnums.length;
        //Find sum(x - mean)^2 portion of equation.
        for (double x : listofnums){
            double distsquare = (x - mean) * (x - mean);
            sum += distsquare;
        }

        SD = Math.sqrt(sum / N);
        return SD;
    }

    //Variance is Standard Deviation to the second power. V = 
    public static Double Variance(double SD){
        //Input result from the Standard Devition method and get variance.
        double variance = SD * SD;
        return variance;
    }

//Chapter 2: Union Intersect Compliment ----------------------------------------

    //Union is the combinations of two lists.
    //Take the parameters of two lists.
    public static List<Integer> Union(Integer[] list1, Integer[] list2) {
        // Create a HashSet to store unique elements.
        HashSet<Integer> unionSet = new HashSet<>();
            
        // Add all elements from list1 to the set
        unionSet.addAll(Arrays.asList(list1));

        // Add all elements from list2 to the set
        unionSet.addAll(Arrays.asList(list2));

        // Create a new ArrayList to store the union
        List<Integer> union = new ArrayList<>(unionSet);

        return union;
    }
    
    //Intersection of two lists is the elements both lists have in common.
    //Take the parameters of two lists.
    //Originally, this was used only for Integer lists, but I had it adjusted
    //for generic arrays. This way, you can use it in Person for finding the intersection of
    //birthdays.
    public static <T extends Comparable<T>> List<T> Intersect(T[] list1, T[] list2) {
        List<T> intersect = new ArrayList<>();

        for (T item1 : list1) {
            for (T item2 : list2) {
                if (item1.compareTo(item2) == 0) {
                    intersect.add(item1);
                    break;
                }
            }
        }
        return intersect;
    }

    //The compliment of the list will be the remaining elements required to complete
    //the list. Or rather, add to the subset to turn it into the original arraylist.
    //Take the parameters of a list and its subset.
    public static List<Integer> Compliment(Integer[] list, Integer[] A){
        List<Integer> compliment = new ArrayList<>();
        for (int i : list){
            if (!Arrays.asList(A).contains(i)){
                compliment.add(i);
            }
        }
        return compliment;
    }

//Chapter 2: Factorial (Big Integer) ---------------------------------------- 

    public static BigInteger Factorial(int n){

        //If input is 0, then just return 0.
        if (n == 0){
            //Researched this on https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html.
            //BigInteger.ONE is the BigInteger 1 constant.
            //Output a 1 because 0! = 1.
            return BigInteger.ONE;
        }

        //Initialize result as 1. You also want to start loop index at 0 
        //because of this.
        BigInteger result = BigInteger.ONE;

        //Then loop from 1 to n, and multiply each value by the result.
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
    
//Chapter 2: Permutations Combinations ---------------------------------------- 

    public static BigInteger Permutations(int n, int r){
        //Solve for nPr = n!/(n-r)!.
        //n is the total number of objects.
        //r is the number of objects selected.

        //Only run if n is greater or equal to r, since it would not be possible
        //to do a permutation otherwise.
        if (n >= r){
            BigInteger numerator = Factorial(n);
            int temp = n - r;
            BigInteger denominator = Factorial(temp);
            BigInteger result = numerator.divide(denominator);
            return result;            
        }
        else {
            return null;
        }
    }

    public static BigInteger Combinations(int n, int r){
        //Solve for nCr = n!/r!(n-r)!.
        //Just multiply Permutation equation by 1/r!.

        //Initialize value 1 and r! to variables
        BigInteger rFact = Factorial(r);
        BigInteger one = BigInteger.ONE;

        //Find 1/r!.
        BigInteger fraction = one.divide(rFact);

        //Find permutation.
        BigInteger perm = Permutations(n, r);

        //Multiply them.
        BigInteger result = fraction.multiply(perm);

        return result;

    }

    public static Double Conditional(Integer[] A, Integer[] B, Integer[] s){
        //Solve for P(A|B) = P(A ∩ B)/P(B) provided P(B) > 0.
        //This is of two events A and B of sample space S
        //The equation describes the probability of A given B has occured.
        //'∩' is symbol for intersection.
        
        //Keep in mind P(A ⋂ B) = n(A ⋂ B)/n(S), where n(A ⋂ B) is number of elements in set A and B
        //and n(S) is number is elements in the sample space
        
        List<Integer> AinterB = Intersect(A, B);

        //A intersect B length.
        int AinterBlength = AinterB.size();
        //n(S)
        int slength = s.length;
        //Length of B.
        int Blength =  B.length;

        //Dont need length of A, but here it is:
        //Integer Alength = A.length;
        //double PA = (double) Alength / slength;
        double PB = (double) Blength / slength;

        //Make sure P(B) > 0.
        if (Blength > 0){

            //P(A ⋂ B) = n(A ⋂ B)/n(S)
            double probAinterB = (double) AinterBlength / slength;

            double result = (probAinterB / PB) * 100;

            //Multiply by 100 to get probability.
            return result;            
        }
        else{
            return null;
        }
    }

    public static Double Bayes(Integer[] A, Integer[] B, Integer[] s){
        //Solve for P(B|A) = P(A|B)P(B)/P(A) provided P(B) > 0 and P(A) > 0.
        //Use conditional to get P(A|B)

            Double ABcond = Conditional(A, B, s);   
            
            //Length of each set and subset.
            int slength = s.length;
            Integer Alength = A.length;        
            int Blength =  B.length;

            //Probability of A and B
            double PA = (double) Alength / slength;
            double PB = (double) Blength / slength;

        if (PA > 0 && PB > 0){
            double result = (ABcond * PB) / PA;
            return result;            
        }
        else{
            return null;
        }

    }

    public static Boolean Independence(Integer[] A, Integer[] B, Integer[] s){
        //Two events are independent if any of these:
        //P(A|B) = P(A),
        //P(B|A) = P(B),
        //P(A ∩ B) = P(A)P(B)
        List<Integer> AinterB = Intersect(A, B);


        //Length of each set and subset.
        int AinterBlength = AinterB.size();
        int slength = s.length;
        int Alength = A.length;  
        int Blength =  B.length;

        double PA = (double) Alength / slength;
        double PB = (double) Blength / slength;

        //P(A ⋂ B) = n(A ⋂ B)/n(S)
        double probAinterB = (double) AinterBlength / slength;

        //Find P(A|B), P(B|A)
        Double ABcond = Conditional(A, B, s);   
        Double BAbayes = Bayes(A, B, s);
        
        if (ABcond == PA){
            return true;
        }
        else if (BAbayes == PB){
            return true;
        }
        else if (probAinterB == (PA * PB)){
            return true;
        }
        else{
            return false;
        }


    
    }

    public static Double Binomial(int n, int y, double p){
        // 1. The experiment consists of a fixed number, n, of identical trials.
        // 2. Each trial results in one of two outcomes: success, S, or failure, F.
        // 3. The probability of success on a single trial is equal to some value p and
        // remains the same from trial to trial. The probability of a failure is equal to
        // q = (1 − p).
        // 4. The trials are independent.
        // 5. The random variable of interest is Y, the number of successes observed
        // during the n trials.

        //p(y)=(n¦y) * p^y * q^(n-y) , y = 0, 1, 2, …, n and 0 ≤ p ≤ 1


        //q is chance of failure: 1-p.
        double q = 1 - p;

        //Find (n y), which is n!/y!(n-y)!
        //Check if nonnegative and if 0 ≤ p ≤ 1 first.
        if (n >= y && n >= 0 && y >= 0 && p >= 0 && p <= 1){
            BigInteger nFact = Factorial(n);
            BigInteger yFact = Factorial(y);
            BigInteger nyFact = Factorial(n-y);

            BigInteger NchooseY = nFact.divide(yFact.multiply(nyFact));

            double PtoY = Math.pow(p, y);
            double QtoNminusY = Math.pow(q, n-y);

            double result = NchooseY.doubleValue() * PtoY * QtoNminusY;
            //Convert to percentage.
            return result * 100;
        }
        else{
            return null;
        }


    }

    public static Double Geometric(int y, double p){
        //p(y) = q^y-1 * p , y = 1, 2, 3, ..., 0 ≤ p ≤ 1

        double q = 1 - p;

        //0 ≤ p ≤ 1
        if (p >= 0 && p <= 1 && y > 0){
            double result = Math.pow(q, y-1) * p;
            return result * 100;            
        }
        else {
            return null;
        }
    }

    public static Double Hypergeometric(int y, int r, int N, int nn){
        //p(y) = (rCy * (N-r)C(n-Y)) / (NCn)
        if (y <= r && nn-y <= N-r && y > 0){
            BigInteger perm1 = Permutations(r, y);
            BigInteger perm2 = Permutations(N-r, nn-y);
            BigInteger perm3 = Permutations(N, nn);

            BigInteger numer = perm1.multiply(perm2);
            double result = numer.divide(perm3).doubleValue() * 100;

            return result;            
        }
        else{
            return null;
        }
    }

    public static Double NegBinomial(int y, int r, double p){
        //p(y) = (y-1Pr-1)*p^r*q^(y-r)
        double q = 1 - p;
        
        if (p >= 0 && p <= 1){

            BigInteger perm = Permutations(y-1, r-1);
            double ppower = Math.pow(p, r);
            double qpower = Math.pow(q, y-r);   
            
            double result = perm.doubleValue() * ppower * qpower;
            return result;
        }
        else {
            return null;
        }
    }

    public static Double Poisson(double mean, int y){
        if (mean > 0){
            double numer = Math.pow(mean, y);
            BigInteger yfact = Factorial(y);
            double epowermean = Math.pow(Math.E, -mean);

            double temp = numer * epowermean;
            double result = temp / yfact.doubleValue();

            return result;
        }
        else{
            return null;
        }
    }

    public static Double Chevychev(int k){
        //P(|Y-µ| < kσ) ≥ 1 - 1/k^2  OR  P(|Y-µ| ≥ kσ) ≤ 1/k^2
        //Plug in k to get how much data lies within the standard deviations k.
        if (k > 1){
            double result = 1 - (1 / (k * k));
            return result;
        }
        else{
            return null;
        }
    }


}


