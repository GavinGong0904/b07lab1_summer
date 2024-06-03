public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		}
}


import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        
        double[] c1 = {6, 5};
        int[] p1 = {0, 3};
        Polynomial p1 = new Polynomial(c1, p1);
        System.out.println(p1.evaluate(3);

        double[] c2 = {-2, -9};
        int[] p2 = {1, 4};
        Polynomial p2 = new Polynomial(c2, p2);
        System.out.println(p2.evaluate(3);

        Polynomial s = p1.add(p2);
        System.out.println(s.evaluate(0.1));

        if (s.hasRoot(1)) {
            System.out.println("1 is a root of s");
        } else {
            System.out.println("1 is not a root of s");
        }

        Polynomial t = p1.multiply(p2);
        System.out.println(t.evaluate(3));

        try {
            Polynomial polynomialText = new Polynomial(new File("polynomial.txt"));
            System.out.println("pFromFile(3) = " + polynomialText.evaluate(3));
        } catch (IOException e) {
            System.out.println("Error reading polynomial from file: " + e.getMessage());
        }

        try {
            s.saveToFile("output_polynomial.txt");
            System.out.println("Polynomial saved to output_polynomial.txt");
        } catch (IOException e) {
            System.out.println("Error saving polynomial to file: " + e.getMessage());
        }
    }
}
