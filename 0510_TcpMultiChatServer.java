package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

	/*
	멀티 채팅 - 서버는 하나만 잇으면 됨
	서버에는 접속한 클라이언트에 대한 정보가 저장되어 있어야 함. 클라이언트 목록(리스트나 배열)을 만들고 각각 소켓이 있음
	어떤 클라이언트에서 서버한테 메시지를 보내면 서버가 받아서 클라이언트 목록에서 소켓을 찾아 그 메시지를 보내줌
	클라이언트는 그 대화명을 받아서 어딘가에 저장할 것. 우리는 Map을 이용할 것.
	key는 대화명, value값은 접속한 클라이언트의 소켓 값
	*/
	
public class TcpMultiChatServer {
	//접속한 클라이언트 정보를 저장할 Map 객체변수 선언
	//key값 : 대화명, value값 : 접속한 클라이언트의 Socket 객체
	
	private Map<String, Socket> clientMap; //동기화 처리가 되어야 양쪽에서 저장이 가능
	
	//생성자
	public TcpMultiChatServer() {
		//clientMap을 동기화 처리가 되도록 생성
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	//서버의 시작 메소드
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("The server is opened");
			
			while(true) { //여러 개의 클라이언트가 접솔할 수 있도록 반복문으로 처리한다.
				socket = server.accept(); //클라이언트의 접속을 기다린다.
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속했습니다.");
				System.out.println();
				
				//스레드 객체 생성 및 실행
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	////////////////////////// serverStart() 메소드 끝 ///////////////////////////
	
	
	//clientMap에 저장된 전체 사용자에게 메시지를 전송하는 메소드
	private void sendToAll(String msg) {
		//clientMap의 데이터 개수만큼 반복
		for(String name : clientMap.keySet()) {
			try {
				//key값(대화명)에 대응하는 Socket 객체의 출력용 스트림 객체를 사용
				DataOutputStream dout = new DataOutputStream(clientMap.get(name).getOutputStream());
				dout.writeUTF(msg);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	////////////////////////// sendAll() 메소드 끝 ///////////////////////////
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TcpMultiChatServer().serverStart();
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	//서버에서 클라이언트로 메시지를 전송하는 Thread를 Inner Class로 작성한다.
	// -> 이유 : Outer Class의 멤버변수(clientMap)를 자유롭게 사용하기 위해서
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;
		
		//생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				//송신용 스트림 객체 생성
				dout = new DataOutputStream(this.socket.getOutputStream());
				
				//수신용 스트림 객체 생성
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		////////////////////////// 생성자 끝 ///////////////////////////
		
		@Override
		public void run() {
			//대화명 중복 피하기 위해 할 일
			String name = "";
			try {
				//클라이언트가 연결이 성공하면 첫 번째로 '대화명'을 입력받아 보낸다.
				//서버에서는 이 '대화명'이 중복되는지 여부를 검사해서 응답으로 클라이언트에게 보내준다.
				
				//클라이언트가 보내온 '대화명'이 중복되지 않을 때까지 반복
				while(true) {
					name = din.readUTF(); //클라이언트가 보낸 '대화명' 받기
					
					if(clientMap.containsKey(name)) { // '대화명'이 중복될 때
						dout.writeUTF("대화명중복"); //대화명중복이라는 메시지를 보낸다.
					}else { //대화명이 중복되지 않으면
						dout.writeUTF("OK");
						break; //반복문 탈출
					}
				} //while문의 끝
				
				//현재 접속한 사람의 대화명을 이용하여 다른 전체 클라이언트에게 대화방 참여 메시지를 모낸다.
				sendToAll("[" + name + "]님이 대화방에 입장했습니다.");
				
				//대화명과 접속한 클라이언트의 Socket 객체를 clientMap에 추가한다.
				clientMap.put(name, socket);
				
				System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
				
				//현재 클라이언트가 보낸 메시지를 받아서 전체 클라이언트에게 보낸다.
				while(din != null) {
					sendToAll(din.readUTF());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//이 finally 영역이 실행된다는 것은 현재 클라이언트가 접속을 종료했다는 의미
				sendToAll("[" + name + "]님이 접속을 종료");
				
				//사용자 목록(cliendMap)에서 해당 대화명을 삭제
				clientMap.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속 종료");
				
				System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
				System.out.println();
			}
		}
		
	}

}
