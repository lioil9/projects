package club.banyuan.company;

import club.banyuan.employee.Employee;

/**
 * 创建一个公司类，里面包含员工对象数组，
 * 并且用len来判断员工个数和有效有效数组的长度
 */
public class Company {
    public Employee[] employeeArrays = new Employee[100];
    public int len = 0;

    /**
     * 添加员工对象，这里最多只能够保存已经定义好长度的员工
     * @param employee
     */
    public void addEmployee(Employee employee) {     
        employeeArrays[len] = employee;
        len++;
    }

    /**
     * 输入员工的名字将员工对象进行删除
     * @param name
     */
    public void deleteEmployee(String name) {
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
    public int sumWage() {
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += employeeArrays[i].wage();
        }
        return sum;
    }
}
