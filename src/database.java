import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public Connection connect;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String username = "root";
        String password = "sql061404";
        String url = "jdbc:mysql://localhost:3306/player";

        Class.forName("com.mysql.cj.jdbc.Driver");

        connect = DriverManager.getConnection(url, username, password);

        return connect;
    }
}
