package club.banyuan;

public class Main {
    public static void main(String[] args) {
        Recommender recommender = new PersonalRecommender();
        recommender.addLikes("张三", "速度与激情");
        recommender.addLikes("张三", "see you again");
        recommender.addLikes("张三", "see you again");
        recommender.addLikes("张三", "楚门的世界");
        recommender.addLikes("李四", "see you again");
        recommender.addLikes("李四", "肖申克的救赎");
        recommender.addLikes("王二", "肖申克的救赎");

        try {
            System.out.println(recommender.recommendByPerson("张三"));
            System.out.println(recommender.likeBoth("张三", "速度与激情", "see you again"));
            System.out.println(recommender.likeBoth("张三", "速度与激情", "see you"));
            System.out.println(recommender.recommendByProject("see you again"));
            System.out.println(recommender.recommendByProject("see you"));
            System.out.println(recommender.recommendByPerson("leo"));
        } catch (UnknownPersonException e) {
            System.out.println(e.getMessage());
        }


    }
}
