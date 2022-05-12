package jdbcBasic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;

/*
	LPROD 테이블에 새로운 데이터 추가하기
	
	1) lprod_gu와 lprod_nm은 사용자로부터 직접 입력 받아서 처리하고,
	lprod_id는 현재의 lprod_id중에서 제일 큰 값+1인 번호로.
	
	2) 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리
 */

public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		Statement stat = null;
		PreparedStatement prest = null;
		ResultSet rSet = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ljh", "java");
			conn= DBUtil.getConnection();

			while (true) {

				System.out.println("상품 코드");
				String gu = scan.nextLine();

				System.out.println("상품 이름");
				String nm = scan.nextLine();

				////////////////////////////////////////////////////
				String sql = "SELECT MAX(NVL(LPROD_ID)) ID FROM LPROD";
				stat = conn.createStatement();
				rSet = stat.executeQuery(sql);
				
				
				//select한 결과가 1개의 레코드일 경우에는 반복문보다는 if문으로 데이터를 확인
				int id = 0;
				if(rSet.next()) {
					id = rSet.getInt("ID") + 1;
					//아니면 rSet.getInt(1) + 1; 컬럼 번호 사용
				}

				////////////////////////////////////////////////////

				String sql2 = "SELECT COUNT(*) count FROM LPROD WHERE LPROD_GU = '" + gu + "'";
				rSet = stat.executeQuery(sql2);

				int count = 0;
				if(rSet.next()) {
					count = rSet.getInt("count");
				}

				////////////////////////////////////////////////////

				if (count != 0) {
					System.out.println("이미 존재하는 상품코드입니다.");
				} else {
					String input = "INSERT INTO LPROD VALUES(?, ?, ?)";
					prest = conn.prepareStatement(input);
					prest.setInt(1, id);
					prest.setString(2, gu);
					prest.setString(3, nm);

					int rtn = prest.executeUpdate();

					if (rtn == 1) {
						System.out.println("추가 성공!");
						break;
					} else {
						System.out.println("추가 실패!");
						break;
					}
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if(prest != null) try {prest.close();} catch (SQLException e) {}
				if(stat != null) try {stat.close();} catch (SQLException e) {}
				if(conn != null) try {conn.close();} catch (SQLException e) {}
			}

	}

}
