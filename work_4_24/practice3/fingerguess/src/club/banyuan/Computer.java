package club.banyuan;


class Computer{
    private String name;
    private static int num = 0;

    /**
     * 定义电脑编号
     */
    public Computer(){
        num++;
        name = "电脑"+num;
    }

    /**
     * 电脑随机出拳
     * @return
     */
    public int guess(){
        int result = (int)(Math.random()*3+1);
        return result;
    }

    /**
     * 获取电脑编号
     * @return
     */
    public String getName(){
        return name;
    }
}