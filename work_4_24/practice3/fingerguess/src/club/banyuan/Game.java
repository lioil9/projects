import java.util.Scanner;

class Game {
    static Player[] playerList;
    static String[] choices = {"剪刀", "石头", "布"};
    static Player p;
    static Scanner sc;
    /**
     * 输入定义电脑数量
     * @param computer
     */
    public static void getComputers(int computer){
        playerList = new Player[computer+1];
        playerList[0] = new Player("玩家"+1);
        for(int i=1; i<=computer; i++){
            playerList[i] = new Player("电脑"+i);
        }
    }
    /**
     * 得到电脑和玩家出拳
     * @return
     */
    public static String getPlayersInput() {
        String judge = "";
        for (int i = 0; i < playerList.length; i++) {
            p = playerList[i];
            if (p.name.contains("电脑")) {
                p.punch = (int)(Math.random() * 3) + 1;
            } else {
                System.out.print(p.name + "输入:");
                p.punch = sc.nextInt();
            }
            //playerList.set(i, p);
            if (!judge.contains(String.valueOf(p.punch))) {
                judge += p.punch;
            }
        }
        return judge;
    }
    /**
     * 比较 1.剪刀 2.石头 3.布
     * @param judge
     */
    public static void compare(String judge){
        Player p1;
        Player p2;
        for (int i = 0; i < playerList.length; i++) {
            p1 = playerList[i];
            System.out.println(p1.name + "出 " + choices[p1.punch - 1]);
            if (judge.length() == 2) {
                for (int j = 0; j < playerList.length; j++) {
                    p2 = playerList[j];
                    if (p1.punch == 1 && p2.punch ==3) {
                        p1.status = "赢了";
                    }else if(p1.punch == 1 && p2.punch ==2){
                        p1.status = "输了";
                    }else if(p1.punch == 2 && p2.punch ==1){
                        p1.status = "赢了";
                    }else if(p1.punch == 2 && p2.punch ==3){
                        p1.status = "输了";
                    }else if(p1.punch == 3 && p2.punch ==1){
                        p1.status = "输了";
                    }else if(p1.punch == 3 && p2.punch ==2){
                        p1.status = "赢了";
                    } else {
                        p1.status = "平手";
                    }
                }
            }
        }
    }

    public void removeLoser() {
        
        if (playerList.length > 1) {
            for (int i = 0; i < playerList.length; i++) {
                p = playerList[i];
                if (p.status.contains("输了")) {
                    System.out.println(p.name + " 出局");
                    i--;
                }
            }
        }
    }
    


    public static void main(String[] args) {
        int round = 1;

    }

    

}