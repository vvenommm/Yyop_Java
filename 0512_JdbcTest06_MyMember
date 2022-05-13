package jdbcBasic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;

/*
	회원 관리를 하는 프로그램을 작성 (mymember 테이블 이용)
	아래 메뉴의 기능을 모두 구현(CRUD 기능 구현)
	
	예시)
	==============================
	1. 자료 추가 -> insert
	2. 자료 삭제 -> delete
	3. 자료 수정 -> update
	4. 전체 자료 출력 -> select
	0. 작업 끝
	==============================
	
	처리조건)
	1) '자료 추가'에서 '회원ID'는 중복 불허 (중복 시 다시 입력)
	2) '자료 삭제'는 '회원ID'를 입력 받아 처리
	3) '자료 수정'에서 '회원ID'는 변경 불가
	
 */

public class JdbcTest06 {
	
	Scanner scan = new Scanner(System.in);
	static Connection conn;
	static Statement stat;
	static PreparedStatement prest; //컴파일을 미리 함(statement와의 차이점) -> 그래서 sql문이랑 prest = conn.preparedStatement(sql);문은 반복문 밖에 둬서 한 번만 컴파일 하면 그대로 컴파일 된 채로 남아있기에 반복문에 넣을 필요 없다.
	static ResultSet rset;

	public static void main(String[] args) {
		
		conn = null;
		stat = null;
		prest = null;
		rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conn= DBUtil.getConnection();
		
		new JdbcTest06().start();

	}
	
	
	///////////////////////////////////////////////////////////////////
	

	private void start() {
		// TODO 프로그램 시작

		while (true) {

			int choice = display();

			switch (choice) {

			case 1: // 1. 자료 추가
				dataInsert();
				break;

			case 2: //2. 자료 삭제
				dataDelete();
				break;
				
			case 3: //3. 자료 수정
				dataUpdate();
				dataUpdate2();
				break;
				
			case 4: //4. 전체 출력
				selectAll();
				break;
				
			case 0: //0. 작업 끝
				disconnect();
				System.out.println("Goodbye--☆★");
				System.exit(0);

			default:
				System.out.println("없는 항목입니다.\n다시 입력해주세요.");
				break;
			}
		}
	}

	private void disconnect() {
		// TODO Auto-generated method stub
		if(prest != null) try {prest.close();} catch (SQLException e) {}
		if(stat != null) try {stat.close();} catch (SQLException e) {}
		if(conn != null) try {conn.close();} catch (SQLException e) {}
		if(rset != null) try {rset.close();} catch (SQLException e) {}
	}


	private void dataUpdate2() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.print("변경할 아이디  > ");
		String id = scan.next();

		// id가 존재하는지 아닌지 확인 (count가 0이면 없는사람)
		int count = getMemberCount(id);

		if (count == 0) {// 등록되어있지 않는 사람
			System.out.println("등록되어있지 않는 아이디입니다.");
			System.out.println("수정작업을 종료합니다.");
			return;
		}

		// 원하는 항목을 선택하기 위한 숫자변수와 수정할 부분의 컬럼명이 들어갈 string변수
		// update mymember set 컬럼명 = 변경할 값 where mem_id = 변경할id;

		int num;
		String updateField = null;
		String updateTitle = null;
		do {
			System.out.println("수정하려는 항목을 선택해주세요.\n[1.비밀번호 2.이름 3.전화번호 4.주소]");
			num = Integer.parseInt(scan.nextLine());

			switch (num) {
			case 1:
				updateField = "mem_pass";
				updateTitle = "비밀번호";
				break;
			case 2:
				updateField = "mem_name";
				updateTitle = "이름";
				break;
			case 3:
				updateField = "mem_tel";
				updateTitle = "전화번호";
				break;
			case 4:
				updateField = "mem_addr";
				updateTitle = "주소";
				break;
			default:
				System.out.println("없는 항목");
			}
		} while (num < 1 || num > 4);

