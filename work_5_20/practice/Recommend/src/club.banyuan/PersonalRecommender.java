package club.banyuan;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PersonalRecommender implements Recommender {
    private HashMap<String, List<String>> likes = new HashMap<>();

    /**
     * @param name    根据人名添加项目，若无人名新建项目添加
     * @param project 添加项目，但每个人的项目中不能有重复
     */
    @Override
    public void addLikes(String name, String project) {
        if (!likes.containsKey(name)) {
            List<String> projectList = new LinkedList<>();
            projectList.add(project);
            likes.put(name, projectList);
        } else if (!likes.get(name).contains(project)) {
            likes.get(name).add(project);
        }
    }

    /**
     * 根据人民和添加的项目判断是否喜欢这两个项目
     *
     * @param name
     * @param projectOne
     * @param projectTwo
     * @return
     * @throws UnknownPersonException
     */
    @Override
    public boolean likeBoth(String name, String projectOne, String projectTwo) throws UnknownPersonException {
        if (!likes.containsKey(name)) {
            throw new UnknownPersonException("查无此人！");
        }
        return likes.get(name).contains(projectOne) && likes.get(name).contains(projectTwo);
    }

    /**
     * 根据人名返回喜欢的项目
     *
     * @param name
     * @return
     * @throws UnknownPersonException
     */
    @Override
    public List<String> recommendByPerson(String name) throws UnknownPersonException {
        if (!likes.containsKey(name)) {
            throw new UnknownPersonException("查无此人！");
        }
        return likes.get(name);
    }

    /**
     * 根据项目调出喜欢这个项目的人还会喜欢什么其他项目
     *
     * @param project
     * @return
     */
    @Override
    public List<String> recommendByProject(String project) throws UnknownProjectException{
        List<String> recommendProject = new LinkedList<>();
        for (List<String> value : likes.values()) {
            if (value.contains(project)) {
                recommendProject.addAll(value);
                recommendProject.remove(project);
            }
        }
        if(recommendProject.size() == 0){
            throw new UnknownProjectException("库中无此项目");
        }
        return recommendProject;
    }
}
