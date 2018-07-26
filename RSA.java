package PrimeTypes;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.StrictMath.pow;

import java.math.BigInteger;

public class RSA {

	private Vector<BigInteger> codedLetters = new Vector<BigInteger>(); 
    char alphabet[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
    
    public  String encrypt(String plainText, String p, String q)
    {
       // long phi = (p - 1) * (q - 1);
        //long n = p * q;

        BigInteger e = new BigInteger("-1");
        p = checkIfNegative(p);
        q = checkIfNegative(q);
        BigInteger pInt = new BigInteger(p);
        BigInteger qInt = new BigInteger(q);


        BigInteger phi = new BigInteger(String.valueOf(pInt.subtract(new BigInteger("1"))
                .multiply(qInt.subtract(new BigInteger("1")))));
        BigInteger n = pInt.multiply(qInt);
        BigInteger bi2;
        for (long i = 2 ; i < phi.intValue(); i++)
        {
            bi2 = BigInteger.valueOf(i) ;
            if (phi.gcd(bi2).equals(new BigInteger("1"))) {
                e = bi2;
                break;
            }
        }
        BigInteger d;
        for (long i = 1; true ; i++) {
            if (e.multiply(new BigInteger(String.valueOf(i))).mod(phi).equals(new BigInteger("1"))) {
                d = new BigInteger(String.valueOf(i));
                break;
            }
        }
        String cipher = "";
        for (int i = 0; i < plainText.length(); i++) {
            char letter = plainText.charAt(i);
            BigInteger c = BigInteger.valueOf(0);
            for (int j = 0; j < 27; j++) {
                if (alphabet[j] == letter) {
                    c = BigInteger.valueOf(j);
                    break;
                }
            }
            //           c = (long) (pow(c, e) % n);
            c = c.modPow(e, n); //c.pow(e).mod(BigInteger.valueOf(n)).intValue());
            if (c.intValue() < 10) {
                cipher += (0);
                cipher += c;
                codedLetters.add(c);
            } else {
                cipher += c;
            	codedLetters.add(c);
            }

        }
        return cipher;
    }

    public String checkIfNegative(String num) {
		// TODO Auto-generated method stub
    	if (num.charAt(0) == '-') {
    		num = num.substring(1, num.length());	
    	}
		return num;
	}

	public  String decrypt(String cipherText, String p, String q)
    {

        BigInteger e = new BigInteger("-1");
        BigInteger pInt = new BigInteger(p);
        BigInteger qInt = new BigInteger(q);
        BigInteger phi = new BigInteger(String.valueOf(pInt.subtract(new BigInteger("1"))
                .multiply(qInt.subtract(new BigInteger("1")))));
        BigInteger n = pInt.multiply(qInt);
        BigInteger bi2;
        for (long i = 2 ; i < phi.intValue(); i++)
        {
            bi2 = BigInteger.valueOf(i) ;
            if (phi.gcd(bi2).equals(new BigInteger("1"))) {
                e = bi2;
                break;
            }
        }
        BigInteger d;
        for (long i = 1; true ; i++) {
            if (e.multiply(new BigInteger(String.valueOf(i))).mod(phi).equals(new BigInteger("1"))) {
                d = new BigInteger(String.valueOf(i));
                break;
            }
        }
        String decrypted = "";
        for (int i = 0; i <  codedLetters.size(); i ++) {
            BigInteger letterIdx2 = codedLetters.get(i);
            int l = letterIdx2.modPow(d, n).intValue();

            char letter = alphabet[l];
            decrypted += (letter);
        }
        return decrypted;
    }

	 public boolean isPrime(long n )
	    {
	        if (n == 1 || n == 2)
	            return true;
	        if (n % 2 == 0)
	            return false;
	        for (long i = 2; i < n; i++)
	            if (n % i == 0)
	                return false;
	        return true;
	    }


    public static void main(String[] args) {
        while(true) {
            int c;
            Scanner input = new Scanner(System.in);
            System.out.println("Choooooooookse");
            //c = input.nextInt();
            //if (c == 1) {
                RSA rsa=  new RSA();
                String p, q;
                String str;
                p = input.nextLine();
                q = input.nextLine();
                str = input.nextLine();
                p = rsa.checkIfNegative(p);
                q = rsa.checkIfNegative(q);
                System.out.println(p + "  " + q);
                BigInteger pInt = new BigInteger(p);
                BigInteger qInt = new BigInteger(q);
                Boolean pprime = rsa.isPrime(pInt.longValue());
                Boolean qprime = rsa.isPrime(qInt.longValue());
                if (!pprime || !qprime) {
                	if (!pprime )
                		System.out.println("P not prime"); 
                	else 
                		System.out.println("q not prime");
                } else {
                	 String out = rsa.encrypt(str,p , q);
                     System.out.println(out);
                     out = rsa.decrypt(out, p, q);
                     System.out.println(out);
                }
               



        }
    }
}
