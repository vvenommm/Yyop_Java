package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {

	public static void main(String[] args) throws IOException {
		// ServerSocket 객체를 생성하고, 클라이언트가 접속하면
		//연결된 Socket을 메시지를 받는 스레드와 보내는 스레드에 주입

		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 준비 중입니다.");
		
		System.out.println("\n--------------------------\n");
		
		Socket socket = server.accept(); //클라이언트의 접속을 기다림
		
		//접속이 되면 연결된 소켓 객체를 주입한 스레드를 생성 후 실행
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();
	}

}
