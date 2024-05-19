/** CSCB07 Lab 1
 * Develop class Polynomial as follows:
 * i. It has one field representing the coefficients of the polynomial using an array of
 * double. A polynomial is assumed to have the form a_0 + a_1x + ... + a_nx^n.
 * For example, the polynomial 6 - 2x + 5x^2 would be represented using the
 * array [6, -2, 0, 5]
 * ii. It has a no-argument constructor that sets the polynomial to zero (i.e. the
 * corresponding array would be [0])
 * iii. It has a constructor that takes an array of double as an argument and sets the
 * coefficients accordingly
 * iv. It has a method named add that takes one argument of type Polynomial and
 * returns the polynomial resulting from adding the calling object and the argument
 * v. It has a method named evaluate that takes one argument of type double
 * representing a value of x and evaluates the polynomial accordingly. For example,
 * if the polynomial is 6 - 2x + 5x^2 and evaluate(-1) is invoked, the result should
 * be 3.
 * vi. It has a method named hasRoot that takes one argument of type double and
 * determines whether this value is a root of the polynomial or not. Note that a root
 * is a value of x for which the polynomial evaluates to zero.
 */
public class Polynomial {
	double[] coefficient;
	
	public Polynomial() {
	    this.coefficient = new double[]{0};
	}
	
	public Polynomial(double[] coefficient) {
	    this.coefficient = coefficient;
	}
	
	public Polynomial add(Polynomial p) {
	    int len = Math.max(this.coefficient.length, p.coefficient.length);
	    double[] result = new double[len];
	    
	    for (int i = 0; i < len; i++) {
	        double coeff_t = 0;
	        double coeff_p = 0;
	        if (i < this.coefficient.length) {
	        	coeff_t = this.coefficient[i];
	        }
	        if (i < p.coefficient.length) {
	        	coeff_p = p.coefficient[i];
	        }
	        result[i] = coeff_t + coeff_p;
	    }
	    return new Polynomial(result);
	}
	
	public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficient.length; i++) {
            result += coefficient[i] * Math.pow(x, i);
        }
        return result;
    }
	
	public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}