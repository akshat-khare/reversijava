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
    public static boolean checkValidV2(String[][] obj, int x, int y, int player){
        String sameone = player+"";
        String otherone = (3- (player))+"";
        boolean row = false;
        boolean column =false;
        boolean diagonalrightslash = false;
        
        if(obj[x][y]=="1"|obj[x][y]=="2"){
            return false;
        }
        //check in the column
        if(x==0){
            //check down
            if(obj[1][y]!=otherone){
                row=false;
            }else{
                for(int i=2;i<8;i++){
                    if(obj[i][y]==sameone){
                        return true;
                    }else if(obj[i][y]=="_"){
                        row = false;
                        break;
                    }
                }
                row=false;
            }
        }else if(x==7){
            //check up
            if(obj[6][y]!=otherone){
                row=false;
            }else{
                for(int i=5;i>=0;i--){
                    if(obj[i][y]==sameone){
                        return true;
                    }else if(obj[i][y]=="_"){
                        row = false;
                        break;
                    }
                }
                row=false;
            }
        }else{
            //checkboth
            if(obj[x+1][y]!=otherone  && obj[x-1][y]!=otherone){
                row = false;
            }else if(obj[x+1][y]!=otherone){
                //checkonly up
                for(int i=x-2;i>=0;i--){
                    if(i==-1){
                        break;
                    }
                    if(obj[i][y]==sameone){
                        return true;
                    }else if(obj[i][y]=="_"){
                        row = false;
                        break;
                    }
                }
                row= false;
            }else if(obj[x-1][y]!=otherone){
                //checkonly down
                for(int i=x+2;i<8;i++){
                    if(i==8){
                        break;
                    }
                    if(obj[i][y]==sameone){
                        return true;
                    }else if(obj[i][y]=="_"){
                        row = false;
                        break;
                    }
                }
                row=false;
            }else{
                //checkboth
                for(int i=x-2;i>=0;i--){
                    if(i==-1){
                        break;
                    }
                    if(obj[i][y]==sameone){
                        return true;
                    }else if(obj[i][y]=="_"){
                        row = false;
                        break;
                    }
                }
                for(int i=x+2;i<8;i++){
                    if(i==8){
                        break;
                    }
                    if(obj[i][y]==sameone){
                        return true;
                    }else if(obj[i][y]=="_"){
                        row = false;
                        break;
                    }
                }
                row=false;
            }
        }
        //checkrow
        if(y==0){
            //check right
            if(obj[x][1]!=otherone){
                column=false;
            }else{
                for(int i=2;i<8;i++){

                    if(obj[x][i]==sameone){
                        return true;
                    }else if(obj[x][i]=="_"){
                        column = false;
                        break;
                    }
                }
                column=false;
            }
        }else if(x==7){
            //check left
            if(obj[x][6]!=otherone){
                column=false;
            }else{
                for(int i=5;i>=0;i--){
                    
                    if(obj[x][i]==sameone){
                        return true;
                    }else if(obj[x][i]=="_"){
                        column = false;
                        break;
                    }
                }
                column=false;
            }
        }else{
            //checkboth
            if(obj[x][y+1]!=otherone  && obj[x][y-1]!=otherone){
                column = false;
            }else if(obj[x][y+1]!=otherone){
                //checkonly left
                for(int i=y-2;i>=0;i--){
                    if(i==-1){
                        break;
                    }
                    if(obj[x][i]==sameone){
                        return true;
                    }else if(obj[x][i]=="_"){
                        column = false;
                        break;
                    }
                }
                column= false;
            }else if(obj[x][y-1]!=otherone){
                //checkonly right
                for(int i=y+2;i<8;i++){
                    if(i==8){
                        break;
                    }
                    if(obj[x][i]==sameone){
                        return true;
                    }else if(obj[x][i]=="_"){
                        column = false;
                        break;
                    }
                }
                column=false;
            }else{
                //checkboth
                for(int i=y-2;i>=0;i--){
                    if(i==-1){
                        break;
                    }
                    if(obj[x][i]==sameone){
                        return true;
                    }else if(obj[x][i]=="_"){
                        column = false;
                        break;
                    }
                }
                for(int i=y+2;i<8;i++){
                    if(i==8){
                        break;
                    }
                    if(obj[x][i]==sameone){
                        return true;
                    }else if(obj[x][i]=="_"){
                        column = false;
                        break;
                    }
                }
                column=false;
            }
        }
        //checkdiagonal 
        


        return false;
    }
}