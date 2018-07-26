package Utilities;

public class PlainPermutation {
    private int[][] ip = new int[8][8];
    private int[][] invIp = new int[8][8];
    private int[][] e = new int[8][6];
    private int[][] p = new int[4][8];

    public int[][] getE() {
        return e;
    }

    public int[][] getP() {
        return p;
    }

    public void setP(int[][] p) {
        this.p = p;
    }

    public void setE(int[][] e) {
        this.e = e;
    }

    public int[][] getIp() {
        return ip;
    }

    public int[][] getInvIp() {
        return invIp;
    }

    public void setInvIp(int[][] invIp) {
        this.invIp = invIp;
    }

    public void setIp(int[][] ip) {
        this.ip = ip;
    }

    public PlainPermutation(){

        initializeIP();
        initializeinvIP();
        initializeE();
        initializeP();
    }

    private void initializeIP(){
        for(int i = 0; i < 4; ++i){
            for(int j = 0; j < 8; ++j){
                ip[i][j] = (i*2) + 58 - (j * 8);
            }
        }
        for(int i = 0; i < 4; ++i){
            for(int j = 0; j < 8; ++j){
                ip[i+4][j] = (i*2) + 57 - (j * 8);
            }
        }
    }
    private void initializeinvIP(){
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 8; i++){
                invIp[i][2*j] = (j*8) + 40 - i;
            }
        }
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 8; i++){
                invIp[i][(2*j) + 1] = (j*8) + 8 - i;
            }
        }
    }
    private void initializeE(){
        int x = 1;
        for(int i = 0; i < 8; ++i){
            for(int j = 1; j < 5; ++j){
                e[i][j] = x;
                x = (x + 1);
            }
        }
        for(int i = 1; i < 8; ++i){
            e[i][0] = i*4;
            e[i-1][5] = i*4 + 1;
        }
        e[0][0] = 32;
        e[7][5] = 1;
    }
    private void initializeP(){
        p[0][0] = 16;
        p[0][1] = 7;
        p[0][2] = 20;
        p[0][3] = 21;
        p[0][4] = 29;
        p[0][5] = 12;
        p[0][6] = 28;
        p[0][7] = 17;

        p[1][0] = 1;
        p[1][1] = 15;
        p[1][2] = 23;
        p[1][3] = 26;
        p[1][4] = 5;
        p[1][5] = 18;
        p[1][6] = 31;
        p[1][7] = 10;

        p[2][0] = 2;
        p[2][1] = 8;
        p[2][2] = 24;
        p[2][3] = 14;
        p[2][4] = 32;
        p[2][5] = 27;
        p[2][6] = 3;
        p[2][7] = 9;

        p[3][0] = 19;
        p[3][1] = 13;
        p[3][2] = 30;
        p[3][3] = 6;
        p[3][4] = 22;
        p[3][5] = 11;
        p[3][6] = 4;
        p[3][7] = 25;
    }

}