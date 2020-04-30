package club.banyuan;

public class Player {
    static final String[] FINGER_TOTAL = {"石头", "剪刀", "布"};
    static final String[] STATUS_TOTAL = {"赢了", "输了", "平局"};
    static final String ROLE_HUMAN = "玩家";
    static final String ROLE_COMPUTER = "电脑";
    private String name;
    private String role;
    private String finger;
    private String status;
    private int id;


    public Player(String role, int id) {
        this.role = role;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String role, int id) {
        this.name = role + id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFinger() {
        return finger;
    }

    public void setFinger(int choice) {
        this.finger = FINGER_TOTAL[choice-1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = STATUS_TOTAL[status-1];
    }
}
