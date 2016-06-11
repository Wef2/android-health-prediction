package ac.jejunu.mcl.model;

/**
 * Created by Kim on 2016-06-11.
 */
public class KalmanFilter {

    double q = 0.000001; // Process variance
    double r = 0.01;// Estimation variance

    public double calculate(int n, double start, double ss[]) {
        double result;
        double[] xhat = new double[n + 1], // estimated true value (posteri)
                xhat_prime = new double[n + 1], // estimated true value (priori)
                p = new double[n + 1], // estimated error (posteri)
                p_prime = new double[n + 1], // estimated error (priori)
                k = new double[n + 1]; // kalman gain
        double cur_ave = 0;
        double mape;

        // Initial guesses
        xhat[0] = start;
        p[0] = 1;

        for (int i = 1; i <= n; i++) {
            // time update
            xhat_prime[i] = xhat[i - 1];
            p_prime[i] = p[i - 1] + q;

            // measurement update
            k[i] = p_prime[i] / (p_prime[i] + r);
            xhat[i] = xhat_prime[i] + k[i] * (ss[i - 1] - xhat_prime[i]);
            p[i] = (1 - k[i]) * p_prime[i];

            // calculate running average
            cur_ave = (cur_ave * (i - 1) + ss[i - 1]) / ((double) i);

            // calculate mape
            mape = Math.abs(((ss[i - 1] - xhat[i]) / ss[i - 1])) * 100;

        }
        result = xhat[n];
        return result;
    }
}
