package PrimeTypes;

import java.util.ArrayList;

public class PrimitiveRoot {
    public String testPrimitiveRoot(int g, int n)
    {
        String res = "";
        if (g == n) {
            res = g + " is not primitive root to " + n;
        } else {
            Primality p = new Primality();
            ArrayList r = p.phi(n);
            ArrayList v = new ArrayList();
            for (int i = 0; i < n; i++) {
                v.add(0);
            }
            for (int i = 0; i < n; i++) {
                int num = (int) (Math.pow(g, i) % n);
                v.set(num, 1);
            }
            int num = 0;
            boolean emptyRes = true;
            for (int i = 0; i < r.size(); i++) {
                if ((int)v.get((int) r.get(i)) == 1) {
                    num++;
                } else {
                    if (emptyRes) {
                        res +=  "\nAs numbers ";
                        emptyRes = false;
                    }
                    res += (int) r.get(i);
                    res += " ";
                }
            }
            if (num == r.size())
                res +=  g + " is primitive root to " + n ;
            else
                res += "not a primitive root " +
                        " mod " + n;
        }

        return res;
    }

}
