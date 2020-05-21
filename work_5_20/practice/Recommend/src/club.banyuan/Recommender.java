package club.banyuan;

import java.util.List;

public interface Recommender {
    void addLikes(String name, String project);

    boolean likeBoth(String name, String projectOne, String projectTwo) throws UnknownPersonException;

    List<String> recommendByPerson(String name) throws UnknownPersonException;

    List<String> recommendByProject(String project) ;

}
