package PrimeTypes;


import java.math.BigInteger;
import java.util.ArrayList;

public class discreteLog {

    public String log(long a, long p, long M) {
        ArrayList<table> listb = new ArrayList<table>();
        for (long i = 1 ; i < M; i++) {
            table entry = new table ();
            long startTime = System.nanoTime();
            entry.i = (int) getI(p,a,i);
            long endTime = System.nanoTime();
            entry.computationTime = (endTime - startTime ) ;
            entry.b = (int) i;
            listb.add(entry);

        }
        return printTable (listb) ;

    }
    public String printTable (ArrayList<table> listb) {
        String res = "";
        res += ("b	" +" i	" + "time\n");
        for (long i = 0 ; i < listb.size();i++) {
            res += (listb.get((int) i).b +"	" + listb.get((int) i).i + "	" + listb.get((int) i).computationTime +" nano sec\n");
        }
        return res;
    }

    public long getI (long p, long a, long b) {
        long i ;
        BigInteger bi1 = BigInteger.valueOf(a) ;
        BigInteger pp = BigInteger.valueOf(p) ;
        for ( i = 0 ; i <= p-1 ; i++) {
            if (i==0)
                continue;
            if (b == (bi1.pow((int) i)).mod(pp).intValue() ) {
                break;
            }
        }
        return i;

    }


}
