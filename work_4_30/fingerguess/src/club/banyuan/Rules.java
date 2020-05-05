package club.banyuan;

public class Rules {
  public static final String ROLE_HUMAN = "人";
  private static int playersLeft;

  public static int getPlayersLeft() {
    return playersLeft;
  }

  public static void setPlayersLeft(int playersLeft) {
    Rules.playersLeft = playersLeft;
  }

  static void printWinner(Player[] players) {
    // 输出获胜方
    for (Player player : players) {
      if (!player.isOut()) {
        System.out.println("获胜方是" + player.getRole() + player.getId());
      }
    }
  }

  static boolean isFinalWinner(Player[] players) {
    int winnerCount = 0;
    for (Player player : players) {
      if (!player.isOut()) {
        winnerCount++;
      }
    }
    return winnerCount == 1;
  }

  static boolean isDraw(String rlt) {
    return rlt.length() == 3 || rlt.length() == 1;
  }

  static void outPlayer(Player[] players, String rlt) {
    int winnerFinger = getWinnerFinger(rlt);
    for (Player player : players) {
      if (!player.isOut() && player.getFinger() != winnerFinger) {
        player.setOut(true);
        System.out.println(
            player.toString() + "输掉了");
        playersLeft --;
      } else if (!player.isOut()) {
        System.out.println(
            player.toString() + "胜利");
      }
    }
  }

  static boolean showFingers(Player[] players) {
    boolean flag = false;
    System.out.println("当前游戏有"+getPlayersLeft()+"人");
    for (Player player : players) {
      if (player.isOut()) {
        continue;
      }
      if(ROLE_HUMAN.equals(player.getRole())){
        flag = player.showFinger();
      }else{
        player.showFinger();
      }
    }
    return flag;
  }

  static String competeResult(Player[] players) {
    StringBuilder rlt = new StringBuilder();
    for (Player player : players) {
      if (player.isOut()) {
        continue;
      }
      String oneFinger = player.getFinger() + "";
      if (!rlt.toString().contains(oneFinger)) {
        rlt.append(oneFinger);
      }
    }
    return rlt.toString();
  }

  //  12 21 = 1
  //         13 31 = 3
  //         23 32 = 2
  private static int getWinnerFinger(String rlt) {
    if ("12".equals(rlt) || "21".equals(rlt)) {
      return 1;
    } else if ("13".equals(rlt) || "31".equals(rlt)) {
      return 3;
    } else {
      return 2;
    }
  }
}
