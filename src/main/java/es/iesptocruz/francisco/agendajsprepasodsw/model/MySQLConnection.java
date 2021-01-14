package es.iesptocruz.francisco.agendajsprepasodsw.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static final String DATABASE = "agenda2";

    public static final String TCONTACT = "contacts";
    public static final String TADDRESS = "addresses";
    public static final String TPHONE = "phones";

    public static final String TCONTACT_ID = "id_contact";
    public static final String TCONTACT_NAME = "contact_name";
    public static final String TCONTACT_SURNAME = "surname";
    public static final String TCONTACT_BIRTH = "birth";
    public static final String TCONTAC_EMAIL = "email";

    public static final String TADDRESS_ID = "id_address";
    public static final String TADDRESS_NAME = "address_name";
    public static final String TADDRESS_DESCRIPTION = "address_description";
    public static final String TADDRESS_STREET = "street";
    public static final String TADDRESS_NUMBER = "address_number";
    public static final String TADDRESS_CITY = "city";
    public static final String TADDRESS_STATE = "state";
    public static final String TADDRESS_COUNTRY = "country";
    public static final String TADDRESS_CONTACT = "contact_id";

    public static final String TPHONES_ID = "id_phone";
    public static final String TPHONES_TYPE = "type";
    public static final String TPHONES_DESCRIPTION = "phone_description";
    public static final String TPHONES_NUMBER = "phone_number";
    public static final String TPHONES_CONTACT = "contact_id";

    String jdbcUrl;
    String user;
    String passwd;
    private Connection connection;
    private static MySQLConnection mySQLConnection;

    private MySQLConnection(String db, String user, String passwd) throws SQLException {
        jdbcUrl = "jdbc:mysql://localhost/" + db + "?serverTimezone=UTC";
        this.user = user;
        this.passwd = passwd;
        loadMySQLDriver();
        mysql();
    }

    public static MySQLConnection getMySQLConnection() throws Exception {
        if (mySQLConnection == null) {
            throw new Exception();
        } else {
            return mySQLConnection;
        }
    }

    public static MySQLConnection getInstance(String db, String user, String passwd) throws Exception {
        if (mySQLConnection != null) {
            if (mySQLConnection.connection != null && !mySQLConnection.connection.isClosed()) {
                mySQLConnection.connection.close();
            }
        }
        mySQLConnection = new MySQLConnection(db, user, passwd);
        return mySQLConnection;
    }

    public void loadMySQLDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection mysql() throws SQLException {
        return mysql(jdbcUrl, user, passwd);
    }

    private Connection mysql(String jdbcUrl, String user, String passwd) throws SQLException {
        connection = null;
        if (jdbcUrl != null && !jdbcUrl.isEmpty()) {
            this.jdbcUrl = jdbcUrl;
        }
        if (user != null && !user.isEmpty()) {
            this.user = user;
        }
        if (passwd != null && !passwd.isEmpty()) {
            this.passwd = passwd;
        }

        connection = DriverManager.getConnection(jdbcUrl, user, passwd);
        return connection;
    }



    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static void setMySQLConnection(MySQLConnection mySQLConnection) {
        MySQLConnection.mySQLConnection = mySQLConnection;
    }
}
