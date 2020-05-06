package club.banyuan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  // TODO
  // 1. 如果用户随意输入内容，不满足条件的，提示用户重新输入
  // 2. 用户输入多少人参与猜拳（2~5人）提示，输入0退出程序
  // 3. 用户猜拳界面，输入0，返回到上一级，重新开始选择参与人数
  // 4. 用户进入到猜拳界面，提示当前的人数的信息，例如"当前游戏3人猜拳"，每次猜拳下一轮开始时，显示本轮剩余玩家数量，
  public static void main(String[] args) {
    while (true) {
      int playerCount = 0;
      while (true) {
        // 用try catch只能输入数字，再设置只能输入2-5数字，为0时退出
        try {
          System.out.println("用户输入多少人参与猜拳（2~5人）\n输入0结束。");
          Scanner scanner = new Scanner(System.in);
          playerCount = scanner.nextInt();
          if (playerCount == 0) {
            System.exit(0);
          } else if (playerCount < 2 || playerCount > 5) {
            System.out.println("输入数据有误,请重新输入..");
          } else {
            break;
          }
        } catch (InputMismatchException e) {
          System.out.println("输入数据有误,请重新输入..");
        }
      }
      //设置剩余人数
      Rules.setPlayersLeft(playerCount);
      boolean flag1 = false;
      Player[] players = Player.initPlayers(playerCount);

      while (true) {
        boolean flag = Rules.showFingers(players);
        //输入为0时跳出循环
        if(flag) {
          flag1 = true;
          break;
        }
        String rlt = Rules.competeResult(players);

        if (Rules.isDraw(rlt)) {
          System.out.println("平局");
          continue;
        } else {
          Rules.outPlayer(players, rlt);
        }
        if (Rules.isFinalWinner(players)) {
          break;
        }
      }
      if(flag1){
        continue;
      }
      Rules.printWinner(players);
    }
  }

}