		System.out.println();
		scan.nextLine(); // 버퍼 비우기
		System.out.println("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();

		try {
			conn = DBUtil.getConnection();

			String sql = "UPDATE MYMEMBER SET " + updateField + " = ? where mem_id = ? ";
			prest = conn.prepareStatement(sql);
			prest.setNString(1, updateData);
			prest.setString(2, id);

			count = prest.executeUpdate();

			if (count > 0) {
				System.out.println(id + " 회원의 " + updateField + "를 수정했습니다.");
			} else {
				System.out.println("수정 작업 실패!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}


	private int getMemberCount(String id) {
		String sql = "SELECT COUNT(*) COUNT FROM MYMEMBER WHERE MEM_ID = ?";
		int count = 0;
		try {
			prest = conn.prepareStatement(sql);
			prest.setNString(1, id);

			rset = prest.executeQuery();

			if (rset.next()) {
				count = rset.getInt("COUNT");
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return count;
	}


	///////////////////////////////////////////////////////////////////
	
	
	private void selectAll() {
		// TODO 4. 전체 출력
		
		String sql = "";
		
		System.out.println("My Member의 모든 정보를 출력합니다.\n");
		
		sql = "SELECT MEM_ID ID, MEM_PASS PW, MEM_NAME NM, MEM_TEL TEL, MEM_ADDR ADDR FROM MYMEMBER";
		try {
			stat = conn.createStatement();
			rset = stat.executeQuery(sql);
			System.out.println("===========================================================");
			System.out.println(" 아이디\t비밀번호\t이름\t전화번호\t주소");
			while(rset.next()) {
				System.out.println(" " + rset.getString("ID") + "\t" + rset.getString(2) + "\t" + rset.getString("NM") + "\t" + rset.getString(4) + "\t" + rset.getString("ADDR"));
				System.out.println("-------------------------------------------------------");
			}
			System.out.println("===========================================================");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	///////////////////////////////////////////////////////////////////


	private void dataUpdate() {
		// TODO 3. 자료 수정
		
		String sql = "SELECT COUNT(*) COUNT FROM MYMEMBER WHERE MEM_ID = ?";
		String id;
		
		while (true) {
			System.out.println("수정하려는 정보 ID 입력 : ");
			id = scan.nextLine();

			try {
				prest = conn.prepareStatement(sql);
				prest.setNString(1, id);

				rset = prest.executeQuery();

				int dup = 0;
				if (rset.next()) {
					dup = rset.getInt("COUNT");
				}

				if (dup == 0) {
					System.out.println("존재하는 ID가 아닙니다.");
				} else {
					break;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		System.out.println("수정하려는 항목을 선택해주세요.\n[1.비밀번호 2.이름 3.전화번호 4.주소]");
		int choice = Integer.parseInt(scan.nextLine());
		
		switch(choice) {
		
		case 1: //1.비밀번호
			System.out.println("변경할 비밀번호를 입력해주세요.");
			String pw = scan.nextLine();
			
			sql = "UPDATE MYMEMBER SET MEM_PASS = ? WHERE MEM_ID = ?";
			try {
				prest = conn.prepareStatement(sql);
				prest.setString(1, pw);
				prest.setString(2, id);
				
				int rtn = prest.executeUpdate();
				if(rtn > 0) {
					System.out.println("비밀번호 변경 성공!");
				}else {
					System.out.println("비밀번호 변경 실패ㅠ");
				}
				System.out.println("홈으로 이동합니다.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2: //2.이름
			System.out.println("변경할 이름을 입력해주세요.");
			String name = scan.nextLine();
			
			sql = "UPDATE MYMEMBER SET MEM_NAME = ? WHERE MEM_ID = ?";
			try {
				prest = conn.prepareStatement(sql);
				prest.setString(1, name);
				prest.setString(2, id);
				
				int rtn = prest.executeUpdate();
				if(rtn > 0) {
					System.out.println("이름 변경 성공!");
				}else {
					System.out.println("이름 변경 실패ㅠ");
				}
				System.out.println("홈으로 이동합니다.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3: //3.전화번호
			System.out.println("변경할 전화번호를 입력해주세요.");
			String tel = scan.nextLine();
			
			sql = "UPDATE MYMEMBER SET MEM_TEL = ? WHERE MEM_ID = ?";
			try {
				prest = conn.prepareStatement(sql);
				prest.setString(1, tel);
				prest.setString(2, id);
				
				int rtn = prest.executeUpdate();
				if(rtn > 0) {
					System.out.println("전화번호 변경 성공!");
				}else {
					System.out.println("비밀번호 변경 실패ㅠ");
				}
				System.out.println("홈으로 이동합니다.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4: //4.주소
			System.out.println("변경할 주소를 입력해주세요.");
			String addr = scan.nextLine();
			
			sql = "UPDATE MYMEMBER SET MEM_ADDR = ? WHERE MEM_ID = ?";
			try {
				prest = conn.prepareStatement(sql);
				prest.setString(1, addr);
				prest.setString(2, id);
				
				int rtn = prest.executeUpdate();
				if(rtn > 0) {
					System.out.println("주소 변경 성공!");
				}else {
					System.out.println("주소 변경 실패ㅠ");
				}
				System.out.println("홈으로 이동합니다.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("없는 항목입니다.");
			System.out.println("홈으로 이동합니다.");
		}
		
		
	}

	
	///////////////////////////////////////////////////////////////////


	private void dataDelete() {
		// TODO 자료 삭제 -> delete

		String sql = "SELECT COUNT(*) COUNT FROM MYMEMBER WHERE MEM_ID = ?";
		String id = "";
		while (true) {
			System.out.println("삭제하려는 정보 ID 입력 : ");
			id = scan.nextLine();

			try {
				prest = conn.prepareStatement(sql);
				prest.setNString(1, id);

				rset = prest.executeQuery();

				int dup = 0;
				if (rset.next()) {
					dup = rset.getInt("COUNT");
				}

				if (dup == 0) {
					System.out.println("존재하는 ID가 아닙니다.");
				} else {
					break;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
		
		try {
			prest = conn.prepareStatement(sql);
			prest.setString(1, id);
			
			int rtn = prest.executeUpdate();
			
			if(rtn > 0) {
				System.out.println("삭제 완료");
			}else {
				System.out.println("삭제 실패");
			}
			System.out.println("홈으로 이동합니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	///////////////////////////////////////////////////////////////////


	private void dataInsert() {
		// TODO 자료 추가하기 -> insert

		// 중복 검사를 위해 data 검색해오기
		try {
			String id = "";
			while (true) {

				System.out.println("ID 입력 : ");
				id = scan.nextLine();

				String sql = "SELECT COUNT(*) COUNT FROM MYMEMBER WHERE MEM_ID = ?";
				prest = conn.prepareStatement(sql);
				prest.setNString(1, id);
				
				rset = prest.executeQuery();
				

				int dup = 0;
				while (rset.next()) {
					dup += rset.getInt("COUNT");
				}

				if (dup != 0) {
					System.out.println("이미 존재하는 ID입니다.");
				}else {
					break;
				}
			}
			
			System.out.println("비밀번호 입력 : ");
			String pw = scan.nextLine();
			
			System.out.println("이    름 입력 : ");
			String nm = scan.nextLine();
			
			System.out.println("전화번호 입력(숫자만) : ");
			String tel = scan.nextLine();
			
			System.out.println("주    소 입력 : ");
			String addr = scan.nextLine();
			
			
			//추가하기
			String sql = "INSERT INTO MYMEMBER VALUES(?, ?, ?, ?, ?)";
			prest = conn.prepareStatement(sql);
			prest.setString(1, id);
			prest.setString(2, pw);
			prest.setString(3, nm);
			prest.setString(4, tel);
			prest.setString(5, addr);
			
			int rtn = prest.executeUpdate();
			
			if(rtn > 0) {
				System.out.println("추가 성공!");
			} else {
				System.out.println("추가 실패!");
			}
			System.out.println("홈으로 이동합니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	///////////////////////////////////////////////////////////////////

	
	private int display() {
		// TODO 메뉴 출력
		
		System.out.println("┌─────────────────┐"); 
		System.out.println("│  1. 자료  추가  │"); 
		System.out.println("│  2. 자료  삭제  │"); 
		System.out.println("│  3. 자료  수정  │"); 
		System.out.println("│  4. 전체  출력  │"); 
		System.out.println("│  0. 종      료  │"); 
		System.out.println("└─────────────────┘\n"); 
		System.out.println("번호 입력 : ");
		
		int choice = Integer.parseInt(scan.nextLine());
		return choice;
	}

}
