import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class playFair {
    static char matrix[][] = new char[5][5];

    public  String encryption(String plainText, String KeyWord) {
        List plainPair = dived2pair(plainText);
        removeDuplicate(KeyWord);
        String cipher = new String();
        String pair = "";
        int char1[] = new int[2];
        int char2[] = new int[2];
        for (int i = 0; i < plainPair.size(); i++) {
            pair = (String) plainPair.get(i);
            char1 = searchMatrix(pair.charAt(0));
            char2 = searchMatrix(pair.charAt(1));
            if (char1[0] == char2[0]) {
                if (char1[1] < 4)
                    char1[1]++;

                else
                    char1[1] = 0;
                if (char2[1] < 4)
                    char2[1]++;
                else
                    char2[1] = 0;
            } else if (char1[1] == char2[1]) {
                if (char1[0] < 4)
                    char1[0]++;
                else
                    char1[0] = 0;
                if (char2[0] < 4)
                    char2[0]++;
                else
                    char2[0] = 0;
            } else {
                int temp = char1[1];
                char1[1] = char2[1];
                char2[1] = temp;
            }
            cipher = cipher + matrix[char1[0]][char1[1]] + matrix[char2[0]][char2[1]];
        }
        return cipher;

    }

    public  int[] searchMatrix(char letter) {
        int[] position = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == letter) {
                    position[0] = i;
                    position[1] = j;
                    break;
                }
            }
        }
        return position;
    }

    public  List dived2pair(String plainText) {
        List str = new ArrayList<String>();
        String pair = "";
        char dulicate = 'x';
        for (int i = 0, j = 0; i < plainText.length(); i++, j++) {
            if ((i + 1) < plainText.length()) {

                if (plainText.charAt(i) == plainText.charAt(i + 1)) {
                    pair += plainText.charAt(i);
                    pair += dulicate;
                    str.add(j, pair);
                    pair = "";
                } else {
                    pair = plainText.substring(i, i + 2);
                    str.add(j, pair);
                    i++;
                    pair = "";
                }
            }
        }
		/*
		 * for (int i = 0 ; i < str.size();i++) System.out.println(str.get(i));
		 */
        return str;
    }

    public  void buildalpha(String keyWord) {
        boolean flag = true;
        char current;
        String key = keyWord;
        for (int i = 0; i < 26; i++) {
            current = (char) (i + 97);
            if (current == 'j')
                continue;
            for (int j = 0; j < keyWord.length(); j++) {
                if (current == keyWord.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                key = key + current;
            flag = true;
        }
        //System.out.println(key);
        keyMatrix(key);
    }

    public  void keyMatrix(String keyWord) {

        int inx = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (inx < keyWord.length()) {
                    matrix[row][col] = keyWord.charAt(inx);
                    inx++;
                }
            }
        }
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				System.out.print(matrix[row][col] + "  ");
			}
			System.out.println();
		}

    }
    public  String splitting(String str) {
        String[] splited = str.split(" ");
        String s = "" ;
        for (int i = 0 ; i <splited.length ; i++ ) {
            s+=splited[i];
        }
        return s;

    }

    public  void removeDuplicate(String str) { // play fair exaplme a repeat 3
        // List<Character> output = new ArrayList<Character>();
        String output = new String();
        output = output + str.charAt(0);
        boolean flag = true;
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < output.length(); j++) {
                if (output.charAt(j) == str.charAt(i)) {
                    flag = false;
                }
            }
            if (flag) {
                output = output + str.charAt(i);
            }
            flag = true;
        }
        buildalpha(output); // add alpha after key to adding them to matrix

    }
    public  String decrypt(String cipher, String key ) {

        List plainPair = dived2pair(cipher);
        removeDuplicate(key);
        String plain = new String();
        String pair = "";
        int char1[] = new int[2];
        int char2[] = new int[2];
        for (int i = 0; i < plainPair.size(); i++) {
            pair = (String) plainPair.get(i);
            char1 = searchMatrix(pair.charAt(0));
            char2 = searchMatrix(pair.charAt(1));
            if (char1[0] == char2[0]) {
                if (char1[1] > 0)
                    char1[1]--;

                else
                    char1[1] = 4;
                if (char2[1] > 0)
                    char2[1]--;
                else
                    char2[1] = 4;
            } else if (char1[1] == char2[1]) {
                if (char1[0] > 0)
                    char1[0]--;
                else
                    char1[0] = 4;
                if (char2[0] > 0)
                    char2[0]--;
                else
                    char2[0] = 4;
            } else {
                int temp = char2[1];
                char2[1] = char1[1];
                char1[1] = temp;
            }
            plain = plain + matrix[char1[0]][char1[1]] + matrix[char2[0]][char2[1]];
        }
        plain = removeX(plain);

        return plain;
    }
    public  String removeX(String plain) {
        String str = "";
        for (int i = 0 ; i < plain.length() ; i++) {

            if (plain.charAt(i) =='x' && plain.charAt(i-1) == plain.charAt(i+1))
                continue;
            else
                str += plain.charAt(i);


        }
        return str;

    }

    public static void main(String[] arg) {
        /*String plainText , keyword ;
        System.out.println(" Enter plain text ");
        Scanner input = new Scanner(System.in);
        plainText = input.nextLine();
        System.out.println(" Enter Key word ");
        keyword = input.nextLine();
        String cipher = encryption(plainText.toLowerCase(), keyword.toLowerCase());
        System.out.println("cipher is : " + cipher);
        plainText = decrypt(cipher, keyword.toLowerCase());
        System.out.println("plain text is : " + plainText);*/
    }

}
