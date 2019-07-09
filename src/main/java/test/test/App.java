package test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String Num1 = null;
		String Num2 = null;
		
		int id = 2;
		
		try {
			//Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/data/sum2num.accdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT [num1], [num2] FROM [sumtable] WHERE ID=" + id);
			
			while(rs.next()) {
				Num1 = rs.getString(1);
				Num2 = rs.getString(2);
				System.out.println("First Number:" + Num1 + "\n" + "Second Number" + Num2);
			}
			
			int i = Integer.parseInt(Num1);
			int j = Integer.parseInt(Num2);
			int res = i + j;
			
			String result =Integer.toString(res);
			
			String q = "UPDATE sumtable SET (result) = (?) WHERE ID=" + id;
			PreparedStatement st = conn.prepareStatement(q);
			st.setString(1, result);
			st.executeUpdate();
			System.out.print("Result Field is successfully Updated");
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			
		}
    }
}
