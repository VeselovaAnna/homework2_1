package homework2_1.DAO;

import homework2_1.entity.Developers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevelopersDao {
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


    public DevelopersDao() {
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
                    connection.prepareStatement("INSERT INTO developers (NAME_FIRST, SURNAME, salary) VALUES (?, ?, ?)");

            upDateSt =
                    connection.prepareStatement("UPDATE developers SET NAME_FIRST = ?, SURNAME = ?, salary = ? WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // СОЗДАНИЕ ОБЪЕКТА в БД
    public void create(Developers developers) {
        try {
            createSt.setString(1, developers.getNameFirst());
            createSt.setString(2, developers.getSurname());
            createSt.setInt(3, developers.getSalary());
            createSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // изменить объект в таблице
    public void upDate(Developers developers) {
        try {
            upDateSt.setString(1, developers.getNameFirst());
            upDateSt.setString(2, developers.getSurname());
            upDateSt.setInt(3, developers.getSalary());
            upDateSt.setLong(4, developers.getId());
            upDateSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


// запрос в БД на вывод информации

    public Developers getDevelopers(long id) {
//        String selectSql =
//                " SELECT ID, NAME_FIRST, SURNAME, salary " +
//                        " FROM developers " +
//                        " WHERE id = " + id;


        String selectSql =
                " SELECT ID, NAME_FIRST, SURNAME, salary " +
                        " FROM developers " +
                        " WHERE id IN " +
                        "(SELECT developers_id FROM developers_project WHERE projects_id IN " +
                        "(SELECT projects_id FROM project WHERE id =" + id;
        ResultSet rs = null;
        System.out.println("jl");
        try {
            rs = st.executeQuery(selectSql);

            if (rs.first()) { //превращение в объект java
                Developers developers = new Developers();
                developers.setId(rs.getLong("id"));
                developers.setNameFirst(rs.getString("NAME_FIRST"));
                developers.setSurname(rs.getString("SURNAME"));
                developers.setSalary(rs.getInt("salary"));
                return developers;

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
        // String sql = "DELETE FROM developers";
        String sql = "DELETE FROM developers WHERE ID =" + id;
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Developers> getAllDevelopers() {
        List<Developers> result = new ArrayList<Developers>();
        String sql = "SELECT ID, NAME_FIRST, SURNAME, salary FROM developers";
        ResultSet rs = null;

        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Developers developers = new Developers();
                developers.setId(rs.getLong("id"));
                developers.setNameFirst(rs.getString("NAME_FIRST"));
                developers.setSurname(rs.getString("SURNAME"));
                developers.setSalary(rs.getInt("salary"));
                result.add(developers);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);

        }

        return result;
    }


    public List<Developers> getJavaDeveloper() {
        List<Developers> result = new ArrayList<Developers>();
        String sql =
                " SELECT ID, NAME_FIRST, SURNAME, salary " +
                        " FROM developers " +
                        " WHERE id IN " +
                        "(SELECT developer_id FROM developers_skills WHERE skills_id IN " +
                        "(SELECT id FROM skills WHERE skills_name = 'Java'))";

        ResultSet rs = null;

        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Developers developers = new Developers();
                developers.setId(rs.getLong("id"));
                developers.setNameFirst(rs.getString("NAME_FIRST"));
                developers.setSurname(rs.getString("SURNAME"));
                developers.setSalary(rs.getInt("salary"));
                result.add(developers);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);

        }

        return result;
    }


    public List<Developers> getAllMiddleDeveloper() {
        List<Developers> result = new ArrayList<Developers>();
        String selectSql =
                " SELECT ID, NAME_FIRST, SURNAME, salary " +
                        " FROM developers " +
                        " WHERE id IN " +
                        "(SELECT developer_id FROM developers_skills WHERE skills_id IN " +
                        "(SELECT id FROM skills WHERE degree = 'Java'))";

        ResultSet rs = null;
        System.out.println("");
        try {
            rs = st.executeQuery(selectSql);

            if (rs.next()) { //превращение в объект java
                Developers developers = new Developers();
                developers.setId(rs.getLong("id"));
                developers.setNameFirst(rs.getString("NAME_FIRST"));
                developers.setSurname(rs.getString("SURNAME"));
                developers.setSalary(rs.getInt("salary"));
                result.add(developers);

            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResultSet(rs);

        }
        return result;

    }


    public int getSumDevelopersSalaryInProject(long id) {
        String sql = "SELECT sum(salary) FROM developers WHERE id IN" +
                "(SELECT developer_id FROM developers_project WHERE project_id IN" +
                "(SELECT id FROM project WHERE id =" + id + "))";

        ResultSet rs = null;
        int sumSalary = 0;

        try {
            rs = st.executeQuery(sql);
            rs.first();
            sumSalary = rs.getInt("sum(salary)");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
        }

        return sumSalary;
    }



    //закрыть
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
