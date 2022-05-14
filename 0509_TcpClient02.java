package tcp;

import java.net.Socket;

public class TcpClient02 {

	public static void main(String[] args) {
		// Socket 객체를 생성하여 서버에 연결 요청을 보내고 연결이 완료되면
		//이 소켓을 메시지를 받는 스레드와 보내는 스레드에 주입
		
		try {
			Socket socket = new Socket("localhost", 7777);
//			Socket socket = new Socket("192.168.46.19", 7777); //달래 아이피 주소
			System.out.println("서버에 연결되었습니다.");
			
			 Sender sender = new Sender(socket);
			 Receiver receiver = new Receiver(socket);
			 
			 sender.start();
			 receiver.start();
			 
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
