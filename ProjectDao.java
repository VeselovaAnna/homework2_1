package homework2_1.DAO;

import homework2_1.entity.Projects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

    private String DB_DRIVR = "com.mysql.cj.jdbc.Driver";
    private String SERVER_PATH = "localhost:3306";
    private String DB_NAME = "homework_1";
    private String DB_LOGIN = "root";
    private String DB_PASSWORD = "root";

    String connectionUrl = "jdbc:mysql://" + SERVER_PATH + "/" + DB_NAME +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private Connection connection;
    private Statement st;
    private PreparedStatement createSt;
    private PreparedStatement upDateSt;


    public ProjectDao() {
        initDbDriver();
        initConnection();
        initPreparedStatement();
    }


    private void initDbDriver() {
        try {
            Class.forName(DB_DRIVR);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void initConnection() {
        try {
            connection = DriverManager.getConnection(connectionUrl, DB_LOGIN, DB_PASSWORD);
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void initPreparedStatement() {
        try {
            createSt =
                    connection.prepareStatement("INSERT INTO projects (NAME_project, customers_id, companies_id, cost) VALUES (?, ?,?, ?)");

            upDateSt =
                    connection.prepareStatement("UPDATE projects SET NAME_project = ?, customers_id = ?, companies_id = ?,cost = ?  WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // СОЗДАНИЕ ОБЪЕКТА в БД
    public void create(Projects projects) {
        try {
            createSt.setString(1, projects.getNameProject());
            createSt.setLong(2, projects.getIdCompanies());
            createSt.setLong(3, projects.getIdCustomers());
            createSt.setInt(4, projects.getCost());
            createSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // изменить объект в таблице
    public void upDate(Projects projects) {
        try {
            upDateSt.setString(1, projects.getNameProject());
            upDateSt.setLong(2, projects.getIdCompanies());
            upDateSt.setLong(3, projects.getIdCustomers());
            upDateSt.setInt(4, projects.getCost());
            upDateSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Projects getProjects(long id) {
        String selectSql =
                " SELECT ID, NAME_project, companies_id, customers_id, cost " +
                        " FROM projects " +
                        " WHERE id = " + id;
        ResultSet rs = null;
        System.out.println("jl");
        try {
            rs = st.executeQuery(selectSql);

            if (rs.first()) { //превращение в объект java
                Projects projects = new Projects();
                projects.setId(rs.getLong("id"));
                projects.setNameProject(rs.getString("NAME_project"));
                projects.setIdCompanies(rs.getLong("companies_id"));
                projects.setIdCustomers(rs.getLong("customers_id"));
                projects.setCost(rs.getInt("cost"));
                return projects;

            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResultSet(rs);
        }

    }

    // удаление объекта
    public void delete(long id) {
        // String sql = "DELETE FROM projects;
        String sql = "DELETE FROM projects WHERE ID =" + id;
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Projects> getAllProjects() {
        List<Projects> result = new ArrayList<Projects>();
        String sql = "SELECT ID, NAME_project, companies_id, customers_id, cost FROM projects";
        ResultSet rs = null;


        try {
            rs = st.executeQuery(sql);


            while (rs.next()) {


                Projects projects = new Projects();
                projects.setId(rs.getLong("id"));
                projects.setNameProject(rs.getString("NAME_project"));
                projects.setIdCompanies(rs.getLong("companies_id"));
                projects.setIdCustomers(rs.getLong("customers_id"));
                projects.setCost(rs.getInt("cost"));
                result.add(projects);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);

        }

        return result;
    }

    public void developerToProject(long idDeveloper, long idProject) {
        String sql = "INSERT INTO developers_projects (developers_id, projects_id) VALUES" +
                "(" + idDeveloper + ", " + idProject + ")";

        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
