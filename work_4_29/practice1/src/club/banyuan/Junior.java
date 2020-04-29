package club.banyuan;

public class Junior extends Students{

    public Junior(String name) {
        super(name);
    }

    @Override
    public void sutdy() {
        System.out.println("初中生"+name+"正在学习");
    }
}
