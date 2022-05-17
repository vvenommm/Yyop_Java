package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
	JDBC드라이버를 로딩하고 Connection객체를 생성하여 반환하는 메서드로 구성된 class
	(dbinfo.properties 파일의 내용으로 설정하는 방법) -> ResourceBundle객체 이용하기
 */

public class DBUtil3 {
	static ResourceBundle bundle; //ResourceBundle 객체 변수 선언
	
	// 정적 (static) 초기화 블럭
	// 클래스에서 db를 사용하려고할때 바로실행되는 static초기화 블럭을 만들어준다.
	//최초 1회 실행만 하면 됨
	static {
		bundle = ResourceBundle.getBundle("config.dbinfo"); //객체 생성

		// 드라이버 로딩하기
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패...~~~~~~");
			e.printStackTrace();
		}
	}

	// Connection객체를 생성하여 반환하는 메서드
	public static Connection getConnection() {

		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ljh", "java");
			return DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"), bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("DB연결 실패...!!!!");
			e.printStackTrace();
			return null;
		}
	}
}
