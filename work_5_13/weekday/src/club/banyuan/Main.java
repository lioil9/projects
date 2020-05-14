package club.banyuan;

public class Main {
    /**
     * 打印是否为假日
     * @param weekday 枚举类Weekday中对象
     */
    public static void print(Weekday weekday){
        if(weekday.isHoliday()){
            System.out.println(weekday+"是假日");
        }else {
            System.out.println(weekday+"不是假日");
        }
    }

    public static void main(String[] args) {
        for (Weekday weekday : Weekday.values()) {
            print(weekday);
        }
        Weekday sat = Weekday.SATURDAY;
        for (Weekday day : Weekday.values()) {
            int temp = day.compareTo(sat);
            if(temp < 0){
                System.out.println(day+"序号比"+sat.name()+"小"+Math.abs(temp)+",比较值为；"+temp);
            }else if(temp > 0){
                System.out.println(day+"序号比"+sat.name()+"大"+temp+",比较值为："+temp);
            }else {
                System.out.println(day+"序号和"+sat.name()+"相等,比较值为："+temp);
            }
        }
    }

}
