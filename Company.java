package homework2_1.entity;

public class Company {

    private long id;
    private String nameCompanies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCompanies() {
        return nameCompanies;
    }

    public void setNameCompanies(String nameCompanies) {
        this.nameCompanies = nameCompanies;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", nameCompanies='" + nameCompanies + '\'' +
                '}';
    }
}
