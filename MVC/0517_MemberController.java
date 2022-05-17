package mvcController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mvcService.IMemberService;
import mvcService.MemberServiceImpl;
import mvcVo.MemberVO;

/*
	MVC(Model, View, Controller 패턴) : MVC 패턴의 비즈니스 로직(모델)을 처리하는 클래스

- VO(Value Obejcet), DTO(Data Transfer Object) : 데이터를 저장하는 역할만 하는 클래스
- DAO(Data Acess Object) -> SQL문을 DB 서버엣 보내서 결과를 얻어오는 역할만 수행하는ㄴ 클래스
- Service : 일을 수행하는 중간 관리자와 같은 역할을 수행하는 클래스.
			서비스는 일이 있으면 그 일에 필요한 DAO를 호출해서 일을 처리한 후 처리 결과를 Controllr에 전달
- Controller : 비즈니스 로직이 시작되는 곳으로 사용자의 요청에 맞는 일을 만들어서 Service에게 일을 시키고 Service가 보내온 처리 결과를 화면에 반영시키기 위해 View에게 전달한다.
 */
public class MemberController {
	private Scanner scan = new Scanner(System.in);
	
	private IMemberService service; 	// Service객체가 저장될 변수 선언
	
	// 생성자
	public MemberController() {
		service = new MemberServiceImpl();
	}
	
	public static void main(String[] args) {
		new MemberController().memberStart();
	}
	
	public void memberStart() {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
				case 1 :	// 자료 추가
					insert(); break;
				case 2 :	// 자료 삭제
					delete(); break;
				case 3 :	// 자료 수정  ==> 전체 항목 수정
					update(); break;
				case 4 :	// 자료 출력
					displayAll(); break;
				case 5 :	// 자료 수정2 ==> 원하는 항목만 수정
					update2(); break;
				case 0 :	// 종료
					System.out.println("회원 관리 프로그램을 마칩니다...");
					return;
				default :
					System.out.println("작업 번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요..");
			}
		}
	}
	
	// 메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("------------------");
		System.out.println(" 1. 자 료 추 가");
		System.out.println(" 2. 자 료 삭 제");
		System.out.println(" 3. 자 료 수 정");
		System.out.println(" 4. 자 료 출 력");
		System.out.println(" 0. 종       료");
		System.out.println("------------------");
		System.out.print("번호 입력 : ");
		return scan.nextInt();
	}
	
	// 회원 정보를 수정하는 메서드 ==> 원하는 항목만 수정
	private void update2() {
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("수정할 회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
		
		if(count==0) {	// 없는 회원일 경우...
			System.out.println(memId + "은(는) 없는 회원ID 입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		int num;
		String updateField = null;
		String updateTitle = null;
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println("1. 비밀번호      2. 회원이름     3. 전화번호    4. 회원주소");
			System.out.println("------------------------------------------");
			System.out.print("수정 항목 선택 >> ");
			num = scan.nextInt();
			
			switch(num) {
				case 1 : updateField = "mem_pass"; updateTitle="비밀번호";
					break;
				case 2 : updateField = "mem_name"; updateTitle="회원이름";
					break;
				case 3 : updateField = "mem_tel"; updateTitle="전화번호";
					break;
				case 4 : updateField = "mem_addr"; updateTitle="회원주소";
					break;
				default : 
					System.out.println("수정 항목을 잘못 선택했습니다. 다시 선택하세요.");
			}
			
		}while(num<1 || num>4);
		
		System.out.println();
		scan.nextLine();  // 버퍼 비우기
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();
		
		//수정할 정보를 Map에 추가한다.
		//key값 정보 -> 회원ID(memId), 수정할컬럼명(field), 수정할데이터(data)
		
		//맵 객체 생성
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memId", memId);
		paramMap.put("field", updateField);
		paramMap.put("data", updateData);
		
		

		int cnt = service.updateMember2(paramMap);

		if (cnt > 0) {
			System.out.println(memId + " 회원의 " + updateField + "를 수정했습니다.");
		} else {
			System.out.println("수정 작업 실패!!!");
		}

	}

	// 전체 회원 정보를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println("ID\t비밀번호\t회원이름\t전화번호\t회원주소");
		System.out.println("-------------------------------------------------------");
		
		List<MemberVO> memList = service.getAllMember();
		if(memList==null || memList.size()==0) {
			System.out.println("\t출력할 회원 정보가 하~나도 없습니다.");
		}else {
			// List갯수만큼 반복 처리
			for(MemberVO memVo : memList) {
				System.out.println(memVo.getMem_id() + " \t " + memVo.getMem_pass() 
						+ " \t " + memVo.getMem_name() + " \t " + memVo.getMem_tel() 
						+ "\t" + memVo.getMem_addr() );
			}
		}
		
		System.out.println("-------------------------------------------------------");
		
	}
	
	
	// 회원 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("수정할 회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
		
		if(count==0) {	// 없는 회원일 경우...
			System.out.println(memId + "은(는) 없는 회원ID 입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		System.out.println();
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("새로운 비밀번호 >> ");
		String newMemPass = scan.next();
		
		System.out.print("새로운 회원이름 >> ");
		String newMemName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String newMemTel = scan.next();
		
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.print("새로운 회원주소 >> ");
		String newMemAddr = scan.nextLine();
		
		// 수정할 데이터를 저장할 VO객체 생성
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(newMemPass);
		memVo.setMem_name(newMemName);
		memVo.setMem_tel(newMemTel);
		memVo.setMem_addr(newMemAddr);
		
		int cnt = service.updateMember(memVo);
			
		if(cnt>0) {
			System.out.println(memId + " 회원 정보 수정 완료!!");
		}else {
			System.out.println(memId + " 회원 정보 수정 실패~~");
		}
			
			
		
		
	}
	
	
	// 회원 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("삭제할 회원ID >> ");
		String memId = scan.next();
		
		int cnt = service.deleteMember(memId);
			
		if(cnt>0) {
			System.out.println(memId + " 회원 정보 삭제 성공!!");
		}else {
			System.out.println(memId + " 회원은 없거나 삭제에 실패했습니다...");
		}
	}
	
	
	// 회원 정보를 insert하는 메서드
	private void insert() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		int count = 0;
		String memId = null;	// 회원ID가 저장될 변수
		
		do {
			System.out.print("회원ID >> ");
			memId = scan.next();
			count = service.getMemberCount(memId);
			if(count>0) {
				System.out.println(memId + "는(은) 이미 등록된 회원ID 입니다." );
				System.out.println("다른 회원ID를 입력하세요.");
			}
			
		}while(count>0);
		
		System.out.print("비밀번호 >> ");
		String memPass = scan.next();
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();		// 입력 버퍼 비우기
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		// 입력 받은 데이터를 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(memPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.insertMember(memVo);
			
		if(cnt>0) {
			System.out.println(memId + "회원 정보 추가 완료!!!");
		}else {
			System.out.println(memId + "회원 정보 추가 실패!!!");
		}
	}
}
