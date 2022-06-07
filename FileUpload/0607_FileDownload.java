package kr.or.ddit.basic.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.basic.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.basic.fileupload.service.IFileinfoService;
import kr.or.ddit.basic.fileupload.vo.FileInfoVO;

@WebServlet("/FileDownload.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//매개변수로 넘어온 fileno값 구하기
		String strFileno = request.getParameter("fileno");
		int fileNo = Integer.parseInt(strFileno);
		
		//fileno값을 이용해 db에서 해당 파일 정보 가져오기
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		FileInfoVO fvo = service.getFileinfo(fileNo);
		
		//업로드한 파일들이 저장될 폴더 설정
		String uploadPath = "d:/z_fileUpload/uploadFiles";
		
		//저장될 폴더가 없으면 새로 생성
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		response.setCharacterEncoding("utf-8");
		
		//다운받을 파일의 File객체 생성 ==> 실제 저장된 파일명을 지정하여 생성
		File downFile = new File(file, fvo.getSave_file_name());
		
		if(downFile.exists()) {
			//파일이 있을 때
			response.setContentType("application/octet-stream; charset=utf-8");
			String headerKey = "content-disposition";
			
			//이곳에는 다운로드 시 클라이언트에 저장될 파일 이름을 지정하는 곳으로 원래의 파일명으로 지정
			String headerValue = "attachment; filename=\"" + fvo.getOrigin_file_name() + "\";";
			
			response.setHeader(headerKey, headerValue);
			
			BufferedOutputStream out = null;
			BufferedInputStream in = null;
			
			try {
				//출력용 스트림 객체 생성 ==> response객체 이용
				out = new BufferedOutputStream(response.getOutputStream());
				
				//파일 입력용 스트림 객체 생성
				in = new BufferedInputStream(new FileInputStream(downFile));
				
				byte[] temp = new byte[1024];
				int len = 0;
				
				while((len = in.read(temp))>0) {
					out.write(temp, 0, len);
				}
				out.flush();
				
			} catch (IOException e) {
				System.out.println("입출력 오류 : " + e.getMessage());
			}finally {
				if(in != null) try {in.close();}catch(IOException e) {}
				if(out != null) try {out.close();}catch(IOException e) {}
			}
			
		}else {
			//파일이 없을 떄
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<h3>" + fvo.getOrigin_file_name() + "파일이 존재하지 않음<h3>");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
