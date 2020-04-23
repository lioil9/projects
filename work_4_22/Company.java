/**
 * 创建一个公司类，里面包含员工对象数组，
 * 并且用len来判断员工个数和有效有效数组的长度
 */
class Company {
    Employee[] employeeArrays = new Employee[100];
    int len = 0;

    /**
     * 添加员工对象，这里最多只能够保存已经定义好长度的员工
     * @param employee
     */
    void addEmployee(Employee employee) {     
        employeeArrays[len] = employee;
        len++;
    }

    /**
     * 输入员工的名字将员工对象进行删除
     * @param name
     */
    void deleteEmployee(String name) {
        boolean flag = true;
        if(len == 0){
            System.out.println("公司当前无员工，不可删除");
            return;
        }
        for (int i = 0; i < len; i++) {
            if (employeeArrays[i].name.equals(name)) {
                flag = false;
                employeeArrays[i] = employeeArrays[len-1];
                employeeArrays[len-1] = null;
                len--;
            }
        }
        if(flag){
            System.out.println("无此员工，无法删除");
            return;
        }
        
    }

    /**
     * 计算并返回公司员工薪水的总和
     * @return
     */
    int sumWage() {
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += employeeArrays[i].wage();
        }
        return sum;
    }
}

/**
 * 定义一个普通员工类，包含其名字，工作时间，基本工资属性
 */
class Employee {
    String name;
    int workTime;
    int baseWage;

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
    int wage() {
        if (workTime > 196)
            return baseWage + 200 * (workTime - 196);
        else
            return baseWage;
    }

}

class Main {

    /**
     * 打印当前公司员工的名字
     * @param company
     */
    static void printName(Company company) {
        System.out.print("公司的员工有：");
        for (int i = 0; i < company.len; i++) {
            System.out.print(company.employeeArrays[i].name + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //创建名为销售公司的公司对象
        Company salesCompany = new Company();
        //创建员工对象
        Employee e1 = new Employee("张三", 205, 3000);
        Employee e2 = new Employee("李四", 220, 3000);
        Employee e3 = new Employee("王五", 180, 3000);
        Employee e4 = new Employee("mac", 196, 3000);
        //将员工添加到公司的员工数组中
        salesCompany.addEmployee(e1);
        salesCompany.addEmployee(e2);
        salesCompany.addEmployee(e3);
        salesCompany.addEmployee(e4);

        printName(salesCompany);

        System.out.println(e1.name+"的综合工资："+e1.wage());
        System.out.println(e2.name+"的综合工资："+e2.wage());
        System.out.println(e3.name+"的综合工资："+e3.wage());
        System.out.println(e4.name+"的综合工资："+e4.wage());
        System.out.println("公司需要支付的工资总数为："+salesCompany.sumWage());

        salesCompany.deleteEmployee("nac");
        printName(salesCompany);
        System.out.println("删除员工后公司需要支付的总工资："+salesCompany.sumWage());
        
    }
        
}