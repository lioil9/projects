package club.banyuan;

public class Senior extends Students{

    public Senior(String name) {
        super(name);
    }
    @Override
    public void sutdy() {
        System.out.println("高中生"+name+"正在学习");
    }


}
