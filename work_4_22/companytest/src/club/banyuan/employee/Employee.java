package club.banyuan.employee;

/**
 * 定义一个普通员工类，包含其名字，工作时间，基本工资属性
 */
public class Employee {
    public String name;
    public int workTime;
    public int baseWage;

    /**
     * 构造员工对象属性并赋值
     * @param name
     * @param workTime
     * @param baseWage
     */
    public Employee(String name, int workTime, int baseWage) {
        this.name = name;
        this.workTime = workTime;
        this.baseWage = baseWage;
    }

    public Employee() {
    }

    /**
     * 计算并返回员工综合工资
     * @return
     */
    public int wage() {
        if (workTime > 196)
            return baseWage + 200 * (workTime - 196);
        else
            return baseWage;
    }

}
