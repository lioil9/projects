package club.banyuan;

import club.banyuan.company.Company;
import club.banyuan.employee.Employee;

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

        salesCompany.deleteEmployee("mac");
        printName(salesCompany);
        System.out.println("删除员工后公司需要支付的总工资："+salesCompany.sumWage());
        
    }
        
}