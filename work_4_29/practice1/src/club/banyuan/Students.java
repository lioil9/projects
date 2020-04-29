package club.banyuan;

public class Students {
    protected String name;

    public Students(String name) {
        this.name = name;
    }

    public void sutdy(){
        System.out.println(name+"正在学习");
    }
    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }
}
