package kr.or.ddit.basic.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.basic.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.basic.fileupload.service.IFileinfoService;
import kr.or.ddit.basic.fileupload.vo.FileInfoVO;


/*
	Servlet3.0 이상에서 파일 업로드를 하려면 서블릿에 @MultipartConfig 애노테이션을 설정해야 함
	
	@MultipartConfig 설정 변수들
		1) location : 업로드한 파일이 임시로 저장될 경로 지정 (기본값 : "" --> 시스템의 임시폴더)
		2) fileSizeThreshold : 이 곳에서 지정한 값보다 큰 파일이 전송되면 location에 지정한 폴더에 임시 저장 (단위 : byte, 기본값 : 0 -> 무조건 임시 디렉토리를 사용하라는 뜻)
		3) maxFileSize : 1개 파일의 최대 크기 (단위 : byte, 기본값 : -1L(무제한)
		4) maxRequestSize : 서버로 전송되는 request 데이터 전체의 최대 크기 (모든 파일 크기 + form에서 만든 데이터까지)(단위 : byte, 기본값 : -1L(무제한)
	
 */
@WebServlet("/fileUpload.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10, //10mb
		maxFileSize = 1024 * 1024 * 30, //40mb
		maxRequestSize = 1024 * 1024 * 100 //100mb
		)

public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get방식으로 호출 : fileUpload폼을 보여주는 jsp문서로 forwarding
		request.getRequestDispatcher("/basic/fileupload/fileUpload.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post방식으로 호출 : 클라이언트가 보낸 파을 받아서 처리
		request.setCharacterEncoding("utf-8");
		
		//업로드한 파일들이 저장될 폴더 설정
		String uploadPath = "d:/z_fileUpload/uploadFiles";
		
		//저장될 폴더가 없으면 새로 생성
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//////////////////////////////////////////////////////////////////////////////
		
		/*
			파일이 아닌 일반 데이터 받는 방법
			getParameter() 메소드나 getParameterValues() 메소드를 이용
		 */
		String userName = request.getParameter("userName");
		System.out.println("일반 파라미터 데이터 : " + userName);
		
		//////////////////////////////////////////////////////////////////////////////
		
		//전송받은 파일 데이터 처리
		
		// 1. 전송되어 온 파일의 이름(원래의 파일명)이 저장될 변수 선언
		String fileName = "";
		
		//1-1. 전송된 파일이 2개 이상일 경우, 파일 정보를 넣어서 처리하기 위한 List객체 생성
		List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();
		
		/*
			Servlet 3.0 이상에서 파일 처리를 위해 추가된 메소드들
			1) request.getParts() ==> 전체 Part 객체를 Collection객체에 담아서 반환
			2) request.getPart("part이름(보통은 name값을 사요)") ==> 지정한 'part이름'을 가진 개별 Part객체를 반환. 파트라는 단위로 전송
		 */
		
		//전체 part객체 개수만큼 반복 처리
		for(Part part : request.getParts()) {
			//파일명 구하기
			fileName = extractFileName(part);
			
			//찾은 파일명이 공백("")이면 파일이 아닌 일반 매개변수라는 뜻
			if(!"".equals(fileName)) { //파일인지 검사
				//1개의 Upload 파일에 대한 정보를 저장할 VO객체 생성
				FileInfoVO fvo = new FileInfoVO();
				
				fvo.setFile_writer(userName); //작성자를 vo에 세팅
				fvo.setOrigin_file_name(fileName); //실제 파일명을 vo에 세팅
				
				//실제 저장되는 파일 이름의 중복을 방지하기 위해 UUID를 이용해 저장할 파일명을 생성
				String saveFileName = UUID.randomUUID().toString();
				fvo.setSave_file_name(saveFileName); //저장할 파일명을 vo에 세팅
				
				//Part객체의 getSize() 메소드를 이용해서 업로드 하는 파일 크기 알아내기(단위 : byte)
				//byte단위의 파일 크기를 KB단위로 변환해서 vo에 세팅
				fvo.setFile_size((int)Math.ceil(part.getSize() / 1024.0));
				
				try {
					//Part 객체의 write() 메소드를 이용하여 업로드된 파일을 지정된 위치에 저장
					//형식) part.write("저장할 경로명 / 저장할 파일명");
					part.write(uploadPath + File.separator + saveFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
				fileList.add(fvo); //upload된 파일 정보를 List에 추가
			} //if문 끝
			
		} //for문 끝
		
		//List에 저장된 파일정보 데이터를 db에 insert
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		for(FileInfoVO fvo : fileList) {
			service.insertFileinfo(fvo);
		}
		
		//저장 완료 후 파일목록 보기
		response.sendRedirect(request.getContextPath() + "/fileList.do");
		
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	/*
		Part 객체의 구조
			1) 파일이 아닌 일반 데이터의 경우
			sdfsdj23230sdljsld													==> Part를 구분하는 구분선
			content-disposition: form-data; name="username"  ==> 파라미터 이름
																								==> 빈 줄
			hong 																			==> 파라미터 값
			
			2) 파일일 경우
			sdfsdj23230sdljsld																				==> Part를 구분하는 구분선
			content-disposition: form-data; name="upFile1"; filename="test1.txt" ==> 파일 정보
			content-type: text/plain																			==> 파일의 정료
																															==> 빈 줄
			abcd1234안녕하세요																				==> 파일 내용
	 */
	//Part 구조 안에서 파일명을 찾는 메소드
	private String extractFileName(Part part) {
		String fileName = "";
		String contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				//filename="test1.txt"에서 test1.txt만 추출하기
				fileName = item.substring(item.indexOf("=")+2, item.length()-1);
			}
		}
		
		
		return fileName;
	}
	

}
