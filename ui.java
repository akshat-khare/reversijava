import java.util.*;
public class ui{
    static String[][] disp = new String[8][8];
    // static int onesscore = 0;
    // static int twoscore = 0;
    static int[] score = new int[3];
    public static void main(String[] args) {
        //Random random = new Random();
        initialize();
        //System.out.println(disp[0][0]);
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
        int[] columnindex = new int[8];
        for(int k=0;k<8;k++){
            columnindex[k]=k;
        }
        System.out.println("\\"+" "+Arrays.toString(columnindex));
        for(int i=0;i<8;i++){
            System.out.println(i+" "+Arrays.toString(disp[i]));
        }
        int chanceofplayer=1;
        Scanner s= new Scanner(System.in);
        while(true){
            if(score[1]+score[2]==64){
                break;
            }
            String[][] tempdisc =new  String[8][8];
            for(int h=0;h<8;h++){
                for(int g=0;g<8;g++){
                    tempdisc[h][g]=disp[h][g];
                }
            }
            boolean movesav = checkifvalid(tempdisc, chanceofplayer);
            if(movesav){
                System.out.println("you have a valid move and it is =");
                System.out.println("\\"+" "+Arrays.toString(columnindex));
                for(int i=0;i<8;i++){
                    System.out.println(i+" "+Arrays.toString(tempdisc[i]));
                }
            }else{
                System.out.println("you have no valid move");
            }
            // System.out.println("")
            // System.out.println("\\"+" "+Arrays.toString(columnindex));
            //     for(int i=0;i<8;i++){
            //         System.out.println(i+" "+Arrays.toString(tempdisc[i]));
            //     }

            System.out.println("Score is: player 1 has "+score[1]+" points and player 2 has "+score[2]+" points");
            System.out.println("you are player "+chanceofplayer);
            System.out.println("Enter your x coordinate where you want to place: ");
            int x=s.nextInt();
            // System.out.println("x is "+x);
            System.out.println("Enter your y coordinate where you want to place: ");
            int y=s.nextInt();
            // System.out.println("y is "+y);
            boolean wasvalid = checkValidV2(disp, x, y, chanceofplayer);
            if(wasvalid){
                System.out.println("your doing was valid");

                System.out.println("\\"+" "+Arrays.toString(columnindex));
                for(int i=0;i<8;i++){
                    System.out.println(i+" "+Arrays.toString(disp[i]));
                }
                chanceofplayer = 3-chanceofplayer;
            }else{
                System.out.println("Your doing was invalid");
                System.out.println("\\"+" "+Arrays.toString(columnindex));
                for(int i=0;i<8;i++){
                    System.out.println(i+" "+Arrays.toString(disp[i]));
                }
            }
            System.out.println("______________");
            //s.close();
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
        // onesscore=onesscore+2;
        // twoscore=twoscore+2;
        score[1]=2;
        score[2]=2;
    }
    public static boolean checkValidV2(String[][] obj, int x, int y, int player){

        
        String sameone = player+"";
        String otherone = (3- (player))+"";
        int initialscore = score[player];
        // boolean row = false;
        // boolean column =false;
        // boolean diagonalforwardlash = false;
        // boolean diagonalbackwardlash = false;
        // System.out.println("sameone is "+sameone);
        // System.out.println("otherone is "+otherone);
        // System.out.println("player is "+player);
        if(obj[x][y].equals("1")|obj[x][y].equals("2")){
            return false;
        }
        
        //checkforwaddiagonal/
        //x and y
        //check up x- y+    x-1 times if x+y<=7  6-y if x+y>7
        boolean forwardup=false;
        if(x==1 | x==0| y==6 | y==7){
            //upnotpossbile
        }else{
            if(!(obj[x-1][y+1].equals(otherone))){
                //upnotpossible
            }else{
                int numtimes=0;
                if(x+y<=7){
                    numtimes= x-1;
                }else{
                    numtimes= 6-y;
                }
                int tempx= x-2;
                int tempy= y+2;
                for(int i=0;i<numtimes;i++){
                    if(obj[tempx][tempy].equals(sameone)){
                        tempx= x-1;
                        tempy= y+1;
                        for(int j=0;j<=i;j++){
                            obj[tempx][tempy]=sameone;
                            tempx--;
                            tempy++;
                            score[player]=score[player]+i+1;
                        }
                        forwardup=true;

                        break;

                    }else if(obj[tempx][tempy].equals("_")){
                        //upnotpossible
                        break;
                    }
                    tempx--;
                    tempy++;
                }
                //upnotpossible
            }
        }
        //check down
        //x++ y-- y-1 times if x+y<=7 6-x if x+y>7
        boolean forwarddown =false;
        if(x==6 | x==7| y==0 | y==1){
            //upnotpossbile
        }else
        {
            if(!(obj[x+1][y-1].equals(otherone))){
                //upnotpossible
            }else{
                int numtimes=0;
                if(x+y<=7){
                    numtimes= y-1;
                }else{
                    numtimes= 6-x;
                }
                int tempx= x+2;
                int tempy= y-2;
                for(int i=0;i<numtimes;i++){
                    if(obj[tempx][tempy].equals(sameone)){
                        tempx= x+1;
                        tempy= y-1;
                        for(int j=0;j<=i;j++){
                            obj[tempx][tempy]=sameone;
                            tempx++;
                            tempy--;
                            score[player] = score[player]+i+1;
                        }
                        forwarddown=true;
                        break;

                    }else if(obj[tempx][tempy].equals("_")){
                        //upnotpossible
                        break;
                    }
                    tempx++;
                    tempy--;
                }
                //upnotpossible
            }
        }
        //checkbackslash
        //
        //checkup x-- y-- if y>=x x-1 times else y-1 times
        boolean backup = false;
        if(x==0|x==1|y==0|y==1){

        }else{
            if(!(obj[x-1][y-1].equals(otherone))){
                //upnotpossible
            }else{
                int numtimes=0;
                if(x<=y){
                    numtimes= x-1;
                }else{
                    numtimes= y-1;
                }
                int tempx= x-2;
                int tempy= y-2;
                for(int i=0;i<numtimes;i++){
                    if(obj[tempx][tempy].equals(sameone)){
                        tempx= x-1;
                        tempy= y-1;
                        for(int j=0;j<=i;j++){
                            obj[tempx][tempy]=sameone;
                            tempx--;
                            tempy--;
                            score[player] = score[player]+i+1;
                        }
                        backup=true;
                        break;

                    }else if(obj[tempx][tempy].equals("_")){
                        //upnotpossible
                        break;
                    }
                    tempx--;
                    tempy--;
                }
                //upnotpossible
            }
        }
        boolean backdown = false;
        
        // checkdown if y>=x 6-y times else 6-x times
        if(x==6|x==7|y==6|y==7){

        }else{
            if(!(obj[x+1][y+1].equals(otherone))){
                //upnotpossible
            }else{
                int numtimes=0;
                if(x<=y){
                    numtimes= 6-y;
                }else{
                    numtimes= 6-x;
                }
                int tempx= x+2;
                int tempy= y+2;
                for(int i=0;i<numtimes;i++){
                    if(obj[tempx][tempy].equals(sameone)){
                        tempx= x+1;
                        tempy= y+1;
                        for(int j=0;j<=i;j++){
                            obj[tempx][tempy]=sameone;
                            tempx++;
                            tempy++;
                            score[player] = score[player]+i+1;
                        }
                        forwardup=true;
                        break;

                    }else if(obj[tempx][tempy].equals("_")){
                        //upnotpossible
                        break;
                    }
                    tempx++;
                    tempy++;
                }
                //upnotpossible
            }
        }
        //horizontal
        //check right
        // System.out.println("it must enter here for righthori");
        boolean horizontalright =false;
        if(!(y==6| y==7) && obj[x][y+1].equals(otherone)){
            for(int i=y+2;i<=7;i++){
                if(obj[x][i].equals(sameone)){
                    for(int j=y+1;j<=i-1;j++){
                        obj[x][j]=sameone;
                        score[player] = score[player]+i-y-1;
                    }
                    horizontalright= true;
                    // System.out.println("true saabit ho chuka hai");
                    break;
                }else if(obj[x][i].equals("_")){
                    break;
                }
            }
        }
        // System.out.println("it must enter here for lefthori");
        boolean horizontalleft =false;
        if(!(y==0| y==1) && obj[x][y-1].equals(otherone)){
            // System.out.println("it must enter here for ");
            for(int i=y-2;i>=0;i--){
                if(obj[x][i].equals(sameone)){
                    for(int j=y-1;j>=i+1;j--){
                        obj[x][j]=sameone;
                        score[player] = score[player]+y-i-1;
                    }
                    horizontalleft= true;
                    
                    // System.out.println("true saabit ho chuka hai");
                    break;
                }else if(obj[x][i].equals("_")){
                    break;
                }
            }
        }

        boolean verticaldown =false;
        if(!(x==6| x==7) && obj[x+1][y].equals(otherone)){
            for(int i=x+2;i<=7;i++){
                if(obj[i][y].equals(sameone)){
                    for(int j=x+1;j<=i-1;j++){
                        obj[j][y]=sameone;
                        score[player] = score[player]+i-x-1;
                    }
                    verticaldown= true;
                    break;
                }else if(obj[i][x].equals("_")){
                    break;
                }
            }
        }
        boolean verticalup =false;
        if(!(x==0| x==1) && obj[x-1][y].equals(otherone)){
            for(int i=x-2;i>=0;i--){
                if(obj[i][y].equals(sameone)){
                    for(int j=x-1;j>=i+1;j--){
                        obj[j][y]=sameone;
                        score[player] = score[player]+x-i-1;
                    }
                    verticalup= true;
                    break;
                }else if(obj[i][y].equals("_")){
                    break;
                }
            }
        }

        if(horizontalleft==true |horizontalright==true|verticaldown==true|verticalup==true|forwarddown==true|forwardup==true|backup==true|backdown==true){
            obj[x][y]=sameone;
            score[player]=score[player]+1;
            score[3-player]=score[3-player] -(score[player]-initialscore-1);    
            return true;
        }else{
            return false;
        }
    }
    public static boolean checkifvalid(String[][] obj,int player){
        String sameone = player+"";
        String otherone = (3- (player))+"";
        boolean finbool=false;
        for(int k=0;k<8;k++){
            for(int l=0;l<8;l++){

                int x=k;
                int y=l;
                // if(obj[x][y].equals(sameone)| obj[x][y].equals(otherone)){
                //     continue;
                // }

                // int initialscore = score[player];
                // boolean row = false;
                // boolean column =false;
                // boolean diagonalforwardlash = false;
                // boolean diagonalbackwardlash = false;
                // System.out.println("sameone is "+sameone);
                // System.out.println("otherone is "+otherone);
                // System.out.println("player is "+player);
                if(obj[x][y].equals("1")|obj[x][y].equals("2")){
                    continue;
                }
                
                //checkforwaddiagonal/
                //x and y
                //check up x- y+    x-1 times if x+y<=7  6-y if x+y>7
                boolean forwardup=false;
                if(x==1 | x==0| y==6 | y==7){
                    //upnotpossbile
                }else{
                    if(!(obj[x-1][y+1].equals(otherone))){
                        //upnotpossible
                    }else{
                        int numtimes=0;
                        if(x+y<=7){
                            numtimes= x-1;
                        }else{
                            numtimes= 6-y;
                        }
                        int tempx= x-2;
                        int tempy= y+2;
                        for(int i=0;i<numtimes;i++){
                            if(obj[tempx][tempy].equals(sameone)){
                                // tempx= x-1;
                                // tempy= y+1;
                                // for(int j=0;j<=i;j++){
                                //     obj[tempx][tempy]=sameone;
                                //     tempx--;
                                //     tempy++;
                                //     score[player]=score[player]+i+1;
                                // }
                                forwardup=true;

                                break;

                            }else if(obj[tempx][tempy].equals("_") | obj[tempx][tempy].equals("=") ){
                                //upnotpossible
                                break;
                            }
                            tempx--;
                            tempy++;
                        }
                        //upnotpossible
                    }
                }
                //check down
                //x++ y-- y-1 times if x+y<=7 6-x if x+y>7
                boolean forwarddown =false;
                if(x==6 | x==7| y==0 | y==1){
                    //upnotpossbile
                }else
                {
                    if(!(obj[x+1][y-1].equals(otherone))){
                        //upnotpossible
                    }else{
                        int numtimes=0;
                        if(x+y<=7){
                            numtimes= y-1;
                        }else{
                            numtimes= 6-x;
                        }
                        int tempx= x+2;
                        int tempy= y-2;
                        for(int i=0;i<numtimes;i++){
                            if(obj[tempx][tempy].equals(sameone)){
                                // tempx= x+1;
                                // tempy= y-1;
                                // for(int j=0;j<=i;j++){
                                //     obj[tempx][tempy]=sameone;
                                //     tempx++;
                                //     tempy--;
                                //     score[player] = score[player]+i+1;
                                // }
                                forwarddown=true;
                                break;

                            }else if(obj[tempx][tempy].equals("_") | obj[tempx][tempy].equals("=")){
                                //upnotpossible
                                break;
                            }
                            tempx++;
                            tempy--;
                        }
                        //upnotpossible
                    }
                }
                //checkbackslash
                //
                //checkup x-- y-- if y>=x x-1 times else y-1 times
                boolean backup = false;
                if(x==0|x==1|y==0|y==1){

                }else{
                    if(!(obj[x-1][y-1].equals(otherone))){
                        //upnotpossible
                    }else{
                        int numtimes=0;
                        if(x<=y){
                            numtimes= x-1;
                        }else{
                            numtimes= y-1;
                        }
                        int tempx= x-2;
                        int tempy= y-2;
                        for(int i=0;i<numtimes;i++){
                            if(obj[tempx][tempy].equals(sameone)){
                                // tempx= x-1;
                                // tempy= y-1;
                                // for(int j=0;j<=i;j++){
                                //     obj[tempx][tempy]=sameone;
                                //     tempx--;
                                //     tempy--;
                                //     score[player] = score[player]+i+1;
                                // }
                                backup=true;
                                break;

                            }else if(obj[tempx][tempy].equals("_") | obj[tempx][tempy].equals("=")){
                                //upnotpossible
                                break;
                            }
                            tempx--;
                            tempy--;
                        }
                        //upnotpossible
                    }
                }
                boolean backdown = false;
                
                // checkdown if y>=x 6-y times else 6-x times
                if(x==6|x==7|y==6|y==7){

                }else{
                    if(!(obj[x+1][y+1].equals(otherone))){
                        //upnotpossible
                    }else{
                        int numtimes=0;
                        if(x<=y){
                            numtimes= 6-y;
                        }else{
                            numtimes= 6-x;
                        }
                        int tempx= x+2;
                        int tempy= y+2;
                        for(int i=0;i<numtimes;i++){
                            if(obj[tempx][tempy].equals(sameone)){
                                // tempx= x+1;
                                // tempy= y+1;
                                // for(int j=0;j<=i;j++){
                                //     obj[tempx][tempy]=sameone;
                                //     tempx++;
                                //     tempy++;
                                //     score[player] = score[player]+i+1;
                                // }
                                forwardup=true;
                                break;

                            }else if(obj[tempx][tempy].equals("_") | obj[tempx][tempy].equals("=")){
                                //upnotpossible
                                break;
                            }
                            tempx++;
                            tempy++;
                        }
                        //upnotpossible
                    }
                }
                //horizontal
                //check right
                // System.out.println("it must enter here for righthori");
                boolean horizontalright =false;
                if(!(y==6| y==7) && obj[x][y+1].equals(otherone)){
                    for(int i=y+2;i<=7;i++){
                        if(obj[x][i].equals(sameone)){
                            // for(int j=y+1;j<=i-1;j++){
                            //     obj[x][j]=sameone;
                            //     score[player] = score[player]+i-y-1;
                            // }
                            horizontalright= true;
                            // System.out.println("true saabit ho chuka hai");
                            break;
                        }else if(obj[x][i].equals("_") | obj[x][i].equals("=")){
                            break;
                        }
                    }
                }
                // System.out.println("it must enter here for lefthori");
                boolean horizontalleft =false;
                if(!(y==0| y==1) && obj[x][y-1].equals(otherone)){
                    // System.out.println("it must enter here for ");
                    for(int i=y-2;i>=0;i--){
                        if(obj[x][i].equals(sameone)){
                            // for(int j=y-1;j>=i+1;j--){
                            //     obj[x][j]=sameone;
                            //     score[player] = score[player]+y-i-1;
                            // }
                            horizontalleft= true;
                            
                            // System.out.println("true saabit ho chuka hai");
                            break;
                        }else if(obj[x][i].equals("_") | obj[x][i].equals("=")){
                            break;
                        }
                    }
                }

                boolean verticaldown =false;
                if(!(x==6| x==7) && obj[x+1][y].equals(otherone)){
                    for(int i=x+2;i<=7;i++){
                        if(obj[i][y].equals(sameone)){
                            // for(int j=x+1;j<=i-1;j++){
                            //     obj[j][y]=sameone;
                            //     score[player] = score[player]+i-x-1;
                            // }
                            verticaldown= true;
                            break;
                        }else if(obj[i][x].equals("_") | obj[i][x].equals("=")){
                            break;
                        }
                    }
                }
                boolean verticalup =false;
                if(!(x==0| x==1) && obj[x-1][y].equals(otherone)){
                    for(int i=x-2;i>=0;i--){
                        if(obj[i][y].equals(sameone)){
                            // for(int j=x-1;j>=i+1;j--){
                            //     obj[j][y]=sameone;
                            //     score[player] = score[player]+x-i-1;
                            // }
                            verticalup= true;
                            break;
                        }else if(obj[i][y].equals("_") | obj[i][y].equals("=")){
                            break;
                        }
                    }
                }

                if(horizontalleft==true |horizontalright==true|verticaldown==true|verticalup==true|forwarddown==true|forwardup==true|backup==true|backdown==true){
                    obj[x][y]="$";
                    // score[player]=score[player]+1;
                    // score[3-player]=score[3-player] -(score[player]-initialscore-1);    
                    finbool= true;
                }




            }
        }
        return finbool;
    }
}