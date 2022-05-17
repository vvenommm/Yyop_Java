package singleton;

/*
	singleton 패턴 : 객체를 1개만 만드는 방법(외부에서 new 명령을 사용하지 못 하게 한다.)
	
	- 사용 이유
	1) 메모리의 낭비를 방지
	2) 쉬운 데이터 공유
	
	- Singleton 클래스 만드는 방법(필수 구성 요소)
	1) 자기 자신 class의 참조값이 저장될 변수를 private static으로 선언
	2) 모든 생성자의 접근 제한자를 private으로 (생성자를 쓰지 않으면 컴파일러가 알아서 기본 생성자를 만들기 때문에 그렇게 되면 private이 아니어서 꼭 생성자 작성해줘야 함)
	3) 자기 자신 class의 인스턴스를 생성하고 반환하는 메소드를 public static으로 작성
	   이 메소드의 이름은 보통 getInstance로 작성
	
 */
public class MySingleton {
	//1번 - 클래스 이름과 같게 쓰고 변수명은 자유롭게 = 자기 자신의 참조값이 저장될 변수
	private static MySingleton single;
	
	//2번 - 최소 기본 생성자는 있어야 함. 안 그럼 private이 아닌 생성자를 컴파일러가 만듦
//	private MySingleton() {	} //아무것도 안 하면 그냥 생성자 만들어 놓기만 해도 됨
	private MySingleton() {	System.out.println("it is constructor");} //생성자 호출되는지 시험용
	
	//3번
	public static MySingleton getInstance() {
		if(single == null) single = new MySingleton();
		return single;
	}
	
	//기타 이 클래스가 처리할 내용들을 작성
	public void test() {
		System.out.println("싱글톤 클래스의 메소드 호출");
	}

}
