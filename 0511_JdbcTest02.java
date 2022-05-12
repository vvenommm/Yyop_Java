package jdbcBasic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// Q. 사용자로부터 LPROD_ID값을 입력 받아 입력한 값보다 LPROD_ID가 큰 자료들을 출력.

public class JdbcTest02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection ction = null;
		Statement stat = null;
		ResultSet rSet = null;
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("숫자 입력");
		int input = Integer.parseInt(scan.nextLine());
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ction = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ljh", "java");
			String sql = "SELECT LPROD_ID ID, LPROD_GU GU, LPROD_NM NM FROM LPROD WHERE LPROD_ID > " + input;
			
			stat = ction.createStatement();
			rSet = stat.executeQuery(sql);
			
			System.out.println("=========================");
			while(rSet.next()) {
//				if(rSet.getInt("id") > input) {
					System.out.println("LPROD_ID : " + rSet.getInt("id"));
					System.out.println("LPROD_GU : " + rSet.getString("gu"));
					System.out.println("LPROD_NM : " + rSet.getString("nm"));
					System.out.println("==================================");
//				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rSet != null) try {rSet.close();} catch (SQLException e) {}
			if(stat != null) try {stat.close();} catch (SQLException e) {}
			if(ction != null) try {ction.close();} catch (SQLException e) {}
		}
		
		

	}

}
