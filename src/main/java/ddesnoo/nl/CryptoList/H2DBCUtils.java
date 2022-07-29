package ddesnoo.nl.CryptoList;

//Importeer alle benodigd heden
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DBCUtils {

    //Voor gedefineerde database URL en Inlog gegevens
    private static String JDBC_URL = "jdbc:h2:mem:maindb";
    private static String JDBC_Username = "Danny";
    private static String JDBC_Password = "YourPassword";

    public static Connection getConnection () {
        Connection DB_connection = null;

        //Als er een connectie gemaakt kan worden dan maakt hij een connectie met de Database anders stuurt hij een null terug
        try{
            DB_connection = DriverManager.getConnection(JDBC_URL, JDBC_Username, JDBC_Password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return DB_connection;
    }

    public static void printSQLException (SQLException exc) {
        for (Throwable e: exc) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = exc.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
