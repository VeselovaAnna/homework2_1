package homework2_1.DAO;

/*public class CustomersDao {

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

   // Сustomers customers = new Сustomers();

    public CustomersDao() {
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
                    connection.prepareStatement("INSERT INTO customers (NAME_customers) VALUES (?)");

            upDateSt =
                    connection.prepareStatement("UPDATE customers SET NAME_customers = ? WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // СОЗДАНИЕ ОБЪЕКТА в БД
    public void create(Customers customers) {
        try {
            createSt.setString(1, customers.getNameCustomers());
            createSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // изменить объект в таблице
    public void upDate(Customers customers) {
        try {
            upDateSt.setString(1, customers.getNameCustomers());
            upDateSt.setLong(2, customers.getId());
            upDateSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


// запрос в БД на вывод информации

    public Сustomers getCustomers(long id) {
        String selectSql =
                " SELECT ID, NAME_customers " +
                        " FROM customers " +
                        " WHERE id = " + id;
        ResultSet rs = null;
        System.out.println("jl");
        try {
            rs = st.executeQuery(selectSql);

            if (rs.first()) { //превращение в объект java
                Сustomers customers = new Сustomers();
                customers.setId(rs.getLong("id"));
                customers.setNameCustomers(rs.getString("NAME_customers"));
                return customers;

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
        // String sql = "DELETE FROM customers";
        String sql = "DELETE FROM customers WHERE ID =" + id;
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Сustomers> getAllCustomers() {
        List<Сustomers> result = new ArrayList<Customers>();
        String sql = "SELECT ID, NAME_customers FROM customers";
        ResultSet rs = null;


        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {

                Сustomers customers = new Сustomers();
                customers.setId(rs.getLong("id"));
                customers.setNameCustomers(rs.getString("NAME_customers"));
                result.add(customers);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);

        }

        return result;
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
*/




