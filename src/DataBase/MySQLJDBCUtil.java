package DataBase;

//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
import java.sql.*;

public class MySQLJDBCUtil {
//    public static Connection getConnection() throws SQLException {
//        Connection conn = null;
//        
//        try (FileInputStream f = new FileInputStream("C:\\Users\\extra\\Google Drive\\COMIT\\Git\\BookingManagementInProgress\\BookingProgram\\src\\DataBase\\db.properties")) {
//
//            // load the properties file
//            Properties pros = new Properties();
//            pros.load(f);
//
//            // assign db parameters
//            String url = pros.getProperty("url");
//            String user = pros.getProperty("user");
//            String password = pros.getProperty("password");
//
//            // create a connection to the database
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(url, user, password);
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        return conn;
//    }
//}

//	
////	heroku version
	public static Connection getConnection() throws SQLException {
		Connection conn = null;

		try {

			String url = System.getenv("url");
			String user = System.getenv("user");
			String password = System.getenv("password");
			// create a connection to the database
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
