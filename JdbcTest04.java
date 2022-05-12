package jdbcBasic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement prest = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ljh", "java");
			
			System.out.println("계좌번호 정보 추가하기\n\n");
			
			System.out.println("보이스피싱 : 여기 경찰서인데..연루돼서..계좌번호가 뭐죠?");
			String accntNo = scan.nextLine();
			
			System.out.println("보이스피싱 : 은행이 어딘데요?");
			String bankName = scan.nextLine();
			
			System.out.println("보이스피싱 : 성함 다시 말씀해주시죠");
			String name = scan.nextLine();
			
			//Statement 객체 이용해 데이터 추가
//			String sql = "INSERT INTO bankinfo values ('" + accntNo + "', '" +  bankName + "', '" + name + "', sysdate)";
//			
//			
//			stat = conn.createStatement();
//			
//			/*
//			 	Select문을 실행할 때는 executeQuery() 메소드를 사용,
//			 	insert, update, delete 등 select 아닌 쿼리 사용 시
//			 	executeUpdate() 메소드 사용 -> 반환값 : 작업 성공한 레코드 수
//			 */
//			int cnt = stat.executeUpdate(sql);
//			
//			System.out.println("반환값 : " + cnt);
			
			//혹은 PreparedStatement 객체를 이용해 처리
			String sql = "INSERT INTO bankinfo values (?, ?, ?, sysdate)"; //물음표를 따옴표로 묶으면 안 됨
			
			//PreparedStatement 객체 생성 -> 사용할 쿼리문 인수값으로 넘겨줌
			prest = conn.prepareStatement(sql);
			
			//쿼리문의 물음표 자리에 들어가 데이터 세팅
			//형식 ) prest.set자료형이름(물음표번호, 세팅할 데이터);
			prest.setString(1, accntNo);
			prest.setString(2, bankName);
			prest.setString(3, name);
			
			//데이터 세팅이 완료되면 쿼리문을 실행
			int cnt = prest.executeUpdate();
			
			System.out.println("반환값 : " + cnt);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(prest != null) try {prest.close();} catch (SQLException e) {}
			if(stat != null) try {stat.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}

	}

}
