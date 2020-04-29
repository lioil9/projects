package club.banyuan;

public class Primary extends Students{
    public Primary(String name) {
        super(name);
    }

    @Override
    public void sutdy() {
        System.out.println("小学生"+name+"正在学习");
    }

}
