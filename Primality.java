package PrimeTypes;

import java.util.ArrayList;
import java.util.Scanner;

public class Primality {

    public boolean isPrime(long n )
    {
        if (n == 1 || n == 2)
            return true;
        if (n % 2 == 0)
            return false;
        for (long i = 2; i < n; i++)
            if (n % i == 0)
                return true;

        return false;
    }

    public ArrayList<Integer> phi(long n) {
        ArrayList relativePrimes = new ArrayList<>();
        relativePrimes.add(1);
        for (int i = 1; i < n; i++) {
            boolean commonFactor = false;
            if (n % i == 0)
                continue;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    if (n % j == 0) {
                        commonFactor = true;
                        break;
                    }
                }
            }
            if (!commonFactor) {
                relativePrimes.add(i);
            }
        }
        return relativePrimes;
    }

    public String Euler(long n, Integer a)
    {
        String res = "";
        ArrayList r = phi(n);
        boolean relatPrime =  false;
        for (long i = 0; i < r.size(); i++) {
            if (r.get((int) i) == a)
                relatPrime = true;
        }
        if (relatPrime) {
           res = "Yes, a is relative prime to n";
           if (Math.pow(a, r.size()) % n == 1) {
               res += "and the rule is valid";
           }
        } else {
            res = "A not relative to n";
            if (n % a == 0) {
                res += " because n is divisible by a";
            } else {
                res += " because there is a common factor between a and n";
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        Primality p = new Primality();
        Scanner in = new Scanner(System.in);
        int c = in.nextInt();
        if (c == 1) {
            int n = in.nextInt();
            if (p.isPrime(n)) {
                if (n == 1)
                    System.out.println(1 + "\nSize: 1");
                else {
                    for (int i = 1; i < n; i++) // print array from 1 to n-1
                    System.out.println(i);
                    System.out.println("Size: " + (n - 1));
                }
            } else {
                ArrayList r = p.phi(n);
                for (int i = 0; i < r.size(); i++) {
                    System.out.println(i + " ");
                }
                System.out.println("Size: " + r.size());
            }
        } else if (c == 2) {
            int n = in.nextInt();
            Integer a = in.nextInt();
            String res = p.Euler(n, a);
            System.out.println(res);
        }
    }
}
