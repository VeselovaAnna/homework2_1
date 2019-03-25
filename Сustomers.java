package homework2_1.entity;

public class Сustomers {
    private long id;
    private String nameCustomers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCustomers() {
        return nameCustomers;
    }

    public void setNameCustomers(String nameCustomers) {
        this.nameCustomers = nameCustomers;
    }

    @Override
    public String toString() {
        return "customers{" +
                "id=" + id +
                ", nameCustomers='" + nameCustomers + '\'' +
                '}';
    }
}
