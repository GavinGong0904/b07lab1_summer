/** CSCB07 Lab 2
 * Modify Polynomial.java as follows:
 * a. Replace the array representing the coefficients by two arrays: one representing the nonzero
 * coefficients (of type double) and another one representing the corresponding
 * exponents (of type int). For example, the polynomial 6 - 2x + 5x^3 would be represented
 * using the arrays [6, -2, 5] and [0, 1, 3]
 * b. Update the existing methods accordingly
 * c. Add a method named multiply that takes one argument of type Polynomial and returns
 * the polynomial resulting from multiplying the calling object and the argument. The
 * resulting polynomial should not contain redundant exponents.
 * d. Add a constructor that takes one argument of type File and initializes the polynomial
 * based on the contents of the file. You can assume that the file contains one line with no
 * whitespaces representing a valid polynomial. For example: the line 5-3x2+7x8
 * corresponds to the polynomial 5 - 3x^2 + 7x^8
 * Hint: you might want to use the following methods: split of the String class, parseInt of
 * the Integer class, and parseDouble of the Double class
 * e. Add a method named saveToFile that takes one argument of type String representing a
 * file name and saves the polynomial in textual format in the corresponding file (similar to
 * the format used in part d)
 * f. You can add any supplementary classes/methods you might find useful
 */
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Polynomial {
	double[] coefficient;
	int[] power;
	
	public Polynomial() {
	    this.coefficient = new double[]{0};
	    this.power = new int[]{0};
	}
	
	public Polynomial(double[] coefficient, int[] power) {
	    this.coefficient = coefficient;
	    this.power = power;
	}

//	public int getAddLength(Polynomial polynomial) {
//		int length = 0, i = 0, j = 0;
//		while (i < this.power.length && j < polynomial.power.length) {
//			if (this.power[i] == polynomial.power[j]) {
//				length++;
//				i++;
//				j++;
//			} else if (this.power[i] < polynomial.power[j]) {
//				i++;
//			} else {
//				j++;
//			}
//		}
//		return length;
//	}
//
//	public Polynomial add(Polynomial polynomial) {
//		int length = getAddLength(polynomial);
//		double[] resultCoefficient = new double[length];
//	    int[] resultPower = new int[length];
//	    int i = 0, j = 0;
//	    for (int k = 0; k < length; k++) {
//	        if (i == this.power.length) {
//	        	resultCoefficient[k] = polynomial.coefficient[j];
//	        	resultPower[k] = polynomial.power[j];
//	        	j++;
//	        } else if (j == polynomial.power.length) {
//	        	resultCoefficient[k] = this.coefficient[i];
//	        	resultPower[k] = this.power[i];
//	        	i++;
//	        } else {
//	        	resultCoefficient[k] = this.coefficient[i] + polynomial.coefficient[j];
//	        	resultPower[k] = polynomial.power[i];
//	        	i++;
//	        	j++;
//	        }
//	    }
//	    return new Polynomial(resultCoefficient, resultPower);
//	}

    public Polynomial add(Polynomial polynomial) {
        List<Double> resultCoefficients = new ArrayList<>();
        List<Integer> resultPowers = new ArrayList<>();
        int i = 0, j = 0;
        while (i < this.coefficient.length || j < polynomial.coefficient.length) {
            if (i < this.coefficient.length && (j >= polynomial.coefficient.length || this.power[i] > polynomial.power[j])) {
                resultCoefficients.add(this.coefficient[i]);
                resultPowers.add(this.power[i]);
                i++;
            } else if (j < polynomial.coefficient.length && (i >= this.coefficient.length || this.power[i] < polynomial.power[j])) {
                resultCoefficients.add(polynomial.coefficient[j]);
                resultPowers.add(polynomial.power[j]);
                j++;
            } else {
                resultCoefficients.add(this.coefficient[i] + polynomial.coefficient[j]);
                resultPowers.add(this.power[i]);
                i++;
                j++;
            }
        }
        double[] resultCoefficientArray = new double[resultCoefficients.size()];
        int[] resultPowerArray = new int[resultPowers.size()];

        for (int k = 0; k < resultCoefficients.size(); k++) {
            resultCoefficientArray[k] = resultCoefficients.get(k);
            resultPowerArray[k] = resultPowers.get(k);
        }
        return new Polynomial(resultCoefficientArray, resultPowerArray);
    }
	
	public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficient.length; i++) {
            result += this.coefficient[i] * Math.pow(x, this.power[i]);
        }
        return result;
    }
	
	public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

