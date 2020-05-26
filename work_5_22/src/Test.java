
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Test extends JFrame implements ActionListener {

  JTextArea timeArea; //时间显示
  JButton startBtn, pauseBtn, resetBtn;
  private int mins = 0, sec = 0, mSec = 0;
  private long currentTime;                            //记录系统当前时间。
  private long difference;                            //秒表记录并要显示的时间。
  private long timeOfStart;                            //记录按下"开始"时的系统时间。
  private long timeLengthOfPause;                            //按下"暂停"时记录累计计时时间。
  Timer timer;
  private String timeStr, str1, str2, str3;
  private boolean first = true;                            //第一次按下后，first=false.
  private boolean start = false;                            //"开始"按下后，start=true.
  private boolean pause = false;                            //"暂停"按下后 pause=true.

  public Test(String str) {
    super(str);
    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());

    //北边：时间显示区域
    timeArea = new JTextArea("", 1, 12);
    timeArea.setFont(new Font("宋体", Font.ITALIC, 24));
    timeArea.setText("00:00:00");
    contentPane.add(timeArea, BorderLayout.NORTH);//中间：启动和暂停按钮
    JPanel pCenter = new JPanel(new GridLayout(1, 2));
    startBtn = new JButton("开始");
    pCenter.add(startBtn);
    startBtn.addActionListener(this);
    pauseBtn = new JButton("暂停");
    pCenter.add(pauseBtn);
    pauseBtn.addActionListener(this);
    contentPane.add(pCenter, BorderLayout.CENTER);
    //南边：复位按钮
    resetBtn = new JButton("复位");
    resetBtn.addActionListener(this);
    contentPane.add(resetBtn, BorderLayout.SOUTH);

    timer = new Timer(10, this);            //设定定时器周期为10毫秒

    pack();
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //关闭窗口退出程序。
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == timer) {
      currentTime = System.currentTimeMillis();        //当前时间

      difference = timeLengthOfPause + (currentTime - timeOfStart);    //计时时间。
      difference = difference / 10;            //以10ms为单位。
      mins = (int) (difference / (60 * 100));        //分钟数
      sec = (int) ((difference / 100) % 60);            //秒数
      mSec = (int) difference % 100;            //1(10)毫秒数。

      //显示新时间
      str1 = (mins < 10) ? ("0" + mins + ":") : (mins + ":");
      str2 = (sec < 10) ? ("0" + sec + ":") : (sec + ":");
      str3 = (mSec < 10) ? ("0" + mSec) : (mSec + "");

      timeStr = str1 + str2 + str3;
      timeArea.setText(timeStr);
    }
    //只有在开始时或者复位后，或按下暂停键才起作用。正在计时时按此键不起作用。
    //
    if (e.getSource() == startBtn && (first || pause)) {
      start = true;                    //设置已经启动标志。
      pause = false;                    //清除暂停标志。
      if (first)                    //开始第一次或复位后
      {                        //按"开始"才执行。
        timeLengthOfPause = 0;
        first = false;
      }
      timeOfStart = System.currentTimeMillis();        //按开始的时候的时刻记录.
      timer.start();                    //启动计时。
    }//只有在计时时按此键才有效。否则无效。
    //
    if (e.getSource() == pauseBtn && start) {
      pause = true;                    //设置暂停标志。
      start = false;                    //清除启动标志。
      timeLengthOfPause = mins * 60 * 1000 + sec * 1000 + mSec * 10;   //暂停后记录已累计计时的毫秒数。
      timer.stop();                    //停止计时。
    }

    //任何时候都可以复位。
    //
    if (e.getSource() == resetBtn) {
      mins = 0;
      sec = 0;
      mSec = 0;
      timer.stop();
      first = true;
      start = false;
      timeLengthOfPause = 0;                //开始时以前累计的时间清零。
      timeArea.setText("00:00:00");            //显示区清零。
    }
  }

  public static void main(String args[]) {
    Test s = new Test("秒表--stopwatch");
  }
}