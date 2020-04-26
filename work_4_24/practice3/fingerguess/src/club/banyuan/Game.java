package club.banyuan;

import club.banyuan.Player;
import club.banyuan.Computer;

class Game{

    /**
     * 创建电脑数组
     * @param num 人数
     * @return 
     */
    public Computer[] computers(int num){
        Computer[] computerArray = new Computer[num-1];
        for(int i=0; i<num-1; i++){
            Computer computer = new Computer();
            computerArray[i] = computer;
        }
        return computerArray;
    }

    public int[] guessing(Player player, int num){
        int[] guessArray = new int[num];
        int[] count = {0,0,0};

        guessArray[0] = player.guess();
        for(int i=1; i<num; i++){
            guessArray[i] = Game.computerArray[i-1].guess;
        }
        //判断数组中剪刀石头布分布
        for(int i=0; i<num; i++){
            switch(guessArray[i]){
                case 1:
                    count[0]++;
                    break;
                case 2:
                    count[1]++;
                    break;
                case 3:
                    coutn[2]++;
                    break;
            }
        }
        for(int i=0; i<count.length; i++){
            if(count[i] == 0){
                
            }
        }
    }

    
    public static void main(String[] args) {
        Computer computer1 = new Computer();
        Computer computer2 = new Computer();
        System.out.println(computer1.getName());
        System.out.println(computer2.getName());
    }
}