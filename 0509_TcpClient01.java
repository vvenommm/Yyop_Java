package tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException{
		//현재 자신의 컴퓨터를 나타내는 법
		//1) 원래의 IP 주소 : 192.168.46.46 (잘 모를 수도, 수시로 변할 수도..)
		//2) 지정된 IP 주소 : 127.0.0.1 (어디서든 이 아이피는 내 컴퓨터. 프로그램이 실행된 컴퓨터)
		//3) 원래 컴퓨터 이름 : HostName으로 뜨는 이름
		//4) 지정된 컴퓨터 이름 : localhost(자기 자신(컴퓨터)을 나타내는 이름)
		
		
		// 서버의 ip 주소와 port 번호를 지정하여 scoket 객체를 생성
		// Socket 객체는 생성이 완료되면 자동으로 해당 서버로 연결 요청을 보낸다.
		Socket socket = new Socket("localhost", 7777);
		
		//이 Socket 객체를 생성하는 명령 이후의 내용은 서버와 연결된 후에 실행되는 부분

		
		//접속한 상대방에 대한 정보 출력하기
		System.out.println("접속한 상대방(클라이언트) 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress()); //ip주소를 관리하는 클래스 = inetAddress(상대의 ip를 갖고있는 객체)
		System.out.println("Port번호 : " + socket.getPort());

		System.out.println("\n--------------------------\n");

		// 현재 컴에 대한 정보 출력하기
		System.out.println("연결된 현재 컴(서버) 정보");
		System.out.println("IP 주소 : " + socket.getLocalAddress()); // 현 컴퓨터의 (소켓과 연결된) ip주소
		System.out.println("Port번호 : " + socket.getLocalPort()); // 그냥 getPort는 상대의 ip, LocalPort가 내 ip

		System.out.println("\n--------------------------\n");
		
		//서버에서 보내온 메시지를 받아서 화면에 출력
		// -> Socket 객체의 InputStream 객체를 이용해서 데이터를 수신 받는다.
		//	 (Socket의 getInputStream() 메소드를 이용하여 InputStream 객체를 구할 수 있다.
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);
		
		//메시지 받아서 출력하기
		System.out.println("서버에서 보내온 메시지 : " + din.readUTF());
		
		System.out.println("\n--------------------------\n");
		
		
		//소켓과 스트림 닫기
		System.out.println("연결 종료");
		
		din.close();
		socket.close();
		
	}

}
