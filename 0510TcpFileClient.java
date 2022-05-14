package tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//클라이언트는 서버에 접속되면 'd:/02_HighJava/Other' 폴더에 있는 '핑구.jpeg'을 전송한다.
//파일을 읽어서 Socket으로 출력한다.

public class TcpFileClient {
	private Socket socket;
	private BufferedInputStream bin;
	private BufferedOutputStream bout;
	private DataOutputStream dout;
	
	public void clientStart() {
		//전송할 파일의 정보를 갖는 file객체 생성
		//File file = new File("d:/02_HighJava/Other/핑구.jpeg"); 다이얼로그 추가하려고 주석처리
		File file = new File("OPEN");
		String fileName = file.getName(); //파일명 구하기
		if(!file.exists()) {//전송할 파일이 없으면
			System.out.println(fileName + " 파일이 없습니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("서버에 접속되었습니다.");
			System.out.println("파일 전송 시작");
			
			//서버에 접속하면 첫 번째로 전송할 파일의 파일명을 전송
			dout = new DataOutputStream(socket.getOutputStream()); //버퍼기능이 반감 됨. 그러면 느린 쪽에 속도가 맞춰짐. 그래서 버퍼용으로 한 번 감싸줌(46번 줄)
			dout.writeUTF(fileName);
			
			//파일 내용을 읽어서 소켓으로 전송하기
			
			//파일 읽기용 스트림 객체 생성
			bin = new BufferedInputStream(new FileInputStream(file));
			
			//서버로 전송할 출력용 스트림 객체 생성
			bout = new BufferedOutputStream(dout);
			
			//파일 내용을 읽어서 서버로 보내기
			byte[] temp = new byte[1024];
			int len = 0;
			
			while((len = bin.read(temp))>0) {
				bout.write(temp, 0, len); //출력
			}
			
			bout.flush(); //버퍼 사용했으므로
			
			System.out.println("파일 전송 완료!");
			
			
		} catch (Exception e) {
			System.out.println("파일 전송 실패!!!!!!!!!");
			e.printStackTrace(); //어디서 오류가 났는지 보기위해
		} finally {
			if(bin!=null) try {bin.close();} catch (IOException e2) {}
			if(bout!=null) try {bout.close();} catch (IOException e2) {}
			if(socket!=null) try {socket.close();} catch (IOException e2) {}
		}
		
	}
		
		////////////////////////// 다이얼로그 추가 /////////////////////////////////
	   //option은 "SAVE" 또는 "OPEN"값을 갖는다.
	   public File fileSelectDialog (String option) {
	      //swing의 파일 열기, 저장 창 연습
	      
	            JFileChooser chooser = new JFileChooser();
	            
	            // 선택할 파일의 확장자 설정
	            FileNameExtensionFilter txt = new FileNameExtensionFilter("text파일(*.txt)", "txt");
	            FileNameExtensionFilter img = new FileNameExtensionFilter("이미지파일",new String [] {"png", "jpg", "gif"});
	            FileNameExtensionFilter doc = new FileNameExtensionFilter("MS word 파일","docx", "doc");
	            
	            
	            // 설정된 확장자를 Chooser에 추가한다.
	            chooser.addChoosableFileFilter(txt);
	            chooser.addChoosableFileFilter(img);
	            chooser.addChoosableFileFilter(doc);
	            
	            
	            chooser.setFileFilter(txt); //확장자 목록 중에 기본적으로 선택될 확장자 지정
	            chooser.setAcceptAllFileFilterUsed(false);  //모든 파일 보이게할지여부를 지정 (미 지정시 true)
	            
	            //Dialog창이 나타날 때 보여주는 기본 경로 설정하기(미 설정시 document폴더가 보여짐)

	            chooser.setCurrentDirectory(new File("d:/d_other"));
	            
	            // Dialog창 만들기
	            int result;
	            if("OPEN".equals(option.toUpperCase())){
	               result = chooser.showOpenDialog(new Panel());      //열기용 dialog창
	            }else if("SAVE".equals(option.toUpperCase())){ result = chooser.showSaveDialog(new Panel()); }     //저장용 dialog창
	            else {System.out.println("option은 SAVE 또는 OPEN만 가능합니다."); return null;}
	            
	            File selectedFile = null;
	            
	            //dialog창에서 파일을 선택한 후 '열기'버튼 또는 '저장'버튼을 눌렀을 때 선택한 파일 정보 가져오기
	            if(result == JFileChooser.APPROVE_OPTION) { //'열기' 또는 '저장' 버튼 눌렀는지 여부 검사
	               selectedFile = chooser.getSelectedFile();   //선택한 파일 정보 가져오기
	               
	   }      return selectedFile;
	}
		
		

	public static void main(String[] args) {
		
		new TcpFileClient().clientStart();

	}

}
