package derby_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection1 {

	static Connection connection;

	static Statement statement;

	static ResultSet executeQuery;

	static String url = "jdbc:derby://localhost:1527/D:\\Netmeds\\DB\\db_rwos_common;user=rwos;password=rwos";

//	static int count = 0;

	public static void setup(String Query) throws ClassNotFoundException, SQLException {

		Class.forName("org.apache.derby.jdbc.ClientDriver");

		connection = DriverManager.getConnection(url);

		statement = connection.createStatement();

		executeQuery = statement.executeQuery(Query);

		while (executeQuery.next()) {

//			if (count != 10) {
//
//				count++;

			System.out.println(executeQuery.getString("GSTIN"));
//			}
		}

		connection.close();

	}

	public static void main(String[] args)
		throws ClassNotFoundException, SQLException {

		setup("SELECT GSTIN FROM RWOS.RWPARTY");
	}

}
