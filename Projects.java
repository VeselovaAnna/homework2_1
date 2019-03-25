package homework2_1.entity;

public class Projects {
    private long id;
    private String nameProject;
    private int cost;
    private long idCompanies;
    private long idCustomers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public long getIdCompanies() {
        return idCompanies;
    }

    public void setIdCompanies(long idCompanies) {
        this.idCompanies = idCompanies;
    }

    public long getIdCustomers() {
        return idCustomers;
    }

    public void setIdCustomers(long idCustomers) {
        this.idCustomers = idCustomers;
    }

    @Override
    public String toString() {
        return "projects{" +
                "id=" + id +
                ", nameProject='" + nameProject + '\'' +
                ", cost=" + cost +
                ", idCompanies=" + idCompanies +
                ", idCustomers=" + idCustomers +
                '}';
    }
}
