package javaDatabaseConnectivity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {

	// private static final String Admin_URL = "jdbc:mysql://10.82.0.93:3306/";

	private static final String Local_URL = "jdbc:mysql://localhost/";
	private static final String Local_DB = "student_info";
	private static final String Local_USERNAME = "root";
	private static final String Local_PASSWORD = "root";

	private static final String DRIVER = "com.mysql.jdbc.Driver";

	public static Connection getLocalDBConnection() {

		Connection conn = null;

		// Register JDBC driver
		try {
			Class.forName(DRIVER).newInstance();

			// Open a connection
			conn = DriverManager.getConnection(Local_URL + Local_DB, Local_USERNAME, Local_PASSWORD);
			System.out.println("Connected Local Database Successfully...\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;

	}

	public static void close(Connection conn) {
		close(null, null, conn);
	}

	public static void close(Statement stmt, Connection conn) {
		close(null, stmt, conn);
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {

			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}

	}
        
    public static void getData() {
		Connection conn = getLocalDBConnection();

		Statement stmt;
		try {
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from student");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			}
			close(rs, stmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
          public static void insertData() {
		Connection conn = getLocalDBConnection();

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "insert into student values(5,'prateek','05/11/2017','male',8753674564)";
			int count = stmt.executeUpdate(query);
			System.out.println("Total inserted data: " + count);

			close(stmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
        public static void deleteData() {
		Connection conn = getLocalDBConnection();

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "delete from student where id=5";
			int count = stmt.executeUpdate(query);
			System.out.println("Total deleted data: " + count);

			close(stmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateData() {
		Connection conn = getLocalDBConnection();

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "update student set name='naman' where id=3";
			int count = stmt.executeUpdate(query);
			System.out.println("Total updated data: " + count);

			close(stmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
            try {
            	getData();
                insertData();
                getData();
                deleteData();
                getData();
                updateData();
                getData();
            } catch (Exception e) {
                e.printStackTrace();
            }
              
}
}
