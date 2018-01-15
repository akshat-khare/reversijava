import java.util.*;
public class ui{
    static String[][] disp = new String[8][8];
    public static void main(String[] args) {
        //Random random = new Random();
        initialize();
        System.out.println(disp[0][0]);
        // for(int i=0;i<8;i++){
        //     for(int j=0;j<8;j++){
        //         int temp= random.nextInt(3);
        //         if(temp==0){
        //             disp[i][j]="_";
        //         }else{
        //             disp[i][j]=temp+"";
        //         }
        //     }
        // }
        for(int i=0;i<8;i++){
            System.out.println(Arrays.toString(disp[i]));
        }
    }
    public static void initialize(){
        disp[3][3] = "1";
        disp[4][4] = "1";
        disp[3][4] = "2";
        disp[4][3] = "2";
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(disp[i][j]!="1" && disp[i][j]!="2"){
                    disp[i][j]="_";
                }
            }
        }
    }
}