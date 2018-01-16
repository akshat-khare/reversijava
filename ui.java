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
        // boolean row = false;
        // boolean column =false;
        // boolean diagonalforwardlash = false;
        // boolean diagonalbackwardlash = false;
        if(obj[x][y]=="1"|obj[x][y]=="2"){
            return false;
        }
        //check in the column
        // if(x==0){
        //     //check down
        //     if(obj[1][y]!=otherone){
        //         row=false;
        //     }else{
        //         for(int i=2;i<8;i++){
        //             if(obj[i][y]==sameone){

        //                 return true;
        //             }else if(obj[i][y]=="_"){
        //                 row = false;
        //                 break;
        //             }
        //         }
        //         row=false;
        //     }
        // }else if(x==7){
        //     //check up
        //     if(obj[6][y]!=otherone){
        //         row=false;
        //     }else{
        //         for(int i=5;i>=0;i--){
        //             if(obj[i][y]==sameone){
        //                 return true;
        //             }else if(obj[i][y]=="_"){
        //                 row = false;
        //                 break;
        //             }
        //         }
        //         row=false;
        //     }
        // }else{
        //     //checkboth
        //     if(obj[x+1][y]!=otherone  && obj[x-1][y]!=otherone){
        //         row = false;
        //     }else if(obj[x+1][y]!=otherone){
        //         //checkonly up
        //         for(int i=x-2;i>=0;i--){
        //             if(i==-1){
        //                 break;
        //             }
        //             if(obj[i][y]==sameone){
        //                 return true;
        //             }else if(obj[i][y]=="_"){
        //                 row = false;
        //                 break;
        //             }
        //         }
        //         row= false;
        //     }else if(obj[x-1][y]!=otherone){
        //         //checkonly down
        //         for(int i=x+2;i<8;i++){
        //             if(i==8){
        //                 break;
        //             }
        //             if(obj[i][y]==sameone){
        //                 return true;
        //             }else if(obj[i][y]=="_"){
        //                 row = false;
        //                 break;
        //             }
        //         }
        //         row=false;
        //     }else{
        //         //checkboth
        //         for(int i=x-2;i>=0;i--){
        //             if(i==-1){
        //                 break;
        //             }
        //             if(obj[i][y]==sameone){
        //                 return true;
        //             }else if(obj[i][y]=="_"){
        //                 row = false;
        //                 break;
        //             }
        //         }
        //         for(int i=x+2;i<8;i++){
        //             if(i==8){
        //                 break;
        //             }
        //             if(obj[i][y]==sameone){
        //                 return true;
        //             }else if(obj[i][y]=="_"){
        //                 row = false;
        //                 break;
        //             }
        //         }
        //         row=false;
        //     }
        // }
        // //checkrow
        // if(y==0){
        //     //check right
        //     if(obj[x][1]!=otherone){
        //         column=false;
        //     }else{
        //         for(int i=2;i<8;i++){

        //             if(obj[x][i]==sameone){
        //                 return true;
        //             }else if(obj[x][i]=="_"){
        //                 column = false;
        //                 break;
        //             }
        //         }
        //         column=false;
        //     }
        // }else if(x==7){
        //     //check left
        //     if(obj[x][6]!=otherone){
        //         column=false;
        //     }else{
        //         for(int i=5;i>=0;i--){
                    
        //             if(obj[x][i]==sameone){
        //                 return true;
        //             }else if(obj[x][i]=="_"){
        //                 column = false;
        //                 break;
        //             }
        //         }
        //         column=false;
        //     }
        // }else{
        //     //checkboth
        //     if(obj[x][y+1]!=otherone  && obj[x][y-1]!=otherone){
        //         column = false;
        //     }else if(obj[x][y+1]!=otherone){
        //         //checkonly left
        //         for(int i=y-2;i>=0;i--){
        //             if(i==-1){
        //                 break;
        //             }
        //             if(obj[x][i]==sameone){
        //                 return true;
        //             }else if(obj[x][i]=="_"){
        //                 column = false;
        //                 break;
        //             }
        //         }
        //         column= false;
        //     }else if(obj[x][y-1]!=otherone){
        //         //checkonly right
        //         for(int i=y+2;i<8;i++){
        //             if(i==8){
        //                 break;
        //             }
        //             if(obj[x][i]==sameone){
        //                 return true;
        //             }else if(obj[x][i]=="_"){
        //                 column = false;
        //                 break;
        //             }
        //         }
        //         column=false;
        //     }else{
        //         //checkboth
        //         for(int i=y-2;i>=0;i--){
        //             if(i==-1){
        //                 break;
        //             }
        //             if(obj[x][i]==sameone){
        //                 return true;
        //             }else if(obj[x][i]=="_"){
        //                 column = false;
        //                 break;
        //             }
        //         }
        //         for(int i=y+2;i<8;i++){
        //             if(i==8){
        //                 break;
        //             }
        //             if(obj[x][i]==sameone){
        //                 return true;
        //             }else if(obj[x][i]=="_"){
        //                 column = false;
        //                 break;
        //             }
        //         }
        //         column=false;
        //     }
        // }
        //checkforwaddiagonal/
        //x and y
        //check up x- y+    x-1 times if x+y<=7  6-y if x+y>7
        boolean forwardup=false;
        if(x==1 | x==0| y==6 | y==7){
            //upnotpossbile
        }else{
            if(obj[x-1][y+1]!=otherone){
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
                    if(obj[tempx][tempy]==sameone){
                        tempx= x-2;
                        tempy= y+2;
                        for(int j=0;j<i-1;j++){
                            obj[tempx][tempy]=sameone;
                            tempx--;
                            tempy++;
                        }
                        forwardup=true;
                        break;

                    }else if(obj[tempx][tempy]=="_"){
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
            if(obj[x+1][y-1]!=otherone){
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
                    if(obj[tempx][tempy]==sameone){
                        tempx= x+2;
                        tempy= y-2;
                        for(int j=0;j<i-1;j++){
                            obj[tempx][tempy]=sameone;
                            tempx++;
                            tempy--;
                        }
                        forwarddown=true;
                        break;

                    }else if(obj[tempx][tempy]=="_"){
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
            if(obj[x-1][y-1]!=otherone){
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
                    if(obj[tempx][tempy]==sameone){
                        tempx= x-2;
                        tempy= y-2;
                        for(int j=0;j<i-1;j++){
                            obj[tempx][tempy]=sameone;
                            tempx--;
                            tempy--;
                        }
                        backup=true;
                        break;

                    }else if(obj[tempx][tempy]=="_"){
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
            if(obj[x+1][y+1]!=otherone){
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
                    if(obj[tempx][tempy]==sameone){
                        tempx= x+2;
                        tempy= y+2;
                        for(int j=0;j<i-1;j++){
                            obj[tempx][tempy]=sameone;
                            tempx++;
                            tempy++;
                        }
                        forwardup=true;
                        break;

                    }else if(obj[tempx][tempy]=="_"){
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
        boolean horizontalright =false;
        if(!(y==6| y==7) && obj[x][y+1]==otherone){
            for(int i=y+2;i<=7;i++){
                if(obj[x][i]==sameone){
                    for(int j=y+1;j<=i-1;j++){
                        obj[x][j]=sameone;
                    }
                    horizontalright= true;
                    break;
                }else if(obj[x][i]=="_"){
                    break;
                }
            }
        }
        boolean horizontalleft =false;
        if(!(y==0| y==1) && obj[x][y-1]==otherone){
            for(int i=y-2;i>=0;i--){
                if(obj[x][i]==sameone){
                    for(int j=y-1;j>=i+1;j--){
                        obj[x][j]=sameone;
                    }
                    horizontalleft= true;
                    break;
                }else if(obj[x][i]=="_"){
                    break;
                }
            }
        }
        boolean verticaldown =false;
        if(!(x==6| x==7) && obj[x+1][y]==otherone){
            for(int i=x+2;i<=7;i++){
                if(obj[i][y]==sameone){
                    for(int j=x+1;j<=i-1;j++){
                        obj[j][y]=sameone;
                    }
                    verticaldown= true;
                    break;
                }else if(obj[i][x]=="_"){
                    break;
                }
            }
        }
        boolean verticalup =false;
        if(!(x==0| x==1) && obj[x-1][y]==otherone){
            for(int i=x-2;i>=0;i--){
                if(obj[i][y]==sameone){
                    for(int j=x-1;j>=i+1;j--){
                        obj[j][y]=sameone;
                    }
                    verticalup= true;
                    break;
                }else if(obj[i][y]=="_"){
                    break;
                }
            }
        }

        if(horizontalleft==true |horizontalright==true|verticaldown==true|verticalup==true|forwarddown==true|forwardup==true|backup==true|backdown==true){
            return true;
        }else{
            return false;
        }
    }
}