//	public Polynomial multiply(Polynomial polynomial) {
//		HashSet<Integer> powerSet = new HashSet<>();
//		List<Integer> powerList = new ArrayList<>();
//		for (int i = 0; i < this.power.length; i++) {
//			for (int j = 0; j < polynomial.power.length; j++) {
//				powerSet.add(this.power[i] + polynomial.power[j]);
//			}
//		}
//		int[] resultPower = new int[powerSet.size()];
//		for (int i = 0; i < powerList.size(); i++) {
//		    resultPower[i] = powerList.get(i);
//		}
//		Collections.sort(powerList);
//		for (int i = 0; i < powerList.size(); i++) {
//		    resultPower[i] = powerList.get(i);
//		}
//		double[] resultCoefficient = new double[resultPower.length];
//		int k = 0;
//		double l = 0;
//		for (int i = 0; i < this.power.length; i++) {
//			for (int j = i; j < polynomial.power.length; j++) {
//				k = this.power[i] + polynomial.power[j];
//				l = this.coefficient[i] * polynomial.coefficient[j];
//				for (int m = 0; m < resultPower.length; m++) {
//					if (resultPower[m] == k) {
//						resultCoefficient[m] += l;
//					}
//				}
//			}
//		}
//		return new Polynomial(resultCoefficient, resultPower);
//	}
	
	public Polynomial multiply(Polynomial polynomial) {
        List<Double> resultCoefficients = new ArrayList<>();
        List<Integer> resultPowers = new ArrayList<>();
        for (int i = 0; i < this.coefficient.length; i++) {
            for (int j = 0; j < polynomial.coefficient.length; j++) {
                double newCoefficient = this.coefficient[i] * polynomial.coefficient[j];
                int newPower = this.power[i] + polynomial.power[j];
                int index = resultPowers.indexOf(newPower);
                if (index != -1) {
                    resultCoefficients.set(index, resultCoefficients.get(index) + newCoefficient);
                } else {
                    resultCoefficients.add(newCoefficient);
                    resultPowers.add(newPower);
                }
            }
        }
        double[] resultCoefficientArray = new double[resultCoefficients.size()];
        int[] resultPowerArray = new int[resultPowers.size()];
        for (int k = 0; k < resultCoefficients.size(); k++) {
            resultCoefficientArray[k] = resultCoefficients.get(k);
            resultPowerArray[k] = resultPowers.get(k);
        }
        return new Polynomial(resultCoefficientArray, resultPowerArray);
    }

	public Polynomial(File file) throws IOException {
        // Get the string from file.
		Scanner scanner = new Scanner(file);
        String polynomialStr = scanner.nextLine();
        scanner.close();
        // Deal with the string.
		String[] part = polynomialStr.split("(?=[+-])");
		List<Double> coefficientList = new ArrayList<>();
		List<Integer> powerList = new ArrayList<>();
		for (int i = 0; i < part.length; i++) {
		    String term = part[i];
		    double coefficient = 0;
		    int power = 0;
		    if (term.contains("x")) {
		        String[] itemPart = term.split("x");
		        // The coefficient part.
		        if (itemPart[0].isEmpty() || itemPart[0].equals("+")) {
		            coefficient = 1;
		        } else if (itemPart[0].equals("-")) {
		            coefficient = -1;
		        } else {
		            coefficient = Double.parseDouble(itemPart[0]);
		        }
		        // The power part.
		        if (itemPart.length > 1) {
		            power = Integer.parseInt(itemPart[1].replace("^", ""));
		        } else {
		            power = 1;
		        }
		    } else {
		        coefficient = Double.parseDouble(term);
		        power = 0;
		    }
		    coefficientList.add(coefficient);
		    powerList.add(power);
		}
		this.coefficient = new double[coefficientList.size()];
        this.power = new int[powerList.size()];
        for (int i = 0; i < coefficientList.size(); i++) {
            this.coefficient[i] = coefficientList.get(i);
            this.power[i] = powerList.get(i);
        }
	}
	
	public void saveToFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < this.coefficient.length; i++) {
            double coefficient = this.coefficient[i];
            int power = this.power[i];
            // Add sign.
            if (coefficient > 0 && i != 0) {
                printWriter.print("+");
            }
            // Add coefficient.
            if (coefficient != 1 || power == 0) {
                printWriter.print(coefficient);
            }
            // Add power.
            if (power != 0) {
                printWriter.print("x");
                if (power != 1) {
                	printWriter.print("^" + power);
                }
            }
        }
        printWriter.close();
    }
}