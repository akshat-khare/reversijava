import java.util.*;

class Main
{
    static int input[][] = new int[4][4];
    static int output[][] = new int[15][40];
    static int move;
    static int player1Led, player2Led;
    static int player1btn, player2btn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;i++){
                if()
            }
        }
    }
    static move(int player, int x, int y){
        // output[x][y] = player==1? 1: 0;
        if(checkValid(player,x,y)){
            // Play the move if valid
        }else{
            // Ask again for the input
        }
    }
    static checkValid(int player, int x, int y){
        boolean valid = false;
        int required = player==1? 2: 1;
        int consecutive = player==1? 1: 2;
        for(int i=y+1;i<8;){
            if(output[x][i]==consecutive){
                i++;
            }else{
                if(output[x][i]==required){
                    valid = true;
                    required = (required+2)%2 +1;
                    consecutive = (consecutive+2)%2 +1;
                }else{
                    valid = false;
                }
            }
        }
    }
}