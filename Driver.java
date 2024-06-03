import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println("We expect to get 0.0 and we get " + p.evaluate(3));
        
        double[] c1 = {6, 5};
        int[] p1 = {0, 3};
        Polynomial poly1 = new Polynomial(c1, p1);
        System.out.println("We expect to get 141.0 and we get " + poly1.evaluate(3));

        double[] c2 = {-2, -9};
        int[] p2 = {1, 4};
        Polynomial poly2 = new Polynomial(c2, p2);
        System.out.println("We expect to get -735.0 and we get " + poly2.evaluate(3));

        Polynomial s = poly1.add(poly2);
        System.out.println("We expect to get 5.8041 and we get " + s.evaluate(0.1));

        if (s.hasRoot(1)) {
            System.out.println("1 is a root of s");
        } else {
            System.out.println("1 is not a root of s");
        }

        Polynomial t = poly1.multiply(poly2);
        System.out.println("We expect to get -103635.0 and we get " + t.evaluate(3));

        try {
            Polynomial polynomialText = new Polynomial(new File("polynomial.txt"));
            System.out.println("We expect to get 45905.0 and we get " + polynomialText.evaluate(3));
        } catch (IOException e) {
            System.out.println("Error");
        }

        try {
            s.saveToFile("output_polynomial.txt");
            System.out.println("Polynomial has saved to output_polynomial.txt");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
