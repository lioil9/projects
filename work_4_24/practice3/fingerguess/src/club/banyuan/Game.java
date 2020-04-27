package club.banyuan;

import java.util.Scanner;
import club.banyuan.Player;

class Game {
    static Player[] playerList;
    static String[] choices = {"剪刀", "石头", "布"};
    static Player p;
    static Scanner sc;
    static int len;
    /**
     * 输入定义电脑数量
     * @param computer
     */
    public static void getComputers(int computer){
        len = computer+1;
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
     * 
     * @param p1
     * @param p2
     * @param count
     * @return
     */
    public static int compare(Player p1, Player p2, int count){
            if (p1.punch == 1) {
                if (p2.punch == 3) {
                    count++;
                } else if (p2.punch == 2) {
                    count--;
                }
            } else if (p1.punch == 2) {
                if (p2.punch == 1) {
                    count++;
                } else if (p2.punch == 3) {
                    count--;
                }
            } else if (p1.punch == 3) {
                if (p2.punch == 2) {
                    count++;
                } else if (p2.punch == 1) {
                    count--;
                }
            }
            return count;
    }

    public static void setStatus(String judge){
        Player p1;
        Player p2;
        for (int i = 0; i < len; i++) {
            p1 = playerList[i];
            System.out.println(p1.name + "出 " + choices[p1.punch- 1]);
            int count = 0;
            if (judge.length() == 2) {
                for (int j = 0; j < len; j++) {
                    p2 = playerList[j];
                    count = compare(p1, p2, count);
                }
                if (count >= 1) {
                    p1.status = "赢了";
                } else if (count < 0) {
                    p1.status = "输了";
                }
            } else {
                p1.status = "平手";
            }
        }
    }

    public static void removeLoser() {
        if (len > 1) {
            for (int i = 0; i < len; i++) {
                p = playerList[i];
                if (p.status.contains("输了")) {
                    System.out.println(p.name + " 出局");
                    playerList[i] = playerList[len-1];
                    len--;
                    i--;
                }
            }
        }
    }
    private static void display() {
        for (int i = 0; i < len; i++) {
            p = playerList[i];
            System.out.println(p.name + p.status);
        }
    }

    public static void main(String[] args) {
        int round = 1;
        while (true) {
            sc = new Scanner(System.in);
            try {
                System.out.print("请输入电脑玩家数:");
                int computers = sc.nextInt();
                getComputers(computers);
                break;
            } catch (Exception e) {
                System.out.println("输入错误，请输入数字");
            }
        }
        while (len > 1) {
            System.out.println("----------第" + round++ + "回合-----------");
            while (true) {
                sc = new Scanner(System.in);
                try {
                    System.out.println("玩家猜拳:1.剪刀,2.石头,3.布");
                    setStatus(getPlayersInput());;
                    break;
                } catch (Exception e) {
                    System.out.println("输入错误，请输入数字1~3");
                }
            }
            System.out.println("-------------回合結束--------------");
            display();
            System.out.println("----------------------------------");
            removeLoser();
        }
        System.out.println("-------------最后赢家-------------");
        System.out.println(playerList[0].name);
        sc.close();

    }

    

}