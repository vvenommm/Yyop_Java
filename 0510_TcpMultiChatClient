package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/*
		멀티 채팅 - 서버는 하나만 잇으면 됨
		서버에는 접속한 클라이언트에 대한 정보가 저장되어 있어야 함. 클라이언트 목록(리스트나 배열)을 만들고 각각 소켓이 있음
		어떤 클라이언트에서 서버한테 메시지를 보내면 서버가 받아서 클라이언트 목록에서 소켓을 찾아 그 메시지를 보내줌
		클라이언트는 그 대화명을 받아서 어딘가에 저장할 것. 우리는 Map을 이용할 것.
		key는 대화명, value값은 접속한 클라이언트의 소켓 값
	 */
	
public class TcpMultiChatClient {
	
	public void clientStart() {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			
			//전송용 스레드와 수신용 스레드를 생성하고 실행
			ClientSender sender = new ClientSender(socket);
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//////////////////////////clientStart() 메소드 끝 ///////////////////////////
	
	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();
	}
	
	//메시지 전송용 스레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataOutputStream dout;
		private DataInputStream din;
		
		private String name; //대화명이 저장될 변수
		private Scanner scan;
		
		//생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			
			try {
				din = new DataInputStream(this.socket.getInputStream()); //수신용
				dout = new DataOutputStream(this.socket.getOutputStream()); //송신용
				
				if(dout != null) {
					do {
						//클라이언트용 프로그램은 처음 실행하면 서버에 접속하고 접속이 성공하면
						//첫번째 '대화명'을 입력받아 전송하고, '대화명'의 중복 여부를 응답으로 받아서 확인한다.
						
						System.out.println("대화명 입력");
						String name = scan.nextLine(); //대화명 입력
						
						dout.writeUTF(name); //대화명 전송
						
						//대화명의 중복여부를 응답으로 받는다.
						String feedBack = din.readUTF();
						
						if("대화명중복".equals(feedBack)) { //대화명이 중복일 때
							System.out.println(name + "은/는 이미 있는 대화명");
							System.out.println("다른 대화명을 입력");
						}else { //대화명이 중복되지 않을 때
							this.name = name;
							System.out.println("[" + name + "] 등장");
							break; //대화명 중복 확인 반복문 탈출
						}
					}while(true);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		//////////////////////////생성자 끝 ///////////////////////////
		
		
		@Override
		public void run() {
			try {
				while(din != null) {
					//키보드로 입력한 메시지를 서버로 전송
					dout.writeUTF("[" + name + "] " + scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	//////////////////////////전송용 스레드 끝 ///////////////////////////

	
	//메시지 수신용 스레드
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream din;
		
		//생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		//////////////////////////생성자 끝 ///////////////////////////
		
		@Override
		public void run() {
			try {
				while(din != null) {
					//서버로부터 받은 메시지를 화면에 출력한다.
					System.out.println(din.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
