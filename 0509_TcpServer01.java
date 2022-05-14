package tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {

	public static void main(String[] args) throws IOException {
		// TCP 소켓 통신을 하기 위해 ServerSocket 객체를 생성
		ServerSocket server = new ServerSocket(7777);
		System.out.println("접속을 기다림");
	
		System.out.println("\n--------------------------\n");
		
		//accept() 메소드 : 클라이언트에서 연결 요정이 올 때까지 기다림
		//					연결 요청이 오면 새로운 Socket 객체를 생성해서 클라이언트의 Socket과 연결
		Socket socket = server.accept();
		
		//accept() 메소드 이후의 소스는 연결이 완료되어야만 실행되는 부분
		System.out.println("클라이언트와 연결되었습니다.");
		
		System.out.println("\n--------------------------\n");

		//접속한 상대방에 대한 정보 출력하기
		System.out.println("접속한 상대방(클라이언트) 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress()); //ip주소를 관리하는 클래스 = inetAddress(상대의 ip를 갖고있는 객체)
		System.out.println("Port번호 : " + socket.getPort());
		
		System.out.println("\n--------------------------\n");

		//현재 컴에 대한 정보 출력하기
		System.out.println("연결된 현재 컴(서버) 정보");
		System.out.println("IP 주소 : " + socket.getLocalAddress()); //현 컴퓨터의 (소켓과 연결된) ip주소
		System.out.println("Port번호 : " + socket.getLocalPort()); //그냥 getPort는 상대의 ip, LocalPort가 내 ip
		
		System.out.println("\n--------------------------\n");
		
		//클라이언트에게 메시지 보내기 : Socket의 outputstream 객체를 이용해서 데이터를 전송
		//								(Socket의 getOutputStream() 메소드를 이용해서 OutputStream 객체를 구함)
		OutputStream out = socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);
		
		//메시지 보내기
		dout.writeUTF("Hello. 환영쓰");
		System.out.println("메시지 전송 완");
		
		//소켓과 스트림 닫기
		dout.close();
		socket.close();
		server.close();
		
		
		

	}

}
