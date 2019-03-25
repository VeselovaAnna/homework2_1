package homework2_1.DAO;

import homework2_1.entity.Skills;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDao {

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


    public SkillDao() {
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
                    connection.prepareStatement("INSERT INTO skills (skills_name, degree) VALUES (?, ?)");

            upDateSt =
                    connection.prepareStatement("UPDATE skills SET skills_name = ?, degree = ? WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Skills skills) {
        try {
            createSt.setString(1, skills.getNameSkills());
            createSt.setString(2, skills.getLevel());
            createSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void upDate(Skills skills) {
        try {
            upDateSt.setString(1, skills.getNameSkills());
            upDateSt.setString(2, skills.getLevel());
            upDateSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


// запрос в БД на вывод информации

    public Skills getSkills(long id) {
        String selectSql =
                " SELECT id, skills_name, degree" +
                        " FROM skills " +
                        " WHERE id = " + id;
        ResultSet rs = null;
        System.out.println("jl");
        try {
            rs = st.executeQuery(selectSql);

            if (rs.first()) { //превращение в объект java
                Skills skills = new Skills();
                skills.setId(rs.getLong("id"));
                skills.setNameSkills(rs.getString("skills_name"));
                skills.setLevel(rs.getString("degree"));
                return skills;

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

    public void delete(long id) {
        // String sql = "DELETE FROM skills";
        String sql = "DELETE FROM skills WHERE ID =" + id;
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Skills> getAllDevelopers() {
        List<Skills> result = new ArrayList<Skills>();
        String sql = "SELECT id, skills_name, degree  FROM skills";
        ResultSet rs = null;


        try {
            rs = st.executeQuery(sql);


            while (rs.next()) {

                Skills skills = new Skills();
                skills.setId(rs.getLong("id"));
                skills.setNameSkills(rs.getString("skills_name"));
                skills.setLevel(rs.getString("degree"));
                result.add(skills);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);

        }

        return result;
    }

    public void developerToSkill(long idDeveloper, long idskills) {
        String sql = "INSERT INTO developers_skills (developers_id, skills_id) VALUES" +
                "(" + idDeveloper + ", " + idskills + ")";

        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
