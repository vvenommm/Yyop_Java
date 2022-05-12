package jdbcBasic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//Q. LPROD_ID 값을 2개 입력 받아서 두 값 중 작은 값부터 큰 값 사이의 자료를 출력

public class JdbcTest03 {

	public static void main(String[] args) {
		
		Connection ction = null;
		Statement stat = null;
		ResultSet rSet = null;
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("숫자 입력1");
		int num1 = Integer.parseInt(scan.nextLine());
		
		System.out.println("숫자 입력2");
		int num2 = Integer.parseInt(scan.nextLine());
		int temp = 0;
		
		if(num2 < num1) {
			temp = num2;
			num2 = num1;
			num1 = temp;
		}
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ction = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ljh", "java");
			String sql = "SELECT LPROD_ID ID, LPROD_GU GU, LPROD_NM NM FROM LPROD WHERE LPROD_ID BETWEEN " + num1 + " and " + num2;
			
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
