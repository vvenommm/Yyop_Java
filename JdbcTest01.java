package jdbcBasic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
	JDBC(Java Database Connectivity) 라이브러리를 이용한 DB자료 처리하기
	
	데이터베이스 처리 순서
	1. JDBC 드라이버 로딩 => JDBC 라이브러리를 사용할 수 있도록 메모리로 읽어들이는 작업
		Class.forName("oracle.jdbc.driver.OracleDriver");
		-> JDBC 버전 4.0 이상에서는 getConnection() 메소드에서 자동으로 로드, 생략 가능
	
	2. DB에 접속하기 -> DriverManager.getConnection() 메소드 이용
					 -> 접속이 완료되면 Connection 객체 반환.
	
	3. 질의 -> SQL 문장을 DB 서버로 보내서 결과를 얻음
	  (Statement 객체나 PreparedStatement 객체를 이용)
	
	4. 결과 처리 -> 질의 결과를 받아서 원하는 작업을 수행
		1) SQL문이 select일 때 결과가 ResultSet 객체에 저장되어 반환
		2) SQL문이 select가 아닐 때에는 정수값이 반환 (정수 = 실행 성공한 레코드 수)
	
	5. 사용한 자원들 반납하기 -> close() 메소드 사용
 */

public class JdbcTest01 {

	public static void main(String[] args) {
		// DB 작업에 필요한 객체 변수 선언
		Connection cnntion = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB 연결하기 -> 연결되면 Connection 객체 생성
			cnntion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ljh", "java"); //네트워크를 통해 접속하는 것이기 때문에 url을 입력해줘야 함.
			
			//3. 질의
			//3-1. SQL문 작성
			String sql = "SELECT LPROD_ID, LPROD_GU GU, LPROD_NM NM FROM LPROD";
			
			//3-2. Statement 객체 생성 : 질의를 서버로 전송하고 결과를 얻어오는 객체 (Connection 객체를 이용해서 생성)
			stmt = cnntion.createStatement();
			
			//3-3. SQL문을 서버로 보내서 결과 얻기 (실행할 SQL문이 SELECT문이기 때문에 처리결과가 ResultSet 객체에 저장되어 반환)
			rs = stmt.executeQuery(sql);
			
			//4. 결과 처리하기 -> 한 레코드씩 화면에 출력하기
			// -> 질의 결과가 저장된 ResultSet 객체에서 데이터를 차례로 꺼내기 위해 반복문과 next()메소드 사용
			//rs.next() : ResultSet 객체의 데이터를 가리키는 포인터를 다음 레코드로 이동, 그 곳에서 데이터 여부에 따라 또 true / false 반환
			System.out.println("===== 결 과 =====");
			while(rs.next()) { 
				//next() 메소드는 데이터가 여부에 따라 true / false 반환
				//포인터가 가리키는 곳의 자료를 가져오는 법
				//1) rs.get자료형이름("컬럼명" 혹은 "alias");
				//2) rs.get자료형이름(컬럼번호); -> 번호는 1부터 시작
			
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID")); //컬럼명. alias를 줬으면 그걸 적어줘야 함.
				System.out.println("LPROD_GU : " + rs.getString(2)); //컬럼번호
				System.out.println("LPROD_NM : " + rs.getString("NM")); //alias
				System.out.println("===== 결 과 =====");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//5. 자원반남
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(stmt != null) try {stmt.close();} catch (SQLException e) {}
			if(cnntion != null) try {cnntion.close();} catch (SQLException e) {}
		}
		
		System.out.println("end!");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
