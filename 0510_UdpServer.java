package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
	UDP방식 : 비연결 지향, 없는 신뢰성, 비정렬 데이터 전송(순서가 없음), TCP보다 빠른 속도
	
	DatagramSocket 객체와 DatagramPacket 객체를 이용해서 통신
	- DatagramSocket : 데이터의 송수신과 관련된 작업을 수행 (우체부 역할)
	- DatagramPacket : 주고 받는 데이터와 관련된 작업을 수행 (우편물) -> 수신 생성자와 송신용 생성자를 따로 제공
	- TCP 경우 스트림을 이용해서 송수신하지만 UDP 경우 데이터그램을 이용해서 송수신
 */

public class UdpServer {

	public static void main(String[] args) {
		try {
			//통신할 포트번호를 지정하여 소켓을 생성
			DatagramSocket socket = new DatagramSocket(8888);
			
			System.out.println("server is on");
			
			//수신용 패킷 객체 변수와 송신용 패킷 객체 변수 선언
			DatagramPacket inpacket, outpacket;
			
			while(true) {
				//데이터가 저장될 byte형 배열 생성
				byte[] bMsg = new byte[512];
				
				//수신용 패킷 객체 생성 -> 데이터가 저장될 byte형 배열, 배열의 길이를 이용해서 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				//데이터를 수신 -> receive() 메소드 이용 : receive() 메소드는 메시지 데이터가 올 때까지 기다림
				//수신된 데이터의 패킷정보는 지정한 패킷변수(inpacket)에 저장
				socket.receive(inpacket);
				
				//수신받은 패킷 객체를 이용해서 상대의 주소, 포트 번호 등을 알 수 잇음
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				System.out.println("상대 IP정보 : " + address);
				System.out.println("상대 port번호 : " + port);
				System.out.println("\n-------------------------------------------------------\n");
				
				//상대방이 보낸 메시지 출력하기
				//inpacket.getLength() -> 실제 읽어온 데이터의 길이 반환 (상대가 보낸 데이터 길이)
				//inpacket.getData() -> 실제로 읽어온 데이터를 byte 배열로 반환 (실제 읽어온 데이터는 수신용 패킷에 지정한 byte 배열에도 저장)
				//데이터가 문자열이면 이 데이터를 String으로 변환해야 함
				//String msg = new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8"); 혹은 아래처럼 써도 됨
				String msg = new String(bMsg, 0, inpacket.getLength(), "utf-8");
				
				//이 예제에서는 클라이언트가 보낸 메시지가 '/end'면 작업을 끝내도록 한다.
				if("/end".equals(msg)) {
					break; //반복문 탈출
				}
				
				System.out.println("상대방이 보낸 메시지 : " + msg);
				System.out.println();
				
				System.out.println("\n-------------------------------------------------------\n");
				
				//상대방에게 메시지 보내기 (수신받은 메시지 그대로 송신)
				//송신할 메시지를 byte형 배열로 변환
				
				byte[] sendMsg = msg.getBytes("utf-8");
				
				//송신용 패킷 객체 생성
				//-> 전송할 데이터가 들어있는 byte형 배열, 전송할 자료의 길이(배열의 길이), 상대 주소 정보, 상대 포트 번호를 지정하여 생성
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				//송신하기 -> send() 메소드 이용 : 전송할 패킷 넣기
				socket.send(outpacket);
				System.out.println("송신 완");
				System.out.println("\n-------------------------------------------------------\n");
				
				
						
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
