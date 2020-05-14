package club.banyuan;

public enum Weekday {
    MONDAY("星期一"),TUESDAY("星期二"),WEDNESDAY("星期三"),
    THURSDAY("星期四"),FRIDAY("星期五"),SATURDAY("星期六"),
    SUNDAY("星期日");

    private final String name;

    /**
     * 构造枚举类中对象
     * @param name
     */
    Weekday(String name) {
        this.name = name;
    }

    /**
     * 判断是否为工作日
     * @return 工作日为true，假日为false
     */
    public boolean isWeekDay(){
        return !isHoliday();
    }

    /**
     * 判断是否为假日
     * @return 与判断工作日方法返回值相反
     */
    public boolean isHoliday(){
        return (this == SATURDAY || this == SUNDAY);
    }

    /**
     * 重写toString方法
     * @return 返回对象属性name
     */
    @Override
    public String toString() {
        return name;
    }
}
