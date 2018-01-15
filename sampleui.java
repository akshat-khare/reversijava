import java.util.*;
public class ui{
    static int[][] disp = new int[8][8];
    public static void main(String[] args) {
        Random random = new Random();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                disp[i][j]= random.nextInt(2);
            }
        }
        for(int i=0;i<8;i++){
            System.out.println(disp[i].toString());
        }
    }
}