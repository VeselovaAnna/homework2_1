package homework2_1.DAO;





import homework2_1.entity.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompaniesDao {
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


    public CompaniesDao(){
        initConnection();
        initPreparedStatement();
        initDbDriver();
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


    //запрос в БД на добавление

    private void initPreparedStatement() {
        try {
            createSt =
                    connection.prepareStatement("INSERT INTO companies (name) VALUES (?)");

            upDateSt =
                    connection.prepareStatement("UPDATE companies SET NAME_companies = ? WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCompanies(Company company) {
        try {
            createSt.setString(1, company.getNameCompanies());
            createSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Company getCompaniesById(long companiesId) {
        String sql = "SELECT id, name FROM companies WHERE id=" + companiesId;

        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);

            if (rs.first()) {
                Company company = new Company();
                company.setId(rs.getLong("id"));
                company.setNameCompanies(rs.getString("name"));
                return company;

            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
        }

        return null;
    }

    public List<Company> getAllCompanies() {
        List<Company> result = new ArrayList<Company>();

        String sql = "SELECT id, name FROM companies";

        ResultSet rs = null;

        try {
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Company companies = new Company();
                companies.setId(rs.getLong("id"));
                companies.setNameCompanies(rs.getString("name"));

                result.add(companies);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
        }

        return result;
    }


        private void closeResultSet (ResultSet rs){
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        public void updateCompanies(Company company){
            try {
                upDateSt.setLong(2, company.getId());
                upDateSt.setString(1, company.getNameCompanies());
                upDateSt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public void deleteCompaies(long id) {
            // String sql = "DELETE FROM companies";
            String sql = "DELETE FROM companies WHERE ID =" + id;
            try {
                st.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
