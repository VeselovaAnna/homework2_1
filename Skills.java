package homework2_1.entity;

public class Skills {

    private long id;
    private String nameSkills;
    private  String degree;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSkills() {
        return nameSkills;
    }

    public void setNameSkills(String nameSkills) {
        this.nameSkills = nameSkills;
    }

    public String getLevel() {
        return degree;
    }

    public void setLevel(String level) {
        this.degree = level;
    }

    @Override
    public String toString() {
        return "skills{" +
                "id=" + id +
                ", nameSkills='" + nameSkills + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}
