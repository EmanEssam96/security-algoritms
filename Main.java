package PrimeTypes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true) {

            Scanner input = new Scanner(System.in);

            System.out.println("Question num: ");
            int c = input.nextInt();
            if (c == 1) {
                System.out.println("Enter g, n");
                int g = input.nextInt();
                int n = input.nextInt();
                PrimitiveRoot primRoot = new PrimitiveRoot();
                System.out.println(primRoot.testPrimitiveRoot(g, n));
            } else {
                System.out.println("Enter base");
                int a = input.nextInt();
                System.out.println("Enter Modular");
                int p = input.nextInt();

                discreteLog obj = new discreteLog();

                System.out.println(obj.log(Math.abs(a), Math.abs(p), Math.abs(p)));
            }

        }
    }

}
