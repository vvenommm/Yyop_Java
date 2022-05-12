package tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//이 클래스(스레드)는 Socket을 통해 메시지를 보내는 역할 담당
//전송용 스레드 작성하기
public class Sender extends Thread {
	private Socket socket;
	private DataOutputStream dataOut;
	private String name; //누가 보냈는지
	private Scanner scan;
	
	//생성자
	public Sender(Socket socket) {
		this.socket = socket;
		scan = new Scanner(System.in);
		System.out.print("이름 입력 : ");
		name = scan.nextLine();
		
		try {
			dataOut = new DataOutputStream(this.socket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {
//		while(dataOut != null) {
//			try {
//				dataOut.writeUTF(name + " : " + scan.nextLine());
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
		
File file = new File("d:/02_HighJava/Other/핑구.jpeg");
		
		if(!file.exists()) {
			System.out.println(file.getPath() + "파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다.");
			return;
			
		}
		
		try {
			//복사할 파일 스트림 객체 생성 (원본 파일을 읽어올 스트림 객체)
			FileInputStream fin = new FileInputStream(file);
			
			//복사될 파일 스트림 객체 생성 (대상 파일에 출력할 스트림 객체)
			FileOutputStream fout = new FileOutputStream("d:/02_HighJava/Other/연습용/핑구_복사본.jpeg");
			
			BufferedInputStream bis = new BufferedInputStream(fin);
            OutputStream bin = new FileOutputStream("d:/02_HighJava/Other/연습용/핑구_복사본.jpeg");
            BufferedOutputStream bout = new BufferedOutputStream(fout); 

			
			System.out.println("복사 시작");
			
			int data; //읽어온 데이터가 저장될 변수
			
			while((data = fin.read()) != -1) {
				fout.write(data);
			}
			
			//버퍼스트림을 이용하기
//			while((data = bin.read()) != -1) {
//				bout.write(data);
//			}
			
			bout.flush();
			
			
			System.out.println("복사 완료");
			
			//스트림 닫기
//			fin.close();
//			fout.close();
			bin.close();
			bout.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
