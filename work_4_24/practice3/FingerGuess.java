class FingerGuess{
    FingerGuess[] playerArray = new FingerGuess[5];
    private int id;
    private int num;

    /**
     * 设置玩家人数
     * @param num
     */
    public void setNum(int num){
        if(num<2 || num>5){
            System.out.println("输入玩家为2～5人");
            return;
        }
        this.num = num;
    }

    /**
     * 出拳 ，1. 剪刀 2. 石头 3. 布
     * @param finger
     */
    public void play(int finger){
        array[0] = finger;
        for(int i=1; i<num; i++){
            int computer = (int)(Math.random()*3+1);
            array[i] = computer;
        }
    }

    /**
     * 对出完拳后的数组进行比较，淘汰
     */
    public void compare(){

    }

    public static void main(String[] args) {
        
    }
}