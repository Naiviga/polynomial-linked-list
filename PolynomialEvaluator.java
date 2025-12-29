import java.util.Scanner;


class PolynomialNode {
    double coefficient;
    int power;
    PolynomialNode next;

    public PolynomialNode(double coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
        this.next = null;
    }
}


 // Polynomial implemented using Linked List
 
class Polynomial {
    private PolynomialNode head;
    private int degree;
    private int numberOfTerms;

    public Polynomial() {
        head = null;
        degree = 0;
        numberOfTerms = 0;
    }

    // Adds a term in descending order of power
    public void addTerm(double coefficient, int power) {

        if (coefficient == 0) {
            return;
        }

        PolynomialNode newNode = new PolynomialNode(coefficient, power);

        if (head == null || power > head.power) {
            newNode.next = head;
            head = newNode;
        } else {
            PolynomialNode current = head;

            while (current.next != null && current.next.power > power) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        numberOfTerms++;
        degree = Math.max(degree, power);
    }

    // Evaluates the polynomial for a given x
    public double evaluate(double x) {
        double result = 0.0;
        PolynomialNode current = head;

        while (current != null) {
            result += current.coefficient * Math.pow(x, current.power);
            current = current.next;
        }

        return result;
    }

    // Displays the polynomial
    public void display() {
        PolynomialNode current = head;

        while (current != null) {
            System.out.print(current.coefficient + "x^" + current.power);
            if (current.next != null) {
                System.out.print(" + ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public int getDegree() {
        return degree;
    }

    public int getNumberOfTerms() {
        return numberOfTerms;
    }
}

// MAIN CLASS

public class PolynomialEvaluator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Polynomial polynomial = new Polynomial();

        System.out.print("Enter number of terms: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println("Enter details for term " + i);

            System.out.print("Coefficient: ");
            double coeff = sc.nextDouble();

            System.out.print("Power: ");
            int power = sc.nextInt();

            polynomial.addTerm(coeff, power);
        }

        System.out.print("\nPolynomial Expression: ");
        polynomial.display();

        System.out.print("\nEnter value of x: ");
        double x = sc.nextDouble();

        System.out.println("Value of polynomial at x = " + x + " is: "
                + polynomial.evaluate(x));

        System.out.println("Degree of Polynomial: " + polynomial.getDegree());
        System.out.println("Number of Terms: " + polynomial.getNumberOfTerms());

        sc.close();
    }
}
