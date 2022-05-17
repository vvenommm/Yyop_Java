package singleton;

public class SingletonTest {

	public static void main(String[] args) {
//		MySingleton test1 = new MySingleton(); //외무에서 new 명령 사용 불가라 컴파일 오류 발생
		
		MySingleton test2 = MySingleton.getInstance(); //객체 생성된 single을 반환하여 test2에 담기
		MySingleton test3 = MySingleton.getInstance(); //test2와 test3는 같은 객체
		
		System.out.println("test2 : " + test2);
		System.out.println("test3 : " + test3);
		System.out.println();
		System.out.println(test2 == test3);
		System.out.println(test2.equals(test3));
		
		test2.test();
	}

}
