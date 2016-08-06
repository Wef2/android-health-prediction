package mcl.jejunu.healthapp.prediction;

import java.util.List;

import mcl.jejunu.healthapp.object.Exercise;

/**
 * Created by neo-202 on 2016-06-29.
 */
public class PredictionFilter {

    public static double q = 0.000001; // Process variance
    public static double r = 0.01;// Estimation variance

    public static double predict(int n, double start, List<Integer> numbers) {
        double result;
        double[] xhat = new double[n + 1], // estimated true value (posteri)
                xhat_prime = new double[n + 1], // estimated true value (priori)
                p = new double[n + 1], // estimated error (posteri)
                p_prime = new double[n + 1], // estimated error (priori)
                k = new double[n + 1]; // kalman gain
        // Initial guesses
        xhat[0] = start;
        p[0] = 1;

        double total = 0;
        for(Integer integer : numbers){
            total = total + integer;
        }
        double average;
        average = total / numbers.size();
        int count = 0;

        for (int i = 1; i <= n; i++) {
            // time update
            xhat_prime[i] = xhat[i - 1];
            p_prime[i] = p[i - 1] + q;

            // average
            total = total + xhat_prime[i];
            count = count + 1;
            average = total / (numbers.size() + count);

            // measurement update
            k[i] = p_prime[i] / (p_prime[i] + r);
            xhat[i] = xhat_prime[i] + k[i] * ( average - xhat_prime[i]);
            p[i] = (1 - k[i]) * p_prime[i];
        }
        result = xhat[n];
        return result;
    }
}
