import java.util.*;

public class Hill {

    private String cipherStr, plainText, keyStr, decrypted;
    private  int degree;
    private Vector<Vector<Character>> key = new Vector<Vector<Character>>();
    private Vector<Integer> cipher = new Vector<Integer>();

    public  Hill (String p, String k, int d) {
        plainText = p;
        keyStr = k;
        degree = d;
        keyStrToVec(k);
        cipherStr = "";
        decrypted = "";
    }

    public int letterToCode(Character l) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alpha.length(); i++) {
            if (alpha.charAt(i) == l)
                return i;
        }
        return -1;
    }

    public Character codeToLetter(int c) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        return alpha.charAt(c);
    }

    public void keyStrToVec (String k) { ////////////////handle side conditions
        key.clear();
        if (degree == 2) {
            Vector<Character> v = new Vector();
            v.add(k.charAt(0));
            v.add(k.charAt(1));
            key.add(v);
            v = new Vector();
            v.add(k.charAt(2));
            v.add(k.charAt(3));
            key.add(v);

        }
        if (degree == 3) {
            Vector<Character> v = new Vector();
            v.add(keyStr.charAt(0));
            v.add(keyStr.charAt(1));
            v.add(keyStr.charAt(2));
            key.add(v);
            v = new Vector();
            v.add(keyStr.charAt(3));
            v.add(keyStr.charAt(4));
            v.add(keyStr.charAt(5));
            key.add(v);
            v = new Vector();
            v.add(keyStr.charAt(6));
            v.add(keyStr.charAt(7));
            v.add(keyStr.charAt(8));
            key.add(v);
        }
    } ///////// handle extra condition

    public void matByVecMultiplication (Vector plain) {
        for (int i = 0; i < plain.size(); i++) {
            Vector row = key.get(i);
            int sum = 0;
            for (int j = 0; j < plain.size(); j++) {
                int keyCode = letterToCode((Character) row.get(j));
                int letterCode = letterToCode((Character) plain.get(j));
                sum += letterCode * keyCode;
            }
            cipher.add(sum % 26);
        }
    }

    private void convertCipherToText() {
        for (int i = 0; i < cipher.size(); i++) {
            cipherStr += codeToLetter(cipher.get(i));
        }
    }

    public String encrypt () {
        Vector<Character> text = new Vector<Character>();
        for (int i = 0; i < plainText.length(); i++) {
            text.add(plainText.charAt(i));
            if (text.size() == degree) {
                matByVecMultiplication(text);
                text = new Vector<Character>();
            }
        }
        if (plainText.length() % degree  != 0) {
            for (int i = text.size(); i < degree ; i++) {
                text.add('a');
                if (i == degree)
                    matByVecMultiplication(text);
            }
        }
        convertCipherToText();
        return cipherStr;
    }

    public String decrypt(String t, String k) {
        keyStrToVec(k);
        if (degree == 2) {
            int d_1 = 0;
            // get multiplicative inverse
            int dk = (letterToCode(key.get(0).get(0)) * letterToCode(key.get(1).get(1))
                    - letterToCode(key.get(1).get(0)) *  letterToCode(key.get(0).get(1)));
            dk %= 26;
            if (dk < 0)
                dk += 26;
            for (int i = 1; i <= 100; i++) {
                if ((dk * i) % 26 == 1) {
                    d_1 = i;
                    break;
                }
            }
            // adgujate matrix
            Vector<Vector<Integer>> key_1 = new Vector<Vector<Integer>>();
            Vector intKey = new Vector();
            for (int i = 0; i < key.size(); i++) {
                Vector row = key.get(i);
                intKey = new Vector();
                for (int j = 0; j < key.get(0).size(); j++) {
                    int keyCode = letterToCode((Character) row.get(j));
                    intKey.add(keyCode);
                }
                key_1.add(intKey);
            }
            int tmp = key_1.get(0).get(0);
            key_1.get(0).set(0, (key_1.get(1).get(1) * d_1) % 26);
            if (key_1.get(0).get(0) < 0)
                key_1.get(0).set(0, (key_1.get(0).get(0) + 26));
            key_1.get(1).set(1, (tmp * d_1) % 26);
            if (key_1.get(1).get(1) < 0)
                key_1.get(1).set(1, (key_1.get(1).get(1) + 26));
            key_1.get(0).set(1, (( -key_1.get(0).get(1) % 26) * d_1)  % 26);
            if (key_1.get(0).get(1) < 0)
                key_1.get(0).set(1, (key_1.get(0).get(1) + 26));
            key_1.get(1).set(0, (( -key_1.get(1).get(0) % 26) * d_1 ) % 26 );
            if (key_1.get(1).get(0) < 0)
                key_1.get(1).set(0, (key_1.get(1).get(0) + 26));


            Vector<Character> text = new Vector<Character>();
            for (int l = 0; l < cipherStr.length(); l++) {
                text.add(cipherStr.charAt(l));
                if (text.size() == degree) {
                    for (int i = 0; i < key_1.get(1).size(); i++) {
                        Vector row = key_1.get(i);
                        int sum = 0;
                        for (int j = 0; j < key_1.get(1).size(); j++) {
                            int letterCode = letterToCode((Character) text.get(j));
                            sum += letterCode * key_1.get(i).get(j);
                        }
                        decrypted += codeToLetter(sum % 26);
                    }
                    text = new Vector<Character>();
                }
            }

            if (decrypted.length() % degree  != 0) {
                for (int i = decrypted.length(); i <= degree ; i++) {
                    text.add('a');
                }
            }
        }
        return decrypted;

    }



}
